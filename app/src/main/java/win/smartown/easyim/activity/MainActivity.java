package win.smartown.easyim.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import win.smartown.easyim.R;
import win.smartown.easyim.ui.fragment.ConversationFragment;
import win.smartown.easyim.ui.target.ConversationJumpTarget;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ConversationFragment.newInstance(getJumpTarget()))
                .commit();
    }

    private ConversationJumpTarget getJumpTarget() {
        ConversationJumpTarget target = new ConversationJumpTarget();
        target.setP2pActivityClass(Activity.class);
        target.setTeamActivityClass(Activity.class);
        return target;
    }

}
