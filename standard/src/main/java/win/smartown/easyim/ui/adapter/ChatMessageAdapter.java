package win.smartown.easyim.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import win.smartown.easyim.standard.R;

/**
 * Created by smartown on 2018/2/8 10:33.
 * <br>
 * Desc:
 * <br>
 */
public abstract class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ChatMessageViewHolder> {

    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatMessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_chat_msg, parent, false
        ));
    }

    public static final class ChatMessageViewHolder extends RecyclerView.ViewHolder {

        public TextView msgTextView;

        public ChatMessageViewHolder(View itemView) {
            super(itemView);
            msgTextView = itemView.findViewById(R.id.msg);
        }
    }
}