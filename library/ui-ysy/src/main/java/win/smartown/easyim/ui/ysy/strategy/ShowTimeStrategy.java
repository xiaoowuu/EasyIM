package win.smartown.easyim.ui.ysy.strategy;

import win.smartown.easyim.im.base.Message;

/**
 * @author 雷小武
 * 创建时间：2018/10/16 16:23
 * 版权：成都智慧一生约科技有限公司
 * 类描述：新消息与上一条消息的的时间间隔超过5分钟则显示时间
 */
public class ShowTimeStrategy extends BaseShowTimeStrategy {

    /**
     * 5分钟，新消息与上一条消息的的时间间隔超过5分钟则显示时间
     */
    private static final long FIVE_MIN = 5 * 60 * 1000;

    @Override
    public boolean needShowTime(Message message, Message lastShowTimeMessage) {
        return message.getTime() - lastShowTimeMessage.getTime() > FIVE_MIN;
    }
}
