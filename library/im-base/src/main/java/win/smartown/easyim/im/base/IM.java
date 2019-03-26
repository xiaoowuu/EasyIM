package win.smartown.easyim.im.base;

import android.content.Context;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 10:26
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class IM {

    private static IM sImService;

    public static void init(IM sImService) {
        IM.sImService = sImService;
    }

    public static IM getInstance() {
        return sImService;
    }

    protected Context context;
    protected Set<OnConversationChangedListener> onConversationChangedListeners;
    protected Map<String, OnMessageChangedListener> onMessageChangedListeners;

    public IM(Context context) {
        this.context = context.getApplicationContext();
        onConversationChangedListeners = new HashSet<>();
        onMessageChangedListeners = new HashMap<>();
    }

    public abstract void login(LoginListener listener, String... params);

    public abstract void logout(LogoutListener listener, String... params);

    public abstract int getStatus();

    public abstract boolean isLogin();

    public abstract void refreshConversations();

    public abstract void removeConversation(Conversation conversation);

    public abstract void removeMessage(Message message);

    public abstract int getUnreadCount();

    public void addOnConversationChangedListener(OnConversationChangedListener listener) {
        onConversationChangedListeners.add(listener);
    }

    public void removeOnConversationChangedListener(OnConversationChangedListener listener) {
        onConversationChangedListeners.add(listener);
    }

    public abstract User getUser(String account);

    public abstract void refreshMessages(String account, int type);

    public void addOnMessageChangedListener(String account, OnMessageChangedListener listener) {
        onMessageChangedListeners.put(account, listener);
    }

    public void removeOnMessageChangedListener(String account) {
        onMessageChangedListeners.remove(account);
    }

    public abstract void sendTextMessage(String account, int type, String text);

    public abstract void sendImageMessage(String account, int type, File file);

    public abstract void sendProductMessage(String account, int type, ProductInfo productInfo);

    public abstract Message createProductMessage(String account, int type, boolean message, ProductInfo productInfo);

    public abstract void onConversationFragmentResume();

    public abstract void onConversationFragmentPause();

    public abstract void onChatFragmentResume(String account, int type);

    public abstract void onChatFragmentPause();

}