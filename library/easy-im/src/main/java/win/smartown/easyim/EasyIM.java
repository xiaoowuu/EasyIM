package win.smartown.easyim;

import win.smartown.easyim.im.base.IMService;
import win.smartown.easyim.ui.base.UI;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:29
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class EasyIM {

    private static IMService sIMService;
    private static UI sUI;

    public static void init(IMService service, UI ui) {
        sIMService = service;
        sUI = ui;
    }

    public static IMService getIMService() {
        return sIMService;
    }

    public static UI getUI() {
        return sUI;
    }
}
