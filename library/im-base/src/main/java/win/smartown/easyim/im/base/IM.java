package win.smartown.easyim.im.base;

import android.content.Context;

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

    public abstract void refreshConversations();

    public void addOnConversationChangedListener(OnConversationChangedListener listener) {
        onConversationChangedListeners.add(listener);
    }

    public void removeOnConversationChangedListener(OnConversationChangedListener listener) {
        onConversationChangedListeners.add(listener);
    }

    public abstract void refreshMessages(String account, int type);

    public void addOnMessageChangedListener(String account, OnMessageChangedListener listener) {
        onMessageChangedListeners.put(account, listener);
    }

    public void removeOnMessageChangedListener(String account) {
        onMessageChangedListeners.remove(account);
    }

    public abstract void sendMessage(String account, int type, String text);

}