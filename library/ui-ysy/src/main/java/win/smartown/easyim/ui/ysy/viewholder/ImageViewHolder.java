package win.smartown.easyim.ui.ysy.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.ysy.R;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:17
 * 版权：成都智慧一生约科技有限公司
 * 类描述：图片消息ViewHolder
 */
public class ImageViewHolder extends BaseViewHolder {

    public ImageView ivImage;

    public ImageViewHolder(@NonNull View itemView, boolean send) {
        super(itemView, send);
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

    /**
     * @param view 内容View
     */
    @Override
    protected void initContentView(View view) {
        ivImage = view.findViewById(R.id.iv_image);
    }

    @Override
    public void showMessage(Message message) {
        super.showMessage(message);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivImage.getLayoutParams();
        int imageWidth = ivImage.getResources().getDimensionPixelSize(R.dimen.dp196);
        float scale = (float) imageWidth / message.getImageWidth();
        int imageHeight = (int) (scale * message.getImageHeight());
        params.width = imageWidth + ivImage.getPaddingLeft() + ivImage.getPaddingRight();
        params.height = imageHeight + ivImage.getPaddingTop() + ivImage.getPaddingBottom();
        ivImage.setLayoutParams(params);

        int dp2 = ivImage.getResources().getDimensionPixelSize(R.dimen.dp2);
        int roundingRadius = (int) (dp2 / scale);
        Glide.with(itemView)
                .load(message.getImageUrl())
                .apply(new RequestOptions().transform(new RoundedCorners(roundingRadius < dp2 ? dp2 : roundingRadius)))
                .into(ivImage);
    }

}
