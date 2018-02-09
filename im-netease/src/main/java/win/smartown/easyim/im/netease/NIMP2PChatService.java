package win.smartown.easyim.im.netease;

import com.netease.nimlib.sdk.NIMSDK;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import win.smartown.easyim.standard.IMP2PChatService;
import win.smartown.easyim.ui.adapter.ChatMessageAdapter;

/**
 * Created by smartown on 2018/2/8 10:14.
 * <br>
 * Desc:
 * <br>
 */
public class NIMP2PChatService extends IMP2PChatService implements Observer<List<IMMessage>> {

    private List<IMMessage> messages;
    private RequestCallback<List<IMMessage>> queryMessageListCallback = new RequestCallback<List<IMMessage>>() {
        @Override
        public void onSuccess(List<IMMessage> param) {
            messages.clear();
            messages.addAll(param);
            Collections.reverse(messages);
            getChatMessageAdapter().notifyDataSetChanged();
        }

        @Override
        public void onFailed(int code) {

        }

        @Override
        public void onException(Throwable exception) {

        }
    };

    public NIMP2PChatService(String jsonString) {
        super(jsonString);
        messages = new ArrayList<>();
        NIMSDK.getMsgService().queryMessageList(getAccount(), SessionTypeEnum.P2P, 0, 100).setCallback(queryMessageListCallback);
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
        IMMessage message = MessageBuilder.createTextMessage(getAccount(), SessionTypeEnum.P2P, msg);
        NIMSDK.getMsgService().sendMessage(message, false);
        messages.add(message);
        getChatMessageAdapter().notifyDataSetChanged();
    }

    @Override
    public void observeReceiveMessage(boolean observe) {
        NIMSDK.getMsgServiceObserve().observeReceiveMessage(this, observe);
    }

    @Override
    public void onEvent(List<IMMessage> imMessages) {
        messages.addAll(imMessages);
        getChatMessageAdapter().notifyDataSetChanged();
    }

    private static class Adapter extends ChatMessageAdapter {

        private List<IMMessage> messages;

        public Adapter(List<IMMessage> messages) {
            this.messages = messages;
        }

        @Override
        public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
            IMMessage message = messages.get(position);
            holder.msgTextView.setText(message.getFromNick() + " : " + message.getContent());
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }
    }

}
