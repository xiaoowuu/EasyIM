package win.smartown.easyim.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.HashMap;

import win.smartown.easyim.Constants;
import win.smartown.easyim.EasyIM;
import win.smartown.easyim.standard.IMConversationService;
import win.smartown.easyim.standard.R;
import win.smartown.easyim.ui.adapter.ConversationAdapter;
import win.smartown.easyim.ui.target.ConversationJumpTarget;

/**
 * Created by smartown on 2018/2/6 15:53.
 * <br>
 * Desc:
 * <br>
 */
public class ConversationFragment extends Fragment implements IMConversationService.ConversationChangedListener, ConversationAdapter.JumpHandler {

    private static final String EXTRA_JUMP_TARGET = "EXTRA_JUMP_TARGET";

    public static ConversationFragment newInstance(ConversationJumpTarget target) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_JUMP_TARGET, target);
        ConversationFragment fragment = new ConversationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View noDataView;
    private RecyclerView mRecyclerView;
    private EditText msgEditText;
    private IMConversationService imConversationService;
    private ConversationJumpTarget jumpTarget;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(EXTRA_JUMP_TARGET)) {
            jumpTarget = getArguments().getParcelable(EXTRA_JUMP_TARGET);
        }
        imConversationService = EasyIM.getImServiceFactory().createImConversationService();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, null);
        initView(view);
        return view;
    }

    @Override
    public void onDestroy() {
        if (imConversationService != null) {
            imConversationService.unRegisterConversationWatcher();
            imConversationService = null;
        }
        super.onDestroy();
    }

    private void initView(View view) {
        noDataView = view.findViewById(R.id.no_data);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        msgEditText = view.findViewById(R.id.msg);
        view.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (msgEditText.length() > 0) {
                    String[] data = msgEditText.getText().toString().split(",");
                    imConversationService.sendMsgTo(data[0], data[1]);
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (imConversationService != null) {
            ConversationAdapter conversationAdapter = imConversationService.getConversationAdapter();
            conversationAdapter.setJumpHandler(this);
            mRecyclerView.setAdapter(conversationAdapter);
            imConversationService.registerConversationWatcher(this);
            imConversationService.getConversations();
        }
    }

    @Override
    public void onConversationChanged(int count) {
        noDataView.setVisibility(count > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void jumpToP2P(String jsonString) {
        if (jumpTarget != null) {
            try {
                Intent intent = new Intent(getActivity(), Class.forName(jumpTarget.getP2pActivityClass()));
                intent.putExtra(Constants.CONVERSATION_CHAT_ROOM_PARAMS, jsonString);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void jumpToTeam(String jsonString) {
        if (jumpTarget != null) {
            try {
                Intent intent = new Intent(getActivity(), Class.forName(jumpTarget.getTeamActivityClass()));
                intent.putExtra(Constants.CONVERSATION_CHAT_ROOM_PARAMS, jsonString);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}