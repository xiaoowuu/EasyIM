package win.smartown.easyim.ui.ysy.viewholder;

import android.view.View;

import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.adapter.BaseAdapter;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:17
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class UnknownViewHolder extends MessageViewHolder {

    public UnknownViewHolder(View itemView, boolean send, BaseAdapter adapter) {
        super(itemView, send, adapter);
    }

    /**
     * @return 消息内容布局
     */
    @Override
    public int getContentLayout() {
        if (send) {
            return R.layout.item_message_unknown_send;
        }
        return R.layout.item_message_unknown_received;
    }

}
