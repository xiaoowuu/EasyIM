package win.smartown.easyim.im.base;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 10:32
 * 版权：成都智慧一生约科技有限公司
 * 类描述：注销回调
 */
public interface LogoutListener {
    /**
     * 注销成功
     *
     * @param result 注销结果
     */
    void onLogoutSuccess(Object result);

    /**
     * 注销失败
     *
     * @param throwable 异常
     */
    void onLogoutFailed(Throwable throwable);
}
