package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.BittingCode;
import com.kkkcut.e20j.adapter.CodeFindToothAdapter;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.NetUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.eventbus.InputFinishBean;
import com.kkkcut.e20j.bean.gsonBean.CodeFindToothRes;
import com.kkkcut.e20j.dao.ToothCodeDaoManager;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.activity.FrameActivity;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.GetUUID;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class CodeFindToothFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {
    public static final String ISN = "isn";
    public static final String KEY_ID = "keyID";
    public static final String SERIES = "series";
    private CodeFindToothAdapter adapter;

    @BindView(R.id.bt_search_online)
    Button btSearchOnline;

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.rv_tooth_list)
    RecyclerView rvToothList;
    private ToothCodeDaoManager toothCodeDaoManager;

    @BindView(R.id.tv_series)
    TextView tvSeries;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_code_find_tooth;
    }

    public static CodeFindToothFragment newInstance(int i, String str, String str2) {
        CodeFindToothFragment codeFindToothFragment = new CodeFindToothFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("keyID", i);
        bundle.putString("series", str);
        bundle.putString(ISN, str2);
        codeFindToothFragment.setArguments(bundle);
        return codeFindToothFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        String string = getArguments().getString("series");
        if (!TextUtils.isEmpty(string)) {
            this.tvSeries.setText(string);
        }
        this.toothCodeDaoManager = new ToothCodeDaoManager(getArguments().getInt("keyID"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvToothList.setLayoutManager(linearLayoutManager);
        CodeFindToothAdapter codeFindToothAdapter = new CodeFindToothAdapter();
        this.adapter = codeFindToothAdapter;
        codeFindToothAdapter.setOnItemClickListener(this);
        this.rvToothList.setAdapter(this.adapter);
        this.rvToothList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        ((FrameActivity) getActivity()).bindToEditor(this.etSearch, 0);
    }

    private void doSearch(final String str, final String str2) {
        addDisposable(Observable.fromCallable(new Callable<List<BittingCode>>() { // from class: com.kkkcut.e20j.ui.fragment.CodeFindToothFragment.3
            @Override // java.util.concurrent.Callable
            public List<BittingCode> call() throws Exception {
                return CodeFindToothFragment.this.toothCodeDaoManager.codeFindTooth(str, str2);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<BittingCode>>() { // from class: com.kkkcut.e20j.ui.fragment.CodeFindToothFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<BittingCode> list) throws Exception {
                if (list == null || list.size() == 0) {
                    CodeFindToothFragment.this.adapter.setNewData(new ArrayList());
                    ToastUtil.showToast(R.string.no_data_was_found);
                } else {
                    CodeFindToothFragment.this.adapter.setNewData(list);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.CodeFindToothFragment.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ToastUtil.showToast(R.string.no_data_was_found);
            }
        }));
    }

    @OnClick({R.id.bt_search_offline, R.id.bt_search_online})
    public void onViewClicked(View view) {
        String trim = this.etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtil.showToast(R.string.not_null);
            return;
        }
        String string = getArguments().getString(ISN);
        if (TextUtils.isEmpty(string)) {
            string = "0";
        }
        switch (view.getId()) {
            case R.id.bt_search_offline /* 2131361973 */:
                doSearch(trim, string);
                hideSoftInput();
                this.etSearch.clearFocus();
                return;
            case R.id.bt_search_online /* 2131361974 */:
                doSearchOnline(trim, string);
                hideSoftInput();
                this.etSearch.clearFocus();
                return;
            default:
                return;
        }
    }

    private void doSearchOnline(String str, String str2) {
        if (!NetUtil.isNetworkConnected(getContext())) {
            ToastUtil.showToast(R.string.network_unavailable);
            return;
        }
        showLoadingDialog(getString(R.string.waitting));
        if (TextUtils.isEmpty(str2)) {
            str2 = "0";
        }
        addDisposable(((Apis) RetrofitManager.getInstance().createApi(Apis.class)).getToothByCode(TUitls.codeFindToothParam(str, getArguments().getInt("keyID"), GetUUID.getUUID(), SPUtils.getString("series"), str2)).map(new Function<CodeFindToothRes, List<BittingCode>>() { // from class: com.kkkcut.e20j.ui.fragment.CodeFindToothFragment.7
            @Override // io.reactivex.functions.Function
            public List<BittingCode> apply(CodeFindToothRes codeFindToothRes) throws Exception {
                if (TextUtils.equals(codeFindToothRes.getCode(), "0")) {
                    ArrayList arrayList = new ArrayList();
                    List<CodeFindToothRes.DataBean> data = codeFindToothRes.getData();
                    if (data != null) {
                        for (CodeFindToothRes.DataBean dataBean : data) {
                            BittingCode bittingCode = new BittingCode();
                            bittingCode.setBitting(dataBean.getBitting());
                            bittingCode.setIsn(dataBean.getIsn());
                            arrayList.add(bittingCode);
                        }
                        return arrayList;
                    }
                    throw new Exception(CodeFindToothFragment.this.getString(R.string.no_data_was_found));
                }
                throw new Exception(codeFindToothRes.getMsg());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.kkkcut.e20j.ui.fragment.CodeFindToothFragment.6
            @Override // io.reactivex.functions.Action
            public void run() throws Exception {
                CodeFindToothFragment.this.dismissLoadingDialog();
            }
        }).subscribe(new Consumer<List<BittingCode>>() { // from class: com.kkkcut.e20j.ui.fragment.CodeFindToothFragment.4
            @Override // io.reactivex.functions.Consumer
            public void accept(List<BittingCode> list) throws Exception {
                if (list.size() == 0) {
                    ToastUtil.showToast(R.string.no_data_was_found);
                }
                CodeFindToothFragment.this.adapter.setNewData(list);
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.CodeFindToothFragment.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ToastUtil.showToast(th.getMessage());
            }
        }));
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        BittingCode bittingCode = (BittingCode) baseQuickAdapter.getData().get(i);
        if (bittingCode == null) {
            return;
        }
        char[] charArray = bittingCode.getBitting().toCharArray();
        String str = "";
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (i2 == charArray.length - 1) {
                str = str + charArray[i2] + ";";
            } else if ("-".contains(String.valueOf(charArray[i2]))) {
                str = str.substring(0, str.lastIndexOf(",")) + ";";
            } else {
                str = str + charArray[i2] + ",";
            }
        }
        EventBus.getDefault().post(new EventCenter(2, new InputFinishBean(false, str)));
        onBack();
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.code);
    }
}
