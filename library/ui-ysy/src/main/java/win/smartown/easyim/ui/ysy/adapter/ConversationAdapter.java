package win.smartown.easyim.ui.ysy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.User;
import win.smartown.easyim.ui.base.ActionHandler;
import win.smartown.easyim.ui.base.BaseUI;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.util.ImageLoader;
import win.smartown.easyim.ui.ysy.util.TimeUtil;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 15:33
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> implements View.OnClickListener {

    private List<Conversation> conversations;

    public ConversationAdapter() {
        conversations = new ArrayList<>();
    }

    public void setData(List<Conversation> conversations) {
        this.conversations = conversations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_conversation, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Conversation conversation = conversations.get(i);
        User user = IM.getInstance().getUser(conversation.getId());
        ImageLoader.loadHeadImage(user.getAvatar(), viewHolder.ivPortrait);
        viewHolder.tvNick.setText(TextUtils.isEmpty(user.getNick()) ? conversation.getId() : user.getNick());
        viewHolder.tvContent.setText(conversation.getLastMessageContent());
        viewHolder.tvTime.setText(TimeUtil.getTimeShowString(conversation.getLastMessageTime()));
        viewHolder.contentView.setTag(conversation);
        viewHolder.contentView.setOnClickListener(this);
        viewHolder.tvDelete.setTag(conversation);
        viewHolder.tvDelete.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    @Override
    public void onClick(View v) {
        ActionHandler actionHandler = BaseUI.getInstance().getActionHandler();
        Object tag = v.getTag();
        if (actionHandler != null && tag instanceof Conversation) {
            Conversation conversation = (Conversation) tag;
            int id = v.getId();
            if (id == R.id.rl_content) {
                actionHandler.startChat(v.getContext(), conversation);
            } else if (id == R.id.tv_delete) {

            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private View contentView;
        private ImageView ivPortrait;
        private TextView tvNick;
        private TextView tvContent;
        private TextView tvTime;
        private TextView tvDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contentView = itemView.findViewById(R.id.rl_content);
            ivPortrait = itemView.findViewById(R.id.iv_portrait);
            tvNick = itemView.findViewById(R.id.tv_nick);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvDelete = itemView.findViewById(R.id.tv_delete);
        }
    }
}