package win.smartown.easyim.im.base;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:42
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class Conversation<Data> {

    protected Data data;

    public Conversation(Data data) {
        this.data = data;
    }

    /**
     * @return 会话投降
     */
    public abstract String getPortrait();

    /**
     * @return 昵称
     */
    public abstract String getNick();

    /**
     * @return 最后一条消息内容
     */
    public abstract String getLastMessageContent();

    /**
     * @return 最后一条消息的类型
     */
    public abstract int getLastMessageType();

    /**
     * @return 最后一条消息的时间
     */
    public abstract long getLastMessageTime();

    /**
     * @return 未读数
     */
    public abstract int getUnreadCount();

}
