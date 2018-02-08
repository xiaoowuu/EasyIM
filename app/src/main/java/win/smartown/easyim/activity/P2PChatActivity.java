package win.smartown.easyim.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import win.smartown.easyim.Constants;
import win.smartown.easyim.R;
import win.smartown.easyim.ui.fragment.P2PChatFragment;

/**
 * Created by smartown on 2018/2/8 10:10.
 * <br>
 * Desc:
 * <br>
 */
public class P2PChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, P2PChatFragment.newInstance(getIntent().getStringExtra(Constants.CONVERSATION_CHAT_ROOM_PARAMS)))
                .commit();
    }

}
