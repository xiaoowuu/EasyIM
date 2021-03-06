package win.smartown.easyim.ui.ysy;

import win.smartown.easyim.im.base.ProductInfo;
import win.smartown.easyim.ui.base.ChatFragment;
import win.smartown.easyim.ui.base.ConversationFragment;
import win.smartown.easyim.ui.base.UI;
import win.smartown.easyim.ui.ysy.fragment.YSYChatFragment;
import win.smartown.easyim.ui.ysy.fragment.YSYConversationFragment;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 15:54
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class YSYUI extends UI {

    /**
     * @param params 参数
     * @return {@link YSYConversationFragment}
     */
    @Override
    public ConversationFragment getConversationFragment(String... params) {
        return new YSYConversationFragment();
    }

    @Override
    public ChatFragment getChatFragment(ProductInfo productInfo, String... params) {
        return YSYChatFragment.newInstance(productInfo, params[0], Integer.parseInt(params[1]));
    }
}
