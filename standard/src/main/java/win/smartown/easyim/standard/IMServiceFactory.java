package win.smartown.easyim.standard;

import android.support.annotation.NonNull;

/**
 * Created by smartown on 2018/2/6 15:46.
 * <br>
 * Desc:
 * <br>
 */
public class IMServiceFactory {

    @NonNull
    public static <LoginResult> IMService<LoginResult> createIMService(Class<? extends IMService<LoginResult>> aClass) {
        try {
            return aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
