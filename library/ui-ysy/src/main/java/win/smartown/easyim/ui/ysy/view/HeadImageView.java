package win.smartown.easyim.ui.ysy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import win.smartown.easyim.ui.ysy.R;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/10 10:43
 */
public class HeadImageView extends View {

    private List<String> images = new ArrayList<>();
    private int maxCount = 4;
    private int shape = 1;
    private Map<String, Bitmap> bitmap = new HashMap<>();
    private Bitmap defaultBitmap;

    public HeadImageView(Context context) {
        super(context);
    }

    public HeadImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size);
    }

    public void setImages(List<String> images) {
        this.images = images;
        loadImages();
        invalidate();
    }

    private void loadImages() {
        for (final String url : images) {
            Glide.with(this).asBitmap().load(url).override(100).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    bitmap.put(url, resource);
                    invalidate();
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (images.isEmpty()) {

        } else if (images.size() == 1) {

        } else if (images.size() == 2) {

        } else if (images.size() == 3) {

        } else {

        }
    }

    private Bitmap mergeBitmap2() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        for (int i = 0; i < 2; i++) {
//            canvas.drawBitmap(getBitmap(images.get(i)),);
        }
        return bitmap;
    }

    private Bitmap mergeBitmap3() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        for (int i = 0; i < 2; i++) {
//            canvas.drawBitmap(getBitmap(images.get(i)),);
        }
        return bitmap;
    }

    private Bitmap mergeBitmap4() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        for (int i = 0; i < 2; i++) {
//            canvas.drawBitmap(getBitmap(images.get(i)),);
        }
        return bitmap;
    }

    private Bitmap getBitmap(String url) {
        if (!TextUtils.isEmpty(url)) {
            if (bitmap.containsKey(url)) {
                return bitmap.get(url);
            }
        }
        if (defaultBitmap == null) {
            defaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ico_default_head);
        }
        return defaultBitmap;
    }
}
