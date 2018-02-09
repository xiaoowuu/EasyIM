package win.smartown.easyim.im.hyphenate;

import android.view.View;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import win.smartown.easyim.standard.IMConversationService;
import win.smartown.easyim.ui.adapter.ConversationAdapter;

/**
 * Created by smartown on 2018/2/8 14:55.
 * <br>
 * Desc:
 * <br>
 */
public class EasemobConversationService extends IMConversationService implements  EMMessageListener {

    private List<String> conversationIds;
    private Map<String, EMConversation> conversations;

    @Override
    protected ConversationAdapter createConversationAdapter() {
        conversationIds = new ArrayList<>();
        return new Adapter();
    }

    @Override
    public void getConversations() {
        conversations = EMClient.getInstance().chatManager().getAllConversations();
        conversationIds.clear();
        conversationIds.addAll(conversations.keySet());
        getConversationAdapter().notifyDataSetChanged();
    }

    @Override
    public void registerConversationWatcher(ConversationChangedListener listener) {
        super.registerConversationWatcher(listener);
        EMClient.getInstance().chatManager().addMessageListener(this);
    }

    @Override
    public void unRegisterConversationWatcher() {
        EMClient.getInstance().chatManager().removeMessageListener(this);
    }

    @Override
    public void sendMsgTo(String account, String msg) {
        EMMessage message = EMMessage.createTxtSendMessage(msg, account);
        message.setAttribute(Constants.MESSAGE_FIELD_NICK, Easemob.getUserInfoProvider().getNick());
        message.setAttribute(Constants.MESSAGE_FIELD_HEAD, Easemob.getUserInfoProvider().getHead());
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        getConversations();
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

    private class Adapter extends ConversationAdapter implements View.OnClickListener {

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(this);
            String username = conversationIds.get(position);
            EMConversation conversation = conversations.get(username);
            if (conversation.getAllMsgCount() > 0) {
                EMMessage message = conversation.getLastMessage();
                EMConversation.EMConversationType conversationType = conversation.getType();
                switch (conversationType) {
                    case Chat:
                        holder.nameTextView.setText(message.getStringAttribute(Constants.MESSAGE_FIELD_NICK, ""));
                        ImageUtil.loadImage(message.getStringAttribute(Constants.MESSAGE_FIELD_HEAD, ""), holder.headImageView);
                        break;
                    default:
                        break;
                }

                EMMessageBody messageBody = message.getBody();
                if (messageBody instanceof EMTextMessageBody) {
                    holder.msgTextView.setText(((EMTextMessageBody) messageBody).getMessage());
                }
            }
        }

        @Override
        public int getItemCount() {
            return conversationIds.size();
        }

        @Override
        public void onClick(View view) {
            if (jumpHandler != null) {
                int position = (int) view.getTag();
                EMConversation conversation = conversations.get(conversationIds.get(position));
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("chatTarget", conversation.conversationId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                EMConversation.EMConversationType conversationType = conversation.getType();
                switch (conversationType) {
                    case Chat:
                        jumpHandler.jumpToP2P(jsonObject.toString());
                        break;
                    case GroupChat:
                        jumpHandler.jumpToTeam(jsonObject.toString());
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
