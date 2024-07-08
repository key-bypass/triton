package com.kkkcut.e20j.ui.fragment.keyselect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.ModelSeries;
import com.kkkcut.e20j.DbBean.ModelYear;
import com.kkkcut.e20j.adapter.SeriesSelectAdapter;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import com.cutting.machine.MachineInfo;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class SeriesSelectFragment extends BaseBackFragment {
    private SeriesSelectAdapter mAdapter;
    private LinearLayoutManager manager;

    RecyclerView rvCategoryList;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void afterTextChanged(Editable editable) {
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyselect_series;
    }

    public static SeriesSelectFragment newInstance(int i, int i2, String str) {
        SeriesSelectFragment seriesSelectFragment = new SeriesSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("modelID", i);
        bundle.putInt("category", i2);
        bundle.putString("title", str);
        seriesSelectFragment.setArguments(bundle);
        return seriesSelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
        getModelYears(getArguments().getInt("modelID"));
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.manager = linearLayoutManager;
        this.rvCategoryList.setLayoutManager(linearLayoutManager);
        SeriesSelectAdapter seriesSelectAdapter = new SeriesSelectAdapter(getContext());
        this.mAdapter = seriesSelectAdapter;
        this.rvCategoryList.setAdapter(seriesSelectAdapter);
        this.rvCategoryList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.mAdapter.setOnItemChildClickListener(new SeriesSelectAdapter.OnItemChildClickListener() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesSelectFragment$$ExternalSyntheticLambda1
            @Override // com.kkkcut.e20j.adapter.SeriesSelectAdapter.OnItemChildClickListener
            public final void onItemChildClick(String str, ModelSeries modelSeries) {
                SeriesSelectFragment.this.m66xe5cd62fb(str, modelSeries);
            }
        });
        this.mAdapter.setOnItemClickListener(new SeriesSelectAdapter.OnItemClickListener() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesSelectFragment$$ExternalSyntheticLambda2
            @Override // com.kkkcut.e20j.adapter.SeriesSelectAdapter.OnItemClickListener
            public final void onItemClick(View view, int i, String str, int i2) {
                SeriesSelectFragment.this.m67xebd12e5a(view, i, str, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-keyselect-SeriesSelectFragment, reason: not valid java name */
    public /* synthetic */ void m66xe5cd62fb(String str, ModelSeries modelSeries) {
        start(KeyOperateFragment.newInstance(new GoOperatBean(modelSeries, getArguments().getString("title") + ">" + str)));
        hideSoftInput();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initView$1$com-kkkcut-e20j-ui-fragment-keyselect-SeriesSelectFragment, reason: not valid java name */
    public /* synthetic */ void m67xebd12e5a(View view, int i, String str, int i2) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_space_a);
        int childCount = linearLayout.getChildCount();
        if (childCount == 1) {
            showSeries(view, i, str, i2);
        } else {
            ((ImageView) view.findViewById(R.id.iv_arrow)).setImageResource(R.drawable.arrow_key_select);
            linearLayout.removeViews(1, childCount - 1);
        }
    }

    private void showSeries(final View view, final int i, final String str, final int i2) {
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesSelectFragment$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List modelSeries;
                modelSeries = KeyInfoDaoManager.getInstance().getModelSeries(i);
                return modelSeries;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe( obj -> {
                SeriesSelectFragment.this.m69x57bdac18(i2, view, str, (List) obj);

        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showSeries$4$com-kkkcut-e20j-ui-fragment-keyselect-SeriesSelectFragment, reason: not valid java name */
    public /* synthetic */ void m69x57bdac18(int i, View view, final String str, List list) throws Exception {
        if (list.size() == 0) {
            ToastUtil.showToast(R.string.no_data_was_found);
            return;
        }
        TopLinearSmoothScroller topLinearSmoothScroller = new TopLinearSmoothScroller(getActivity());
        topLinearSmoothScroller.setTargetPosition(i);
        this.manager.startSmoothScroll(topLinearSmoothScroller);
        ((ImageView) view.findViewById(R.id.iv_arrow)).setImageResource(R.drawable.arrow_bottom);
        int i2 = -1;
        LinearLayout linearLayout = view.findViewById(R.id.ll_space_a);
        Iterator it = list.iterator();
        View view2 = null;
        while (it.hasNext()) {
            final ModelSeries modelSeries = (ModelSeries) it.next();
            if (modelSeries.getFK_KeyID() != i2) {
                view2 = getLayoutInflater().inflate(R.layout.item_years_child_us, (ViewGroup) linearLayout, false);
                TextView textView = view2.findViewById(R.id.tv_title_id);
                if (MachineInfo.isE20Us(getContext())) {
                    textView.setText("Card");
                } else {
                    textView.setText(R.string.ic_card);
                }
                ((TextView) view2.findViewById(R.id.tv_ic_card)).setText(String.valueOf(modelSeries.getFK_KeyID()));
                ((TextView) view2.findViewById(R.id.tv_cuts)).setText(modelSeries.getCuts());
                ResUpdateUtils.showKeyImage(getContext(), modelSeries.getFK_KeyID(), (ImageView) view2.findViewById(R.id.iv_thumb));
                linearLayout.addView(view2);
                i2 = modelSeries.getFK_KeyID();
            }
            LinearLayout linearLayout2 = view2.findViewById(R.id.ll_series_container);
            View inflate = getLayoutInflater().inflate(R.layout.item_serie_us, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.tv_series)).setText(modelSeries.getCodeSeries());
            TextView textView2 = inflate.findViewById(R.id.tv_nick_name);
            if (textView2 != null) {
                textView2.setText(modelSeries.getName());
            }
            TextView textView3 = inflate.findViewById(R.id.tv_isn);
            final String isn = modelSeries.getISN();
            textView3.setText(isn);
            linearLayout2.addView(inflate);
            inflate.setOnClickListener( view3 -> {
                    SeriesSelectFragment.this.m68x51b9e0b9(str, modelSeries, isn, view3);

            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showSeries$3$com-kkkcut-e20j-ui-fragment-keyselect-SeriesSelectFragment, reason: not valid java name */
    public /* synthetic */ void m68x51b9e0b9(String str, ModelSeries modelSeries, String str2, View view) {
        String str3;
        String str4 = getArguments().getString("title") + ">" + str;
        if (MachineInfo.isE20Us(getContext())) {
            str3 = str4 + "(Card:" + modelSeries.getFK_KeyID() + ")";
        } else {
            str3 = str4 + "--" + getString(R.string.ic_card) + ":" + modelSeries.getFK_KeyID();
        }
        start(KeyOperateFragment.newInstance(new GoOperatBean(modelSeries, str3, str2)));
    }

    private void getModelYears(final int i) {
        addDisposable(Observable.fromCallable(() -> {
                List modelYears;
                modelYears = KeyInfoDaoManager.getInstance().getModelYears(i);
                return modelYears;

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(obj -> {
                SeriesSelectFragment.this.m65x3628abc1((List) obj);

        }));
    }

    private Bitmap byteToBitmap(byte[] bArr) {
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, null);
        }
        return null;
    }

    /* renamed from: setData, reason: merged with bridge method [inline-methods] */
    public void m65x3628abc1(List<ModelYear> list) {
        this.mAdapter.setDatas(list);
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
