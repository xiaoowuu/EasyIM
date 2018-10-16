package win.smartown.easyim.ui.ysy.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import win.smartown.easyim.ui.ysy.R;

/**
 * @author 雷小武
 * 创建时间：2018/10/16 14:49
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class ImageLoader {

    public static void loadHeadImage(String image, ImageView imageView) {
        Glide.with(imageView)
                .load(image)
                .apply(new RequestOptions().error(R.drawable.ico_default_head).placeholder(R.drawable.ico_default_head))
                .into(imageView);
    }

}
