package win.smartown.easyim.im.netease;

import com.netease.nimlib.sdk.NIMSDK;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamMember;

import java.util.ArrayList;
import java.util.List;

import win.smartown.easyim.im.base.Group;
import win.smartown.easyim.im.base.GroupMember;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/10 10:37
 */
public class NIMGroup extends Group<Team> {
    public NIMGroup(Team team) {
        super(team);
    }

    @Override
    public String getId() {
        return data.getId();
    }

    @Override
    public String getName() {
        return data.getName();
    }

    @Override
    public String getIcon() {
        return data.getIcon();
    }

    @Override
    public void getGroupMembers(final GetGroupMembersCallback callback) {
        NIMSDK.getTeamService().queryMemberList(data.getId()).setCallback(new RequestCallback<List<TeamMember>>() {
            @Override
            public void onSuccess(List<TeamMember> param) {
                if (param != null) {
                    List<String> images = new ArrayList<>(param.size());
                    List<GroupMember> members = new ArrayList<>(param.size());
                    for (TeamMember member : param) {
                        GroupMember groupMember = new NIMGroupMember(member);
                        members.add(groupMember);
                        images.add(groupMember.getAvatar());
                    }
                    avatars = images;
                    groupMembers = members;
                    callback.godGroupMembers();
                }
            }

            @Override
            public void onFailed(int code) {

            }

            @Override
            public void onException(Throwable exception) {

            }
        });
    }

}
