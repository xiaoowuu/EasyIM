package win.smartown.easyim.im.netease;

import com.netease.nimlib.sdk.team.model.Team;

import win.smartown.easyim.im.base.Group;

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
}
