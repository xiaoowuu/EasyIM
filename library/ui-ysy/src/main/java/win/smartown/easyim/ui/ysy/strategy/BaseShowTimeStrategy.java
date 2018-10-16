package win.smartown.easyim.ui.ysy.strategy;

import java.util.LinkedList;
import java.util.List;

import win.smartown.easyim.im.base.Message;

/**
 * @author 雷小武
 * 创建时间：2018/10/16 16:17
 * 版权：成都智慧一生约科技有限公司
 * 类描述：消息时间显示策略
 */
public abstract class BaseShowTimeStrategy {

    protected LinkedList<Message> list;

    public BaseShowTimeStrategy() {
        list = new LinkedList<>();
    }

    public void addMessages(List<Message> messages) {
        for (Message message : messages) {
            addMessage(message);
        }
    }

    public void addMessage(Message message) {
        if (list.isEmpty()) {
            list.add(message);
        } else if (needShowTime(message, list.getLast())) {
            list.add(message);
        }
    }

    /**
     * 判断消息是否需要显示时间
     *
     * @param message 消息
     * @return 消息是否需要显示时间
     */
    public boolean needShowTime(Message message) {
        return list.contains(message);
    }

    /**
     * 新添加的消息是否需要显示时间
     *
     * @param message             消息
     * @param lastShowTimeMessage 上一条显示时间的消息
     * @return 新添加的消息是否需要显示时间
     */
    protected abstract boolean needShowTime(Message message, Message lastShowTimeMessage);

}