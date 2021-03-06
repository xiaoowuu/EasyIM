package win.smartown.easyim.util;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import win.smartown.easyim.activity.ChatActivity;
import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.base.ProductInfo;

/**
 * @author 雷小武
 * 创建时间：2018/9/30 9:39
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class ActionHandler implements win.smartown.easyim.ui.base.ActionHandler {
    @Override
    public void startChat(Context context, Conversation conversation) {
        ChatActivity.startChat(context, conversation.getId(), conversation.getType());
    }

    @Override
    public void previewImage(ArrayList<String> images, int index) {
        Log.i("ActionHandler", index + "");
    }

    @Override
    public void showProductDetail(ProductInfo productInfo) {
        Log.i("ActionHandler", "showProductDetail");
    }
}