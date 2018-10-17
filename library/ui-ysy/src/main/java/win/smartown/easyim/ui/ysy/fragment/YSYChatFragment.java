package win.smartown.easyim.ui.ysy.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.FileProvider;
import com.yanzhenjie.permission.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;
import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.base.ChatFragment;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.adapter.MessageAdapter;
import win.smartown.easyim.ui.ysy.strategy.ShowTimeStrategy;
import win.smartown.easyim.ui.ysy.util.Glide4Engine;
import win.smartown.easyim.ui.ysy.util.KeyboardUtils;

import static android.app.Activity.RESULT_OK;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:21
 * 版权：成都智慧一生约科技有限公司
 * 类描述：聊天界面
 */
public class YSYChatFragment extends ChatFragment implements View.OnClickListener {

    /**
     * 拍照
     */
    private static final int REQUEST_CAMERA = 11;
    /**
     * 选择图片
     */
    private static final int REQUEST_PICK = 12;
    private RecyclerView rvMessage;
    private LinearLayoutManager linearLayoutManager;
    private EditText etMessage;
    private ImageView ivMore;
    private TextView tvSend;
    private LinearLayout llAction;
    private MessageAdapter messageAdapter;

    private File tempImageFile;

    /**
     * 创建一个聊天
     *
     * @param account 聊天对象账号
     * @param type    聊天类型:
     *                <br>
     *                单聊{@link Conversation#TYPE_SINGLE}
     *                <br>
     *                群聊{@link Conversation#TYPE_GROUP}
     * @return 聊天界面
     */
    public static YSYChatFragment newInstance(String account, int type) {
        Bundle args = new Bundle();
        args.putString(ChatFragment.ACCOUNT, account);
        args.putInt(ChatFragment.TYPE, type);
        YSYChatFragment fragment = new YSYChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_chat;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView(@NonNull View view) {
        rvMessage = view.findViewById(R.id.rv_message);
        etMessage = view.findViewById(R.id.et_message);
        ivMore = view.findViewById(R.id.iv_more);
        tvSend = view.findViewById(R.id.tv_send);
        llAction = view.findViewById(R.id.ll_action);

        rvMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                llAction.setVisibility(View.GONE);
                KeyboardUtils.hideSoftInput(getActivity());
                etMessage.clearFocus();
                return false;
            }
        });
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etMessage.length() > 0) {
                    ivMore.setVisibility(View.GONE);
                    tvSend.setVisibility(View.VISIBLE);
                } else {
                    ivMore.setVisibility(View.VISIBLE);
                    tvSend.setVisibility(View.GONE);
                }
            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMessage.setLayoutManager(linearLayoutManager);
        messageAdapter = new MessageAdapter(new ShowTimeStrategy());
        rvMessage.setAdapter(messageAdapter);
        etMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                llAction.setVisibility(View.GONE);
                scrollToBottomDelay(200);
                return false;
            }
        });
        tvSend.setOnClickListener(this);
        ivMore.setOnClickListener(this);
        view.findViewById(R.id.ll_camera).setOnClickListener(this);
        view.findViewById(R.id.ll_photo).setOnClickListener(this);
    }

    @Override
    public void onReceivedMessage(List<Message> messages) {
        messageAdapter.addMessages(messages);
        scrollToBottomDelay(200);
    }

    @Override
    public void onSendMessage(Message message) {
        messageAdapter.addMessage(message);
        scrollToBottom();
    }

    @Override
    public void onMessageStatusChanged(Message message) {
        messageAdapter.onMessageStatusChanged(message);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.et_message) {
            llAction.setVisibility(View.GONE);
            scrollToBottomDelay(200);
        } else if (id == R.id.tv_send) {
            checkInput();
        } else if (id == R.id.iv_more) {
            KeyboardUtils.hideSoftInput(getActivity());
            etMessage.clearFocus();
            if (llAction.getVisibility() != View.VISIBLE) {
                llAction.setVisibility(View.VISIBLE);
                scrollToBottomDelay(200);
            } else {
                llAction.setVisibility(View.GONE);
            }
        } else if (id == R.id.ll_camera) {
            checkCameraPermission();
        } else if (id == R.id.ll_photo) {
            checkStoragePermission();
        }
    }

    /**
     * 校验输入内容
     */
    private void checkInput() {
        if (etMessage.length() > 0) {
            IM.getInstance().sendTextMessage(account, type, etMessage.getText().toString());
            etMessage.setText("");
        }
    }

    /**
     * 延时滚动到消息列表底部
     *
     * @param millis 延时毫秒
     */
    private void scrollToBottomDelay(long millis) {
        rvMessage.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollToBottom();
            }
        }, millis);
    }

    /**
     * 滚动到消息列表底部
     */
    private void scrollToBottom() {
        linearLayoutManager.scrollToPosition(messageAdapter.getItemCount() - 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //拍照回调
            case REQUEST_CAMERA:
                if (resultCode == RESULT_OK) {
                    if (tempImageFile != null && tempImageFile.exists()) {
                        compressImage(tempImageFile);
                    }
                }
                break;
            //选择图片回调
            case REQUEST_PICK:
                if (resultCode == RESULT_OK) {
                    List<String> list = Matisse.obtainPathResult(data);
                    if (list != null && !list.isEmpty()) {
                        compressImage(new File(list.get(0)));
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 检查相机权限
     */
    private void checkCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.Group.CAMERA)
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {

                        }
                    })
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            takePhoto();
                        }
                    })
                    .start();
        } else {
            takePhoto();
        }
    }

    /**
     * 调用系统相机拍照
     */
    private void takePhoto() {
        tempImageFile = new File(getActivity().getExternalCacheDir(), String.valueOf(System.currentTimeMillis()));
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getFileUri(tempImageFile));
        startActivityForResult(openCameraIntent, REQUEST_CAMERA);
        llAction.setVisibility(View.GONE);
    }

    /**
     * 检查文件系统权限
     */
    private void checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.Group.STORAGE)
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {

                        }
                    })
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            pickImage();
                        }
                    })
                    .start();
        } else {
            pickImage();
        }
    }

    /**
     * 选择图片
     */
    private void pickImage() {
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .countable(true)
                .maxSelectable(1)
                .theme(R.style.Matisse_Zhihu)
                .imageEngine(new Glide4Engine())
                .forResult(REQUEST_PICK);
        llAction.setVisibility(View.GONE);
    }

    /**
     * File转Uri
     *
     * @param file 文件
     * @return Uri
     */
    private Uri getFileUri(File file) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            uri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".file.path.share", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     * 压缩图片
     *
     * @param file 原文件
     */
    private void compressImage(final File file) {
        Luban.with(getActivity())
                .load(file)
                .ignoreBy(100)
                .setTargetDir(getActivity().getExternalCacheDir().getPath())
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        return file.getName() + "_compress";
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        IM.getInstance().sendImageMessage(account, type, file);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
                .launch();
    }

}