package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import com.kkkcut.e20j.DbBean.userDB.JpushMsg;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class MessageDetailFragment extends BaseBackFragment {
    private static final String JPUSH_MSG = "jpushMsg";

    @BindView(R.id.tv_content)
    TextView tvContent;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_message_detail;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static MessageDetailFragment newInstance(JpushMsg jpushMsg) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(JPUSH_MSG, jpushMsg);
        MessageDetailFragment messageDetailFragment = new MessageDetailFragment();
        messageDetailFragment.setArguments(bundle);
        return messageDetailFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        JpushMsg jpushMsg = (JpushMsg) getArguments().getParcelable(JPUSH_MSG);
        this.tvTitle.setText(jpushMsg.getTitle());
        this.tvContent.setText(jpushMsg.getContent());
    }
}
