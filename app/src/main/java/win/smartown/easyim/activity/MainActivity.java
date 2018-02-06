package win.smartown.easyim.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import win.smartown.easyim.R;
import win.smartown.easyim.nim.NIMConversationService;
import win.smartown.easyim.ui.fragment.ConversationFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ConversationFragment.newInstance(NIMConversationService.class))
                .commit();
    }
}
