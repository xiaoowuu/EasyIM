package win.smartown.easyim.ui.ysy.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import win.smartown.easyim.ui.ysy.R;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/3/25 9:39
 */
public class CommonDialog extends DialogFragment implements View.OnClickListener {

    public static CommonDialog newInstance() {
        Bundle args = new Bundle();
        CommonDialog fragment = new CommonDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private View view;
    private TextView tvContent;
    private TextView tvLeft;
    private TextView tvRight;
    private String content;
    private String leftText;
    private String rightText;
    private View.OnClickListener onClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_common, null);
        initViews();
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable());
            window.requestFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    private void initViews() {
        tvContent = view.findViewById(R.id.tv_content);
        tvLeft = view.findViewById(R.id.tv_left);
        tvRight = view.findViewById(R.id.tv_right);

        if (!TextUtils.isEmpty(content)) {
            tvContent.setText(content);
        }
        if (!TextUtils.isEmpty(leftText)) {
            tvLeft.setText(leftText);
        }
        if (!TextUtils.isEmpty(rightText)) {
            tvRight.setText(rightText);
        }
        if (onClickListener != null) {
            tvLeft.setOnClickListener(this);
            tvRight.setOnClickListener(this);
        }
    }

    public CommonDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public CommonDialog setLeftText(String leftText) {
        this.leftText = leftText;
        return this;
    }

    public CommonDialog setRightText(String rightText) {
        this.rightText = rightText;
        return this;
    }

    public CommonDialog setOnButtonClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(v);
            dismiss();
        }
    }
}
