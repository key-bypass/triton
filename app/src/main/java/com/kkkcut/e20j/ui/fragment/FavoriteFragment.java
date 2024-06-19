package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.userDB.CollectionData;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.adapter.FavoriteAdapter;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.bean.gsonBean.GetTestData;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.us.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class FavoriteFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    private static final int PAGE_SIZE = 50;
    private static final String TYPE = "type";
    BaseQuickAdapter adapter;

    @BindView(R.id.bt_get_test_data)
    Button btGetTestData;

    @BindView(R.id.et_search)
    EditText etSearch;
    private int pageIndex;

    @BindView(R.id.rv_user_data)
    RecyclerView rvUserData;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_favorite;
    }

    static /* synthetic */ int access$008(FavoriteFragment favoriteFragment) {
        int i = favoriteFragment.pageIndex;
        favoriteFragment.pageIndex = i + 1;
        return i;
    }

    public static FavoriteFragment newInstance() {
        Bundle bundle = new Bundle();
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        favoriteFragment.setArguments(bundle);
        return favoriteFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvUserData.setLayoutManager(linearLayoutManager);
        this.rvUserData.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter();
        this.adapter = favoriteAdapter;
        this.rvUserData.setAdapter(favoriteAdapter);
        getDataList(0, 50, false);
        if (MyApplication.getInstance().isShowRealDepth()) {
            this.btGetTestData.setVisibility(0);
        }
        this.adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
            public void onLoadMoreRequested() {
                FavoriteFragment.access$008(FavoriteFragment.this);
                Log.d(FavoriteFragment.TAG, "onLoadMoreRequested() called" + FavoriteFragment.this.pageIndex);
                FavoriteFragment favoriteFragment = FavoriteFragment.this;
                favoriteFragment.getDataList(favoriteFragment.pageIndex, 50, false);
            }
        }, this.rvUserData);
        this.adapter.setOnItemChildClickListener(this);
        this.adapter.setOnItemClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDataList(final int i, final int i2, final boolean z) {
        final String trim = this.etSearch.getText().toString().trim();
        Disposable subscribe = Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FavoriteFragment.this.m26x9c541203(i, i2, trim);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                FavoriteFragment.this.m27x55cb9fa2(z, (List) obj);
            }
        });
        clearDisposable();
        addDisposable(subscribe);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getDataList$1$com-kkkcut-e20j-ui-fragment-FavoriteFragment, reason: not valid java name */
    public /* synthetic */ void m27x55cb9fa2(boolean z, List list) throws Exception {
        if (z) {
            this.adapter.setNewData(list);
        } else {
            this.adapter.addData((Collection) list);
        }
        if (list.size() < 50) {
            this.adapter.loadMoreEnd(false);
        } else {
            this.adapter.loadMoreComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getData, reason: merged with bridge method [inline-methods] */
    public List m26x9c541203(int i, int i2, String str) {
        return UserDataDaoManager.getInstance(getContext()).getCollection(i, i2, str);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        int id = view.getId();
        if (id == R.id.iv_delete) {
            UserDataDaoManager.getInstance(getContext()).deleteCollection((CollectionData) baseQuickAdapter.getData().get(i));
            baseQuickAdapter.remove(i);
        } else {
            if (id != R.id.iv_edit) {
                return;
            }
            showEditDialog(baseQuickAdapter.getData().get(i), (TextView) ((View) view.getParent().getParent()).findViewById(R.id.tv_remark));
        }
    }

    private void showEditDialog(final Object obj, final TextView textView) {
        final String remark = ((CollectionData) obj).getRemark();
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setTip(getString(R.string.enter_remarks));
        if (!TextUtils.isEmpty(remark)) {
            editDialog.setEditTextContent(remark);
        }
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.2
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public void onDialogButClick(String str) {
                if (TextUtils.isEmpty(str) || str.equals(remark)) {
                    return;
                }
                textView.setText(str);
                CollectionData collectionData = (CollectionData) obj;
                collectionData.setRemark(str);
                UserDataDaoManager.getInstance(FavoriteFragment.this.getContext()).collectKey(collectionData);
            }
        });
        editDialog.show();
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        getArguments().getInt(TYPE);
        start(KeyOperateFragment.newInstance(new GoOperatBean((CollectionData) baseQuickAdapter.getData().get(i))));
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        getArguments().getInt(TYPE);
        return getString(R.string.favorites);
    }

    @OnClick({R.id.bt_delete_all, R.id.bt_get_test_data})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != R.id.bt_delete_all) {
            if (id != R.id.bt_get_test_data) {
                return;
            }
            addDisposable(((Apis) RetrofitManager.getInstance().createApi(Apis.class)).getTestData(TUitls.getTestData(SPUtils.getString("series", "E219082007"))).subscribeOn(Schedulers.io()).flatMap(new Function<GetTestData, ObservableSource<GetTestData.DataListBean>>() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.8
                @Override // io.reactivex.functions.Function
                public ObservableSource<GetTestData.DataListBean> apply(GetTestData getTestData) throws Exception {
                    if (!"0".equals(getTestData.getCode())) {
                        Log.i(FavoriteFragment.TAG, "getCode: 0");
                        return null;
                    }
                    List<GetTestData.DataListBean> data_list = getTestData.getData_list();
                    if (data_list == null) {
                        Log.i(FavoriteFragment.TAG, "data_list: null");
                        return null;
                    }
                    return Observable.fromIterable(data_list);
                }
            }).map(new Function<GetTestData.DataListBean, CollectionData>() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.7
                @Override // io.reactivex.functions.Function
                public CollectionData apply(GetTestData.DataListBean dataListBean) throws Exception {
                    new Random().nextInt(Integer.MAX_VALUE);
                    CollectionData collectionData = new CollectionData();
                    collectionData.setTitle(dataListBean.getTitle());
                    collectionData.setToothCode(dataListBean.getTooth_Code());
                    collectionData.setBasicDataID(Integer.parseInt(dataListBean.getBasic_data_ID()));
                    collectionData.setCuts(dataListBean.getCuts());
                    return collectionData;
                }
            }).doOnNext(new Consumer<CollectionData>() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.6
                @Override // io.reactivex.functions.Consumer
                public void accept(CollectionData collectionData) throws Exception {
                    UserDataDaoManager.getInstance(FavoriteFragment.this.getContext()).collectKey(collectionData);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CollectionData>() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.4
                @Override // io.reactivex.functions.Consumer
                public void accept(CollectionData collectionData) throws Exception {
                    Log.i(FavoriteFragment.TAG, "accept: " + collectionData.getTitle());
                    FavoriteFragment.this.adapter.addData((BaseQuickAdapter) collectionData);
                }
            }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.5
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    Log.i(FavoriteFragment.TAG, "throwable: " + th.getMessage());
                }
            }));
        } else {
            RemindDialog remindDialog = new RemindDialog(getContext());
            remindDialog.setRemindMsg(getString(R.string.delete_all_recordes));
            remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.3
                @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        FavoriteFragment.this.deleteAll();
                    }
                }
            });
            remindDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteAll() {
        addDisposable(Observable.fromCallable(new Callable<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(UserDataDaoManager.getInstance(FavoriteFragment.this.getContext()).deleteAllCollections());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment.9
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    FavoriteFragment.this.adapter.setNewData(null);
                }
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED, value = {R.id.et_search})
    public void afterTextChanged(Editable editable) {
        this.pageIndex = 0;
        getDataList(0, 50, true);
    }
}
