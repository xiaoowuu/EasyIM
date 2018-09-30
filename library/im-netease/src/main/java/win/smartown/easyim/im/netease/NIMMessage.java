package win.smartown.easyim.im.netease;

import android.text.TextUtils;

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
    public boolean equals(Object obj) {
        if (obj instanceof NIMMessage) {
            return TextUtils.equals(((NIMMessage) obj).data.getUuid(), data.getUuid());
        }
        return false;
    }
}
