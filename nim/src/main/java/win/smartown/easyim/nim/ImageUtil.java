package win.smartown.easyim.nim;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by smartown on 2018/2/7 11:35.
 * <br>
 * Desc:
 * <br>
 */
public class ImageUtil {
    public static void loadImage(String imageUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop();
        Glide.with(imageView).load(imageUrl).apply(options).into(imageView);
    }
}
