package win.smartown.easyim.easemob;

import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import win.smartown.easyim.standard.IMConversationService;
import win.smartown.easyim.standard.IMP2PChatService;
import win.smartown.easyim.standard.IMService;
import win.smartown.easyim.standard.IMServiceFactory;

/**
 * Created by smartown on 2018/2/8 16:22.
 * <br>
 * Desc:
 * <br>
 */
public class EasemobServiceFactory extends IMServiceFactory {

    public EasemobServiceFactory(Context applicationContext) {
        super(applicationContext);
        //初始化
        EMClient.getInstance().init(applicationContext, new EMOptions());
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);
    }

    @Override
    public IMService createImService() {
        return new EasemobService();
    }

    @Override
    public IMConversationService createImConversationService() {
        return new EasemobConversationService();
    }

    @Override
    public IMP2PChatService createImP2PChatService(String jsonString) {
        return new EasemobP2PChatService(jsonString);
    }
}
