package win.smartown.easyim.ui.ysy.viewholder;

import android.view.View;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.adapter.BaseAdapter;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:17
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class NotificationViewHolder extends MessageViewHolder {

    public NotificationViewHolder(View itemView, BaseAdapter adapter) {
        super(itemView, adapter);
    }

    @Override
    public void showMessage(Message message) {
        getTextView(R.id.tv_content).setText(message.getNotificationContent());
    }

    /**
     * @return 消息内容布局
     */
    @Override
    public int getContentLayout() {
        return R.layout.item_message_notification;
    }

}
