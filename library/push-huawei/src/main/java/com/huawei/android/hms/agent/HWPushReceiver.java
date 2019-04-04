package com.huawei.android.hms.agent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.huawei.hms.support.api.push.PushReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/4 10:13
 */
public class HWPushReceiver extends PushReceiver {

    @Override
    public void onEvent(Context context, Event event, Bundle extras) {
        super.onEvent(context, event, extras);
        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            if (extras != null && extras.containsKey("pushMsg")) {
                try {
                    JSONArray jsonArray = new JSONArray(extras.getString("pushMsg"));
                    if (jsonArray.length() > 0) {
                        JSONObject object = jsonArray.getJSONObject(0);
                        if (object.has("nim")) {
                            startAPP(context);
                            return;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        onNotificationClick(context, event, extras);
    }

    public void onNotificationClick(Context context, Event event, Bundle extras) {

    }

    private void startAPP(Context context) {
        Intent intent = new Intent();
        intent.setComponent(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("OFFLINE_PUSH", true);
        intent.putExtra("CHANNEL", "HW");
        context.startActivity(intent);
    }
}
