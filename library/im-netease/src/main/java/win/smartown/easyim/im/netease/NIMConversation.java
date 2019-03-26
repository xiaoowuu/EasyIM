package win.smartown.easyim.im.netease;

import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.model.RecentContact;

import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.netease.custom.ProductAttachment;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 14:27
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class NIMConversation extends Conversation<RecentContact> {

    public NIMConversation(RecentContact data) {
        super(data);
    }

    /**
     * @return 会话id
     */
    @Override
    public String getId() {
        return data.getContactId();
    }

    /**
     * @return 会话类型
     */
    @Override
    public int getType() {
        switch (data.getSessionType()) {
            case P2P:
                return Conversation.TYPE_SINGLE;
            case Team:
                return Conversation.TYPE_GROUP;
            default:
                return Conversation.TYPE_OTHER;
        }
    }

    /**
     * @return 会话投降
     */
    @Override
    public String getPortrait() {
        return "";
    }

    /**
     * @return 最后一条发送人昵称
     */
    @Override
    public String getLastMessageFromAccount() {
        return data.getFromAccount();
    }

    /**
     * @return 最后一条发送人昵称
     */
    @Override
    public String getLastMessageFromNick() {
        return data.getFromNick();
    }

    /**
     * @return 最后一条消息内容
     */
    @Override
    public String getLastMessageContent() {
        MsgAttachment attachment = data.getAttachment();
        if (attachment instanceof ProductAttachment) {
            return String.format("[%s]", ((ProductAttachment) attachment).getProductInfo().getProductName());
        }
        return data.getContent();
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
        return data.getTime();
    }

    /**
     * @return 未读数
     */
    @Override
    public int getUnreadCount() {
        return data.getUnreadCount();
    }
}
