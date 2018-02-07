package win.smartown.easyim.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import win.smartown.easyim.standard.R;

/**
 * Created by smartown on 2018/2/6 16:15.
 * <br>
 * Desc:
 * <br>
 */
public abstract class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.Holder> {

    protected JumpHandler jumpHandler;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation, parent, false));
    }

    public JumpHandler getJumpHandler() {
        return jumpHandler;
    }

    public void setJumpHandler(JumpHandler jumpHandler) {
        this.jumpHandler = jumpHandler;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public ImageView headImageView;
        public TextView nameTextView;
        public TextView msgTextView;
        public TextView timeTextView;

        public Holder(View itemView) {
            super(itemView);
            headImageView = itemView.findViewById(R.id.conversation_head);
            nameTextView = itemView.findViewById(R.id.conversation_name);
            msgTextView = itemView.findViewById(R.id.conversation_msg);
            timeTextView = itemView.findViewById(R.id.conversation_time);
        }
    }

    public interface JumpHandler {
        void jumpToP2P(HashMap<String, String> params);

        void jumpToTeam(HashMap<String, String> params);
    }
}
