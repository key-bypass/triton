package com.kkkcut.e20j.ui.fragment.setting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.Manufacturer;
import com.kkkcut.e20j.DbBean.userDB.ManufacturerHidden;
import com.kkkcut.e20j.adapter.BrandVisibilityAdapter;
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class BrandVisiBilitySettingFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {
    BrandVisibilityAdapter hideBrandAdapter;

    @BindView(R.id.indexBar_brand_hide)
    IndexBar indexBarBrandHide;

    @BindView(R.id.indexBar_brand_show)
    IndexBar indexBarBrandShow;

    @BindView(R.id.rv_brand_hidden)
    RecyclerView rvBrandHidden;

    @BindView(R.id.rv_brand_show)
    RecyclerView rvBrandShow;
    BrandVisibilityAdapter showBrandAdapter;

    @BindView(R.id.tvSideBarHint_brad_hide)
    TextView tvSideBarHintBradHide;

    @BindView(R.id.tvSideBarHint_brad_show)
    TextView tvSideBarHintBradShow;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_brand_visibility_settting;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static BrandVisiBilitySettingFragment newInstance() {
        Bundle bundle = new Bundle();
        BrandVisiBilitySettingFragment brandVisiBilitySettingFragment = new BrandVisiBilitySettingFragment();
        brandVisiBilitySettingFragment.setArguments(bundle);
        return brandVisiBilitySettingFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.showBrandAdapter = new BrandVisibilityAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvBrandShow.setLayoutManager(linearLayoutManager);
        this.rvBrandShow.setAdapter(this.showBrandAdapter);
        this.showBrandAdapter.setOnItemClickListener(this);
        this.rvBrandShow.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.indexBarBrandShow.setmPressedShowTextView(this.tvSideBarHintBradShow).setNeedRealIndex(true).setmLayoutManager(linearLayoutManager);
        this.hideBrandAdapter = new BrandVisibilityAdapter();
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(1);
        this.rvBrandHidden.setLayoutManager(linearLayoutManager2);
        this.rvBrandHidden.setAdapter(this.hideBrandAdapter);
        this.hideBrandAdapter.setOnItemClickListener(this);
        this.rvBrandHidden.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.indexBarBrandHide.setmPressedShowTextView(this.tvSideBarHintBradHide).setNeedRealIndex(true).setmLayoutManager(linearLayoutManager2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    public void onUserVisible() {
        getManufacturer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getManufacturer() {
        addDisposable(Observable.fromCallable(new Callable<List<Manufacturer>>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.5
            @Override // java.util.concurrent.Callable
            public List<Manufacturer> call() throws Exception {
                return UserDataDaoManager.getInstance(BrandVisiBilitySettingFragment.this.getContext()).getManufacturerHidden();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<List<Manufacturer>>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.4
            @Override // io.reactivex.functions.Consumer
            public void accept(List<Manufacturer> list) throws Exception {
                if (BrandVisiBilitySettingFragment.this.hideBrandAdapter != null) {
                    BrandVisiBilitySettingFragment.this.hideBrandAdapter.setNewData(list);
                    BrandVisiBilitySettingFragment.this.indexBarBrandHide.setmSourceDatas(list).invalidate();
                }
            }
        }).observeOn(Schedulers.io()).map(new Function<List<Manufacturer>, List<Integer>>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.3
            @Override // io.reactivex.functions.Function
            public List<Integer> apply(List<Manufacturer> list) throws Exception {
                ArrayList arrayList = new ArrayList();
                Iterator<Manufacturer> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(it.next().getManufacturerId()));
                }
                return arrayList;
            }
        }).map(new Function<List<Integer>, List<Manufacturer>>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.2
            @Override // io.reactivex.functions.Function
            public List<Manufacturer> apply(List<Integer> list) throws Exception {
                Log.i(BrandVisiBilitySettingFragment.TAG, "Thread3: " + Thread.currentThread().getName());
                return KeyInfoDaoManager.getInstance().getManufacturersExceptKeys(list);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Manufacturer>>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<Manufacturer> list) throws Exception {
                if (BrandVisiBilitySettingFragment.this.showBrandAdapter != null) {
                    BrandVisiBilitySettingFragment.this.showBrandAdapter.setNewData(list);
                    BrandVisiBilitySettingFragment.this.indexBarBrandShow.setmSourceDatas(list).invalidate();
                }
            }
        }));
    }

    @OnClick({R.id.iv_hide, R.id.iv_show})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_hide) {
            hide();
        } else {
            if (id != R.id.iv_show) {
                return;
            }
            show();
        }
    }

    private void show() {
        List<Manufacturer> data = this.hideBrandAdapter.getData();
        ArrayList arrayList = new ArrayList();
        for (Manufacturer manufacturer : data) {
            if (manufacturer.isChecked()) {
                arrayList.add(new ManufacturerHidden(manufacturer));
            }
        }
        Observable.fromIterable(data).subscribeOn(Schedulers.io()).filter(new Predicate<Manufacturer>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.9
            @Override // io.reactivex.functions.Predicate
            public boolean test(Manufacturer manufacturer2) throws Exception {
                return manufacturer2.isChecked();
            }
        }).map(new Function<Manufacturer, ManufacturerHidden>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.8
            @Override // io.reactivex.functions.Function
            public ManufacturerHidden apply(Manufacturer manufacturer2) throws Exception {
                return new ManufacturerHidden(manufacturer2);
            }
        }).doOnNext(new Consumer<ManufacturerHidden>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.7
            @Override // io.reactivex.functions.Consumer
            public void accept(ManufacturerHidden manufacturerHidden) throws Exception {
                UserDataDaoManager.getInstance(BrandVisiBilitySettingFragment.this.getContext()).showManufacturer(manufacturerHidden);
            }
        }).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.6
            @Override // io.reactivex.functions.Action
            public void run() throws Exception {
                BrandVisiBilitySettingFragment.this.getManufacturer();
            }
        }).subscribe();
    }

    private void hide() {
        List<Manufacturer> data = this.showBrandAdapter.getData();
        ArrayList arrayList = new ArrayList();
        for (Manufacturer manufacturer : data) {
            Log.i(TAG, "name: " + manufacturer.getManufacturerName() + "--ischecked:" + manufacturer.isChecked());
            if (manufacturer.isChecked()) {
                arrayList.add(new ManufacturerHidden(manufacturer));
            }
        }
        Observable.fromIterable(data).subscribeOn(Schedulers.io()).filter(new Predicate<Manufacturer>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.13
            @Override // io.reactivex.functions.Predicate
            public boolean test(Manufacturer manufacturer2) throws Exception {
                return manufacturer2.isChecked();
            }
        }).map(new Function<Manufacturer, ManufacturerHidden>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.12
            @Override // io.reactivex.functions.Function
            public ManufacturerHidden apply(Manufacturer manufacturer2) throws Exception {
                return new ManufacturerHidden(manufacturer2);
            }
        }).doOnNext(new Consumer<ManufacturerHidden>() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.11
            @Override // io.reactivex.functions.Consumer
            public void accept(ManufacturerHidden manufacturerHidden) throws Exception {
                UserDataDaoManager.getInstance(BrandVisiBilitySettingFragment.this.getContext()).hideManufacturer(manufacturerHidden);
            }
        }).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.10
            @Override // io.reactivex.functions.Action
            public void run() throws Exception {
                BrandVisiBilitySettingFragment.this.getManufacturer();
            }
        }).subscribe();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Manufacturer manufacturer = (Manufacturer) baseQuickAdapter.getData().get(i);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_brand_visibility);
        manufacturer.setChecked(!checkBox.isChecked());
        checkBox.performClick();
    }
}
