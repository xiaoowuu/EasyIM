package win.smartown.easyim.ui.ysy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.adapter.BaseAdapter;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:17
 * 版权：成都智慧一生约科技有限公司
 * 类描述：图片消息ViewHolder
 */
public class ImageViewHolder extends MessageViewHolder {

    public ImageViewHolder(View itemView, boolean send, BaseAdapter adapter) {
        super(itemView, send, adapter);
    }

    /**
     * @return 消息内容布局
     */
    @Override
    public int getContentLayout() {
        if (send) {
            return R.layout.item_message_image_send;
        }
        return R.layout.item_message_image_received;
    }

    @Override
    public void showMessage(Message message) {
        super.showMessage(message);
        ImageView ivImage = getImageView(R.id.iv_image);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivImage.getLayoutParams();
        int imageWidth = ivImage.getResources().getDimensionPixelSize(R.dimen.dp198);
        float scale = (float) imageWidth / message.getImageWidth();
        int imageHeight = (int) (scale * message.getImageHeight());
        params.width = imageWidth + ivImage.getPaddingLeft() + ivImage.getPaddingRight();
        params.height = imageHeight + ivImage.getPaddingTop() + ivImage.getPaddingBottom();
        ivImage.setLayoutParams(params);

        int dp3 = ivImage.getResources().getDimensionPixelSize(R.dimen.dp3);
        Glide.with(itemView)
                .load(message.getImageUrl())
                .apply(new RequestOptions().transform(new RoundedCorners(dp3)))
                .into(ivImage);
    }

}