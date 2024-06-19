package com.kkkcut.e20j.ui.fragment.keyselect;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnTextChanged;
import com.kkkcut.e20j.DbBean.china.ModelChina;
import com.kkkcut.e20j.adapter.ModelChinaSelectAdapter;
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
public class ModelChinaSelectFragment extends BaseBackFragment {
    public static final String TAG = "TechnicalInfoBrandSelectFragment";

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.indexBar)
    IndexBar indexBar;
    private ModelChinaSelectAdapter mAdapter;
    private List<ModelChina> mDatas1;
    private SuspensionDecoration mDecoration;

    @BindView(R.id.rv_category_list)
    RecyclerView rvCategoryList;
    private List<ModelChina> tempData = new ArrayList();

    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyselect_child;
    }

    public static ModelChinaSelectFragment newInstance(int i, String str) {
        ModelChinaSelectFragment modelChinaSelectFragment = new ModelChinaSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("brandID", i);
        bundle.putString("title", str);
        modelChinaSelectFragment.setArguments(bundle);
        return modelChinaSelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
        getModels(getArguments().getInt("brandID"));
    }

    private void initView() {
        this.etSearch.setHint(getString(R.string.search_by_model));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.rvCategoryList.setLayoutManager(linearLayoutManager);
        ModelChinaSelectAdapter modelChinaSelectAdapter = new ModelChinaSelectAdapter(getContext());
        this.mAdapter = modelChinaSelectAdapter;
        this.rvCategoryList.setAdapter(modelChinaSelectAdapter);
        SuspensionDecoration suspensionDecoration = new SuspensionDecoration(getContext());
        this.mDecoration = suspensionDecoration;
        this.rvCategoryList.addItemDecoration(suspensionDecoration);
        this.rvCategoryList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.indexBar.setmPressedShowTextView(this.tvSideBarHint).setNeedRealIndex(true).setmLayoutManager(linearLayoutManager);
        this.mAdapter.setOnKeySelectItemClickListener(new ModelChinaSelectAdapter.OnKeySelectItemClickListener() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.ModelChinaSelectFragment.1
            @Override // com.kkkcut.e20j.adapter.ModelChinaSelectAdapter.OnKeySelectItemClickListener
            public void onItemClick(int i) {
                String string = ModelChinaSelectFragment.this.getArguments().getString("title");
                ModelChina modelChina = (ModelChina) ModelChinaSelectFragment.this.tempData.get(i);
                String modelName = modelChina.getModelName();
                if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(modelChina.getModelName_CN())) {
                    modelName = modelChina.getModelName_CN();
                }
                ModelChinaSelectFragment modelChinaSelectFragment = ModelChinaSelectFragment.this;
                modelChinaSelectFragment.goSeries(((ModelChina) modelChinaSelectFragment.tempData.get(i)).getModelID(), string + ">" + modelName);
            }
        });
    }

    public void goSeries(int i, String str) {
        start(SeriesChinaSelectFragment.newInstance(i, getArguments().getInt("category"), str));
        hideSoftInput();
    }

    private void getModels(final int i) {
        Log.i("TechnicalInfoBrandSelectFragment", "getModels: " + i);
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.ModelChinaSelectFragment$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List chinaModels;
                chinaModels = KeyInfoDaoManager.getInstance().getChinaModels(i);
                return chinaModels;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.ModelChinaSelectFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ModelChinaSelectFragment.this.m59x29258140((List) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getModels$1$com-kkkcut-e20j-ui-fragment-keyselect-ModelChinaSelectFragment, reason: not valid java name */
    public /* synthetic */ void m59x29258140(List list) throws Exception {
        Log.i("TechnicalInfoBrandSelectFragment", "getModels: " + list);
        if (list != null) {
            this.tempData = list;
            this.mDatas1 = list;
            setData(list);
        }
    }

    public void setData(List<ModelChina> list) {
        this.mAdapter.setDatas(list);
        this.indexBar.setmSourceDatas(list).invalidate();
        this.mDecoration.setmDatas(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED, value = {R.id.et_search})
    public void afterTextChanged(Editable editable) {
        String modelName;
        this.tempData = new ArrayList();
        for (int i = 0; i < this.mDatas1.size(); i++) {
            ModelChina modelChina = this.mDatas1.get(i);
            if (MachineInfo.isChineseMachine()) {
                modelName = modelChina.getModelName_CN();
            } else {
                modelName = modelChina.getModelName();
            }
            if (!TextUtils.isEmpty(modelName) && modelName.toLowerCase().startsWith(editable.toString().toLowerCase())) {
                this.tempData.add(modelChina);
            }
        }
        setData(this.tempData);
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
