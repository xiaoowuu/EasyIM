package win.smartown.easyim.easemob;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import win.smartown.easyim.standard.IMService;

/**
 * Created by smartown on 2018/2/8 14:26.
 * <br>
 * Desc:
 * <br>
 */
public class EasemobService implements IMService {

    /**
     * @param loginCallback callback
     * @param params        [username,password]
     */
    @Override
    public void login(final LoginCallback loginCallback, String... params) {
        EMClient.getInstance().login(params[0], params[1], new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                loginCallback.onLoginSuccess("");
            }

            @Override
            public void onError(int code, String error) {
                loginCallback.onLoginFailed(new Exception(code + ":" + error));
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    @Override
    public void logout(String... params) {
        EMClient.getInstance().logout(true);
    }
}
