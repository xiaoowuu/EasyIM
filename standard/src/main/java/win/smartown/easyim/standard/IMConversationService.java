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
        conversationAdapter = createConversationAdapter();
    }

    protected abstract ConversationAdapter createConversationAdapter();

    public abstract void getConversations();

    public void registerConversationWatcher(ConversationChangedListener listener) {
        conversationChangedListener = listener;
    }

    public abstract void unRegisterConversationWatcher();

    public abstract void sendMsgTo(String account, String msg);

    public ConversationAdapter getConversationAdapter() {
        return conversationAdapter;
    }

    public interface ConversationChangedListener {
        void onConversationChanged(int count);
    }

}
