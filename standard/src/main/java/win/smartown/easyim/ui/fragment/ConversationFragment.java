package win.smartown.easyim.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import win.smartown.easyim.standard.IMConversationService;
import win.smartown.easyim.standard.R;

/**
 * Created by smartown on 2018/2/6 15:53.
 * <br>
 * Desc:
 * <br>
 */
public class ConversationFragment extends Fragment implements IMConversationService.ConversationChangedListener {

    private static final String EXTRA_CLASS = "EXTRA_CLASS";

    public static ConversationFragment newInstance(Class<? extends IMConversationService> aClass) {
        Bundle args = new Bundle();
        args.putString(EXTRA_CLASS, aClass.getName());
        ConversationFragment fragment = new ConversationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View noDataView;
    private RecyclerView mRecyclerView;
    private IMConversationService imConversationService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConversationService();
    }

    private void initConversationService() {
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(EXTRA_CLASS)) {
            try {
                String className = bundle.getString(EXTRA_CLASS);
                Class aClass = Class.forName(className);
                imConversationService = (IMConversationService) (aClass.newInstance());
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
        imConversationService.unRegisterConversationWatcher();
        super.onDestroy();
    }

    private void initView(View view) {
        noDataView = view.findViewById(R.id.no_data);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(imConversationService.getConversationAdapter());
        imConversationService.setConversationChangedListener(this);
        imConversationService.registerConversationWatcher();
        imConversationService.getConversations();
        imConversationService.sendMsgTo("test2", System.currentTimeMillis() + "");
    }

    @Override
    public void onConversationChanged(int count) {
        noDataView.setVisibility(count > 0 ? View.GONE : View.VISIBLE);
    }
}