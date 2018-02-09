package win.smartown.easyim.standard;

import android.content.Context;

/**
 * Created by smartown on 2018/2/6 15:46.
 * <br>
 * Desc:
 * <br>
 */
public abstract class IMServiceFactory {

    public IMServiceFactory(Context applicationContext) {

    }

    public abstract IMService createImService();

    public abstract IMConversationService createImConversationService();

    public abstract IMP2PChatService createImP2PChatService(String jsonString);

}
