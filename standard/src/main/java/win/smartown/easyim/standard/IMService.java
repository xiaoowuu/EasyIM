package win.smartown.easyim.standard;

/**
 * Created by smartown on 2018/2/6 11:23.
 * <br>
 * Desc:
 * <br>
 */
public interface IMService<LoginResult> {

    void login(LoginCallback<LoginResult> loginCallback, String... params);

    void logout(String... params);

    interface LoginCallback<LoginResult> {

        void onLoginSuccess(LoginResult result);

        void onLoginFailed(Throwable throwable);

    }

}
