package win.smartown.easyim.ui.ysy.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.User;
import win.smartown.easyim.ui.base.ActionHandler;
import win.smartown.easyim.ui.base.UI;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.util.ImageLoader;
import win.smartown.easyim.ui.ysy.util.TimeUtil;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 15:33
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class ConversationAdapter extends BaseAdapter<BaseAdapter.BaseViewHolder> implements BaseAdapter.OnItemChildClickListener {

    private List<Conversation> conversations;

    public ConversationAdapter() {
        conversations = new ArrayList<>();
        setOnItemChildClickListener(this);
    }

    public void setData(List<Conversation> conversations) {
        this.conversations = conversations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BaseAdapter.BaseViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_conversation, viewGroup, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder viewHolder, int i) {
        Conversation conversation = conversations.get(i);
        User user = IM.getInstance().getUser(conversation.getId());
        ImageLoader.loadHeadImage(user.getAvatar(), viewHolder.getImageView(R.id.iv_portrait));
        viewHolder.getTextView(R.id.tv_nick).setText(TextUtils.isEmpty(user.getNick()) ? conversation.getId() : user.getNick());
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
    public void onItemChildClick(View view, int position) {
        ActionHandler actionHandler = UI.getInstance().getActionHandler();
        if (actionHandler != null) {
            Conversation conversation = conversations.get(position);
            int id = view.getId();
            if (id == R.id.rl_content) {
                actionHandler.startChat(view.getContext(), conversation);
            } else if (id == R.id.tv_delete) {
                // TODO: 2018/10/16 删除会话
            }
        }
    }
}