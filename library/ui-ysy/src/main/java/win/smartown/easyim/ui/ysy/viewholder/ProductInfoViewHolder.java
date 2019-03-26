package win.smartown.easyim.ui.ysy.viewholder;

import android.view.View;

import com.bumptech.glide.Glide;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.im.base.ProductInfo;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.adapter.BaseAdapter;
import win.smartown.easyim.ui.ysy.util.TimeUtil;

/**
 * @author 雷小武
 * 创建时间：2018/10/8 9:17
 * 版权：成都智慧一生约科技有限公司
 * 类描述：商品信息ViewHolder
 */
public class ProductInfoViewHolder extends MessageViewHolder {

    public ProductInfoViewHolder(View itemView, BaseAdapter adapter) {
        super(itemView, adapter);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.item_message_product_info;
    }

    @Override
    public void showMessage(Message message) {
        getTextView(R.id.tv_time).setText(TimeUtil.getTimeShowString(message.getTime(), true));
        ProductInfo productInfo = message.getProductInfo();
        if (productInfo != null) {
            addOnChildClickListener(R.id.rl_product);
            Glide.with(itemView).load(productInfo.getProductImagePath()).into(getImageView(R.id.iv_product));
            getTextView(R.id.tv_product_name).setText(productInfo.getProductName());
            getTextView(R.id.tv_product_price).setText(String.format("¥%s", productInfo.getProductPrice()));
            addOnChildClickListener(R.id.tv_send_product);
        }
    }
}
