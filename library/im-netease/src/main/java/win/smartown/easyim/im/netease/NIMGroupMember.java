package win.smartown.easyim.im.netease;

import android.text.TextUtils;

import com.netease.nimlib.sdk.team.model.TeamMember;

import win.smartown.easyim.im.base.GroupMember;
import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.User;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/10 14:03
 */
public class NIMGroupMember extends GroupMember<TeamMember> {
    public NIMGroupMember(TeamMember teamMember) {
        super(teamMember);
    }

    @Override
    public String getAccount() {
        return data.getAccount();
    }

    @Override
    public String getNick() {
        return data.getTeamNick();
    }

    @Override
    public String getAvatar() {
        User user = IM.getInstance().getUser(getAccount());
        return user != null && !TextUtils.isEmpty(user.getAvatar()) ? user.getAvatar() : "";
    }
}
