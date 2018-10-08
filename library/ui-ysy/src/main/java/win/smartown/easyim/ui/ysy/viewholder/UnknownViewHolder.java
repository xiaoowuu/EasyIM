package win.smartown.easyim.ui.ysy.viewholder;

import android.support.annotation.NonNull;
import android.view.View;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.ysy.R;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:17
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class UnknownViewHolder extends BaseViewHolder {

    public UnknownViewHolder(@NonNull View itemView, boolean send) {
        super(itemView, send);
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

    /**
     * @param view 内容View
     */
    @Override
    protected void initContentView(View view) {

    }

    @Override
    public void showMessage(Message message) {
        super.showMessage(message);
    }

}
