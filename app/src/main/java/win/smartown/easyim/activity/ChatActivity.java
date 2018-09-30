package win.smartown.easyim.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import win.smartown.easyim.R;
import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.ui.base.BaseChatFragment;
import win.smartown.easyim.ui.base.BaseUI;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:13
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class ChatActivity extends AppCompatActivity {

    /**
     * 进入聊天
     *
     * @param context Context
     * @param account 聊天对象账号
     * @param type    会话类型<br>{@link Conversation#TYPE_SINGLE}<br>{@link Conversation#TYPE_GROUP}<br>{@link Conversation#TYPE_OTHER}
     */
    public static void startChat(Context context, String account, int type) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(BaseChatFragment.ACCOUNT, account);
        intent.putExtra(BaseChatFragment.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String account = getIntent().getStringExtra(BaseChatFragment.ACCOUNT);
        int type = getIntent().getIntExtra(BaseChatFragment.TYPE, Conversation.TYPE_OTHER);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, BaseUI.getInstance().getChatFragment(account, String.valueOf(type)))
                .commit();
    }
}