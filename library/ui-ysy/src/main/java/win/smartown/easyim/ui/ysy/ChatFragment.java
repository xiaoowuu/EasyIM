package win.smartown.easyim.ui.ysy;

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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.FileProvider;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;
import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.base.BaseChatFragment;

import static android.app.Activity.RESULT_OK;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:21
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class ChatFragment extends BaseChatFragment implements View.OnClickListener {

    private static final int REQUEST_CAMERA = 11;
    private static final int REQUEST_PICK = 12;
    private RecyclerView rvMessage;
    private LinearLayoutManager linearLayoutManager;
    private EditText etMessage;
    private ImageView ivMore;
    private TextView tvSend;
    private LinearLayout llAction;
    private MessageAdapter messageAdapter;

    private File tempImageFile;

    public static ChatFragment newInstance(String account, int type) {
        Bundle args = new Bundle();
        args.putString(BaseChatFragment.ACCOUNT, account);
        args.putInt(BaseChatFragment.TYPE, type);
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initView(@NonNull View view) {
        rvMessage = view.findViewById(R.id.rv_message);
        etMessage = view.findViewById(R.id.et_message);
        ivMore = view.findViewById(R.id.iv_more);
        tvSend = view.findViewById(R.id.tv_send);
        llAction = view.findViewById(R.id.ll_action);

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
        messageAdapter = new MessageAdapter();
        rvMessage.setAdapter(messageAdapter);
        etMessage.setOnClickListener(this);
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

    private void checkInput() {
        if (etMessage.length() > 0) {
            IM.getInstance().sendTextMessage(account, type, etMessage.getText().toString());
            etMessage.setText("");
        }
    }

    private void scrollToBottomDelay(long millis) {
        rvMessage.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollToBottom();
            }
        }, millis);
    }

    private void scrollToBottom() {
        linearLayoutManager.scrollToPosition(messageAdapter.getItemCount() - 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (resultCode == RESULT_OK) {
                    if (tempImageFile != null && tempImageFile.exists()) {
                        compressImage(getFileUri(tempImageFile));
                    }
                }
                break;
            case REQUEST_PICK:
                if (resultCode == RESULT_OK) {
                    try {
                        compressImage(data.getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

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

    private void takePhoto() {
        tempImageFile = new File(getActivity().getExternalCacheDir(), String.valueOf(System.currentTimeMillis()));
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getFileUri(tempImageFile));
        startActivityForResult(openCameraIntent, REQUEST_CAMERA);
    }

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

    private void pickImage() {
        tempImageFile = new File(getActivity().getExternalCacheDir(), String.valueOf(System.currentTimeMillis()));
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_PICK);
    }

    private Uri getFileUri(File file) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            uri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".file.path.share", tempImageFile);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     * 压缩图片
     *
     * @param uri 原文件
     */
    private void compressImage(Uri uri) {
        final File compressFile = new File(getActivity().getExternalCacheDir(), String.valueOf(System.currentTimeMillis()));
        Luban.with(getActivity())
                .load(uri)
                .ignoreBy(100)
                .setTargetDir(compressFile.getParent())
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        return compressFile.getName();
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