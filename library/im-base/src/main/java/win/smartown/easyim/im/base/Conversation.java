package win.smartown.easyim.im.base;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:42
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public interface Conversation {

    /**
     * @return 会话投降
     */
    String getPortrait();

    /**
     * @return 昵称
     */
    String getNick();

    /**
     * @return 最后一条消息内容
     */
    String getLastMessageContent();

    /**
     * @return 最后一条消息的类型
     */
    int getLastMessageType();

    /**
     * @return 最后一条消息的时间
     */
    long getLastMessageTime();

    /**
     * @return 未读数
     */
    int getUnreadCount();

}
