package win.smartown.easyim.im.base;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 类描述：商品信息
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/3/25 15:42
 */
public class ProductInfo implements Serializable {

    @JSONField(name = "product_image")
    private String productImagePath;
    @JSONField(name = "product_name")
    private String productName;
    @JSONField(name = "product_price")
    private String productPrice;
    @JSONField(name = "product_id")
    private String productId;
    @JSONField(name = "product_title")
    private String productTitle;
    @JSONField(name = "product_iso2o")
    private boolean isO2O;

    public String getProductImagePath() {
        return productImagePath;
    }

    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public boolean isO2O() {
        return isO2O;
    }

    public void setO2O(boolean o2O) {
        isO2O = o2O;
    }
}