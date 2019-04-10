package win.smartown.easyim.im.base;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/10 10:34
 */
public abstract class Group<Data> {

    protected Data data;

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
}
