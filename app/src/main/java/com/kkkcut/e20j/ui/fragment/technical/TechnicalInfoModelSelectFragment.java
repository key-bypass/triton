package com.kkkcut.e20j.ui.fragment.technical;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnTextChanged;
import com.kkkcut.e20j.DbBean.technical.DataModel;
import com.kkkcut.e20j.adapter.TechnicalInfoModelAdapter;
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar;
import com.kkkcut.e20j.customView.indexlib.suspension.SuspensionDecoration;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import com.liying.core.MachineInfo;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class TechnicalInfoModelSelectFragment extends BaseBackFragment {
    public static final String TAG = "TechnicalInfoBrandSelectFragment";

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.indexBar)
    IndexBar indexBar;
    private TechnicalInfoModelAdapter mAdapter;
    private List<DataModel> mDatas1;
    private SuspensionDecoration mDecoration;

    @BindView(R.id.rv_category_list)
    RecyclerView rvCategoryList;
    private List<DataModel> tempData = new ArrayList();

    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyselect_child;
    }

    public static TechnicalInfoModelSelectFragment newInstance(int i, String str) {
        TechnicalInfoModelSelectFragment technicalInfoModelSelectFragment = new TechnicalInfoModelSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("brandID", i);
        bundle.putString("title", str);
        technicalInfoModelSelectFragment.setArguments(bundle);
        return technicalInfoModelSelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
        getModels(getArguments().getInt("brandID"));
    }

    private void initView() {
        this.etSearch.setHint(getString(R.string.model));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.rvCategoryList.setLayoutManager(linearLayoutManager);
        TechnicalInfoModelAdapter technicalInfoModelAdapter = new TechnicalInfoModelAdapter(getContext());
        this.mAdapter = technicalInfoModelAdapter;
        this.rvCategoryList.setAdapter(technicalInfoModelAdapter);
        SuspensionDecoration suspensionDecoration = new SuspensionDecoration(getContext());
        this.mDecoration = suspensionDecoration;
        this.rvCategoryList.addItemDecoration(suspensionDecoration);
        this.rvCategoryList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.indexBar.setmPressedShowTextView(this.tvSideBarHint).setNeedRealIndex(true).setmLayoutManager(linearLayoutManager);
        this.mAdapter.setOnKeySelectItemClickListener(new TechnicalInfoModelAdapter.OnKeySelectItemClickListener() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoModelSelectFragment.1
            @Override // com.kkkcut.e20j.adapter.TechnicalInfoModelAdapter.OnKeySelectItemClickListener
            public void onItemClick(int i) {
                String string = TechnicalInfoModelSelectFragment.this.getArguments().getString("title");
                DataModel dataModel = (DataModel) TechnicalInfoModelSelectFragment.this.tempData.get(i);
                String modelName = dataModel.getModelName();
                if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(dataModel.getModelName_CN())) {
                    modelName = dataModel.getModelName_CN();
                }
                TechnicalInfoModelSelectFragment technicalInfoModelSelectFragment = TechnicalInfoModelSelectFragment.this;
                technicalInfoModelSelectFragment.goSeries(((DataModel) technicalInfoModelSelectFragment.tempData.get(i)).getModelID(), string + ">" + modelName);
            }
        });
    }

    public void goSeries(int i, String str) {
        start(TechnicalInfoSeriesSelectFragment.newInstance(i, str));
        hideSoftInput();
    }

    private void getModels(final int i) {
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoModelSelectFragment$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List technicalInfoModels;
                technicalInfoModels = KeyInfoDaoManager.getInstance().getTechnicalInfoModels(i);
                return technicalInfoModels;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoModelSelectFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TechnicalInfoModelSelectFragment.this.m72x167b80ce((List) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getModels$1$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoModelSelectFragment, reason: not valid java name */
    public /* synthetic */ void m72x167b80ce(List list) throws Exception {
        if (list != null) {
            this.tempData = list;
            this.mDatas1 = list;
            setData(list);
        }
    }

    public void setData(List<DataModel> list) {
        this.mAdapter.setDatas(list);
        this.indexBar.setmSourceDatas(list).invalidate();
        this.mDecoration.setmDatas(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED, value = {R.id.et_search})
    public void afterTextChanged(Editable editable) {
        String modelName;
        this.tempData = new ArrayList();
        if (this.mDatas1 != null) {
            for (int i = 0; i < this.mDatas1.size(); i++) {
                DataModel dataModel = this.mDatas1.get(i);
                if (MachineInfo.isChineseMachine()) {
                    modelName = dataModel.getModelName_CN();
                } else {
                    modelName = dataModel.getModelName();
                }
                if (!TextUtils.isEmpty(modelName) && modelName.toLowerCase().startsWith(editable.toString().toLowerCase())) {
                    this.tempData.add(dataModel);
                }
            }
            setData(this.tempData);
        }
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        hideSoftInput();
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getArguments().getString("title");
    }
}
