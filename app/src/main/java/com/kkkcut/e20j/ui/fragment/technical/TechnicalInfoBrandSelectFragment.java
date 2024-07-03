package com.kkkcut.e20j.ui.fragment.technical;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kkkcut.e20j.DbBean.technical.DataManufacturer;
import com.kkkcut.e20j.adapter.TechnicalInfoBradAdapter;
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar;
import com.kkkcut.e20j.customView.indexlib.suspension.SuspensionDecoration;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.us.R;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class TechnicalInfoBrandSelectFragment extends BaseBackFragment {
    public static final String TAG = "TechnicalInfoBrandSelectFragment";

    EditText etSearch;

    IndexBar indexBar;
    private TechnicalInfoBradAdapter mAdapter;
    private SuspensionDecoration mDecoration;

    RecyclerView rvCategoryList;

    TextView tvSideBarHint;
    private List<DataManufacturer> mDatas1 = new ArrayList();
    private List<DataManufacturer> tempData = new ArrayList();

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyselect_child;
    }

    public static TechnicalInfoBrandSelectFragment newInstance() {
        return new TechnicalInfoBrandSelectFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
        getManufacturers();
    }

    private void initView() {
        this.etSearch.setHint(getString(R.string.manufacturer));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.rvCategoryList.setLayoutManager(linearLayoutManager);
        TechnicalInfoBradAdapter technicalInfoBradAdapter = new TechnicalInfoBradAdapter(getContext());
        this.mAdapter = technicalInfoBradAdapter;
        this.rvCategoryList.setAdapter(technicalInfoBradAdapter);
        SuspensionDecoration suspensionDecoration = new SuspensionDecoration(getContext());
        this.mDecoration = suspensionDecoration;
        this.rvCategoryList.addItemDecoration(suspensionDecoration);
        this.rvCategoryList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.indexBar.setmPressedShowTextView(this.tvSideBarHint).setNeedRealIndex(true).setmLayoutManager(linearLayoutManager);
        this.mAdapter.setOnKeySelectItemClickListener(new TechnicalInfoBradAdapter.OnKeySelectItemClickListener() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoBrandSelectFragment$$ExternalSyntheticLambda0
            @Override // com.kkkcut.e20j.adapter.TechnicalInfoBradAdapter.OnKeySelectItemClickListener
            public final void onItemClick(int i) {
                TechnicalInfoBrandSelectFragment.this.m71x9c14395e(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoBrandSelectFragment, reason: not valid java name */
    public /* synthetic */ void m71x9c14395e(int i) {
        DataManufacturer dataManufacturer = this.tempData.get(i);
        String manufacturerName = dataManufacturer.getManufacturerName();
        if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(dataManufacturer.getManufacturerNameCN())) {
            manufacturerName = dataManufacturer.getManufacturerNameCN();
        }
        goModel(this.tempData.get(i).getManufacturerId(), manufacturerName);
    }

    private void getManufacturers() {
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoBrandSelectFragment$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List technicalInfoManufacturers;
                technicalInfoManufacturers = KeyInfoDaoManager.getInstance().getTechnicalInfoManufacturers();
                return technicalInfoManufacturers;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoBrandSelectFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TechnicalInfoBrandSelectFragment.this.m70x814ceec5((List) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getManufacturers$2$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoBrandSelectFragment, reason: not valid java name */
    public /* synthetic */ void m70x814ceec5(List list) throws Exception {
        this.tempData = list;
        this.mDatas1 = list;
        setData(list);
    }

    public void setData(List<DataManufacturer> list) {
        this.mAdapter.setDatas(list);
        this.indexBar.setmSourceDatas(list).invalidate();
        this.mDecoration.setmDatas(list);
    }

    public void goModel(int i, String str) {
        start(TechnicalInfoModelSelectFragment.newInstance(i, str));
        hideSoftInput();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void afterTextChanged(Editable editable) {
        String manufacturerName;
        this.tempData = new ArrayList();
        for (int i = 0; i < this.mDatas1.size(); i++) {
            DataManufacturer dataManufacturer = this.mDatas1.get(i);
            if (MachineInfo.isChineseMachine()) {
                manufacturerName = dataManufacturer.getManufacturerNameCN();
            } else {
                manufacturerName = dataManufacturer.getManufacturerName();
            }
            if (!TextUtils.isEmpty(manufacturerName) && manufacturerName.toLowerCase().startsWith(editable.toString().toLowerCase())) {
                this.tempData.add(dataManufacturer);
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
        return getString(R.string.technical_information);
    }
}
