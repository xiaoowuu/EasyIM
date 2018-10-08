package win.smartown.easyim.im.netease;

import android.content.Context;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.NIMSDK;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import java.io.File;
import java.util.Collections;
import java.util.List;

import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.LoginListener;
import win.smartown.easyim.im.base.LogoutListener;
import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.im.base.OnConversationChangedListener;
import win.smartown.easyim.im.base.OnMessageChangedListener;


/**
 * Created by smartown on 2018/2/6 11:52.
 * <br>
 * Desc:
 * <br>
 */
public class NIM extends IM {

    private Observer<List<RecentContact>> recentContactObserver;
    private RequestCallback<List<RecentContact>> recentContactCallback;

    private Observer<List<IMMessage>> receiveMessageObserver;
    private RequestCallbackWrapper<List<IMMessage>> messageCallback;

    private NimUserInfo userInfo;

    public NIM(Context context) {
        super(context);
        SDKOptions options = new SDKOptions();
        options.checkManifestConfig = true;
        NIMClient.init(this.context, null, options);
        initCallback();
    }

    private void initCallback() {
        recentContactObserver = new Observer<List<RecentContact>>() {
            @Override
            public void onEvent(List<RecentContact> recentContacts) {
                refreshConversations();
            }
        };
        recentContactCallback = new RequestCallback<List<RecentContact>>() {

            @Override
            public void onSuccess(List<RecentContact> param) {
                List<Conversation> conversations = Utils.getConversations(param);
                for (OnConversationChangedListener listener : onConversationChangedListeners) {
                    listener.onConversationChanged(conversations);
                }
            }

            @Override
            public void onFailed(int code) {

            }

            @Override
            public void onException(Throwable exception) {

            }
        };
        receiveMessageObserver = new Observer<List<IMMessage>>() {
            @Override
            public void onEvent(List<IMMessage> imMessages) {
                notifyMessageChanged(imMessages);
            }
        };
        messageCallback = new RequestCallbackWrapper<List<IMMessage>>() {
            @Override
            public void onResult(int code, List<IMMessage> result, Throwable exception) {
                notifyMessageChanged(result);
            }
        };
    }

    @Override
    public void login(final LoginListener listener, String... params) {
        LoginInfo loginInfo = null;
        int paramsLength = params.length;
        if (paramsLength == 2) {
            loginInfo = new LoginInfo(params[0], params[1]);
        } else if (paramsLength == 3) {
            loginInfo = new LoginInfo(params[0], params[1], params[2]);
        }
        if (loginInfo != null) {
            NIMSDK.getAuthService().login(loginInfo).setCallback(new RequestCallback<LoginInfo>() {
                @Override
                public void onSuccess(LoginInfo param) {
                    userInfo = NIMSDK.getUserService().getUserInfo(param.getAccount());
                    NIMSDK.getUserService().fetchUserInfo(Collections.singletonList(param.getAccount())).setCallback(new RequestCallback<List<NimUserInfo>>() {
                        @Override
                        public void onSuccess(List<NimUserInfo> param) {
                            NIMSDK.getMsgServiceObserve().observeRecentContact(recentContactObserver, true);
                            NIMSDK.getMsgServiceObserve().observeReceiveMessage(receiveMessageObserver, true);
                            if (param != null && param.size() > 0) {
                                userInfo = param.get(0);
                            }
                        }

                        @Override
                        public void onFailed(int code) {

                        }

                        @Override
                        public void onException(Throwable exception) {

                        }
                    });
                    listener.onLoginSuccess(param);
                }

                @Override
                public void onFailed(int code) {
                    listener.onLoginFailed(new Exception("onFailed:" + code));
                }

                @Override
                public void onException(Throwable exception) {
                    listener.onLoginFailed(exception);
                }
            });
        }
    }

    @Override
    public void logout(LogoutListener listener, String... params) {
        NIMSDK.getAuthService().logout();
    }

    @Override
    public void refreshConversations() {
        NIMSDK.getMsgService().queryRecentContacts().setCallback(recentContactCallback);
    }

    @Override
    public void refreshMessages(String account, int type) {
        IMMessage anchor = MessageBuilder.createEmptyMessage(account, Utils.getSesstionType(type), System.currentTimeMillis());
        NIMSDK.getMsgService().queryMessageListEx(anchor, QueryDirectionEnum.QUERY_OLD, 1000, true).setCallback(messageCallback);
    }

    @Override
    public void sendTextMessage(String account, int type, String text) {
        final IMMessage message = MessageBuilder.createTextMessage(account, Utils.getSesstionType(type), text);
        sendMessage(message);
    }

    @Override
    public void sendImageMessage(String account, int type, File file) {
        final IMMessage message = MessageBuilder.createImageMessage(account, Utils.getSesstionType(type), file);
        sendMessage(message);
    }

    public void sendMessage(final IMMessage message) {
        onSendMessage(message);
        NIMSDK.getMsgService().sendMessage(message, false).setCallback(new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void param) {
                onMessageStatusChanged(message);
            }

            @Override
            public void onFailed(int code) {
                onMessageStatusChanged(message);
            }

            @Override
            public void onException(Throwable exception) {
                onMessageStatusChanged(message);
            }
        });
    }

    private void notifyMessageChanged(List<IMMessage> imMessages) {
        if (imMessages != null && !imMessages.isEmpty()) {
            String account = imMessages.get(0).getSessionId();
            OnMessageChangedListener listener = onMessageChangedListeners.get(account);
            if (listener != null) {
                List<Message> messages = Utils.getMessages(imMessages);
                listener.onReceivedMessage(messages);
            }
        }
    }

    private void onSendMessage(IMMessage imMessage) {
        String account = imMessage.getSessionId();
        OnMessageChangedListener listener = onMessageChangedListeners.get(account);
        if (listener != null) {
            NIMMessage message = new NIMMessage(imMessage);
            listener.onSendMessage(message);
        }
    }

    private void onMessageStatusChanged(IMMessage imMessage) {
        String account = imMessage.getSessionId();
        OnMessageChangedListener listener = onMessageChangedListeners.get(account);
        if (listener != null) {
            NIMMessage message = new NIMMessage(imMessage);
            listener.onMessageStatusChanged(message);
        }
    }

}