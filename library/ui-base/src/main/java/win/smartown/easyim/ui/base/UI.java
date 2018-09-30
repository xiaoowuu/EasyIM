package win.smartown.easyim.ui.base;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:32
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class UI {

    private static UI sUI;

    public static void init(UI ui) {
        sUI = ui;
    }

    public static UI getInstance() {
        return sUI;
    }

    private ActionHandler actionHandler;

    public abstract ConversationFragment getConversationFragment(String... params);

    public abstract ChatFragment getSingleChatFragment(String... params);

    public void setActionHandler(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public ActionHandler getActionHandler() {
        return actionHandler;
    }
}
