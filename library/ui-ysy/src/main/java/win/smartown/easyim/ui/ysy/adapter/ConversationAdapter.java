package win.smartown.easyim.ui.ysy.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.base.Group;
import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.User;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.util.TimeUtil;
import win.smartown.easyim.ui.ysy.view.HeadImageView;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 15:33
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class ConversationAdapter extends BaseAdapter<BaseAdapter.BaseViewHolder> implements Group.GetGroupMembersCallback {

    private List<Conversation> conversations;

    public ConversationAdapter() {
        conversations = new ArrayList<>();
    }

    public void setData(List<Conversation> conversations) {
        this.conversations = conversations;
        notifyDataSetChanged();
    }

    public List<Conversation> getData() {
        return conversations;
    }

    @NonNull
    @Override
    public BaseAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BaseAdapter.BaseViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_conversation, viewGroup, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder viewHolder, int i) {
        Conversation conversation = conversations.get(i);
        List<String> avatar = new ArrayList<>();
        String nick = conversation.getId();
        switch (conversation.getType()) {
            case Conversation.TYPE_SINGLE:
                User user = IM.getInstance().getUser(conversation.getId());
                avatar = Collections.singletonList(user.getAvatar());
                nick = TextUtils.isEmpty(user.getNick()) ? conversation.getId() : user.getNick();
                break;
            case Conversation.TYPE_GROUP:
                Group group = IM.getInstance().getGroup(conversation.getId());
                if (!TextUtils.isEmpty(group.getIcon())) {
                    avatar = Collections.singletonList(group.getIcon());
                } else {
                    if (group.getAvatars() != null) {
                        avatar = group.getAvatars();
                    } else {
                        group.getGroupMembers(this);
                    }
                }
                nick = TextUtils.isEmpty(group.getName()) ? conversation.getId() : group.getName();
                break;
        }
        HeadImageView headImageView = (HeadImageView) viewHolder.getView(R.id.iv_portrait);
        headImageView.setImages(avatar);
        viewHolder.getTextView(R.id.tv_nick).setText(nick);
        viewHolder.getTextView(R.id.tv_content).setText(conversation.getLastMessageContent());
        viewHolder.getTextView(R.id.tv_time).setText(TimeUtil.getTimeShowString(conversation.getLastMessageTime()));
        int unreadCount = conversation.getUnreadCount();
        viewHolder.getTextView(R.id.tv_unread).setVisibility(unreadCount > 0 ? View.VISIBLE : View.GONE);
        viewHolder.getTextView(R.id.tv_unread).setText(unreadCount > 99 ? "99+" : String.valueOf(unreadCount));
        viewHolder.addOnChildClickListener(R.id.rl_content);
        viewHolder.addOnChildClickListener(R.id.tv_delete);
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    @Override
    public void godGroupMembers() {
        notifyDataSetChanged();
    }
}