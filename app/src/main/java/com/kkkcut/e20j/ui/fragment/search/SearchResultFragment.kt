package com.kkkcut.e20j.ui.fragment.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.search.MenuSummary;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment;
import com.kkkcut.e20j.us.R;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class SearchResultFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {
    private static final String CONDITION = "condition";
    AdvSearchAdapter1 advSearchAdapter1;

    RecyclerView rvSearchResult1;

    TextView tvTile;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_advance_search_result;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static SearchResultFragment newInstance(SearchCondition searchCondition) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CONDITION, searchCondition);
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        searchResultFragment.setArguments(bundle);
        return searchResultFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        if (getArguments() == null) {
            return;
        }
        this.tvTile.setText(getConditionStr((SearchCondition) getArguments().getParcelable(CONDITION)));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvSearchResult1.setLayoutManager(linearLayoutManager);
        AdvSearchAdapter1 advSearchAdapter1 = new AdvSearchAdapter1();
        this.advSearchAdapter1 = advSearchAdapter1;
        advSearchAdapter1.setOnItemClickListener(this);
        this.rvSearchResult1.setAdapter(this.advSearchAdapter1);
        getSearchResult((SearchCondition) getArguments().getParcelable(CONDITION));
    }

    private void getSearchResult(final SearchCondition searchCondition) {
        addDisposable(Observable.fromCallable(new Callable<List<MenuSummary>>() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultFragment.5
            @Override // java.util.concurrent.Callable
            public List<MenuSummary> call() throws Exception {
                return KeyInfoDaoManager.getInstance().advSearch(searchCondition);
            }
        }).map(new Function<List<MenuSummary>, List<AdvSearchResult>>() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultFragment.4
            @Override // io.reactivex.functions.Function
            public List<AdvSearchResult> apply(List<MenuSummary> list) throws Exception {
                var linkedHashMap = new LinkedHashMap<Integer, List<MenuSummary>>();
                for (MenuSummary menuSummary : list) {
                    if (linkedHashMap.containsKey(Integer.valueOf(menuSummary.getFK_KeyID()))) {
                        ((List) linkedHashMap.get(Integer.valueOf(menuSummary.getFK_KeyID()))).add(menuSummary);
                    } else {
                        var arrayList = new ArrayList<MenuSummary>();
                        arrayList.add(menuSummary);
                        linkedHashMap.put(Integer.valueOf(menuSummary.getFK_KeyID()), arrayList);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (Integer num : linkedHashMap.keySet()) {
                    List<MenuSummary> list2 = (List) linkedHashMap.get(num);
                    AdvSearchResult advSearchResult = new AdvSearchResult();
                    advSearchResult.setChildList(list2);
                    advSearchResult.setFK_KeyID(num.intValue());
                    if (list2 != null && list2.size() > 0) {
                        advSearchResult.setCuts(list2.get(0).getNofcuts());
                    }
                    arrayList2.add(advSearchResult);
                }
                return arrayList2;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultFragment.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Disposable disposable) throws Exception {
                SearchResultFragment searchResultFragment = SearchResultFragment.this;
                searchResultFragment.showLoadingDialog(searchResultFragment.getString(R.string.waitting));
            }
        }).doFinally(new Action() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultFragment.2
            @Override // io.reactivex.functions.Action
            public void run() throws Exception {
                SearchResultFragment.this.dismissLoadingDialog();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<AdvSearchResult>>() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<AdvSearchResult> list) throws Exception {
                Log.i(SearchResultFragment.TAG, "accept: " + list);
                SearchResultFragment.this.advSearchAdapter1.setNewData(list);
            }
        }));
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        int fK_KeyID = ((AdvSearchResult) baseQuickAdapter.getData().get(i)).getFK_KeyID();
        start(KeyOperateFragment.newInstance(new GoOperatBean(fK_KeyID, "ID:" + fK_KeyID)));
    }

    private String getConditionStr(SearchCondition searchCondition) {
        if (searchCondition == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(searchCondition.getKid())) {
            sb.append(getString(R.string.kid));
            sb.append(":");
            sb.append(searchCondition.getKid());
            sb.append(", ");
        }
        if (!TextUtils.isEmpty(searchCondition.getSilcaCard())) {
            sb.append(getString(R.string.silca_card));
            sb.append(":");
            sb.append(searchCondition.getSilcaCard());
            sb.append(", ");
        }
        if (!TextUtils.isEmpty(searchCondition.getSilcaSN())) {
            sb.append(getString(R.string.silca_sn));
            sb.append(":");
            sb.append(searchCondition.getSilcaSN());
            sb.append(", ");
        }
        if (!TextUtils.isEmpty(searchCondition.getKeyBlank())) {
            sb.append(getString(R.string.key_blank));
            sb.append(":");
            sb.append(searchCondition.getKeyBlank());
            sb.append(", ");
        }
        if (!TextUtils.isEmpty(searchCondition.getKeyBlankManu())) {
            sb.append(getString(R.string.key_manufacturer));
            sb.append(":");
            sb.append(searchCondition.getKeyBlankManu());
            sb.append(", ");
        }
        if (!TextUtils.isEmpty(searchCondition.getLockManu())) {
            sb.append(getString(R.string.lock_manufacturer));
            sb.append(":");
            sb.append(searchCondition.getLockManu());
            sb.append(", ");
        }
        if (!TextUtils.isEmpty(searchCondition.getLockSys())) {
            sb.append(getString(R.string.lock_system));
            sb.append(":");
            sb.append(searchCondition.getLockSys());
            sb.append(", ");
        }
        return sb.toString();
    }
}
