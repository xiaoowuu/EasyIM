package win.smartown.easyim.im.base;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 10:32
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public interface LoginListener {
    void onLoginSuccess(Object result);

    void onLoginFailed(Throwable throwable);
}
