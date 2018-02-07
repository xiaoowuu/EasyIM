package win.smartown.easyim;

import android.app.Application;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;

import win.smartown.easyim.nim.NIMServiceFactory;
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
        EasyIM.setImServiceFactory(new NIMServiceFactory());
        initIM();
    }

    /**
     * 网易云信初始化
     */
    private void initIM() {
        // SDK初始化（启动后台服务，若已经存在用户登录信息， SDK 将完成自动登录）
        // LoginInfo!=null，会自动登录，APP用户登录后，会调用接口主动登录IM，登录成功后保存LoginInfo，再次进入APP初始化时传入LoginInfo自动登录
        // SDKOptions传空，使用默认配置
        NIMClient.init(this, getLoginInfo(), getOptions());
    }

    /**
     * @return 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
     */
    private LoginInfo getLoginInfo() {
        return null;
    }

    private SDKOptions getOptions() {
        SDKOptions options = new SDKOptions();
        options.appKey = "7e189c54b1d789ba7bf174d4e074e585";
        options.checkManifestConfig = true;
        return options;
    }

}
