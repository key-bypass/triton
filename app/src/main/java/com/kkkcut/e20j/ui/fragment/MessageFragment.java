package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.DbBean.userDB.JpushMsg;
import com.kkkcut.e20j.adapter.MessageAdapter;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.OperatorRemindDialog;
import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/* loaded from: classes.dex */
public class MessageFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {
    private MessageAdapter messageAdapter;

    RecyclerView rvMessage;

    TextView tvRemind;
    private String[] usageNoticeCN = {"机器使用须知", "1.本机只可读，切导电钥匙！（不导电钥匙例如，大众迈腾塑料钥匙，路虎沃尔沃铝制钥匙氧化之后）\n2.请尽量使用原厂探针铣刀，副厂未经验证探针有可能会损坏夹具，机器，断针，读，切不准！\n3.请务必保留原厂泡沫箱！！\n4.本机禁止倒置！倒置可能导致铜屑损坏本机电路！\n5.旋转S1夹具，请先关闭S1-D辅助挡块！"};

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_message;
    }

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.messageAdapter = new MessageAdapter();
        this.rvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rvMessage.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.rvMessage.setAdapter(this.messageAdapter);
        this.messageAdapter.setOnItemClickListener(this);
        getMessageFromDb();
        if (MachineInfo.isE9Standard(getContext())) {
            return;
        }
        showOperatorRemind(1);
    }

    private void showOperatorRemind(int i) {
        OperatorRemindDialog operatorRemindDialog = new OperatorRemindDialog(getContext());
        operatorRemindDialog.show();
        operatorRemindDialog.setRemindText(getString(R.string.caution));
        operatorRemindDialog.setType(i);
        operatorRemindDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    private void getMessageFromDb() {
        addDisposable(Observable.fromCallable(new Callable<List>() {
            @Override
            public List call() throws Exception {
                return MessageFragment.this.m44x792c8295();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List>() {
            @Override
            public void accept(List list) throws Throwable {
                MessageFragment.this.m45x13cd4516(list);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getMessageFromDb$0$com-kkkcut-e20j-ui-fragment-MessageFragment, reason: not valid java name */
    public /* synthetic */ List m44x792c8295() throws Exception {
        return UserDataDaoManager.getInstance(getContext()).getJpushMsgs();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getMessageFromDb$1$com-kkkcut-e20j-ui-fragment-MessageFragment, reason: not valid java name */
    public /* synthetic */ void m45x13cd4516(List list) throws Exception {
        if (list == null) {
            list = new ArrayList();
        }
        if (MachineInfo.isChineseMachine()) {
            JpushMsg jpushMsg = new JpushMsg();
            jpushMsg.setTitle(this.usageNoticeCN[0]);
            jpushMsg.setContent(this.usageNoticeCN[1]);
            list.add(0, jpushMsg);
            this.tvRemind.setVisibility(8);
        } else {
            this.tvRemind.setVisibility(0);
        }
        this.messageAdapter.setNewData(list);
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.message);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        start(MessageDetailFragment.newInstance((JpushMsg) baseQuickAdapter.getData().get(i)));
    }
}
