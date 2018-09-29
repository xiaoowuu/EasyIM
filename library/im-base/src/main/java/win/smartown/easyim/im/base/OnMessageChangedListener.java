package win.smartown.easyim.im.base;

import java.util.List;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 14:41
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public interface OnMessageChangedListener {

    void onReceivedMessage(List<Message> messages);

    void onSendMessage(Message message);

    void onMessageStatusChanged(Message message);

}