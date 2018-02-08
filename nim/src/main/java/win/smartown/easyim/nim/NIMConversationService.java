package win.smartown.easyim.nim;

import android.view.View;

import com.netease.nimlib.sdk.NIMSDK;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import win.smartown.easyim.standard.IMConversationService;
import win.smartown.easyim.ui.adapter.ConversationAdapter;

/**
 * Created by smartown on 2018/2/6 16:24.
 * <br>
 * Desc:
 * <br>
 */
public class NIMConversationService extends IMConversationService implements Observer<List<RecentContact>> {

    private List<RecentContact> recentContacts;
    private transient RequestCallback<List<NimUserInfo>> fetchUserInfoCallback = new RequestCallback<List<NimUserInfo>>() {
        @Override
        public void onSuccess(List<NimUserInfo> param) {
            getConversationAdapter().notifyDataSetChanged();
        }

        @Override
        public void onFailed(int code) {

        }

        @Override
        public void onException(Throwable exception) {

        }
    };
    private transient RequestCallback<List<Team>> fetchTeamInfoCallback = new RequestCallback<List<Team>>() {
        @Override
        public void onSuccess(List<Team> param) {
            getConversationAdapter().notifyDataSetChanged();
        }

        @Override
        public void onFailed(int code) {

        }

        @Override
        public void onException(Throwable exception) {

        }
    };

    @Override
    protected ConversationAdapter createConversationAdapter() {
        recentContacts = new ArrayList<>();
        return new NIMConversationAdapter(recentContacts);
    }

    @Override
    public void getConversations() {
        NIMSDK.getMsgService().queryRecentContacts().setCallback(new RequestCallback<List<RecentContact>>() {
            @Override
            public void onSuccess(List<RecentContact> param) {
                recentContacts.clear();
                recentContacts.addAll(param);
                getConversationAdapter().notifyDataSetChanged();
                fetchUserInfo();
                if (conversationChangedListener != null) {
                    conversationChangedListener.onConversationChanged(recentContacts.size());
                }
            }

            @Override
            public void onFailed(int code) {

            }

            @Override
            public void onException(Throwable exception) {

            }
        });
    }

    @Override
    public void registerConversationWatcher(ConversationChangedListener listener) {
        super.registerConversationWatcher(listener);
        NIMSDK.getMsgServiceObserve().observeRecentContact(this, true);
    }

    @Override
    public void unRegisterConversationWatcher() {
        NIMSDK.getMsgServiceObserve().observeRecentContact(this, false);
    }

    @Override
    public void sendMsgTo(String account, String msg) {
        IMMessage message = MessageBuilder.createTextMessage(account, SessionTypeEnum.P2P, msg);
        NIMSDK.getMsgService().sendMessage(message, false);
    }

    @Override
    public void onEvent(List<RecentContact> recentContacts) {
        getConversations();
    }

    private void fetchUserInfo() {
        List<String> userIds = new ArrayList<>();
        List<String> teamIds = new ArrayList<>();
        for (RecentContact contact : recentContacts) {
            String account = contact.getContactId();
            switch (contact.getSessionType()) {
                case P2P:
                    userIds.add(account);
                    break;
                case Team:
                    teamIds.add(account);
                    break;
                default:
                    break;
            }
        }
        NIMSDK.getUserService().fetchUserInfo(userIds).setCallback(fetchUserInfoCallback);
        NIMSDK.getTeamService().queryTeamListById(teamIds).setCallback(fetchTeamInfoCallback);
    }

    private static class NIMConversationAdapter extends ConversationAdapter implements View.OnClickListener {

        private List<RecentContact> recentContacts;

        public NIMConversationAdapter(List<RecentContact> recentContacts) {
            this.recentContacts = recentContacts;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(this);
            RecentContact contact = recentContacts.get(position);
            switch (contact.getSessionType()) {
                case Team:
                    Team team = NIMSDK.getTeamService().queryTeamBlock(contact.getContactId());
                    if (team != null) {
                        ImageUtil.loadImage(team.getIcon(), holder.headImageView);
                        holder.nameTextView.setText(team.getName());
                        holder.msgTextView.setText(contact.getContent());
                    }
                    break;
                case P2P:
                    NimUserInfo info = NIMSDK.getUserService().getUserInfo(contact.getContactId());
                    if (info != null) {
                        ImageUtil.loadImage(info.getAvatar(), holder.headImageView);
                        holder.nameTextView.setText(info.getName());
                        holder.msgTextView.setText(contact.getContent());
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return recentContacts.size();
        }

        @Override
        public void onClick(View view) {
            if (jumpHandler != null) {
                int position = (int) view.getTag();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("chatTarget", recentContacts.get(position).getContactId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SessionTypeEnum sessionType = recentContacts.get(position).getSessionType();
                switch (sessionType) {
                    case P2P:
                        jumpHandler.jumpToP2P(jsonObject.toString());
                        break;
                    case Team:
                        jumpHandler.jumpToTeam(jsonObject.toString());
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
