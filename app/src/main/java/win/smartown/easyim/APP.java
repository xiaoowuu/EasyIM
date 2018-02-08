package win.smartown.easyim;

import android.app.Application;

import win.smartown.easyim.easemob.Easemob;
import win.smartown.easyim.easemob.EasemobServiceFactory;
import win.smartown.easyim.easemob.UserInfoProvider;
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
        EasyIM.setImServiceFactory(new EasemobServiceFactory(this));
        Easemob.setUserInfoProvider(new UserInfoProvider() {
            @Override
            public String getNick() {
//                return "Hello";
                return "World";
            }

            @Override
            public String getHead() {
//                return "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3631616776,4285408993&fm=173&s=B8B27D975EC226DA552DFC0E0300F063&w=218&h=146&img.JPEG";
                return "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1405843285,1057124979&fm=173&s=DCA5C05AC44BE75D4FEBDEB20300500D&w=218&h=146&img.JPEG";
            }
        });
    }

}
