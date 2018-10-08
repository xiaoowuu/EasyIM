package win.smartown.easyim.im.netease;

import android.text.TextUtils;

import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import win.smartown.easyim.im.base.Message;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:53
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class NIMMessage extends Message<IMMessage> {

    public NIMMessage(IMMessage imMessage) {
        super(imMessage);
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
            default:
                return 0;
        }
    }

    /**
     * 获取图片链接
     *
     * @return 图片链接
     */
    @Override
    public String getImageUrl() {
        MsgAttachment attachment = data.getAttachment();
        if (attachment instanceof ImageAttachment) {
            ImageAttachment imageAttachment = (ImageAttachment) attachment;
            return TextUtils.isEmpty(imageAttachment.getUrl()) ? imageAttachment.getPath() : imageAttachment.getUrl();
        }
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NIMMessage) {
            return TextUtils.equals(((NIMMessage) obj).data.getUuid(), data.getUuid());
        }
        return false;
    }
}
