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
<meta-data
    android:name="com.netease.nim.appKey"
    android:value="7e189c54b1d789ba7bf174d4e074e585" />
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
