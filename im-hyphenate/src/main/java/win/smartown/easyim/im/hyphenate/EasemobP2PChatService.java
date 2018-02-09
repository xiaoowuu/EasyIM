package win.smartown.easyim.im.hyphenate;

import android.os.Handler;
import android.os.Message;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;

import java.lang.ref.WeakReference;
import java.util.List;

import win.smartown.easyim.standard.IMP2PChatService;
import win.smartown.easyim.ui.adapter.ChatMessageAdapter;

/**
 * Created by smartown on 2018/2/8 15:18.
 * <br>
 * Desc:
 * <br>
 */
public class EasemobP2PChatService extends IMP2PChatService implements EMMessageListener {

    private List<EMMessage> messages;
    private MessageHandler messageHandler;

    public EasemobP2PChatService(String jsonString) {
        super(jsonString);
        messageHandler = new MessageHandler(this);
        messages = EMClient.getInstance().chatManager().getConversation(getAccount()).getAllMessages();
    }

    @Override
    public ChatMessageAdapter createChatMessageAdapter() {
        return new Adapter(messages);
    }

    @Override
    public String getAccount() {
        return jsonObject.optString("chatTarget");
    }

    @Override
    public void sendMsg(String msg) {
        EMMessage message = EMMessage.createTxtSendMessage(msg, getAccount());
        message.setAttribute(Constants.MESSAGE_FIELD_NICK, Easemob.getUserInfoProvider().getNick());
        message.setAttribute(Constants.MESSAGE_FIELD_HEAD, Easemob.getUserInfoProvider().getHead());
        EMClient.getInstance().chatManager().sendMessage(message);
        messages.add(message);
        getChatMessageAdapter().notifyDataSetChanged();
    }

    @Override
    public void observeReceiveMessage(boolean observe) {
        if (observe) {
            EMClient.getInstance().chatManager().addMessageListener(this);
        } else {
            EMClient.getInstance().chatManager().removeMessageListener(this);
            messageHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        this.messages.addAll(messages);
        messageHandler.sendEmptyMessage(MessageHandler.CODE_MSG_RECEIVED);
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }

    private static class Adapter extends ChatMessageAdapter {

        private WeakReference<List<EMMessage>> messages;

        public Adapter(List<EMMessage> messages) {
            this.messages = new WeakReference<List<EMMessage>>(messages);
        }

        @Override
        public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
            EMMessage message = messages.get().get(position);
            EMMessage.ChatType chatType = message.getChatType();
            String nick = "";
            switch (chatType) {
                case Chat:
                    nick = message.getStringAttribute(Constants.MESSAGE_FIELD_NICK, "");
                    break;
                default:
                    break;
            }
            String msg = "";
            EMMessageBody messageBody = message.getBody();
            if (messageBody instanceof EMTextMessageBody) {
                msg = ((EMTextMessageBody) messageBody).getMessage();
            }
            holder.msgTextView.setText(nick + " : " + msg);
        }

        @Override
        public int getItemCount() {
            return messages.get().size();
        }
    }

    private static class MessageHandler extends Handler {

        private static final int CODE_MSG_RECEIVED = 0X1000;

        private WeakReference<EasemobP2PChatService> reference;

        public MessageHandler(EasemobP2PChatService service) {
            this.reference = new WeakReference<EasemobP2PChatService>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            int code = msg.what;
            switch (code) {
                case CODE_MSG_RECEIVED:
                    reference.get().getChatMessageAdapter().notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }
}
