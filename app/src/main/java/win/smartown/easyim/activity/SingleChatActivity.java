package win.smartown.easyim.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import win.smartown.easyim.R;
import win.smartown.easyim.ui.base.UI;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:13
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class SingleChatActivity extends AppCompatActivity {

    private final static String ACCOUNT = "account";

    public static void startSingleChat(Context context, String account) {
        Intent intent = new Intent(context, SingleChatActivity.class);
        intent.putExtra(ACCOUNT, account);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String account = getIntent().getStringExtra(ACCOUNT);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, UI.getInstance().getSingleChatFragment(account))
                .commit();
    }
}
