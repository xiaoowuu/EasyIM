package win.smartown.easyim.standard;

/**
 * Created by smartown on 2018/2/6 15:46.
 * <br>
 * Desc:
 * <br>
 */
public abstract class IMServiceFactory {

    private IMService imService;
    private IMConversationService imConversationService;

    public IMService getImService() {
        if (imService == null) {
            imService = createImService();
        }
        return imService;
    }

    public void setImService(IMService imService) {
        this.imService = imService;
    }

    public abstract IMService createImService();

    public IMConversationService getImConversationService() {
        if (imConversationService == null) {
            imConversationService = createImConversationService();
        }
        return imConversationService;
    }

    public void setImConversationService(IMConversationService imConversationService) {
        this.imConversationService = imConversationService;
    }

    public abstract IMConversationService createImConversationService();

}
