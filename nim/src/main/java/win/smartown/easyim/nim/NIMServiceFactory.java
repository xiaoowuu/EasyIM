package win.smartown.easyim.nim;

import win.smartown.easyim.standard.IMConversationService;
import win.smartown.easyim.standard.IMService;
import win.smartown.easyim.standard.IMServiceFactory;

/**
 * Created by smartown on 2018/2/7 9:14.
 * <br>
 * Desc:
 * <br>
 */
public class NIMServiceFactory extends IMServiceFactory {

    @Override
    public IMService createImService() {
        return new NIMService();
    }

    @Override
    public IMConversationService createImConversationService() {
        return new NIMConversationService();
    }
}
