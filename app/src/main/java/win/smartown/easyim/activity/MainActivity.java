package win.smartown.easyim.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import win.smartown.easyim.R;
import win.smartown.easyim.ui.base.UI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, UI.getInstance().getConversationFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.test2:
                SingleChatActivity.startSingleChat(this, "test2");
                break;
            case R.id.test3:
                SingleChatActivity.startSingleChat(this, "test3");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
