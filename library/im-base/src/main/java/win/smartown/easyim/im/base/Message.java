package win.smartown.easyim.im.base;

/**
 * @param <Data> SDK消息元数据对象
 * @author 雷小武
 * 创建时间：2018/9/29 11:42
 * 版权：成都智慧一生约科技有限公司
 * 类描述：封装统一message对象，需重写equals方法
 */
public abstract class Message<Data> {

    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMAGE = 2;

    protected Data data;

    public Message(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    /**
     * @return 消息发送人账号
     */
    public abstract String getConversationId();

    /**
     * 此消息是否是我发送的
     *
     * @return 是否是我发送的
     */
    public abstract boolean isSend();

    /**
     * 获取消息内容
     *
     * @return 消息内容
     */
    public abstract String getContent();

    /**
     * 获取消息类型
     *
     * @return 消息类型
     * 文本:{@link Message#TYPE_TEXT}
     * 图片:{@link Message#TYPE_IMAGE}
     */
    public abstract int getType();

    /**
     * 获取图片链接
     *
     * @return 图片链接
     */
    public abstract String getImageUrl();

    /**
     * 获取图片宽度
     *
     * @return 图片宽度
     */
    public abstract int getImageWidth();

    /**
     * 获取图片高度
     *
     * @return 图片高度
     */
    public abstract int getImageHeight();

    /**
     * @return 消息发送人账号
     */
    public abstract String getFromAccount();

    /**
     * @return 消息时间
     */
    public abstract long getTime();

}
