package win.smartown.easyim.im.base;

import java.util.List;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 14:41
 * 版权：成都智慧一生约科技有限公司
 * 类描述：消息记录变化监听器
 */
public interface OnMessageChangedListener {

    /**
     * 收到消息
     *
     * @param messages 新消息列表
     */
    void onReceivedMessage(List<Message> messages);

    /**
     * 发出消息
     *
     * @param message 消息
     */
    void onSendMessage(Message message);

    /**
     * 消息状态发生变化
     *
     * @param message 消息
     */
    void onMessageStatusChanged(Message message);

}