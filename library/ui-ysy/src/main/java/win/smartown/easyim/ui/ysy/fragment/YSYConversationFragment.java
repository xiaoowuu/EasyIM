package win.smartown.easyim.ui.ysy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import win.smartown.easyim.im.base.Conversation;
import win.smartown.easyim.ui.base.ConversationFragment;
import win.smartown.easyim.ui.ysy.R;
import win.smartown.easyim.ui.ysy.adapter.ConversationAdapter;

/**
 * @author 雷小武
 * 创建时间：2018/9/29 15:22
 * 版权：成都智慧一生约科技有限公司
 * 类描述：
 */
public class YSYConversationFragment extends ConversationFragment {

    private RecyclerView rvConversation;
    private TextView tvNone;
    private ConversationAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ConversationAdapter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_conversation;
    }

    @Override
    protected void initView(@NonNull View view) {
        rvConversation = view.findViewById(R.id.rv_conversation);
        rvConversation.setAdapter(adapter);
        tvNone = view.findViewById(R.id.tv_none);
    }

    @Override
    public void onConversationChanged(List<Conversation> conversations) {
        if (conversations.isEmpty()) {
            tvNone.setVisibility(View.VISIBLE);
            rvConversation.setVisibility(View.GONE);
        } else {
            tvNone.setVisibility(View.GONE);
            rvConversation.setVisibility(View.VISIBLE);
            adapter.setData(conversations);
        }
    }
}
