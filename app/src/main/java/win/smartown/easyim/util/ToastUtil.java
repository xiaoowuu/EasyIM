package win.smartown.easyim.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by smartown on 2018/2/6 15:27.
 * <br>
 * Desc:
 * <br>
 */
public class ToastUtil {

    private static Toast toast;

    public static void init(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    public static void showShort(CharSequence text) {
        toast.setText(text);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showLong(CharSequence text) {
        toast.setText(text);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

}
