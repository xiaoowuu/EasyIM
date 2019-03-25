package win.smartown.easyim.im.netease.custom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser;

import win.smartown.easyim.im.base.ProductInfo;

/**
 * 类描述：商品消息解析
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/3/25 16:22
 */
public class ProductAttachmentParser implements MsgAttachmentParser {
    @Override
    public MsgAttachment parse(String attach) {
        try {
            JSONObject jsonObject = JSON.parseObject(attach);
            int type = jsonObject.getInteger("type");
            ProductInfo productInfo = jsonObject.getObject("data", ProductInfo.class);
            if (productInfo != null) {
                return new ProductAttachment(type, productInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
