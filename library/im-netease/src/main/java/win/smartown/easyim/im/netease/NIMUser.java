package win.smartown.easyim.im.netease;

import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import win.smartown.easyim.im.base.User;

/**
 * @author 雷小武
 * 创建时间：2018/10/9 10:44
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class NIMUser extends User<NimUserInfo> {

    public NIMUser(NimUserInfo nimUserInfo) {
        super(nimUserInfo);
    }

    /**
     * 返回用户帐号
     *
     * @return 用户帐号
     */
    @Override
    public String getAccount() {
        if (data == null) {
            return "";
        }
        return data.getAccount();
    }

    /**
     * 返回用户名
     *
     * @return 用户名
     */
    @Override
    public String getNick() {
        if (data == null) {
            return "";
        }
        return data.getName();
    }

    /**
     * 返回用户头像链接地址
     *
     * @return 头像URL、URI
     */
    @Override
    public String getAvatar() {
        if (data == null) {
            return "";
        }
        return data.getAvatar();
    }
}
