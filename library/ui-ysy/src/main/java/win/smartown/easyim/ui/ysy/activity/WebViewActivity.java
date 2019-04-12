package win.smartown.easyim.ui.ysy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import win.smartown.easyim.ui.ysy.R;

/**
 * 类描述：
 *
 * @author xiaoowuu@gmail.com
 * 创建时间：2019/4/12 16:55
 */
public class WebViewActivity extends Activity {

    private WebView webView;

    public static void playVideo(Context context, String videoUrl) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("videoUrl", videoUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.web_view);

        webView.loadUrl(getIntent().getStringExtra("videoUrl"));
    }
}
