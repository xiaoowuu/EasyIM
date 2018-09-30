package win.smartown.easyim.ui.wechat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.base.ActionHandler;
import win.smartown.easyim.ui.base.UI;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:40
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class WxMessageAdapter extends RecyclerView.Adapter<WxMessageAdapter.ViewHolder> implements View.OnClickListener {

    private List<Message> messages;

    public WxMessageAdapter() {
        this.messages = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).isSend()) {
            return R.layout.item_wx_message_send;
        }
        return R.layout.item_wx_message_received;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvContent.setText(messages.get(i).getContent());
        viewHolder.itemView.setTag(i);
        viewHolder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessages(List<Message> messages) {
        this.messages.addAll(messages);
        notifyDataSetChanged();
    }

    public void addMessage(Message message) {
        this.messages.add(message);
        notifyItemInserted(this.messages.size() - 1);
    }

    public void onMessageStatusChanged(Message message) {
        int index = messages.indexOf(message);
        if (index > -1) {
            messages.set(index, message);
            notifyItemChanged(index);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        ActionHandler actionHandler = UI.getInstance().getActionHandler();
        Object tag = v.getTag();
        if (actionHandler!=null&&tag != null) {
            int position = (int) tag;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
