package win.smartown.easyim.im.netease;

import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

/**
 * Created by smartown on 2018/2/7 11:39.
 * <br>
 * Desc:
 * <br>
 */
public class NIM {

    private static NimUserInfo currentUserInfo;

    public static NimUserInfo getCurrentUserInfo() {
        return currentUserInfo;
    }

    public static void setCurrentUserInfo(NimUserInfo currentUserInfo) {
        NIM.currentUserInfo = currentUserInfo;
    }
}
