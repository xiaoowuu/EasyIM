package win.smartown.easyim;

import android.app.Application;

import win.smartown.easyim.im.base.IMService;
import win.smartown.easyim.im.netease.NIMService;
import win.smartown.easyim.ui.base.UI;
import win.smartown.easyim.ui.wechat.WxUI;
import win.smartown.easyim.util.ToastUtil;

/**
 * Created by smartown on 2018/2/6 14:32.
 * <br>
 * Desc:
 * <br>
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
        IMService.init(new NIMService(this));
        UI.init(new WxUI());
    }

}