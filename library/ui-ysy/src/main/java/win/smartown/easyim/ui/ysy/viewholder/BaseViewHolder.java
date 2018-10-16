package win.smartown.easyim.ui.ysy.viewholder;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.im.base.User;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.util.ImageLoader;
import win.smartown.easyim.ui.ysy.util.TimeUtil;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:11
 * 版权：成都智慧一生约科技有限公司
 * 类描述：ViewHolder
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected boolean send;
    public TextView tvTime;
    private ImageView ivPortrait;

    public BaseViewHolder(@NonNull View itemView, boolean send) {
        super(itemView);
        this.send = send;
        tvTime = itemView.findViewById(R.id.tv_time);
        ivPortrait = itemView.findViewById(R.id.iv_portrait);
        ViewGroup contentContainer = itemView.findViewById(R.id.rl_content);
        View contentView = LayoutInflater.from(itemView.getContext()).inflate(getContentLayout(), contentContainer, true);
        initContentView(contentView);
    }

    /**
     * 获取消息内容布局
     *
     * @return 消息内容布局
     */
    @LayoutRes
    protected abstract int getContentLayout();

    /**
     * 初始化内容
     *
     * @param view 内容View
     */
    protected abstract void initContentView(View view);

    /**
     * 显示消息内容
     *
     * @param message 消息
     */
    public void showMessage(Message message) {
        tvTime.setText(TimeUtil.getTimeShowString(message.getTime(), true));
        User user = IM.getInstance().getUser(message.getFromAccount());
        ImageLoader.loadHeadImage(user.getAvatar(), ivPortrait);
    }

}