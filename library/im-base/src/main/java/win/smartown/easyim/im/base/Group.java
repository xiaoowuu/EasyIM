package win.smartown.easyim.im.base;

import java.util.List;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/10 10:34
 */
public abstract class Group<Data> {

    protected Data data;
    protected List<GroupMember> groupMembers;
    protected List<String> avatars;

    public Group(Data data) {
        this.data = data;
    }


    /**
     * 获取群组ID
     *
     * @return 群组ID
     */
    public abstract String getId();

    /**
     * 获取群组名称
     *
     * @return 群组名称
     */
    public abstract String getName();

    /**
     * 获取群头像
     *
     * @return
     */
    public abstract String getIcon();

    /**
     * 获取群成员
     *
     * @return 群成员
     */
    public abstract void getGroupMembers(GetGroupMembersCallback callback);

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public List<String> getAvatars() {
        return avatars;
    }

    public interface GetGroupMembersCallback {
        void godGroupMembers();
    }
}
