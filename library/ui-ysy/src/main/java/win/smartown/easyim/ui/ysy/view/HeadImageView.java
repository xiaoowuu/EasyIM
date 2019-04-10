package win.smartown.easyim.ui.ysy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import win.smartown.easyim.ui.ysy.R;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/10 10:43
 */
public class HeadImageView extends LinearLayout {

    private List<String> images;

    public HeadImageView(Context context) {
        super(context);
        init();
    }

    public HeadImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeadImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
    }

    public void setImages(List<String> images) {
        this.images = images;
        removeAllViews();
        if (images == null || images.isEmpty()) {
            LinearLayout linearLayout = newLinearLayout();
            linearLayout.addView(newImageView(null));
            addView(linearLayout);
        } else if (images.size() <= 2) {
            LinearLayout linearLayout = newLinearLayout();
            for (String image : images) {
                linearLayout.addView(newImageView(image));
            }
            addView(linearLayout);
        } else if (images.size() == 3) {
            LinearLayout linearLayout1 = newLinearLayout();
            linearLayout1.addView(newImageView(images.get(0)));
            linearLayout1.addView(newImageView(images.get(1)));
            LinearLayout linearLayout2 = newLinearLayout();
            linearLayout2.addView(newImageView(images.get(2)));
            addView(linearLayout1);
            addView(linearLayout2);
        } else {
            LinearLayout linearLayout1 = newLinearLayout();
            linearLayout1.addView(newImageView(images.get(0)));
            linearLayout1.addView(newImageView(images.get(1)));
            LinearLayout linearLayout2 = newLinearLayout();
            linearLayout2.addView(newImageView(images.get(2)));
            linearLayout2.addView(newImageView(images.get(3)));
            addView(linearLayout1);
            addView(linearLayout2);
        }
    }

    private LinearLayout newLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.weight = 1;
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);
        return linearLayout;
    }

    private ImageView newImageView(String image) {
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (TextUtils.isEmpty(image)) {
            imageView.setImageResource(R.drawable.ico_default_head);
        } else {
            Glide.with(this).load(image).into(imageView);
        }
        return imageView;
    }
}