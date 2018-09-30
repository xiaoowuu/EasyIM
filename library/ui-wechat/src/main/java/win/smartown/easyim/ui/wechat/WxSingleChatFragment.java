package win.smartown.easyim.ui.wechat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import win.smartown.easyim.im.base.IMService;
import win.smartown.easyim.im.base.Message;
import win.smartown.easyim.ui.base.SingleChatFragment;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 16:21
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class WxSingleChatFragment extends SingleChatFragment implements View.OnClickListener {

    private RecyclerView rvMessage;
    private LinearLayoutManager linearLayoutManager;
    private EditText etMessage;
    private Button btEmotion;
    private Button btMore;
    private Button btSend;
    private WxMessageAdapter messageAdapter;

    public static WxSingleChatFragment newInstance(String account) {
        Bundle args = new Bundle();
        args.putString(SingleChatFragment.ACCOUNT, account);
        WxSingleChatFragment fragment = new WxSingleChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_wx_single_chat;
    }

    @Override
    protected void initView(@NonNull View view) {
        rvMessage = view.findViewById(R.id.rv_message);
        etMessage = view.findViewById(R.id.et_message);
        btEmotion = view.findViewById(R.id.bt_emotion);
        btMore = view.findViewById(R.id.bt_more);
        btSend = view.findViewById(R.id.bt_send);

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
                    btMore.setVisibility(View.GONE);
                    btSend.setVisibility(View.VISIBLE);
                } else {
                    btMore.setVisibility(View.VISIBLE);
                    btSend.setVisibility(View.GONE);
                }
            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMessage.setLayoutManager(linearLayoutManager);
        messageAdapter = new WxMessageAdapter();
        rvMessage.setAdapter(messageAdapter);
        btSend.setOnClickListener(this);}
    @Override
    public void onReceivedMessage(List<Message> messages) {
        messageAdapter.addMessages(messages);
    }

    @Override
    public void onSendMessage(Message message) {
        messageAdapter.addMessage(message);
        linearLayoutManager.scrollToPosition(messageAdapter.getItemCount());
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
        if (id == R.id.bt_send) {
            checkInput();
        }
    }

    private void checkInput() {
        if (etMessage.length() > 0) {
            IMService.getInstance().sendMessage(account, etMessage.getText().toString());
            etMessage.setText("");
        }
    }

}
