package win.smartown.easyim.im.netease;

import android.text.TextUtils;

import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.im.base.ProductInfo;
import win.smartown.easyim.im.netease.custom.ProductAttachment;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:53
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class NIMMessage extends Message<IMMessage> {

    private ImageAttachment imageAttachment;
    private ProductAttachment productAttachment;

    public NIMMessage(IMMessage imMessage) {
        super(imMessage);
    }

    @Override
    public String getConversationId() {
        return data.getSessionId();
    }

    /**
     * @return 是否是我发送的
     */
    @Override
    public boolean isSend() {
        return data.getDirect() == MsgDirectionEnum.Out;
    }

    /**
     * @return 消息内容
     */
    @Override
    public String getContent() {
        return data.getContent();
    }

    @Override
    public int getType() {
        switch (data.getMsgType()) {
            case text:
                return Message.TYPE_TEXT;
            case image:
                return Message.TYPE_IMAGE;
            case custom:
                MsgAttachment attachment = data.getAttachment();
                if (attachment instanceof ProductAttachment) {
                    switch (((ProductAttachment) attachment).getType()) {
                        case ProductAttachment.PRODUCT_MESSAGE:
                            return Message.TYPE_PRODUCT_MESSAGE;
                        case ProductAttachment.PRODUCT_INFO:
                            return Message.TYPE_PRODUCT_INFO;
                    }
                }
            default:
                return 0;
        }
    }

    public ImageAttachment getImageAttachment() {
        if (imageAttachment == null) {
            MsgAttachment attachment = data.getAttachment();
            if (attachment instanceof ImageAttachment) {
                imageAttachment = (ImageAttachment) attachment;
            }
        }
        return imageAttachment;
    }

    public ProductAttachment getProductAttachment() {
        if (productAttachment == null) {
            MsgAttachment attachment = data.getAttachment();
            if (attachment instanceof ProductAttachment) {
                productAttachment = (ProductAttachment) attachment;
            }
        }
        return productAttachment;
    }

    /**
     * 获取图片链接
     *
     * @return 图片链接
     */
    @Override
    public String getImageUrl() {
        ImageAttachment imageAttachment = getImageAttachment();
        if (imageAttachment != null) {
            return !TextUtils.isEmpty(imageAttachment.getPath()) ? imageAttachment.getPath() : imageAttachment.getUrl();
        }
        return "";
    }

    /**
     * 获取图片宽度
     *
     * @return 图片宽度
     */
    @Override
    public int getImageWidth() {
        ImageAttachment imageAttachment = getImageAttachment();
        if (imageAttachment != null) {
            return imageAttachment.getWidth();
        }
        return 1;
    }

    /**
     * 获取图片高度
     *
     * @return 图片高度
     */
    @Override
    public int getImageHeight() {
        ImageAttachment imageAttachment = getImageAttachment();
        if (imageAttachment != null) {
            return imageAttachment.getHeight();
        }
        return 1;
    }

    /**
     * @return 消息发送人账号
     */
    @Override
    public String getFromAccount() {
        return data.getFromAccount();
    }

    @Override
    public ProductInfo getProductInfo() {
        return getProductAttachment().getProductInfo();
    }

    /**
     * @return 消息时间
     */
    @Override
    public long getTime() {
        return data.getTime();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NIMMessage) {
            return TextUtils.equals(((NIMMessage) obj).data.getUuid(), data.getUuid());
        }
        return false;
    }
}
