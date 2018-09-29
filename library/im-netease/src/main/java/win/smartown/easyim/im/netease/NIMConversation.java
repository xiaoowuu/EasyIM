package win.smartown.easyim.im.netease;

import com.netease.nimlib.sdk.msg.model.RecentContact;

import win.smartown.easyim.im.base.Conversation;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 14:27
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class NIMConversation implements Conversation {

    private RecentContact recentContact;

    public NIMConversation(RecentContact recentContact) {
        this.recentContact = recentContact;
    }

    /**
     * @return 会话投降
     */
    @Override
    public String getPortrait() {
        return "";
    }

    /**
     * @return 昵称
     */
    @Override
    public String getNick() {
        return recentContact.getFromNick();
    }

    /**
     * @return 最后一条消息内容
     */
    @Override
    public String getLastMessageContent() {
        return recentContact.getContent();
    }

    /**
     * @return 最后一条消息的类型
     */
    @Override
    public int getLastMessageType() {
        return 0;
    }

    /**
     * @return 最后一条消息的时间
     */
    @Override
    public long getLastMessageTime() {
        return recentContact.getTime();
    }

    /**
     * @return 未读数
     */
    @Override
    public int getUnreadCount() {
        return recentContact.getUnreadCount();
    }
}
