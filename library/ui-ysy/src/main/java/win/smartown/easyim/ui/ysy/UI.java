package win.smartown.easyim.ui.ysy;

import win.smartown.easyim.ui.base.BaseChatFragment;
import win.smartown.easyim.ui.base.BaseConversationFragment;
import win.smartown.easyim.ui.base.BaseUI;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 15:54
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class UI extends BaseUI {

    @Override
    public BaseConversationFragment getConversationFragment(String... params) {
        return new ConversationFragment();
    }

    @Override
    public BaseChatFragment getChatFragment(String... params) {
        return ChatFragment.newInstance(params[0], Integer.parseInt(params[1]));
    }
}
