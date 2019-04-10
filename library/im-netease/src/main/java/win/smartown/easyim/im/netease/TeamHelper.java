package win.smartown.easyim.im.netease;

import android.text.TextUtils;

import com.netease.nimlib.sdk.NIMSDK;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamMember;

import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.User;

/**
 * Created by hzxuwen on 2015/3/25.
 */
public class TeamHelper {

    /**
     * 获取显示名称。用户本人显示“你”
     *
     * @param tid
     * @param account
     * @return
     */
    public static String getTeamMemberDisplayNameYou(String tid, String account) {
        if (account.equals(IM.getInstance().getAccount())) {
            return "你";
        }

        return getDisplayNameWithoutMe(tid, account);
    }

    /**
     * 获取显示名称。用户本人也显示昵称
     * 备注>群昵称>昵称
     */
    public static String getDisplayNameWithoutMe(String tid, String account) {

        String memberNick = getTeamNick(tid, account);
        if (!TextUtils.isEmpty(memberNick)) {
            return memberNick;
        }

        return getUserDisplayName(account);
    }

    public static String getTeamNick(String tid, String account) {
        Team team = NIMSDK.getTeamService().queryTeamBlock(tid);
        if (team != null && team.getType() == TeamTypeEnum.Advanced) {
            TeamMember member = NIMSDK.getTeamService().queryTeamMemberBlock(tid, account);
            if (member != null && !TextUtils.isEmpty(member.getTeamNick())) {
                return member.getTeamNick();
            }
        }
        return null;
    }

    public static String getUserDisplayName(String account) {
        User user = IM.getInstance().getUser(account);
        if (user != null && !TextUtils.isEmpty(user.getNick())) {
            return user.getNick();
        }
        return account;
    }
}
