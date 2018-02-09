package win.smartown.easyim.standard;

/**
 * Created by smartown on 2018/2/6 11:23.
 * <br>
 * Desc:
 * <br>
 */
public interface IMService {

    void login(LoginCallback loginCallback, String... params);

    void logout(String... params);

    interface LoginCallback {

        void onLoginSuccess(Object result);

        void onLoginFailed(Throwable throwable);

    }

}
