package win.smartown.easyim.im.base;

/**
 * @author 雷小武
 * 创建时间：2018/10/9 10:41
 * 版权：成都智慧一生约科技有限公司
 * 类描述：用户资料
 */
public abstract class User<Data> {

    protected Data data;

    public User(Data data) {
        this.data = data;
    }

    /**
     * 返回用户帐号
     *
     * @return 用户帐号
     */
    public abstract String getAccount();

    /**
     * 返回用户名
     *
     * @return 用户名
     */
    public abstract String getNick();

    /**
     * 返回用户头像链接地址
     *
     * @return 头像URL、URI
     */
    public abstract String getAvatar();
}
