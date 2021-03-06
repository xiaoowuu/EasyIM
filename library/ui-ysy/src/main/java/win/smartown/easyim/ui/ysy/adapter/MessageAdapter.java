package win.smartown.easyim.ui.ysy.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.strategy.BaseShowTimeStrategy;
import win.smartown.easyim.ui.ysy.viewholder.ImageViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.LocationViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.MessageViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.NotificationViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.ProductInfoViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.ProductViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.TextViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.UnknownViewHolder;
import win.smartown.easyim.ui.ysy.viewholder.VideoViewHolder;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:40
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class MessageAdapter extends BaseAdapter<MessageViewHolder> {

    private static final int TYPE_TEXT_RECEIVED = 1;
    private static final int TYPE_TEXT_SEND = 2;
    private static final int TYPE_UNKNOWN_RECEIVED = 3;
    private static final int TYPE_UNKNOWN_SEND = 4;
    private static final int TYPE_IMAGE_RECEIVED = 5;
    private static final int TYPE_IMAGE_SEND = 6;
    private static final int TYPE_PRODUCT_RECEIVED = 7;
    private static final int TYPE_PRODUCT_SEND = 8;
    private static final int TYPE_PRODUCT_INFO = 9;
    private static final int TYPE_NOTIFICATION = 10;
    private static final int TYPE_LOCATION_RECEIVED = 11;
    private static final int TYPE_LOCATION_SEND = 12;
    private static final int TYPE_VIDEO_SEND = 13;
    private static final int TYPE_VIDEO_RECEIVED = 14;

    private List<Message> messages;
    private BaseShowTimeStrategy showTimeStrategy;
    private boolean showNick;

    public MessageAdapter(BaseShowTimeStrategy showTimeStrategy) {
        this(showTimeStrategy, false);
    }

    public MessageAdapter(BaseShowTimeStrategy showTimeStrategy, boolean showNick) {
        this.messages = new ArrayList<>();
        this.showTimeStrategy = showTimeStrategy;
        this.showNick = showNick;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if (message.isSend()) {
            switch (message.getType()) {
                case Message.TYPE_TEXT:
                    return TYPE_TEXT_SEND;
                case Message.TYPE_IMAGE:
                    return TYPE_IMAGE_SEND;
                case Message.TYPE_PRODUCT_MESSAGE:
                    return TYPE_PRODUCT_SEND;
                case Message.TYPE_PRODUCT_INFO:
                    return TYPE_PRODUCT_INFO;
                case Message.TYPE_NOTIFICATION:
                    return TYPE_NOTIFICATION;
                case Message.TYPE_LOCATION:
                    return TYPE_LOCATION_SEND;
                case Message.TYPE_VIDEO:
                    return TYPE_VIDEO_SEND;
                default:
                    return TYPE_UNKNOWN_SEND;
            }
        } else {
            switch (message.getType()) {
                case Message.TYPE_TEXT:
                    return TYPE_TEXT_RECEIVED;
                case Message.TYPE_IMAGE:
                    return TYPE_IMAGE_RECEIVED;
                case Message.TYPE_PRODUCT_MESSAGE:
                    return TYPE_PRODUCT_RECEIVED;
                case Message.TYPE_PRODUCT_INFO:
                    return TYPE_PRODUCT_INFO;
                case Message.TYPE_NOTIFICATION:
                    return TYPE_NOTIFICATION;
                case Message.TYPE_LOCATION:
                    return TYPE_LOCATION_RECEIVED;
                case Message.TYPE_VIDEO:
                    return TYPE_VIDEO_RECEIVED;
                default:
                    return TYPE_UNKNOWN_RECEIVED;
            }
        }
    }

    private int getReceivedMessageLayout() {
        return showNick ? R.layout.item_message_received_nick : R.layout.item_message_received;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_TEXT_SEND:
                return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_send, viewGroup, false), true, this);
            case TYPE_IMAGE_SEND:
                return new ImageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_send, viewGroup, false), true, this);
            case TYPE_UNKNOWN_SEND:
                return new UnknownViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_send, viewGroup, false), true, this);
            case TYPE_TEXT_RECEIVED:
                return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getReceivedMessageLayout(), viewGroup, false), false, this);
            case TYPE_IMAGE_RECEIVED:
                return new ImageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getReceivedMessageLayout(), viewGroup, false), false, this);
            case TYPE_UNKNOWN_RECEIVED:
                return new UnknownViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getReceivedMessageLayout(), viewGroup, false), false, this);
            case TYPE_PRODUCT_INFO:
                return new ProductInfoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_product_info, viewGroup, false), this);
            case TYPE_PRODUCT_SEND:
                return new ProductViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_product_send, viewGroup, false), true, this);
            case TYPE_PRODUCT_RECEIVED:
                return new ProductViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_product_received, viewGroup, false), false, this);
            case TYPE_NOTIFICATION:
                return new NotificationViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_notification, viewGroup, false), this);
            case TYPE_LOCATION_SEND:
                return new LocationViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_send, viewGroup, false), true, this);
            case TYPE_LOCATION_RECEIVED:
                return new LocationViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getReceivedMessageLayout(), viewGroup, false), false, this);
            case TYPE_VIDEO_SEND:
                return new VideoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_send, viewGroup, false), true, this);
            case TYPE_VIDEO_RECEIVED:
                return new VideoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getReceivedMessageLayout(), viewGroup, false), false, this);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder viewHolder, int i) {
        Message message = messages.get(i);
        boolean needShowTime = showTimeStrategy.needShowTime(message);

        View tvTime = viewHolder.getView(R.id.tv_time);
        if (tvTime != null) {
            tvTime.setVisibility(needShowTime ? View.VISIBLE : View.GONE);
        }

        if (showNick) {
            View view = viewHolder.getView(R.id.tv_nick);
            if (view instanceof TextView) {
                ((TextView) view).setText(message.getFromNick());
            }
        }
        viewHolder.showMessage(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessages(List<Message> messages) {
        showTimeStrategy.addMessages(messages);
        this.messages.addAll(messages);
        notifyDataSetChanged();
    }

    public void addMessage(Message message) {
        showTimeStrategy.addMessage(message);
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

    public List<Message> getData() {
        return messages;
    }
}
