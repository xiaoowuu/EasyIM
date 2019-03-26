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
 * 类描述：IM相关接口定义
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

    /**
     * 登录
     *
     * @param listener 登录回调
     * @param params   登录参数
     */
    public abstract void login(LoginListener listener, String... params);

    /**
     * 注销
     *
     * @param listener 注销回调
     * @param params   注销参数
     */
    public abstract void logout(LogoutListener listener, String... params);

    /**
     * 获取用户状态
     *
     * @return 用户状态
     */
    public abstract int getStatus();

    /**
     * @return 当前用户是否登录
     */
    public abstract boolean isLogin();

    /**
     * 刷新会话
     */
    public abstract void refreshConversations();

    /**
     * 删除会话
     *
     * @param conversation 会话
     */
    public abstract void removeConversation(Conversation conversation);

    /**
     * 删除消息
     *
     * @param message 消息
     */
    public abstract void removeMessage(Message message);

    /**
     * 获取会话未读条数
     *
     * @return 会话未读条数
     */
    public abstract int getUnreadCount();

    /**
     * 添加会话数据变化监听器
     *
     * @param listener 会话数据变化监听器
     */
    public void addOnConversationChangedListener(OnConversationChangedListener listener) {
        onConversationChangedListeners.add(listener);
    }

    /**
     * 删除会话数据变化监听器
     *
     * @param listener 会话数据变化监听器
     */
    public void removeOnConversationChangedListener(OnConversationChangedListener listener) {
        onConversationChangedListeners.add(listener);
    }

    /**
     * 通过账号获取用户数据
     *
     * @param account 账号
     * @return 用户数据
     */
    public abstract User getUser(String account);

    /**
     * 刷新消息
     *
     * @param account 会话账号
     * @param type    会话类型
     */
    public abstract void refreshMessages(String account, int type);

    /**
     * 添加消息变化监听器
     *
     * @param account  会话账号
     * @param listener 消息变化监听器
     */
    public void addOnMessageChangedListener(String account, OnMessageChangedListener listener) {
        onMessageChangedListeners.put(account, listener);
    }

    /**
     * 删除消息变化监听器
     *
     * @param account 消息变化监听器
     */
    public void removeOnMessageChangedListener(String account) {
        onMessageChangedListeners.remove(account);
    }

    /**
     * 发送文本消息
     *
     * @param account 会话账号
     * @param type    会话类型
     * @param text    文本
     */
    public abstract void sendTextMessage(String account, int type, String text);

    /**
     * 发送图片消息
     *
     * @param account 会话账号
     * @param type    会话类型
     * @param file    图片文件
     */
    public abstract void sendImageMessage(String account, int type, File file);

    /**
     * 发送商品消息消息
     *
     * @param account     会话账号
     * @param type        会话类型
     * @param productInfo 商品信息
     */
    public abstract void sendProductMessage(String account, int type, ProductInfo productInfo);

    /**
     * 创建商品消息
     *
     * @param account     会话账号
     * @param type        会话类型
     * @param message     true：消息；false：商品信息展示；
     * @param productInfo 商品信息
     * @return 商品消息
     */
    public abstract Message createProductMessage(String account, int type, boolean message, ProductInfo productInfo);

    public abstract void onConversationFragmentResume();

    public abstract void onConversationFragmentPause();

    public abstract void onChatFragmentResume(String account, int type);

    public abstract void onChatFragmentPause();

}