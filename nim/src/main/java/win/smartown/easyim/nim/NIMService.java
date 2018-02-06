package win.smartown.easyim.nim;

import com.netease.nimlib.sdk.NIMSDK;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;

import win.smartown.easyim.standard.IMService;

/**
 * Created by smartown on 2018/2/6 11:52.
 * <br>
 * Desc:
 * <br>
 */
public class NIMService implements IMService<LoginInfo> {
    /**
     * @param loginCallback callback
     * @param params        [account,token,appKey]
     */
    @Override
    public void login(final LoginCallback<LoginInfo> loginCallback, String... params) {
        LoginInfo loginInfo = null;
        int paramsLength = params.length;
        if (paramsLength == 2) {
            loginInfo = new LoginInfo(params[0], params[1]);
        } else if (paramsLength == 3) {
            loginInfo = new LoginInfo(params[0], params[1], params[2]);
        }
        if (loginInfo != null) {
            NIMSDK.getAuthService().login(loginInfo).setCallback(new RequestCallback<LoginInfo>() {
                @Override
                public void onSuccess(LoginInfo param) {
                    loginCallback.onLoginSuccess(param);
                }

                @Override
                public void onFailed(int code) {
                    loginCallback.onLoginFailed(new Exception("onFailed:" + code));
                }

                @Override
                public void onException(Throwable exception) {
                    loginCallback.onLoginFailed(exception);
                }
            });
        }
    }

    @Override
    public void logout(String... params) {
        NIMSDK.getAuthService().logout();
    }
}
