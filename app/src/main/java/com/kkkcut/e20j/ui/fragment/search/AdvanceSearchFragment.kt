package com.kkkcut.e20j.ui.fragment.search;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.customView.searchSpinner.SearchableListDialog;
import com.kkkcut.e20j.customView.searchSpinner.SearchableSpinner;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class AdvanceSearchFragment extends BaseBackFragment implements SearchableListDialog.SearchableItem {

    EditText etCard;

    EditText etKeyBlank;

    EditText etLockManufacturer;

    EditText etLockSystem;
    private InputType inputType;

    SearchableSpinner spinnerCard;

    SearchableSpinner spinnerKeyManufacturer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum InputType {
        CARD,
        KEY_BLANK,
        KEY_MANUFACTURER,
        LOCK_MANUFACTURER,
        LOCK_SYSTEM
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_advance_search;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
    }

    public static AdvanceSearchFragment newInstance() {
        return new AdvanceSearchFragment();
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.advanced_search);
    }

    private void prompt(final InputType inputType, EditText editText) {
        final String upperCase = editText.getText().toString().trim().toUpperCase();
        if (TextUtils.isEmpty(upperCase)) {
            return;
        }
        this.inputType = inputType;
        addDisposable(Observable.fromCallable(new Callable<List<String>>() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment.2
            @Override // java.util.concurrent.Callable
            public List<String> call() throws Exception {
                return AdvanceSearchFragment.this.searchInput(inputType, upperCase);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<String>>() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<String> list) throws Exception {
                SearchableListDialog newInstance = SearchableListDialog.newInstance(list);
                newInstance.show(AdvanceSearchFragment.this.getActivity().getFragmentManager(), "Search");
                newInstance.setOnSearchableItemClickListener(AdvanceSearchFragment.this);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment$5, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType;

        static {
            int[] iArr = new int[InputType.values().length];
            $SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType = iArr;
            try {
                iArr[InputType.CARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType[InputType.KEY_BLANK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType[InputType.KEY_MANUFACTURER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType[InputType.LOCK_SYSTEM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType[InputType.LOCK_MANUFACTURER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> searchInput(InputType inputType, String str) {
        int i = AnonymousClass5.$SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType[inputType.ordinal()];
        if (i == 1) {
            int selectedItemPosition = this.spinnerCard.getSelectedItemPosition();
            if (selectedItemPosition == 0) {
                return KeyInfoDaoManager.getInstance().searchKid(str);
            }
            if (selectedItemPosition == 1) {
                return KeyInfoDaoManager.getInstance().searchCard(str);
            }
            if (selectedItemPosition == 2) {
                return KeyInfoDaoManager.getInstance().searchSn(str);
            }
            return null;
        }
        if (i == 2) {
            return KeyInfoDaoManager.getInstance().searchKeyBlank(str);
        }
        if (i == 3) {
            return KeyInfoDaoManager.getInstance().searchKeyManu(str);
        }
        if (i == 4) {
            return KeyInfoDaoManager.getInstance().searchLockSystem(str);
        }
        if (i != 5) {
            return null;
        }
        return KeyInfoDaoManager.getInstance().searchLockManu(str);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_clear /* 2131361915 */:
                this.etLockSystem.setText("");
                this.etKeyBlank.setText("");
                this.etCard.setText("");
                this.etLockManufacturer.setText("");
                this.spinnerKeyManufacturer.setAdapter((SpinnerAdapter) null);
                this.spinnerCard.reset();
                return;
            case R.id.bt_search /* 2131361972 */:
                boolean z = false;
                SearchCondition searchCondition = new SearchCondition();
                String trim = this.etCard.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    int selectedItemPosition = this.spinnerCard.getSelectedItemPosition();
                    if (selectedItemPosition == -1) {
                        ToastUtil.showToast("Please select a card type first");
                        return;
                    }
                    if (selectedItemPosition == 0) {
                        searchCondition.setKid(trim);
                    } else if (selectedItemPosition == 1) {
                        searchCondition.setSilcaCard(trim);
                    } else if (selectedItemPosition == 2) {
                        searchCondition.setSilcaSN(trim);
                    }
                    z = true;
                }
                String trim2 = this.etKeyBlank.getText().toString().trim();
                if (!TextUtils.isEmpty(trim2)) {
                    z = true;
                }
                searchCondition.setKeyBlank(trim2);
                searchCondition.setKeyBlankManu((String) this.spinnerKeyManufacturer.getSelectedItem());
                String trim3 = this.etLockManufacturer.getText().toString().trim();
                if (!TextUtils.isEmpty(trim3)) {
                    z = true;
                }
                searchCondition.setLockManu(trim3);
                String trim4 = this.etLockSystem.getText().toString().trim();
                boolean z2 = TextUtils.isEmpty(trim4) ? z : true;
                searchCondition.setLockSys(trim4);
                if (!z2) {
                    ToastUtil.showToast("Please insert a searching parameter");
                    return;
                } else {
                    start(SearchResultFragment.newInstance(searchCondition));
                    return;
                }
            case R.id.iv_card /* 2131362282 */:
                if (this.spinnerCard.getSelectedItemPosition() == -1) {
                    ToastUtil.showToast("Please select a card type first");
                    return;
                } else {
                    if (TextUtils.isEmpty(this.etCard.getText().toString().trim().toUpperCase())) {
                        return;
                    }
                    prompt(InputType.CARD, this.etCard);
                    return;
                }
            case R.id.iv_key_blank /* 2131362308 */:
                if (TextUtils.isEmpty(this.etKeyBlank.getText().toString().trim().toUpperCase())) {
                    return;
                }
                prompt(InputType.KEY_BLANK, this.etKeyBlank);
                return;
            case R.id.iv_lock_manu /* 2131362315 */:
                if (TextUtils.isEmpty(this.etLockManufacturer.getText().toString().trim().toUpperCase())) {
                    return;
                }
                prompt(InputType.LOCK_MANUFACTURER, this.etLockManufacturer);
                return;
            case R.id.iv_lock_system /* 2131362316 */:
                if (TextUtils.isEmpty(this.etLockSystem.getText().toString().trim().toUpperCase())) {
                    return;
                }
                prompt(InputType.LOCK_SYSTEM, this.etLockSystem);
                return;
            default:
                return;
        }
    }

    @Override // com.kkkcut.e20j.customView.searchSpinner.SearchableListDialog.SearchableItem
    public void onSearchableItemClicked(final Object obj, int i) {
        int i2 = AnonymousClass5.$SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType[this.inputType.ordinal()];
        if (i2 == 1) {
            this.etCard.setText((String) obj);
            return;
        }
        if (i2 == 2) {
            Observable.fromCallable(new Callable<List<String>>() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment.4
                @Override // java.util.concurrent.Callable
                public List<String> call() throws Exception {
                    return AdvanceSearchFragment.this.searchInput(InputType.KEY_MANUFACTURER, (String) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<String>>() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment.3
                @Override // io.reactivex.functions.Consumer
                public void accept(List<String> list) throws Exception {
                    AdvanceSearchFragment.this.spinnerKeyManufacturer.setAdapter((SpinnerAdapter) new ArrayAdapter(AdvanceSearchFragment.this.getContext(), R.layout.support_simple_spinner_dropdown_item, list));
                }
            });
            this.etKeyBlank.setText((String) obj);
        } else if (i2 == 4) {
            this.etLockSystem.setText((String) obj);
        } else {
            if (i2 != 5) {
                return;
            }
            this.etLockManufacturer.setText((String) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    public void onUserVisible() {
        Log.d(TAG, "onUserVisible() called");
        super.onUserVisible();
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, me.yokeyword.fragmentation.ISupportFragment
    public void onSupportVisible() {
        Log.i(TAG, "onSupportVisible: ");
        super.onSupportVisible();
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment, com.kkkcut.e20j.base.BaseFFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        Log.d(TAG, "onHiddenChanged() called with: hidden = [" + z + "]");
        super.onHiddenChanged(z);
    }
}
