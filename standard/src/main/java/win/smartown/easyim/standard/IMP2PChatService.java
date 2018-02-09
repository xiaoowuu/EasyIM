package win.smartown.easyim.standard;

import org.json.JSONException;
import org.json.JSONObject;

import win.smartown.easyim.ui.adapter.ChatMessageAdapter;

/**
 * Created by smartown on 2018/2/6 15:58.
 * <br>
 * Desc:
 * <br>
 */
public abstract class IMP2PChatService {

    protected JSONObject jsonObject;
    private ChatMessageAdapter chatMessageAdapter;

    public IMP2PChatService(String jsonString) {
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public abstract ChatMessageAdapter createChatMessageAdapter();

    public ChatMessageAdapter getChatMessageAdapter() {
        if (chatMessageAdapter == null) {
            chatMessageAdapter = createChatMessageAdapter();
        }
        return chatMessageAdapter;
    }

    public abstract String getAccount();

    public abstract void sendMsg(String msg);

    public abstract void observeReceiveMessage(boolean observe);

}
