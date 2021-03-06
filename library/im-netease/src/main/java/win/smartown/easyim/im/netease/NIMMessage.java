package win.smartown.easyim.im.netease;

import android.text.TextUtils;

import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.LocationAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
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
    private LocationAttachment locationAttachment;
    private VideoAttachment videoAttachment;

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
    public String getNotificationContent() {
        if (getType() == Message.TYPE_NOTIFICATION) {
            return TeamNotificationHelper.getTeamNotificationText(data);
        }
        return "";
    }

    @Override
    public int getType() {
        switch (data.getMsgType()) {
            case text:
                return Message.TYPE_TEXT;
            case image:
                return Message.TYPE_IMAGE;
            case notification:
                return Message.TYPE_NOTIFICATION;
            case location:
                return Message.TYPE_LOCATION;
            case video:
                return Message.TYPE_VIDEO;
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

    private ImageAttachment getImageAttachment() {
        if (imageAttachment == null) {
            MsgAttachment attachment = data.getAttachment();
            if (attachment instanceof ImageAttachment) {
                imageAttachment = (ImageAttachment) attachment;
            }
        }
        return imageAttachment;
    }

    private ProductAttachment getProductAttachment() {
        if (productAttachment == null) {
            MsgAttachment attachment = data.getAttachment();
            if (attachment instanceof ProductAttachment) {
                productAttachment = (ProductAttachment) attachment;
            }
        }
        return productAttachment;
    }

    private LocationAttachment getLocationAttachment() {
        if (locationAttachment == null) {
            MsgAttachment attachment = data.getAttachment();
            if (attachment instanceof LocationAttachment) {
                locationAttachment = (LocationAttachment) attachment;
            }
        }
        return locationAttachment;
    }

    private VideoAttachment getVideoAttachment() {
        if (videoAttachment == null) {
            MsgAttachment attachment = data.getAttachment();
            if (attachment instanceof VideoAttachment) {
                videoAttachment = (VideoAttachment) attachment;
            }
        }
        return videoAttachment;
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

    @Override
    public String getVideoUrl() {
        return !TextUtils.isEmpty(getVideoAttachment().getPath()) ? getVideoAttachment().getPath() : getVideoAttachment().getUrl();
    }

    @Override
    public int getVideoWidth() {
        return getVideoAttachment().getWidth();
    }

    @Override
    public int getVideoHeight() {
        return getVideoAttachment().getHeight();
    }

    /**
     * @return 消息发送人账号
     */
    @Override
    public String getFromAccount() {
        return data.getFromAccount();
    }

    @Override
    public String getFromNick() {
        return data.getFromNick();
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
    public double getLatitude() {
        return getLocationAttachment().getLatitude();
    }

    @Override
    public double getLongitude() {
        return getLocationAttachment().getLongitude();
    }

    @Override
    public String getAddress() {
        return getLocationAttachment().getAddress();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NIMMessage) {
            return TextUtils.equals(((NIMMessage) obj).data.getUuid(), data.getUuid());
        }
        return false;
    }

}
