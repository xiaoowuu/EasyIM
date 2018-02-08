package win.smartown.easyim.easemob;

/**
 * Created by smartown on 2018/2/8 16:24.
 * <br>
 * Desc:
 * <br>
 */
public class Easemob {
    private static UserInfoProvider userInfoProvider;

    public static UserInfoProvider getUserInfoProvider() {
        return userInfoProvider;
    }

    public static void setUserInfoProvider(UserInfoProvider userInfoProvider) {
        Easemob.userInfoProvider = userInfoProvider;
    }
}
