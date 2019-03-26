package win.smartown.easyim.im.base;

import java.util.List;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 14:41
 * 版权：成都智慧一生约科技有限公司
 * 类描述：会话记录变化监听器
 */
public interface OnConversationChangedListener {

    /**
     * 会话记录发送变化
     *
     * @param conversations 新的会话记录列表
     */
    void onConversationChanged(List<Conversation> conversations);

}