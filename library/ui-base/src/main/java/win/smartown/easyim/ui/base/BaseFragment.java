package win.smartown.easyim.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:29
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void initView(@NonNull View view);

}
