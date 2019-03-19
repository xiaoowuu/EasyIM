# EasyIM
- 提供一套统一标准的IM接口供项目使用，在更换IM SDK时可以做到无需更改项目代码，换一套实现即可；
- UI与SDK剥离，可实现多套UI和多种SDK的任意组合，目前仅实现了一套UI和一种SDK；
- 精简功能，摒弃第三方demo的冗余代码。

# 使用方法
- 添加依赖

```groovy
ext {
        versionName = "1.0.1"
        easyim = [
                "im-base"   : "com.yishengyue.library:easyim-im-base:${versionName}",
                "im-netease": "com.yishengyue.library:easyim-im-netease:${versionName}",
                "ui-base"   : "com.yishengyue.library:easyim-ui-base:${versionName}",
                "ui-ysy"    : "com.yishengyue.library:easyim-ui-ysy:${versionName}"
        ]
    }
```

```groovy
//引用具体的IM SDK实现，其会自动引用im-base基础库
implementation rootProject.ext.easyim["im-netease"]
//引用具体的UI实现，其会自动引用ui-base基础库
implementation rootProject.ext.easyim["ui-ysy"]
```
- 配置AndroidManifest.xml

```xml
        <!--云信appKey-->
        <meta-data
            android:name="com.netease.nim.appKey"
            android:value="7e189c54b1d789ba7bf174d4e074e585" />
        <!--小米推送配置-->
        <receiver
            android:name="com.netease.nimlib.mixpush.mi.MiPushReceiver"
            android:exported="true">
            <intent-filter android:priority="0x7fffffff">
                <action android:name="com.xiaomi.mipush.RECEIVE_MESSAGE" />
                <action android:name="com.xiaomi.mipush.MESSAGE_ARRIVED" />
                <action android:name="com.xiaomi.mipush.ERROR" />
            </intent-filter>
        </receiver>
        <!--华为推送配置-->
        <receiver
            android:name="com.netease.nimlib.mixpush.hw.HWPushReceiver"
            android:permission="${applicationId}.permission.PROCESS_PUSH_MSG">
            <intent-filter android:priority="0x7fffffff">
                <!-- 必须,用于接收token -->
                <action android:name="com.huawei.android.push.intent.REGISTRATION" />
                <!-- 必须, 用于接收透传消息 -->
                <action android:name="com.huawei.android.push.intent.RECEIVE" />
                <!-- 必须, 用于接收通知栏消息点击事件 此事件不需要开发者处理，只需注册就可以-->
                <action android:name="com.huawei.intent.action.PUSH_DELAY_NOTIFY" />
                <!-- 用于点击通知栏或通知栏上的按钮后触发onEvent回调 -->
                <action android:name="com.huawei.android.push.intent.CLICK" />
                <!-- 查看push通道是否连接, 不查看则不需要 -->
                <action android:name="com.huawei.intent.action.PUSH_STATE" />
            </intent-filter>
            <meta-data
                android:name="CS_cloud_ablitity"
                android:value="successRateAnalytics" />
        </receiver>
```
- 初始化

```java
IM.init(new NIM(this));
UI.init(new YSYUI());
UI.getInstance().setActionHandler(new ActionHandler());
```
- 登录

```java
IM.getInstance().login(new LoginListener() {

            @Override
            public void onLoginSuccess(Object result) {
                //login success
            }

            @Override
            public void onLoginFailed(Throwable throwable) {
                //login failed
            }
        }, account, password);
```
- 最近会话

```java
getSupportFragmentManager()
    .beginTransaction()
    .replace(R.id.fragment_container, UI.getInstance().getConversationFragment())
    .commit();
```
- 聊天

```java
getSupportFragmentManager()
    .beginTransaction()
    .replace(R.id.fragment_container, UI.getInstance().getChatFragment(account, String.valueOf(type)))
    .commit();
```
