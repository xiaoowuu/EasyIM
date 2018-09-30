package win.smartown.easyim.ui.wechat;

import win.smartown.easyim.ui.base.ChatFragment;
import win.smartown.easyim.ui.base.ConversationFragment;
import win.smartown.easyim.ui.base.UI;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 15:54
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class WxUI extends UI {

    @Override
    public ConversationFragment getConversationFragment(String... params) {
        return new WxConversationFragment();
    }

    @Override
    public ChatFragment getSingleChatFragment(String... params) {
        return WxChatFragment.newInstance(params[0], Integer.parseInt(params[1]));
    }
}
