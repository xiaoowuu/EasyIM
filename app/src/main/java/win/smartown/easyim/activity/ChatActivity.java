package win.smartown.easyim.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import win.smartown.easyim.R;
import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.base.ProductInfo;
import win.smartown.easyim.ui.base.ChatFragment;
import win.smartown.easyim.ui.base.UI;

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
        intent.putExtra(ChatFragment.ACCOUNT, account);
        intent.putExtra(ChatFragment.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String account = getIntent().getStringExtra(ChatFragment.ACCOUNT);
        int type = getIntent().getIntExtra(ChatFragment.TYPE, Conversation.TYPE_OTHER);
        setContentView(R.layout.activity_main);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setO2O(false);
        productInfo.setProductId("");
        productInfo.setProductImagePath("https://pics5.baidu.com/feed/b21bb051f8198618f594eff9c906bf7789d4e6e8.jpeg?token=ee3c78410dd39fbb1c8ffa4d9ebf60d7&s=07335B87C65637DC5E78E58703007081");
        productInfo.setProductName("诺基亚五摄手机PureView意外亮相京东 多少钱值得买？");
        productInfo.setProductPrice("100.00");
        productInfo.setProductTitle("诺基亚五摄手机PureView意外亮相京东");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, UI.getInstance().getChatFragment(productInfo, account, String.valueOf(type)))
                .commit();
    }
}