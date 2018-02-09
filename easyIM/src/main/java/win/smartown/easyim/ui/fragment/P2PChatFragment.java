package win.smartown.easyim.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import win.smartown.easyim.Constants;
import win.smartown.easyim.EasyIM;
import win.smartown.easyim.standard.IMP2PChatService;
import win.smartown.easyim.standard.R;

/**
 * Created by smartown on 2018/2/8 10:02.
 * <br>
 * Desc:
 * <br>
 */
public class P2PChatFragment extends Fragment {

    public static P2PChatFragment newInstance(String jsonString) {
        Bundle args = new Bundle();
        args.putString(Constants.CONVERSATION_CHAT_ROOM_PARAMS, jsonString);
        P2PChatFragment fragment = new P2PChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mRecyclerView;
    private EditText msgEditText;
    private IMP2PChatService chatService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(Constants.CONVERSATION_CHAT_ROOM_PARAMS)) {
            chatService = EasyIM.getImServiceFactory().createImP2PChatService(getArguments().getString(Constants.CONVERSATION_CHAT_ROOM_PARAMS));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_p2p, null);
        initView(view);
        return view;
    }

    @Override
    public void onDestroy() {
        chatService.observeReceiveMessage(false);
        super.onDestroy();
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        msgEditText = view.findViewById(R.id.msg);
        view.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (msgEditText.length() > 0) {
                    chatService.sendMsg(msgEditText.getText().toString());
                    msgEditText.setText("");
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(chatService.getChatMessageAdapter());
        chatService.observeReceiveMessage(true);
    }
}