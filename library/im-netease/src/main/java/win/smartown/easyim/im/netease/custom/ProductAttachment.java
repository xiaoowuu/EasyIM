package win.smartown.easyim.im.netease.custom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;

import win.smartown.easyim.im.base.ProductInfo;


/**
 * 类描述：商品消息附加内容
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/3/25 15:42
 */
public class ProductAttachment implements MsgAttachment {
    // 多端统一
    /**
     * 商品消息
     */
    public static final int PRODUCT_MESSAGE = 1;
    /**
     * 商品信息
     */
    public static final int PRODUCT_INFO = 2;

    protected int type;
    private ProductInfo productInfo;

    public ProductAttachment(int type, ProductInfo productInfo) {
        this.type = type;
        this.productInfo = productInfo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public String toJson(boolean send) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("data", JSON.toJSONString(productInfo));
        return jsonObject.toJSONString();
    }
}
