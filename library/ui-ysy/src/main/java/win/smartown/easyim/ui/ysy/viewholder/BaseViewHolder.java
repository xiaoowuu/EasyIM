package win.smartown.easyim.ui.ysy.viewholder;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.ysy.R;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:11
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected boolean send;
    public ImageView ivPortrait;
    public ViewGroup contentContainer;

    public BaseViewHolder(@NonNull View itemView, boolean send) {
        super(itemView);
        this.send = send;
        ivPortrait = itemView.findViewById(R.id.iv_portrait);
        contentContainer = itemView.findViewById(R.id.rl_content);
        View contentView = LayoutInflater.from(itemView.getContext()).inflate(getContentLayout(), contentContainer, true);
        initContentView(contentView);
    }

    /**
     * @return 消息内容布局
     */
    @LayoutRes
    protected abstract int getContentLayout();

    /**
     * @param view 内容View
     */
    protected abstract void initContentView(View view);

    public abstract void showMessage(Message message);

}