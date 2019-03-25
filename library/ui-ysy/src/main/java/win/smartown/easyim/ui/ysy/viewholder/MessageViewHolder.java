package win.smartown.easyim.ui.ysy.viewholder;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.im.base.User;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.adapter.BaseAdapter;
import win.smartown.easyim.ui.ysy.util.ImageLoader;
import win.smartown.easyim.ui.ysy.util.TimeUtil;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:11
 * 版权：成都智慧一生约科技有限公司
 * 类描述：ViewHolder
 */
public abstract class MessageViewHolder extends BaseAdapter.BaseViewHolder {

    protected boolean send;

    public MessageViewHolder(View itemView, BaseAdapter adapter) {
        super(itemView, adapter);
    }

    public MessageViewHolder(View itemView, boolean send, BaseAdapter adapter) {
        super(itemView, adapter);
        this.send = send;
        ViewGroup contentContainer = (ViewGroup) getView(R.id.rl_content);
        LayoutInflater.from(itemView.getContext()).inflate(getContentLayout(), contentContainer, true);
    }

    /**
     * 获取消息内容布局
     *
     * @return 消息内容布局
     */
    @LayoutRes
    protected abstract int getContentLayout();

    /**
     * 显示消息内容
     *
     * @param message 消息
     */
    public void showMessage(Message message) {
        getTextView(R.id.tv_time).setText(TimeUtil.getTimeShowString(message.getTime(), true));
        User user = IM.getInstance().getUser(message.getFromAccount());
        ImageLoader.loadHeadImage(user.getAvatar(), getImageView(R.id.iv_portrait));
    }

}