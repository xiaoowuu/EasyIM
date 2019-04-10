package win.smartown.easyim.im.base;

/**
 * 类描述：群成员
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/10 10:34
 */
public abstract class GroupMember<Data> {

    protected Data data;

    public GroupMember(Data data) {
        this.data = data;
    }


    /**
     * 获取群成员账号
     *
     * @return 群成员账号
     */
    public abstract String getAccount();

    /**
     * 获取群成员昵称
     *
     * @return 群成员昵称
     */
    public abstract String getNick();

    /**
     * 获取群成员头像
     *
     * @return 群成员头像
     */
    public abstract String getAvatar();
}
