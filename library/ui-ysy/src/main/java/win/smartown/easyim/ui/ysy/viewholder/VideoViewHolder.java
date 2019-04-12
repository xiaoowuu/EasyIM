package win.smartown.easyim.ui.ysy.viewholder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.adapter.BaseAdapter;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:17
 * 版权：成都智慧一生约科技有限公司
 * 类描述：图片消息ViewHolder
 */
public class VideoViewHolder extends MessageViewHolder {

    public VideoViewHolder(View itemView, boolean send, BaseAdapter adapter) {
        super(itemView, send, adapter);
    }

    /**
     * @return 消息内容布局
     */
    @Override
    public int getContentLayout() {
        if (send) {
            return R.layout.item_message_video_send;
        }
        return R.layout.item_message_video_received;
    }

    @Override
    public void showMessage(Message message) {
        super.showMessage(message);
        addOnChildClickListener(R.id.iv_play);
        ImageView ivImage = getImageView(R.id.iv_image);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ivImage.getLayoutParams();
        int imageWidth = ivImage.getResources().getDimensionPixelSize(R.dimen.dp198);
        float scale = (float) imageWidth / message.getVideoWidth();
        int imageHeight = (int) (scale * message.getVideoHeight());
        params.width = imageWidth;
        params.height = imageHeight;
        ivImage.setLayoutParams(params);

        Glide.with(itemView).load(message.getVideoUrl()).into(ivImage);
    }

}
