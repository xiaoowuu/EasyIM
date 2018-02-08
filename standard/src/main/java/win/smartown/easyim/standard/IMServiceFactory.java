package win.smartown.easyim.standard;

/**
 * Created by smartown on 2018/2/6 15:46.
 * <br>
 * Desc:
 * <br>
 */
public abstract class IMServiceFactory {

    public abstract IMService createImService();

    public abstract IMConversationService createImConversationService();

    public abstract IMP2PChatService createImP2PChatService(String jsonString);

}
