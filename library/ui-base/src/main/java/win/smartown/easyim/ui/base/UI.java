package win.smartown.easyim.ui.base;

import win.smartown.easyim.im.base.ProductInfo;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:32
 * 版权：成都智慧一生约科技有限公司
 * 类描述：UI基类
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

    /**
     * 获取会话列表Fragment
     *
     * @param params 参数
     * @return 会话列表Fragment
     */
    public abstract ConversationFragment getConversationFragment(String... params);

    /**
     * 获取聊天fragment
     *
     * @param productInfo 商品信息
     * @param params      参数
     * @return 聊天fragment
     */
    public abstract ChatFragment getChatFragment(ProductInfo productInfo, String... params);

    /**
     * 设置事件处理器，将部分逻辑暴露给APP层处理
     *
     * @param actionHandler 事件处理器
     */
    public void setActionHandler(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    /**
     * @return 事件处理器
     */
    public ActionHandler getActionHandler() {
        return actionHandler;
    }
}
