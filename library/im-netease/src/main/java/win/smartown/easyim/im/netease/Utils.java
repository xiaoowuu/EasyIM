package win.smartown.easyim.im.netease;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.util.ArrayList;
import java.util.List;

import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.base.Message;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 14:50
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class Utils {

    public static List<Conversation> getConversations(List<RecentContact> recentContacts) {
        List<Conversation> conversations = new ArrayList<>(recentContacts.size());
        for (RecentContact contact : recentContacts) {
            conversations.add(new NIMConversation(contact));
        }
        return conversations;
    }

    public static List<Message> getMessages(List<IMMessage> imMessages) {
        List<Message> messages = new ArrayList<>(imMessages.size());
        for (IMMessage message : imMessages) {
            messages.add(new NIMMessage(message));
        }
        return messages;
    }

}
