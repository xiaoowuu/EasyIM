package win.smartown.easyim.ui.base;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:32
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class BaseUI {

    private static BaseUI sUI;

    public static void init(BaseUI ui) {
        sUI = ui;
    }

    public static BaseUI getInstance() {
        return sUI;
    }

    private ActionHandler actionHandler;

    public abstract BaseConversationFragment getConversationFragment(String... params);

    public abstract BaseChatFragment getChatFragment(String... params);

    public void setActionHandler(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public ActionHandler getActionHandler() {
        return actionHandler;
    }
}
