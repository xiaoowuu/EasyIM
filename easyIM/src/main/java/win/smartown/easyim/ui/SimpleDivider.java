package win.smartown.easyim.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 */
public class SimpleDivider extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint dividerPaint;

    /**
     * 构造方法
     *
     * @param dividerHeight 高度
     * @param dividerColor  颜色
     */
    public SimpleDivider(int dividerHeight, int dividerColor) {
        this.dividerHeight = dividerHeight;

        dividerPaint = new Paint();
        dividerPaint.setColor(dividerColor);
    }

    public SimpleDivider(Context context, @DimenRes int dividerHeight, @ColorRes int dividerColor) {
        this(context.getResources().getDimensionPixelSize(dividerHeight), context.getResources().getColor(dividerColor));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}