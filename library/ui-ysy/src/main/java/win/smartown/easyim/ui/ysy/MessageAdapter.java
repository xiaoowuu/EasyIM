package win.smartown.easyim.ui.ysy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.ysy.viewholder.BaseViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.TextViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.UnknownViewHolder;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:40
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class MessageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int TYPE_TEXT_RECEIVED = 1;
    private static final int TYPE_TEXT_SEND = 2;
    private static final int TYPE_UNKNOWN_RECEIVED = 3;
    private static final int TYPE_UNKNOWN_SEND = 4;

    private List<Message> messages;

    public MessageAdapter() {
        this.messages = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if (message.isSend()) {
            switch (message.getType()) {
                case Message.TYPE_TEXT:
                    return TYPE_TEXT_SEND;
                default:
                    return TYPE_UNKNOWN_SEND;
            }
        } else {
            switch (message.getType()) {
                case Message.TYPE_TEXT:
                    return TYPE_TEXT_RECEIVED;
                default:
                    return TYPE_UNKNOWN_RECEIVED;
            }
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_TEXT_SEND:
                return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_send, viewGroup, false), true);
            case TYPE_UNKNOWN_SEND:
                return new UnknownViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_send, viewGroup, false), true);
            case TYPE_TEXT_RECEIVED:
                return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_received, viewGroup, false), false);
            case TYPE_UNKNOWN_RECEIVED:
                return new UnknownViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_received, viewGroup, false), false);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int i) {
        viewHolder.showMessage(messages.get(i));
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

}
