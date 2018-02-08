package win.smartown.easyim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import win.smartown.easyim.EasyIM;
import win.smartown.easyim.R;
import win.smartown.easyim.standard.IMService;
import win.smartown.easyim.util.ToastUtil;

/**
 * Created by smartown on 2018/2/6 14:57.
 * <br>
 * Desc:
 * <br>
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText accountEditText;
    private EditText passwordEditText;

    private IMService imService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imService = EasyIM.getImServiceFactory().createImService();
        setContentView(R.layout.activity_login);
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
            login();
        }
    }

    private void login() {
        imService.login(new IMService.LoginCallback() {

            @Override
            public void onLoginSuccess(Object result) {
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
