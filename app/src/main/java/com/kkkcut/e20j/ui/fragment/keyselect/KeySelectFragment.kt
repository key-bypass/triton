package com.kkkcut.e20j.ui.fragment.keyselect;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.Manufacturer;
import com.kkkcut.e20j.DbBean.Model;
import com.kkkcut.e20j.adapter.KeySelectAdapter;
import com.kkkcut.e20j.base.BaseFragment;
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.us.R;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/* loaded from: classes.dex */
public class KeySelectFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    EditText etSearch;

    IndexBar indexBar;

    RecyclerView rvCategoryList;

    TextView tvSideBarHint;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyselect;
    }

    public static KeySelectFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("category", i);
        KeySelectFragment keySelectFragment = new KeySelectFragment();
        keySelectFragment.setArguments(bundle);
        return keySelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        this.rvCategoryList.setLayoutManager(linearLayoutManager);
        getManufacturers();
    }

    private void getManufacturers() {
        getArguments().getInt("category");
    }

    private void getModels(final int i) {
        addDisposable(Observable.fromCallable(() -> {
                List models;
                models = KeyInfoDaoManager.getInstance().getModels(i);
                return models;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(obj -> KeySelectFragment.this.m58xac7dfe5(obj)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getModels$1$com-kkkcut-e20j-ui-fragment-keyselect-KeySelectFragment, reason: not valid java name */
    public /* synthetic */ void m58xac7dfe5(List list) throws Exception {
        KeySelectAdapter keySelectAdapter = new KeySelectAdapter(list);
        keySelectAdapter.setOnItemClickListener(this);
        this.rvCategoryList.setAdapter(keySelectAdapter);
    }

    private void getModelYears(final int i) {
        addDisposable(Observable.fromCallable(() -> {
                List modelYears;
                modelYears = KeyInfoDaoManager.getInstance().getModelYears(i);
                return modelYears;

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(obj -> {
                KeySelectFragment.this.m57xd564af6a(obj);

        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getModelYears$3$com-kkkcut-e20j-ui-fragment-keyselect-KeySelectFragment, reason: not valid java name */
    public /* synthetic */ void m57xd564af6a(List list) throws Exception {
        KeySelectAdapter keySelectAdapter = new KeySelectAdapter(list);
        keySelectAdapter.setOnItemClickListener(this);
        this.rvCategoryList.setAdapter(keySelectAdapter);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        int itemViewType = baseQuickAdapter.getItemViewType(i);
        if (itemViewType == 0) {
            getModels(((Manufacturer) baseQuickAdapter.getData().get(i)).getManufacturerId());
        } else {
            if (itemViewType != 1) {
                return;
            }
            getModelYears(((Model) baseQuickAdapter.getData().get(i)).getModelID());
        }
    }
}
