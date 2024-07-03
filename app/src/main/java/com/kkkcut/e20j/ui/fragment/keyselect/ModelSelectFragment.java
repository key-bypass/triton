package com.kkkcut.e20j.ui.fragment.keyselect;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.DbBean.Model;
import com.kkkcut.e20j.adapter.ModelSelectAdapter;
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar;
import com.kkkcut.e20j.customView.indexlib.suspension.SuspensionDecoration;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/* loaded from: classes.dex */
public class ModelSelectFragment extends BaseBackFragment {
    public static final String TAG = "TechnicalInfoBrandSelectFragment";

    EditText etSearch;

    IndexBar indexBar;
    private ModelSelectAdapter mAdapter;
    private List<Model> mDatas1;
    private SuspensionDecoration mDecoration;

    RecyclerView rvCategoryList;
    private List<Model> tempData = new ArrayList();

    TextView tvSideBarHint;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyselect_child;
    }

    public static ModelSelectFragment newInstance(int i, int i2, String str) {
        ModelSelectFragment modelSelectFragment = new ModelSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("brandID", i2);
        bundle.putInt("category", i);
        bundle.putString("title", str);
        modelSelectFragment.setArguments(bundle);
        return modelSelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
        getModels(getArguments().getInt("brandID"));
    }

    private void initView() {
        if (getArguments().getInt("category") == 3 || getArguments().getInt("category") == 4 || getArguments().getInt("category") == 5) {
            this.etSearch.setHint(getString(R.string.please_input_the_key_name));
        } else {
            this.etSearch.setHint(getString(R.string.model));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.rvCategoryList.setLayoutManager(linearLayoutManager);
        ModelSelectAdapter modelSelectAdapter = new ModelSelectAdapter(getContext());
        this.mAdapter = modelSelectAdapter;
        this.rvCategoryList.setAdapter(modelSelectAdapter);
        SuspensionDecoration suspensionDecoration = new SuspensionDecoration(getContext());
        this.mDecoration = suspensionDecoration;
        this.rvCategoryList.addItemDecoration(suspensionDecoration);
        this.rvCategoryList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.indexBar.setmPressedShowTextView(this.tvSideBarHint).setNeedRealIndex(true).setmLayoutManager(linearLayoutManager);
        this.mAdapter.setOnKeySelectItemClickListener(new ModelSelectAdapter.OnKeySelectItemClickListener() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.ModelSelectFragment.1
            @Override // com.kkkcut.e20j.adapter.ModelSelectAdapter.OnKeySelectItemClickListener
            public void onItemClick(int i) {
                String string = ModelSelectFragment.this.getArguments().getString("title");
                Model model = (Model) ModelSelectFragment.this.tempData.get(i);
                String modelName = model.getModelName();
                if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(model.getModelName_CN())) {
                    modelName = model.getModelName_CN();
                }
                ModelSelectFragment modelSelectFragment = ModelSelectFragment.this;
                modelSelectFragment.goSeries(((Model) modelSelectFragment.tempData.get(i)).getModelID(), string + ">" + modelName);
            }
        });
    }

    public void goSeries(int i, String str) {
        start(SeriesSelectFragment.newInstance(i, getArguments().getInt("category"), str));
        hideSoftInput();
    }

    private void getModels(final int i) {
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.ModelSelectFragment$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List models;
                models = KeyInfoDaoManager.getInstance().getModels(i);
                return models;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(obj -> {
                ModelSelectFragment.this.m60x379ecaf((List) obj);

        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getModels$1$com-kkkcut-e20j-ui-fragment-keyselect-ModelSelectFragment, reason: not valid java name */
    public /* synthetic */ void m60x379ecaf(List list) throws Exception {
        this.tempData = list;
        this.mDatas1 = list;
        setData(list);
    }

    public void setData(List<Model> list) {
        this.mAdapter.setDatas(list);
        this.indexBar.setmSourceDatas(list).invalidate();
        this.mDecoration.setmDatas(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */

    public void afterTextChanged(Editable editable) {
        String modelName;
        this.tempData = new ArrayList();
        for (int i = 0; i < this.mDatas1.size(); i++) {
            Model model = this.mDatas1.get(i);
            if (MachineInfo.isChineseMachine()) {
                modelName = model.getModelName_CN();
            } else {
                modelName = model.getModelName();
            }
            if (!TextUtils.isEmpty(modelName) && modelName.toLowerCase().startsWith(editable.toString().toLowerCase())) {
                this.tempData.add(model);
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
