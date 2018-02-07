package win.smartown.easyim;

import win.smartown.easyim.standard.IMServiceFactory;

/**
 * Created by smartown on 2018/2/7 9:03.
 * <br>
 * Desc:
 * <br>
 */
public class EasyIM {

    private static IMServiceFactory imServiceFactory;

    public static void setImServiceFactory(IMServiceFactory imServiceFactory) {
        EasyIM.imServiceFactory = imServiceFactory;
    }

    public static IMServiceFactory getImServiceFactory() {
        return imServiceFactory;
    }

}
