package win.smartown.easyim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.netease.nimlib.sdk.auth.LoginInfo;

import win.smartown.easyim.R;
import win.smartown.easyim.util.ToastUtil;
import win.smartown.easyim.standard.IMServiceFactory;
import win.smartown.easyim.nim.NIMService;
import win.smartown.easyim.standard.IMService;

/**
 * Created by smartown on 2018/2/6 14:57.
 * <br>
 * Desc:
 * <br>
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup imProviderGroup;
    private EditText accountEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imProviderGroup = findViewById(R.id.im_service_group);
        accountEditText = findViewById(R.id.account);
        passwordEditText = findViewById(R.id.password);
        findViewById(R.id.login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.login:
                checkInput();
                break;
            default:
                break;
        }
    }

    private void checkInput() {
        if (accountEditText.length() > 0 && passwordEditText.length() > 0) {
            int id = imProviderGroup.getCheckedRadioButtonId();
            switch (id) {
                case R.id.im_service_nim:
                    loginNIM();
                    break;
                case R.id.im_service_rongcloud:
                    break;
                default:
                    break;
            }
        }
    }

    private void loginNIM() {
        IMService<LoginInfo> service = IMServiceFactory.createIMService(NIMService.class);
        service.login(new IMService.LoginCallback<LoginInfo>() {
            @Override
            public void onLoginSuccess(LoginInfo loginInfo) {
                ToastUtil.showShort("onLoginSuccess");
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onLoginFailed(Throwable throwable) {
                ToastUtil.showShort("onLoginFailed:" + throwable.getMessage());
            }
        }, accountEditText.getText().toString(), passwordEditText.getText().toString());
    }

}
