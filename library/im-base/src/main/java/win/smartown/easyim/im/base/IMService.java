package win.smartown.easyim.im.base;

import android.content.Context;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 10:26
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class IMService {

    protected Context context;

    public IMService(Context context) {
        this.context = context.getApplicationContext();
    }

    public abstract void login(LoginListener listener, String... params);

    public abstract void logout(LogoutListener listener, String... params);

}
