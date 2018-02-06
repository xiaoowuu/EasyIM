package win.smartown.easyim.nim;

import com.netease.nimlib.sdk.NIMSDK;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RecentContact;

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

    @Override
    protected void initAdapter() {
        recentContacts = new ArrayList<>();
        conversationAdapter = new ConversationAdapter() {
            @Override
            public void onBindViewHolder(Holder holder, int position) {
                RecentContact contact = recentContacts.get(position);
                holder.nameTextView.setText(contact.getFromNick());
                holder.msgTextView.setText(contact.getContent());
            }

            @Override
            public int getItemCount() {
                return recentContacts.size();
            }
        };
    }

    @Override
    public void getConversations() {
        NIMSDK.getMsgService().queryRecentContacts().setCallback(new RequestCallback<List<RecentContact>>() {
            @Override
            public void onSuccess(List<RecentContact> param) {
                refresh(param);
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
    public void registerConversationWatcher() {
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
        refresh(recentContacts);
    }

    private void refresh(List<RecentContact> recentContacts) {
        if (conversationChangedListener != null) {
            conversationChangedListener.onConversationChanged(recentContacts.size());
        }
        this.recentContacts = recentContacts;
        conversationAdapter.notifyDataSetChanged();
    }
}
