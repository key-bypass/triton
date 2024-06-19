package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.search.BarCodeSearch;
import com.kkkcut.e20j.DbBean.search.CardsSystem;
import com.kkkcut.e20j.DbBean.search.ChinaNumSearch;
import com.kkkcut.e20j.DbBean.search.KeyBlankItemSearch;
import com.kkkcut.e20j.DbBean.search.UsaSearchExtItemBasicData;
import com.kkkcut.e20j.adapter.KeySearchAdapter;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.kkkcut.e20j.ui.activity.FrameActivity;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.liying.core.MachineInfo;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class SearchFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {
    private static final String TYPE = "TYPE";
    FrameActivity activity;

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.flag_bar_code)
    View flagBarCode;

    @BindView(R.id.flag_blitz_card)
    View flagBlitzCard;

    @BindView(R.id.flag_china_key)
    View flagChinaKey;

    @BindView(R.id.flag_dsd)
    View flagDsd;

    @BindView(R.id.flag_key_blank)
    View flagKeyBlank;

    @BindView(R.id.flag_key_id)
    View flagKeyId;

    @BindView(R.id.flag_lkp)
    View flagLkp;

    @BindView(R.id.flag_silca)
    View flagSilca;
    private KeySearchAdapter keySearchAdapter;

    @BindView(R.id.rl_bar_code)
    RelativeLayout rlBarCode;

    @BindView(R.id.rl_blitz_card)
    RelativeLayout rlBlitzCard;

    @BindView(R.id.rl_china_key_num)
    RelativeLayout rlChinaKeyNum;

    @BindView(R.id.rl_dsd)
    RelativeLayout rlDsd;

    @BindView(R.id.rl_key_blank)
    RelativeLayout rlKeyBlank;

    @BindView(R.id.rl_key_id)
    RelativeLayout rlKeyId;

    @BindView(R.id.rl_lkp)
    RelativeLayout rlLkp;

    @BindView(R.id.rl_silca)
    RelativeLayout rlSilca;

    @BindView(R.id.rv_result)
    RecyclerView rvResult;
    private SearchType searchType = SearchType.KEY_BLANK;

    @BindView(R.id.tv_bar_code)
    TextView tvBarCode;

    @BindView(R.id.tv_blitz_card)
    TextView tvBlitzCard;

    @BindView(R.id.tv_china_key_num)
    TextView tvChinaKeyNum;

    @BindView(R.id.tv_dsd)
    TextView tvDsd;

    @BindView(R.id.tv_key_blank)
    TextView tvKeyBlank;

    @BindView(R.id.tv_key_id)
    TextView tvKeyId;

    @BindView(R.id.tv_lkp)
    TextView tvLkp;

    @BindView(R.id.tv_silca)
    TextView tvSilca;

    /* loaded from: classes.dex */
    public enum SearchType {
        KEY_BLANK,
        KEY_ID,
        CHINA_KEY_NUM,
        BLITZ_CARD,
        DSD,
        LKP_DSD,
        SILCA_CARD,
        BAR_CODE
    }

    private boolean isShowRemind(int i) {
        return i == 872 || i == 1510 || i == 909 || i == 1309 || i == 1097 || i == 1373 || i == 1370 || i == 1407 || i == 998;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_search;
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public static SearchFragment newInstance(SearchType searchType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("TYPE", searchType);
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        KeySearchAdapter keySearchAdapter = new KeySearchAdapter();
        this.keySearchAdapter = keySearchAdapter;
        keySearchAdapter.setOnItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvResult.setAdapter(this.keySearchAdapter);
        this.rvResult.setLayoutManager(linearLayoutManager);
        FrameActivity frameActivity = (FrameActivity) getActivity();
        this.activity = frameActivity;
        frameActivity.bindToEditor(this.etSearch, 0);
        new Handler().postDelayed(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.1
            @Override // java.lang.Runnable
            public void run() {
                SearchFragment.this.etSearch.requestFocus();
            }
        }, 500L);
        if (MachineInfo.isE20Us(getContext())) {
            this.rlBlitzCard.setVisibility(0);
            this.rlLkp.setVisibility(0);
            this.rlBarCode.setVisibility(SPUtils.getBoolean("bar_code", true) ? 0 : 8);
            this.tvKeyId.setText("Card");
        } else if (MachineInfo.isChineseMachine()) {
            this.rlChinaKeyNum.setVisibility(0);
            this.rlSilca.setVisibility(8);
        } else {
            this.rlSilca.setVisibility(0);
        }
        if (getArguments() == null || ((SearchType) getArguments().getSerializable("TYPE")) != SearchType.BAR_CODE) {
            return;
        }
        this.rlBarCode.performClick();
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.search);
    }

    @OnClick({R.id.rl_key_blank, R.id.rl_key_id, R.id.rl_china_key_num, R.id.rl_blitz_card, R.id.rl_dsd, R.id.rl_lkp, R.id.rl_silca, R.id.rl_bar_code})
    public void onViewClicked(View view) {
        int color = ThemeUtils.getColor(getContext(), R.attr.color_red_blueDark);
        int color2 = ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333);
        switch (view.getId()) {
            case R.id.rl_bar_code /* 2131362688 */:
                this.etSearch.setText("");
                this.searchType = SearchType.BAR_CODE;
                this.tvKeyBlank.setTextColor(color2);
                this.flagKeyBlank.setBackgroundColor(color2);
                this.tvKeyId.setTextColor(color2);
                this.flagKeyId.setBackgroundColor(color2);
                this.tvChinaKeyNum.setTextColor(color2);
                this.flagChinaKey.setBackgroundColor(color2);
                this.tvBlitzCard.setTextColor(color2);
                this.flagBlitzCard.setBackgroundColor(color2);
                this.tvDsd.setTextColor(color2);
                this.flagDsd.setBackgroundColor(color2);
                this.tvLkp.setTextColor(color2);
                this.flagLkp.setBackgroundColor(color2);
                this.tvSilca.setTextColor(color2);
                this.flagSilca.setBackgroundColor(color2);
                this.tvBarCode.setTextColor(color);
                this.flagBarCode.setBackgroundColor(color);
                this.activity.bindToEditor(this.etSearch, 0);
                this.etSearch.requestFocus();
                return;
            case R.id.rl_blitz_card /* 2131362689 */:
                this.etSearch.setText("");
                this.searchType = SearchType.BLITZ_CARD;
                this.tvKeyBlank.setTextColor(color2);
                this.flagKeyBlank.setBackgroundColor(color2);
                this.tvKeyId.setTextColor(color2);
                this.flagKeyId.setBackgroundColor(color2);
                this.tvChinaKeyNum.setTextColor(color2);
                this.flagChinaKey.setBackgroundColor(color2);
                this.tvBlitzCard.setTextColor(color);
                this.flagBlitzCard.setBackgroundColor(color);
                this.tvDsd.setTextColor(color2);
                this.flagDsd.setBackgroundColor(color2);
                this.tvLkp.setTextColor(color2);
                this.flagLkp.setBackgroundColor(color2);
                this.tvSilca.setTextColor(color2);
                this.flagSilca.setBackgroundColor(color2);
                this.tvBarCode.setTextColor(color2);
                this.flagBarCode.setBackgroundColor(color2);
                this.activity.bindToEditor(this.etSearch, 0);
                this.etSearch.requestFocus();
                return;
            case R.id.rl_china_key_num /* 2131362690 */:
                this.etSearch.setText("");
                this.searchType = SearchType.CHINA_KEY_NUM;
                this.tvKeyBlank.setTextColor(color2);
                this.flagKeyBlank.setBackgroundColor(color2);
                this.tvKeyId.setTextColor(color2);
                this.flagKeyId.setBackgroundColor(color2);
                this.tvChinaKeyNum.setTextColor(color);
                this.flagChinaKey.setBackgroundColor(color);
                this.tvBlitzCard.setTextColor(color2);
                this.flagBlitzCard.setBackgroundColor(color2);
                this.tvDsd.setTextColor(color2);
                this.flagDsd.setBackgroundColor(color2);
                this.tvLkp.setTextColor(color2);
                this.flagLkp.setBackgroundColor(color2);
                this.tvSilca.setTextColor(color2);
                this.flagSilca.setBackgroundColor(color2);
                this.tvBarCode.setTextColor(color2);
                this.flagBarCode.setBackgroundColor(color2);
                this.activity.bindToEditor(this.etSearch, 1);
                this.etSearch.requestFocus();
                return;
            case R.id.rl_container /* 2131362691 */:
            case R.id.rl_data /* 2131362692 */:
            case R.id.rl_move /* 2131362697 */:
            case R.id.rl_parent /* 2131362698 */:
            default:
                return;
            case R.id.rl_dsd /* 2131362693 */:
                this.etSearch.setText("");
                this.searchType = SearchType.DSD;
                this.tvKeyBlank.setTextColor(color2);
                this.flagKeyBlank.setBackgroundColor(color2);
                this.tvKeyId.setTextColor(color2);
                this.flagKeyId.setBackgroundColor(color2);
                this.tvChinaKeyNum.setTextColor(color2);
                this.flagChinaKey.setBackgroundColor(color2);
                this.tvBlitzCard.setTextColor(color2);
                this.flagBlitzCard.setBackgroundColor(color2);
                this.tvDsd.setTextColor(color);
                this.flagDsd.setBackgroundColor(color);
                this.tvLkp.setTextColor(color2);
                this.flagLkp.setBackgroundColor(color2);
                this.tvSilca.setTextColor(color2);
                this.flagSilca.setBackgroundColor(color2);
                this.tvBarCode.setTextColor(color2);
                this.flagBarCode.setBackgroundColor(color2);
                this.activity.bindToEditor(this.etSearch, 1);
                this.etSearch.requestFocus();
                return;
            case R.id.rl_key_blank /* 2131362694 */:
                this.etSearch.setText("");
                this.searchType = SearchType.KEY_BLANK;
                this.tvKeyBlank.setTextColor(color);
                this.flagKeyBlank.setBackgroundColor(color);
                this.tvKeyId.setTextColor(color2);
                this.flagKeyId.setBackgroundColor(color2);
                this.tvChinaKeyNum.setTextColor(color2);
                this.flagChinaKey.setBackgroundColor(color2);
                this.tvBlitzCard.setTextColor(color2);
                this.flagBlitzCard.setBackgroundColor(color2);
                this.tvDsd.setTextColor(color2);
                this.flagDsd.setBackgroundColor(color2);
                this.tvLkp.setTextColor(color2);
                this.flagLkp.setBackgroundColor(color2);
                this.tvSilca.setTextColor(color2);
                this.flagSilca.setBackgroundColor(color2);
                this.tvBarCode.setTextColor(color2);
                this.flagBarCode.setBackgroundColor(color2);
                this.activity.bindToEditor(this.etSearch, 0);
                this.etSearch.requestFocus();
                return;
            case R.id.rl_key_id /* 2131362695 */:
                this.etSearch.setText("");
                this.searchType = SearchType.KEY_ID;
                this.tvKeyBlank.setTextColor(color2);
                this.flagKeyBlank.setBackgroundColor(color2);
                this.tvKeyId.setTextColor(color);
                this.flagKeyId.setBackgroundColor(color);
                this.tvChinaKeyNum.setTextColor(color2);
                this.flagChinaKey.setBackgroundColor(color2);
                this.tvBlitzCard.setTextColor(color2);
                this.flagBlitzCard.setBackgroundColor(color2);
                this.tvDsd.setTextColor(color2);
                this.flagDsd.setBackgroundColor(color2);
                this.tvLkp.setTextColor(color2);
                this.flagLkp.setBackgroundColor(color2);
                this.tvSilca.setTextColor(color2);
                this.flagSilca.setBackgroundColor(color2);
                this.tvBarCode.setTextColor(color2);
                this.flagBarCode.setBackgroundColor(color2);
                this.activity.bindToEditor(this.etSearch, 1);
                this.etSearch.requestFocus();
                return;
            case R.id.rl_lkp /* 2131362696 */:
                this.etSearch.setText("");
                this.searchType = SearchType.LKP_DSD;
                this.tvKeyBlank.setTextColor(color2);
                this.flagKeyBlank.setBackgroundColor(color2);
                this.tvKeyId.setTextColor(color2);
                this.flagKeyId.setBackgroundColor(color2);
                this.tvChinaKeyNum.setTextColor(color2);
                this.flagChinaKey.setBackgroundColor(color2);
                this.tvBlitzCard.setTextColor(color2);
                this.flagBlitzCard.setBackgroundColor(color2);
                this.tvDsd.setTextColor(color2);
                this.flagDsd.setBackgroundColor(color2);
                this.tvLkp.setTextColor(color);
                this.flagLkp.setBackgroundColor(color);
                this.tvSilca.setTextColor(color2);
                this.flagSilca.setBackgroundColor(color2);
                this.tvBarCode.setTextColor(color2);
                this.flagBarCode.setBackgroundColor(color2);
                this.activity.bindToEditor(this.etSearch, 1);
                this.etSearch.requestFocus();
                return;
            case R.id.rl_silca /* 2131362699 */:
                this.etSearch.setText("");
                this.searchType = SearchType.SILCA_CARD;
                this.tvKeyBlank.setTextColor(color2);
                this.flagKeyBlank.setBackgroundColor(color2);
                this.tvKeyId.setTextColor(color2);
                this.flagKeyId.setBackgroundColor(color2);
                this.tvChinaKeyNum.setTextColor(color2);
                this.flagChinaKey.setBackgroundColor(color2);
                this.tvBlitzCard.setTextColor(color2);
                this.flagBlitzCard.setBackgroundColor(color2);
                this.tvDsd.setTextColor(color2);
                this.flagDsd.setBackgroundColor(color2);
                this.tvLkp.setTextColor(color2);
                this.flagLkp.setBackgroundColor(color2);
                this.tvSilca.setTextColor(color);
                this.flagSilca.setBackgroundColor(color);
                this.tvBarCode.setTextColor(color2);
                this.flagBarCode.setBackgroundColor(color2);
                this.activity.bindToEditor(this.etSearch, 1);
                this.etSearch.requestFocus();
                return;
        }
    }

    /* renamed from: com.kkkcut.e20j.ui.fragment.SearchFragment$12, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass12 {
        static final /* synthetic */ int[] $SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType;

        static {
            int[] iArr = new int[SearchType.values().length];
            $SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType = iArr;
            try {
                iArr[SearchType.KEY_BLANK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType[SearchType.KEY_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType[SearchType.CHINA_KEY_NUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType[SearchType.BLITZ_CARD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType[SearchType.DSD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType[SearchType.LKP_DSD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType[SearchType.SILCA_CARD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType[SearchType.BAR_CODE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED, value = {R.id.et_search})
    public void afterTextChanged(Editable editable) {
        switch (AnonymousClass12.$SwitchMap$com$kkkcut$e20j$ui$fragment$SearchFragment$SearchType[this.searchType.ordinal()]) {
            case 1:
                String obj = editable.toString();
                if (!TextUtils.isEmpty(obj)) {
                    searchKeyBlank(obj);
                    return;
                } else {
                    this.keySearchAdapter.setNewData(new ArrayList());
                    return;
                }
            case 2:
                String obj2 = editable.toString();
                if (!TextUtils.isEmpty(obj2)) {
                    searchID(obj2);
                    return;
                } else {
                    this.keySearchAdapter.setNewData(new ArrayList());
                    return;
                }
            case 3:
                String obj3 = editable.toString();
                if (!TextUtils.isEmpty(obj3)) {
                    searchChinaKeyNum(obj3);
                    return;
                } else {
                    this.keySearchAdapter.setNewData(new ArrayList());
                    return;
                }
            case 4:
                String obj4 = editable.toString();
                if (!TextUtils.isEmpty(obj4)) {
                    searchBlitzCardOrDsd(1, obj4);
                    return;
                } else {
                    this.keySearchAdapter.setNewData(new ArrayList());
                    return;
                }
            case 5:
                String obj5 = editable.toString();
                if (!TextUtils.isEmpty(obj5)) {
                    searchBlitzCardOrDsd(2, obj5);
                    return;
                } else {
                    this.keySearchAdapter.setNewData(new ArrayList());
                    return;
                }
            case 6:
                String obj6 = editable.toString();
                if (!TextUtils.isEmpty(obj6)) {
                    searchBlitzCardOrDsd(3, obj6);
                    return;
                } else {
                    this.keySearchAdapter.setNewData(new ArrayList());
                    return;
                }
            case 7:
                String obj7 = editable.toString();
                if (!TextUtils.isEmpty(obj7)) {
                    searchBlitzCardOrDsd(4, obj7);
                    return;
                } else {
                    this.keySearchAdapter.setNewData(new ArrayList());
                    return;
                }
            case 8:
                String obj8 = editable.toString();
                if (!TextUtils.isEmpty(obj8)) {
                    searchBarCode(obj8);
                    return;
                } else {
                    this.keySearchAdapter.setNewData(new ArrayList());
                    return;
                }
            default:
                return;
        }
    }

    private void searchBarCode(final String str) {
        Disposable subscribe = Observable.fromCallable(new Callable<List<BarCodeSearch>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.3
            @Override // java.util.concurrent.Callable
            public List<BarCodeSearch> call() throws Exception {
                return KeyInfoDaoManager.getInstance().searchBarCode(str);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<BarCodeSearch>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.2
            @Override // io.reactivex.functions.Consumer
            public void accept(List<BarCodeSearch> list) throws Exception {
                SearchFragment.this.keySearchAdapter.setNewData(list);
            }
        });
        clearDisposable();
        addDisposable(subscribe);
    }

    private void searchBlitzCardOrDsd(final int i, final String str) {
        Disposable subscribe = Observable.fromCallable(new Callable<List<UsaSearchExtItemBasicData>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.5
            @Override // java.util.concurrent.Callable
            public List<UsaSearchExtItemBasicData> call() throws Exception {
                return KeyInfoDaoManager.getInstance().searchBlitzOrDsd(i, str);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<UsaSearchExtItemBasicData>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.4
            @Override // io.reactivex.functions.Consumer
            public void accept(List<UsaSearchExtItemBasicData> list) throws Exception {
                SearchFragment.this.keySearchAdapter.setNewData(list);
            }
        });
        clearDisposable();
        addDisposable(subscribe);
    }

    private void searchKeyBlank(final String str) {
        Disposable subscribe = Observable.fromCallable(new Callable<List<KeyBlankItemSearch>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.7
            @Override // java.util.concurrent.Callable
            public List<KeyBlankItemSearch> call() throws Exception {
                return KeyInfoDaoManager.getInstance().searchKey(str + "%");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<KeyBlankItemSearch>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.6
            @Override // io.reactivex.functions.Consumer
            public void accept(List<KeyBlankItemSearch> list) throws Exception {
                SearchFragment.this.keySearchAdapter.setNewData(list);
            }
        });
        clearDisposable();
        addDisposable(subscribe);
    }

    private void searchID(final String str) {
        Disposable subscribe = Observable.fromCallable(new Callable<List<CardsSystem>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.9
            @Override // java.util.concurrent.Callable
            public List<CardsSystem> call() throws Exception {
                return KeyInfoDaoManager.getInstance().searchID(str);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<CardsSystem>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.8
            @Override // io.reactivex.functions.Consumer
            public void accept(List<CardsSystem> list) throws Exception {
                SearchFragment.this.keySearchAdapter.setNewData(list);
            }
        });
        clearDisposable();
        addDisposable(subscribe);
    }

    private void searchChinaKeyNum(final String str) {
        Disposable subscribe = Observable.fromCallable(new Callable<List<ChinaNumSearch>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.11
            @Override // java.util.concurrent.Callable
            public List<ChinaNumSearch> call() throws Exception {
                return KeyInfoDaoManager.getInstance().searchChinaKeyNumber(str);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<ChinaNumSearch>>() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment.10
            @Override // io.reactivex.functions.Consumer
            public void accept(List<ChinaNumSearch> list) throws Exception {
                Log.i(SearchFragment.TAG, "accept: " + list.size());
                SearchFragment.this.keySearchAdapter.setNewData(list);
            }
        });
        clearDisposable();
        addDisposable(subscribe);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Object obj = baseQuickAdapter.getData().get(i);
        if (obj instanceof ChinaNumSearch) {
            ChinaNumSearch chinaNumSearch = (ChinaNumSearch) obj;
            String name_CN = chinaNumSearch.getName_CN();
            if (!MachineInfo.isChineseMachine() || TextUtils.isEmpty(name_CN)) {
                name_CN = chinaNumSearch.getName();
            }
            String modelName_CN = chinaNumSearch.getModelName_CN();
            if (!MachineInfo.isChineseMachine() || TextUtils.isEmpty(modelName_CN)) {
                modelName_CN = chinaNumSearch.getModelName();
            }
            String fromYear = chinaNumSearch.getFromYear();
            if (TextUtils.isEmpty(fromYear)) {
                fromYear = "0";
            }
            String toYear = chinaNumSearch.getToYear();
            start(KeyOperateFragment.newInstance(new GoOperatBean(chinaNumSearch, name_CN + ">" + modelName_CN + ">" + fromYear + "~" + (TextUtils.isEmpty(toYear) ? "0" : toYear) + "--ID:" + chinaNumSearch.getfK_KeyID())));
            return;
        }
        String str = "";
        int i2 = 0;
        if (obj instanceof KeyBlankItemSearch) {
            KeyBlankItemSearch keyBlankItemSearch = (KeyBlankItemSearch) obj;
            int fK_KeyID = keyBlankItemSearch.getFK_KeyID();
            if (MachineInfo.isE20Us(getContext())) {
                str = keyBlankItemSearch.getKeyblankItemName() + "(Card:" + fK_KeyID + ")";
            } else {
                str = "" + getString(R.string.key_blanks) + keyBlankItemSearch.getKeyblankItemName() + "--ID:" + fK_KeyID;
            }
            i2 = fK_KeyID;
        }
        if (obj instanceof CardsSystem) {
            i2 = ((CardsSystem) obj).getKeyID();
            if (MachineInfo.isE20Us(getContext())) {
                str = "Card:" + i2;
            } else {
                str = "ID:" + i2;
            }
        }
        if (obj instanceof UsaSearchExtItemBasicData) {
            UsaSearchExtItemBasicData usaSearchExtItemBasicData = (UsaSearchExtItemBasicData) obj;
            i2 = usaSearchExtItemBasicData.getFkKeyID();
            if (usaSearchExtItemBasicData.getFK_SearchExtID() == 1) {
                str = getString(R.string.blitz_card) + ":" + usaSearchExtItemBasicData.getName();
            } else if (usaSearchExtItemBasicData.getFK_SearchExtID() == 2) {
                str = "DSD:" + usaSearchExtItemBasicData.getName();
            } else if (usaSearchExtItemBasicData.getFK_SearchExtID() == 3) {
                str = "LKP DSD:" + usaSearchExtItemBasicData.getName();
            } else if (usaSearchExtItemBasicData.getFK_SearchExtID() == 4) {
                str = getString(R.string.silca_card) + ":" + usaSearchExtItemBasicData.getName();
            }
            str = str + "(Card:" + i2 + ")";
        }
        if (obj instanceof BarCodeSearch) {
            BarCodeSearch barCodeSearch = (BarCodeSearch) obj;
            i2 = barCodeSearch.getFK_KeyID();
            str = getBarCodeTitle(i2, barCodeSearch.getBarCode());
            if (isShowRemind(i2)) {
                BarCodeRemindActivity.start(getActivity(), i2, barCodeSearch.getBarCode());
                return;
            }
        }
        start(KeyOperateFragment.newInstance(new GoOperatBean(i2, str)));
    }

    private String getBarCodeTitle(int i, String str) {
        return ("Bar Code Scanning:" + str) + "(Card:" + i + ")";
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.activity.hideSoftKeyboard();
        super.onDestroy();
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        Bundle bundle;
        int i;
        if (eventCenter.getEventCode() == 55 && (i = (bundle = (Bundle) eventCenter.getData()).getInt(BarCodeRemindActivity.ID)) != 0) {
            start(KeyOperateFragment.newInstance(new GoOperatBean(i, getBarCodeTitle(i, bundle.getString("bar_code")))));
        }
    }
}
