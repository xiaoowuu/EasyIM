package win.smartown.easyim.ui.ysy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.im.base.IM;
import win.smartown.easyim.ui.base.ActionHandler;
import win.smartown.easyim.ui.base.ConversationFragment;
import win.smartown.easyim.ui.base.UI;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.adapter.BaseAdapter;
import win.smartown.easyim.ui.ysy.adapter.ConversationAdapter;
import win.smartown.easyim.ui.ysy.dialog.CommonDialog;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 15:22
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class YSYConversationFragment extends ConversationFragment implements BaseAdapter.OnItemChildClickListener {

    private RecyclerView rvConversation;
    private View llEmpty;
    private ConversationAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ConversationAdapter();
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_conversation;
    }

    @Override
    protected void initView(@NonNull View view) {
        rvConversation = view.findViewById(R.id.rv_conversation);
        rvConversation.setAdapter(adapter);
        llEmpty = view.findViewById(R.id.ll_no_message);
    }

    @Override
    public void onConversationChanged(List<Conversation> conversations) {
        if (conversations.isEmpty()) {
            llEmpty.setVisibility(View.VISIBLE);
            rvConversation.setVisibility(View.GONE);
        } else {
            llEmpty.setVisibility(View.GONE);
            rvConversation.setVisibility(View.VISIBLE);
            adapter.setData(conversations);
        }
    }


    @Override
    public void onItemChildClick(View view, int position) {
        ActionHandler actionHandler = UI.getInstance().getActionHandler();
        if (actionHandler != null) {
            final Conversation conversation = adapter.getData().get(position);
            int id = view.getId();
            if (id == R.id.rl_content) {
                actionHandler.startChat(view.getContext(), conversation);
            } else if (id == R.id.tv_delete) {
                CommonDialog.newInstance()
                        .setContent(getString(R.string.sure_to_delete_conversation))
                        .setOnButtonClick(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (R.id.tv_right == v.getId()) {
                                    IM.getInstance().removeConversation(conversation);
                                }
                            }
                        })
                        .show(getChildFragmentManager(), "");
            }
        }
    }

}
