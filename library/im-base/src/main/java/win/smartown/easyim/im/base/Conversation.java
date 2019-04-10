package win.smartown.easyim.im.base;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:42
 * 版权：成都智慧一生约科技有限公司
 * 类描述：会话记录对象
 * @param <Data> IM SDK会话记录
 */
public abstract class Conversation<Data> {

    /**
     * 会话类型-单聊
     */
    public final static int TYPE_SINGLE = 1;
    /**
     * 会话类型-群聊
     */
    public final static int TYPE_GROUP = 2;
    /**
     * 会话类型-其他
     */
    public final static int TYPE_OTHER = 0;

    protected Data data;

    protected Group group;

    public Conversation(Data data) {
        this.data = data;
    }

    /**
     * @return 会话id
     */
    public abstract String getId();

    /**
     * @return 会话类型<br>{@link Conversation#TYPE_SINGLE}<br>{@link Conversation#TYPE_GROUP}<br>{@link Conversation#TYPE_OTHER}
     */
    public abstract int getType();

    /**
     * @return 会话头像
     */
    public abstract String getPortrait();

    /**
     * @return 最后一条发送人昵称
     */
    public abstract String getLastMessageFromAccount();

    /**
     * @return 最后一条发送人昵称
     */
    public abstract String getLastMessageFromNick();

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

    public Group getGroup() {
        return group;
    }
}
