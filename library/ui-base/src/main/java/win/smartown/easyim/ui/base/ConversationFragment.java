package win.smartown.easyim.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.OnConversationChangedListener;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 11:05
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class ConversationFragment extends BaseFragment implements OnConversationChangedListener {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IM.getInstance().addOnConversationChangedListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        IM.getInstance().refreshConversations();
    }

    @Override
    public void onDestroy() {
        IM.getInstance().removeOnConversationChangedListener(this);
        super.onDestroy();
    }
}