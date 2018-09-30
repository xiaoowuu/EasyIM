package win.smartown.easyim.ui.base;

import android.content.Context;

import win.smartown.easyim.im.base.Conversation;

/**
 * @author 雷小武
 * 创建时间：2018/9/30 9:29
 * 版权：成都智慧一生约科技有限公司
 * 类描述：事物处理器
 */
public interface ActionHandler {

    void startChat(Context context, Conversation conversation);

}