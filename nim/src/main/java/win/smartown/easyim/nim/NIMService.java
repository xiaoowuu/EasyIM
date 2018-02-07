package win.smartown.easyim.nim;

import com.netease.nimlib.sdk.NIMSDK;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import java.util.Collections;
import java.util.List;

import win.smartown.easyim.standard.IMService;

/**
 * Created by smartown on 2018/2/6 11:52.
 * <br>
 * Desc:
 * <br>
 */
public class NIMService implements IMService {
    /**
     * @param loginCallback callback
     * @param params        [account,token,appKey]
     */
    @Override
    public void login(final LoginCallback loginCallback, String... params) {
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
                    NimUserInfo info = NIMSDK.getUserService().getUserInfo(param.getAccount());
                    if (info != null) {
                        NIM.setCurrentUserInfo(info);
                    }
                    NIMSDK.getUserService().fetchUserInfo(Collections.singletonList(param.getAccount())).setCallback(new RequestCallback<List<NimUserInfo>>() {
                        @Override
                        public void onSuccess(List<NimUserInfo> param) {
                            if (param != null && param.size() > 0) {
                                NIM.setCurrentUserInfo(param.get(0));
                            }
                        }

                        @Override
                        public void onFailed(int code) {

                        }

                        @Override
                        public void onException(Throwable exception) {

                        }
                    });
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
