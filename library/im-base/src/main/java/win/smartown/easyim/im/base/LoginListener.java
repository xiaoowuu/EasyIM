package win.smartown.easyim.im.base;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 10:32
 * 版权：成都智慧一生约科技有限公司
 * 类描述：登录回调
 */
public interface LoginListener {
    /**
     * 登录成功
     *
     * @param result 登录结果
     */
    void onLoginSuccess(Object result);

    /**
     * 登录失败
     *
     * @param throwable 失败异常
     */
    void onLoginFailed(Throwable throwable);
}
