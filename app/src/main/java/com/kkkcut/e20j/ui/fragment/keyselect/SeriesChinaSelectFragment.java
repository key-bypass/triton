package com.kkkcut.e20j.ui.fragment.keyselect;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.china.ModelSeriesChina;
import com.kkkcut.e20j.DbBean.china.ModelYearChina;
import com.kkkcut.e20j.adapter.SeriesChinaSelectAdapter;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class SeriesChinaSelectFragment extends BaseBackFragment {
    private SeriesChinaSelectAdapter mAdapter;

    @BindView(R.id.rv_category_list)
    RecyclerView rvCategoryList;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyselect_series;
    }

    public static SeriesChinaSelectFragment newInstance(int i, int i2, String str) {
        SeriesChinaSelectFragment seriesChinaSelectFragment = new SeriesChinaSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("modelID", i);
        bundle.putInt("category", i2);
        bundle.putString("title", str);
        seriesChinaSelectFragment.setArguments(bundle);
        return seriesChinaSelectFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initView();
        getModelYears(getArguments().getInt("modelID"));
    }

    private void initView() {
        this.rvCategoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        SeriesChinaSelectAdapter seriesChinaSelectAdapter = new SeriesChinaSelectAdapter(getContext());
        this.mAdapter = seriesChinaSelectAdapter;
        this.rvCategoryList.setAdapter(seriesChinaSelectAdapter);
        this.rvCategoryList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        this.mAdapter.setOnItemClickListener(new SeriesChinaSelectAdapter.OnItemClickListener() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesChinaSelectFragment$$ExternalSyntheticLambda1
            @Override // com.kkkcut.e20j.adapter.SeriesChinaSelectAdapter.OnItemClickListener
            public final void onItemClick(View view, int i, String str) {
                SeriesChinaSelectFragment.this.m62xa03c1a74(view, i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-keyselect-SeriesChinaSelectFragment, reason: not valid java name */
    public /* synthetic */ void m62xa03c1a74(View view, int i, String str) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_space_a);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_arrow);
        int childCount = linearLayout.getChildCount();
        if (childCount == 1) {
            imageView.setImageResource(R.drawable.arrow_bottom);
            showSeries(view, i, str);
        } else {
            imageView.setImageResource(R.drawable.arrow_key_select);
            linearLayout.removeViews(1, childCount - 1);
        }
    }

    private void getModelYears(final int i) {
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesChinaSelectFragment$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List chinaModelYears;
                chinaModelYears = KeyInfoDaoManager.getInstance().getChinaModelYears(i);
                return chinaModelYears;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesChinaSelectFragment$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SeriesChinaSelectFragment.this.m61x488f7d6a((List) obj);
            }
        }));
    }

    private void showSeries(final View view, final int i, final String str) {
        Log.d(TAG, "showSeries() called with: itemView = [" + view + "], yearID = [" + i + "], years = [" + str + "]");
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesChinaSelectFragment$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List chinaModelSeries;
                chinaModelSeries = KeyInfoDaoManager.getInstance().getChinaModelSeries(i);
                return chinaModelSeries;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesChinaSelectFragment$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SeriesChinaSelectFragment.this.m64xf54d7378(view, str, (List) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showSeries$5$com-kkkcut-e20j-ui-fragment-keyselect-SeriesChinaSelectFragment, reason: not valid java name */
    public /* synthetic */ void m64xf54d7378(View view, final String str, List list) throws Exception {
        if (list.size() == 0) {
            ToastUtil.showToast(R.string.no_data_was_found);
            return;
        }
        int i = -1;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_space_a);
        Iterator it = list.iterator();
        View view2 = null;
        while (it.hasNext()) {
            final ModelSeriesChina modelSeriesChina = (ModelSeriesChina) it.next();
            if (modelSeriesChina.getFK_KeyID() != i) {
                view2 = getLayoutInflater().inflate(R.layout.item_years_china_child, (ViewGroup) linearLayout, false);
                TextView textView = (TextView) view2.findViewById(R.id.tv_detail);
                String str2 = "";
                if (!TextUtils.isEmpty(modelSeriesChina.getName())) {
                    str2 = "" + modelSeriesChina.getName() + " ";
                }
                if (!TextUtils.isEmpty(modelSeriesChina.getCuts())) {
                    str2 = str2 + modelSeriesChina.getCuts() + "齿 ";
                }
                if (!TextUtils.isEmpty(modelSeriesChina.getKeyChinaNum())) {
                    str2 = str2 + modelSeriesChina.getKeyChinaNum() + "钥匙头";
                }
                textView.setText(str2);
                linearLayout.addView(view2);
                i = (int) modelSeriesChina.getFK_KeyID();
            }
            LinearLayout linearLayout2 = (LinearLayout) view2.findViewById(R.id.ll_series_container);
            View inflate = getLayoutInflater().inflate(R.layout.item_series_china, (ViewGroup) null);
            ResUpdateUtils.showKeyImage(getContext(), (int) modelSeriesChina.getFK_KeyID(), (ImageView) inflate.findViewById(R.id.iv_thumb));
            ((TextView) inflate.findViewById(R.id.tv_clamp)).setText(modelSeriesChina.getClampKeyBasicData().getClampNum() + " " + modelSeriesChina.getClampKeyBasicData().getClampSide());
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_align);
            if (modelSeriesChina.getKeyBasicData().getAlign() == 0) {
                textView2.setText(R.string.shoulder);
            } else {
                textView2.setText(R.string.tip);
            }
            linearLayout2.addView(inflate);
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesChinaSelectFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    SeriesChinaSelectFragment.this.m63xa78dfb77(str, modelSeriesChina, view3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showSeries$4$com-kkkcut-e20j-ui-fragment-keyselect-SeriesChinaSelectFragment, reason: not valid java name */
    public /* synthetic */ void m63xa78dfb77(String str, ModelSeriesChina modelSeriesChina, View view) {
        start(KeyOperateFragment.newInstance(new GoOperatBean(modelSeriesChina, (getArguments().getString("title") + ">" + str) + "--ID:" + modelSeriesChina.getFK_KeyID())));
    }

    /* renamed from: setData, reason: merged with bridge method [inline-methods] */
    public void m61x488f7d6a(List<ModelYearChina> list) {
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
