package win.smartown.easyim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import win.smartown.easyim.R;

/**
 * Created by smartown on 2018/2/6 14:57.
 * <br>
 * Desc:
 * <br>
 */
public class FlashActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(FlashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    public void finish() {
        handler.removeCallbacksAndMessages(null);
        handler = null;
        super.finish();
    }
}
