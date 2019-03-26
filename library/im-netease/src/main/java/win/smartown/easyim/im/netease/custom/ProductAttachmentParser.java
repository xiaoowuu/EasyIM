package win.smartown.easyim.im.netease.custom;

import com.alibaba.fastjson.JSON;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser;

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
            return JSON.parseObject(attach, ProductAttachment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
