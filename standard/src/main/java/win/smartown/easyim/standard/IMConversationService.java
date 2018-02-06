package win.smartown.easyim.standard;

import win.smartown.easyim.ui.adapter.ConversationAdapter;

/**
 * Created by smartown on 2018/2/6 15:58.
 * <br>
 * Desc:
 * <br>
 */
public abstract class IMConversationService {

    protected ConversationAdapter conversationAdapter;
    protected ConversationChangedListener conversationChangedListener;

    public IMConversationService() {
        initAdapter();
    }

    protected abstract void initAdapter();

    public abstract void getConversations();

    public abstract void registerConversationWatcher();

    public abstract void unRegisterConversationWatcher();

    public abstract void sendMsgTo(String account, String msg);

    public void setConversationChangedListener(ConversationChangedListener listener) {
        conversationChangedListener = listener;
    }

    public ConversationAdapter getConversationAdapter() {
        return conversationAdapter;
    }

    public interface ConversationChangedListener {
        void onConversationChanged(int count);
    }

}
