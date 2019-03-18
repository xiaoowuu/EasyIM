package win.smartown.easyim.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.OnMessageChangedListener;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:05
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class ChatFragment extends BaseFragment implements OnMessageChangedListener {

    public final static String ACCOUNT = "account";
    public final static String TYPE = "type";
    protected String account;
    protected int type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(ACCOUNT)) {
            if (bundle.containsKey(ACCOUNT)) {
                account = bundle.getString(ACCOUNT);
            }
            if (bundle.containsKey(TYPE)) {
                type = bundle.getInt(TYPE);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IM.getInstance().addOnMessageChangedListener(account, this);
        IM.getInstance().refreshMessages(account, type);
    }

    @Override
    public void onResume() {
        super.onResume();
        IM.getInstance().onChatFragmentResume(account, type);
    }

    @Override
    public void onPause() {
        IM.getInstance().onChatFragmentPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        IM.getInstance().removeOnMessageChangedListener(account);
        super.onDestroy();
    }

}
