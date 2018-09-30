package win.smartown.easyim.ui.wechat;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.ui.base.ActionHandler;
import win.smartown.easyim.ui.base.BaseUI;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 15:33
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> implements View.OnClickListener {

    private List<Conversation> conversations;

    public ConversationAdapter(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_conversation, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Conversation conversation = conversations.get(i);
        viewHolder.ivPortrait.setBackgroundColor(Color.BLUE);
        viewHolder.tvNick.setText(conversation.getId());
        viewHolder.tvContent.setText(conversation.getLastMessageContent());
        viewHolder.tvTime.setText(String.valueOf(conversation.getLastMessageTime()));
        viewHolder.itemView.setTag(conversation);
        viewHolder.itemView.setOnClickListener(this);
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
            actionHandler.startChat(v.getContext(), conversation);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPortrait;
        private TextView tvNick;
        private TextView tvContent;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPortrait = itemView.findViewById(R.id.iv_portrait);
            tvNick = itemView.findViewById(R.id.tv_nick);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}