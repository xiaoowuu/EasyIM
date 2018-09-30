package win.smartown.easyim.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import win.smartown.easyim.im.base.IMService;
import win.smartown.easyim.im.base.OnMessageChangedListener;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:05
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class SingleChatFragment extends BaseFragment implements OnMessageChangedListener {

    public final static String ACCOUNT = "account";
    protected String account;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(ACCOUNT)) {
            account = bundle.getString(ACCOUNT);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IMService.getInstance().addOnMessageChangedListener(account, this);
        IMService.getInstance().refreshMessages(account);
    }

    @Override
    public void onDestroy() {
        IMService.getInstance().removeOnMessageChangedListener(account);
        super.onDestroy();
    }

}
