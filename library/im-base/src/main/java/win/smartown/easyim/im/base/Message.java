package win.smartown.easyim.im.base;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:42
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public interface Message {

    /**
     * @return 是否是我发送的
     */
    boolean isSend();

    /**
     * @return 消息内容
     */
    String getContent();

}
