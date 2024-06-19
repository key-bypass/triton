package com.kkkcut.e20j.ui.fragment.keyselect;

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
import com.kkkcut.e20j.DbBean.Manufacturer;
import com.kkkcut.e20j.adapter.BrandSelectAdapter;
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar;
import com.kkkcut.e20j.customView.indexlib.suspension.SuspensionDecoration;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import com.liying.core.MachineInfo;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class BrandSelectFragment extends BaseBackFragment {
    public static final String TAG = "TechnicalInfoBrandSelectFragment";

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.indexBar)
    IndexBar indexBar;
    private BrandSelectAdapter mAdapter;
    private SuspensionDecoration mDecoration;

    @BindView(R.id.rv_category_list)
    RecyclerView rvCategoryList;

    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;
    private List<Manufacturer> mDatas1 = new ArrayList();
    private List<Manufacturer> tempData = new ArrayList();

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyselect_child;
    }

    public static BrandSelectFragment newInstance(int i, int i2) {
        BrandSelectFragment brandSelectFragment = new BrandSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("category", i);
        bundle.putInt("tag", i2);
        brandSelectFragment.setArguments(bundle);
        return brandSelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
        getManufacturers(getArguments().getInt("category"));
    }

    private void initView() {
        this.etSearch.setHint(getString(R.string.manufacturer));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.rvCategoryList.setLayoutManager(linearLayoutManager);
        BrandSelectAdapter brandSelectAdapter = new BrandSelectAdapter(getContext());
        this.mAdapter = brandSelectAdapter;
        this.rvCategoryList.setAdapter(brandSelectAdapter);
        SuspensionDecoration suspensionDecoration = new SuspensionDecoration(getContext());
        this.mDecoration = suspensionDecoration;
        this.rvCategoryList.addItemDecoration(suspensionDecoration);
        this.rvCategoryList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.indexBar.setmPressedShowTextView(this.tvSideBarHint).setNeedRealIndex(true).setmLayoutManager(linearLayoutManager);
        this.mAdapter.setOnKeySelectItemClickListener(new BrandSelectAdapter.OnKeySelectItemClickListener() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment$$ExternalSyntheticLambda0
            @Override // com.kkkcut.e20j.adapter.BrandSelectAdapter.OnKeySelectItemClickListener
            public final void onItemClick(int i) {
                BrandSelectFragment.this.m56x7698229b(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-keyselect-BrandSelectFragment, reason: not valid java name */
    public /* synthetic */ void m56x7698229b(int i) {
        Manufacturer manufacturer = this.tempData.get(i);
        String manufacturerName = manufacturer.getManufacturerName();
        if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(manufacturer.getManufacturerNameCN())) {
            manufacturerName = manufacturer.getManufacturerNameCN();
        }
        goModel(this.tempData.get(i).getManufacturerId(), manufacturerName);
    }

    private void getManufacturers(final int i) {
        addDisposable(Observable.fromCallable(new Callable<List<Manufacturer>>() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment.4
            @Override // java.util.concurrent.Callable
            public List<Manufacturer> call() throws Exception {
                return UserDataDaoManager.getInstance(BrandSelectFragment.this.getContext()).getManufacturerHidden();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<List<Manufacturer>, List<Integer>>() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment.3
            @Override // io.reactivex.functions.Function
            public List<Integer> apply(List<Manufacturer> list) throws Exception {
                ArrayList arrayList = new ArrayList();
                Iterator<Manufacturer> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(it.next().getManufacturerId()));
                }
                return arrayList;
            }
        }).map(new Function<List<Integer>, List<Manufacturer>>() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment.2
            @Override // io.reactivex.functions.Function
            public List<Manufacturer> apply(List<Integer> list) throws Exception {
                return KeyInfoDaoManager.getInstance().getManufacturers(i, list);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Manufacturer>>() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<Manufacturer> list) throws Exception {
                BrandSelectFragment.this.tempData = list;
                BrandSelectFragment.this.mDatas1 = list;
                BrandSelectFragment.this.setData(list);
            }
        }));
    }

    public void setData(List<Manufacturer> list) {
        this.mAdapter.setDatas(list);
        this.indexBar.setmSourceDatas(list).invalidate();
        this.mDecoration.setmDatas(list);
    }

    public void goModel(int i, String str) {
        if (MachineInfo.isChineseMachine()) {
            start(ModelChinaSelectFragment.newInstance(i, str));
        } else {
            start(ModelSelectFragment.newInstance(getArguments().getInt("category"), i, str));
        }
        hideSoftInput();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED, value = {R.id.et_search})
    public void afterTextChanged(Editable editable) {
        String manufacturerName;
        this.tempData = new ArrayList();
        for (int i = 0; i < this.mDatas1.size(); i++) {
            Manufacturer manufacturer = this.mDatas1.get(i);
            if (MachineInfo.isChineseMachine()) {
                manufacturerName = manufacturer.getManufacturerNameCN();
            } else {
                manufacturerName = manufacturer.getManufacturerName();
            }
            if (!TextUtils.isEmpty(manufacturerName) && manufacturerName.toLowerCase().startsWith(editable.toString().toLowerCase())) {
                this.tempData.add(manufacturer);
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
        return getString(getArguments().getInt("tag"));
    }
}
