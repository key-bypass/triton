package com.kkkcut.e20j.p005ui.fragment.technical;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.kkkcut.e20j.DbBean.technical.DataModelSeries;
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesYear;

import com.kkkcut.e20j.adapter.TechnicalSeriesAdapter;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class TechnicalInfoSeriesSelectFragment extends BaseBackFragment {
    private TechnicalSeriesAdapter mAdapter;

    @BindView(R.id.rv_category_list)
    RecyclerView rvCategoryList;

    @Override // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyselect_series;
    }

    public static TechnicalInfoSeriesSelectFragment newInstance(int i, String str) {
        TechnicalInfoSeriesSelectFragment technicalInfoSeriesSelectFragment = new TechnicalInfoSeriesSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("modelID", i);
        bundle.putString("title", str);
        technicalInfoSeriesSelectFragment.setArguments(bundle);
        return technicalInfoSeriesSelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
        getModelSeries(getArguments().getInt("modelID"));
    }

    private void initView() {
        this.rvCategoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        TechnicalSeriesAdapter technicalSeriesAdapter = new TechnicalSeriesAdapter(getContext());
        this.mAdapter = technicalSeriesAdapter;
        this.rvCategoryList.setAdapter(technicalSeriesAdapter);
        this.rvCategoryList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.mAdapter.setOnItemClickListener(new TechnicalSeriesAdapter.OnItemClickListener() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoSeriesSelectFragment$$ExternalSyntheticLambda1
            @Override // com.kkkcut.e20j.adapter.TechnicalSeriesAdapter.OnItemClickListener
            public final void onItemClick(View view, int i, String str) {
                TechnicalInfoSeriesSelectFragment.this.m80x6fd42498(view, i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoSeriesSelectFragment */
    public /* synthetic */ void m80x6fd42498(View view, int i, String str) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.container);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_arrow);
        int childCount = linearLayout.getChildCount();
        if (childCount == 1) {
            imageView.setImageResource(R.drawable.arrow_bottom);
            showSeriesYear(view, i, str);
        } else {
            imageView.setImageResource(R.drawable.arrow_key_select);
            linearLayout.removeViews(1, childCount - 1);
        }
    }

    private void getModelSeries(final int i) {
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoSeriesSelectFragment$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List technicalInfoModelSeries;
                technicalInfoModelSeries = KeyInfoDaoManager.getInstance().getTechnicalInfoModelSeries(i);
                return technicalInfoModelSeries;
            }
        }).subscribeOn(Schedulers.m398io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoSeriesSelectFragment$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TechnicalInfoSeriesSelectFragment.this.m79x3621488f((List) obj);
            }
        }));
    }

    private void showSeriesYear(final View view, final int i, final String str) {
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoSeriesSelectFragment$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List technicalInfoModelSeriesYear;
                technicalInfoModelSeriesYear = KeyInfoDaoManager.getInstance().getTechnicalInfoModelSeriesYear(i);
                return technicalInfoModelSeriesYear;
            }
        }).subscribeOn(Schedulers.m398io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoSeriesSelectFragment$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TechnicalInfoSeriesSelectFragment.this.m82x17da8d39(view, str, (List) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showSeriesYear$5$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoSeriesSelectFragment */
    public /* synthetic */ void m82x17da8d39(View view, final String str, List list) throws Exception {
        if (list.size() == 0) {
            ToastUtil.showToast(R.string.no_data_was_found);
            return;
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.container);
        View inflate = getLayoutInflater().inflate(R.layout.item_technical_info_child, (ViewGroup) linearLayout, false);
        linearLayout.addView(inflate);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final DataModelSeriesYear dataModelSeriesYear = (DataModelSeriesYear) it.next();
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.ll_series_container);
            View inflate2 = getLayoutInflater().inflate(R.layout.item_technical_info_years, (ViewGroup) null);
            ((TextView) inflate2.findViewById(R.id.tv_year)).setText(dataModelSeriesYear.getSeriesYearName());
            linearLayout2.addView(inflate2);
            inflate2.setOnClickListener(new View.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoSeriesSelectFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    TechnicalInfoSeriesSelectFragment.this.m81x50cea638(str, dataModelSeriesYear, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showSeriesYear$4$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoSeriesSelectFragment */
    public /* synthetic */ void m81x50cea638(String str, DataModelSeriesYear dataModelSeriesYear, View view) {
        String string = getArguments().getString("title");
        Log.i(TAG, "showSeriesYear: " + string);
        start(TechnicalInfoFragment.newInstance(dataModelSeriesYear.getModelSeriesYearID(), (string + ">" + str) + ">" + dataModelSeriesYear.getSeriesYearName()));
    }

    /* renamed from: setData, reason: merged with bridge method [inline-methods] */
    public void m79x3621488f(List<DataModelSeries> list) {
        this.mAdapter.setDatas(list);
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        hideSoftInput();
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getArguments().getString("title");
    }
}