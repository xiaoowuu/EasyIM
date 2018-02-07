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

import win.smartown.easyim.EasyIM;
import win.smartown.easyim.standard.IMConversationService;
import win.smartown.easyim.standard.R;

/**
 * Created by smartown on 2018/2/6 15:53.
 * <br>
 * Desc:
 * <br>
 */
public class ConversationFragment extends Fragment implements IMConversationService.ConversationChangedListener {

    private View noDataView;
    private RecyclerView mRecyclerView;
    private EditText msgEditText;
    private IMConversationService imConversationService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imConversationService = EasyIM.getImServiceFactory().getImConversationService();
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
            EasyIM.getImServiceFactory().setImConversationService(null);

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
            mRecyclerView.setAdapter(imConversationService.getConversationAdapter());
            imConversationService.registerConversationWatcher(this);
            imConversationService.getConversations();
        }
    }

    @Override
    public void onConversationChanged(int count) {
        noDataView.setVisibility(count > 0 ? View.GONE : View.VISIBLE);
    }
}