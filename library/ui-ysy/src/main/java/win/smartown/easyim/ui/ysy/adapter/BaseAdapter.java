package win.smartown.easyim.ui.ysy.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * @param <VH> BaseAdapter.MessageViewHolder
 * @author 雷小武
 * 创建时间：2018/10/15 10:50
 * 版权：成都智慧一生约科技有限公司
 * 类描述：适配器基类，实现item点击事件处理
 */
public abstract class BaseAdapter<VH extends BaseAdapter.BaseViewHolder> extends RecyclerView.Adapter<VH> {

    private OnItemClickListener onItemClickListener;
    private OnItemChildClickListener onItemChildClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        this.onItemChildClickListener = onItemChildClickListener;
    }

    /**
     * MessageViewHolder
     */
    public static class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private WeakReference<BaseAdapter> reference;
        private SparseArray<View> views;

        public BaseViewHolder(View itemView, BaseAdapter adapter) {
            super(itemView);
            reference = new WeakReference<>(adapter);
            views = new SparseArray<>();
        }

        /**
         * 获取子view
         *
         * @param viewId id
         * @return 子view
         */
        public View getView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return view;
        }

        /**
         * 获取子TextView
         *
         * @param viewId id
         * @return 子TextView
         */
        public TextView getTextView(int viewId) {
            return (TextView) getView(viewId);
        }

        /**
         * 获取子ImageView
         *
         * @param viewId id
         * @return 子ImageView
         */
        public ImageView getImageView(int viewId) {
            return (ImageView) getView(viewId);
        }

        /**
         * 添加子View点击事件监听
         *
         * @param viewId id
         */
        public void addOnChildClickListener(int viewId) {
            getView(viewId).setOnClickListener(this);
        }

        /**
         * 设置item点击事件监听
         */
        public void setOnClickListener() {
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            BaseAdapter adapter = reference.get();
            if (adapter != null) {
                int position = getAdapterPosition();
                if (v.equals(itemView)) {
                    if (adapter.onItemClickListener != null) {
                        adapter.onItemClickListener.onItemClick(v, position);
                    }
                } else {
                    if (adapter.onItemChildClickListener != null) {
                        adapter.onItemChildClickListener.onItemChildClick(v, position);
                    }
                }
            }
        }

    }

    /**
     * item点击回调
     */
    public interface OnItemClickListener {
        /**
         * item点击回调
         *
         * @param view     被点击的view
         * @param position 适配器中的位置
         */
        void onItemClick(View view, int position);

    }

    /**
     * item子view点击回调
     */
    public interface OnItemChildClickListener {
        /**
         * item子view点击回调
         *
         * @param view     被点击的view
         * @param position 适配器中的位置
         */
        void onItemChildClick(View view, int position);

    }

}
