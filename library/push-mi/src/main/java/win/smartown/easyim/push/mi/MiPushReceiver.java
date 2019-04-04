package win.smartown.easyim.push.mi;

import android.content.Context;
import android.content.Intent;

import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.Map;

/**
 * 类描述：小米离线推送处理
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/4 9:25
 */
public class MiPushReceiver extends PushMessageReceiver {

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        Map<String, String> extra = miPushMessage.getExtra();
        if (extra.containsKey("nim")) {
            //网易云信消息离线推送
            startAPP(context, miPushMessage);
        } else {
            //其他推送，由应用端继承实现
            onNotificationClick(context, miPushMessage);
        }
    }

    public void onNotificationClick(Context context, MiPushMessage miPushMessage) {

    }

    private void startAPP(Context context, MiPushMessage miPushMessage) {
        Intent intent = new Intent();
        intent.setComponent(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("OFFLINE_PUSH", true);
        intent.putExtra("CHANNEL", "XM");
        intent.putExtra("MESSAGE_FROM", miPushMessage.getTitle());
        intent.putExtra("MESSAGE_CONTENT", miPushMessage.getDescription());
        context.startActivity(intent);
    }
}
