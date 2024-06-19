package com.kkkcut.e20j.ui.fragment.search;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.search.MenuSummary;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.activity.BaseCustomKeyBoardActivity;
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment;
import com.kkkcut.e20j.us.R;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class SearchResultActivity extends BaseCustomKeyBoardActivity implements BaseQuickAdapter.OnItemClickListener {
    private static final String CONDITION = "condition";
    AdvSearchAdapter1 advSearchAdapter1;

    @BindView(R.id.rv_search_result_1)
    RecyclerView rvSearchResult1;

    @BindView(R.id.tv_title)
    TextView tvTile;

    private Context getContext() {
        return this;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected int getContentViewLayoutID() {
        return R.layout.fragment_advance_search_result;
    }

    public static void start(Context context, SearchCondition searchCondition) {
        Intent intent = new Intent(context, (Class<?>) SearchResultActivity.class);
        intent.putExtra(CONDITION, searchCondition);
        context.startActivity(intent);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void initViewsAndEvents() {
        if (getIntent().getParcelableExtra(CONDITION) == null) {
            return;
        }
        this.tvTile.setText(getConditionStr((SearchCondition) getIntent().getParcelableExtra(CONDITION)));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvSearchResult1.setLayoutManager(linearLayoutManager);
        AdvSearchAdapter1 advSearchAdapter1 = new AdvSearchAdapter1();
        this.advSearchAdapter1 = advSearchAdapter1;
        advSearchAdapter1.setOnItemClickListener(this);
        this.rvSearchResult1.setAdapter(this.advSearchAdapter1);
        getSearchResult((SearchCondition) getIntent().getParcelableExtra(CONDITION));
    }

    private void getSearchResult(final SearchCondition searchCondition) {
        addDisposable(Observable.fromCallable(new Callable<List<MenuSummary>>() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultActivity.5
            @Override // java.util.concurrent.Callable
            public List<MenuSummary> call() throws Exception {
                return KeyInfoDaoManager.getInstance().advSearch(searchCondition);
            }
        }).map(new Function<List<MenuSummary>, List<AdvSearchResult>>() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultActivity.4
            @Override // io.reactivex.functions.Function
            public List<AdvSearchResult> apply(List<MenuSummary> list) throws Exception {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (MenuSummary menuSummary : list) {
                    if (linkedHashMap.containsKey(Integer.valueOf(menuSummary.getFK_KeyID()))) {
                        ((List) linkedHashMap.get(Integer.valueOf(menuSummary.getFK_KeyID()))).add(menuSummary);
                    } else {
                        ArrayList arrayList = new ArrayList();
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
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultActivity.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Disposable disposable) throws Exception {
                SearchResultActivity searchResultActivity = SearchResultActivity.this;
                searchResultActivity.showLoadingDialog(searchResultActivity.getString(R.string.waitting));
            }
        }).doFinally(new Action() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultActivity.2
            @Override // io.reactivex.functions.Action
            public void run() throws Exception {
                SearchResultActivity.this.dissmissLoadingDialog();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<AdvSearchResult>>() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchResultActivity.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<AdvSearchResult> list) throws Exception {
                Log.i(SearchResultActivity.TAG, "accept: " + list);
                SearchResultActivity.this.advSearchAdapter1.setNewData(list);
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

    @OnClick({R.id.tv_back})
    public void onClick(View view) {
        if (view.getId() != R.id.tv_back) {
            return;
        }
        finish();
    }
}
