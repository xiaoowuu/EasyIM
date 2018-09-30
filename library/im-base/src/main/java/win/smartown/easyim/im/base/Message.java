package win.smartown.easyim.im.base;

/**
 * @param <Data> SDK消息元数据对象
 * @author 雷小武
 * 创建时间：2018/9/29 11:42
 * 版权：成都智慧一生约科技有限公司
 * 类描述：封装统一message对象，需用重写equals方法
 */
public abstract class Message<Data> {

    protected Data data;

    public Message(Data data) {
        this.data = data;
    }

    /**
     * @return 是否是我发送的
     */
    public abstract boolean isSend();

    /**
     * @return 消息内容
     */
    public abstract String getContent();

}
