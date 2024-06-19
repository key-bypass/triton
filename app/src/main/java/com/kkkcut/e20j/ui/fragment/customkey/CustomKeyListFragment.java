package com.kkkcut.e20j.ui.fragment.customkey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.KeyBasicData;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.adapter.CustomKeyAdapter;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.gsonBean.UploadCustomKey;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.GetUUID;
import com.kkkcut.e20j.utils.SpecificParamUtils;
import com.liying.core.MachineInfo;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class CustomKeyListFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.bt_create_key)
    Button btCreateKey;
    private CustomKeyAdapter customKeyAdapter;

    @BindView(R.id.rv_custom_key)
    RecyclerView rvCustomKey;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_customkey_list;
    }

    public static CustomKeyListFragment newInstance() {
        return new CustomKeyListFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvCustomKey.setLayoutManager(linearLayoutManager);
        this.rvCustomKey.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        CustomKeyAdapter customKeyAdapter = new CustomKeyAdapter();
        this.customKeyAdapter = customKeyAdapter;
        customKeyAdapter.setOnItemChildClickListener(this);
        this.customKeyAdapter.setOnItemClickListener(this);
        this.rvCustomKey.setAdapter(this.customKeyAdapter);
        getCustomKeys();
        if (SPUtils.getBoolean(SpKeys.CUSTOMKEY_WARNING_NEVER_ASK, false)) {
            return;
        }
        WarningDialog warningDialog = new WarningDialog(getContext());
        warningDialog.setRemind(getString(R.string.this_function_requires_professional_lock_knowledge_to_use_if_you_use_this_function_incorrectly_it_may_cause_damage_to_the_fixture_or_machine_please_use_it_with_caution));
        warningDialog.setCheckbox(true, SpKeys.CUSTOMKEY_WARNING_NEVER_ASK);
        warningDialog.show();
    }

    private void getCustomKeys() {
        addDisposable(Observable.fromCallable(new Callable<List<CustomKey>>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.2
            @Override // java.util.concurrent.Callable
            public List<CustomKey> call() throws Exception {
                return UserDataDaoManager.getInstance(CustomKeyListFragment.this.getContext()).getCustomKeys();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<CustomKey>>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<CustomKey> list) throws Exception {
                Log.i(CustomKeyListFragment.TAG, "accept: " + list.size());
                CustomKeyListFragment.this.customKeyAdapter.setNewData(list);
            }
        }));
    }

    @OnClick({R.id.bt_create_key, R.id.bt_load_from_id})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.bt_create_key) {
            start(KeyTypeSelectFragment.newInstance(new CustomKey()));
        } else {
            if (id != R.id.bt_load_from_id) {
                return;
            }
            showLoadIDDialog();
        }
    }

    private void showLoadIDDialog() {
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setTip(getString(R.string.please_input_profile_id));
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.3
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public void onDialogButClick(String str) {
                CustomKeyListFragment.this.loadProfileId(str);
            }
        });
        editDialog.show();
        EditText editText = (EditText) editDialog.findViewById(R.id.et_input);
        if (editText != null) {
            editText.setInputType(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadProfileId(final String str) {
        addDisposable(Observable.fromCallable(new Callable<KeyBasicData>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public KeyBasicData call() throws Exception {
                KeyBasicData basicData = KeyInfoDaoManager.getInstance().getBasicData(Integer.parseInt(str));
                if (basicData != null) {
                    return basicData;
                }
                throw new Exception(CustomKeyListFragment.this.getString(R.string.id_does_not_exist));
            }
        }).map(new Function<KeyBasicData, CustomKey>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.6
            @Override // io.reactivex.functions.Function
            public CustomKey apply(KeyBasicData keyBasicData) throws Exception {
                CustomKey customKey = new CustomKey(keyBasicData);
                String param = SpecificParamUtils.getParam(customKey.getParameter_info(), "locked");
                if (keyBasicData.getIcCard() > 5000 && "1".equals(param)) {
                    throw new Exception(CustomKeyListFragment.this.getString(R.string.cannot_load_this_data));
                }
                if (keyBasicData.getType() == 7 || keyBasicData.getType() == 9) {
                    throw new Exception(CustomKeyListFragment.this.getString(R.string.cannot_load_this_data));
                }
                return customKey;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CustomKey>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.4
            @Override // io.reactivex.functions.Consumer
            public void accept(CustomKey customKey) throws Exception {
                CustomKeyListFragment.this.start(KeyTypeSelectFragment.newInstance(customKey));
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ToastUtil.showToast(R.string.network_unavailable);
            }
        }));
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.my_key_info);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    public void onItemChildClick(final BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        final CustomKey customKey = (CustomKey) baseQuickAdapter.getData().get(i);
        int id = view.getId();
        if (id == R.id.iv_delete) {
            new AlertDialog.Builder(getContext()).setMessage(R.string.the_key_profile_will_be_deleted).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.8
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    UserDataDaoManager.getInstance(CustomKeyListFragment.this.getContext()).deleteCustomKey(customKey);
                    baseQuickAdapter.remove(i);
                }
            }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        } else if (id == R.id.iv_edit) {
            start(KeyTypeSelectFragment.newInstance(customKey));
        } else {
            if (id != R.id.iv_synchronize) {
                return;
            }
            syncData(customKey);
        }
    }

    private void syncData(CustomKey customKey) {
        showLoadingDialog(getString(R.string.waitting), false);
        addDisposable(((Apis) RetrofitManager.getInstance().createApi(Apis.class)).uploadCustomkey(TUitls.uploadCustomkey(SPUtils.getString("series"), GetUUID.getUUID(), customKey)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<UploadCustomKey>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.9
            @Override // io.reactivex.functions.Consumer
            public void accept(UploadCustomKey uploadCustomKey) throws Exception {
                String str;
                String[] split = uploadCustomKey.getMsg().split("\\n");
                if (MachineInfo.isChineseMachine()) {
                    str = split[0];
                } else if (split.length == 2) {
                    str = split[1];
                } else {
                    str = split[0];
                }
                CustomKeyListFragment.this.dismissLoadingDialog();
                ToastUtil.showToast(str);
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.10
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                CustomKeyListFragment.this.dismissLoadingDialog();
                ToastUtil.showToast(R.string.network_unavailable);
            }
        }));
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        start(KeyOperateFragment.newInstance(new GoOperatBean((CustomKey) baseQuickAdapter.getData().get(i))));
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == 11) {
            getCustomKeys();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED, value = {R.id.et_search})
    public void afterTextChanged(final Editable editable) {
        Disposable subscribe = Observable.fromCallable(new Callable<List>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.12
            @Override // java.util.concurrent.Callable
            public List call() throws Exception {
                return UserDataDaoManager.getInstance(CustomKeyListFragment.this.getContext()).fuzzyQueryCustomKeys(editable.toString());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List>() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.11
            @Override // io.reactivex.functions.Consumer
            public void accept(List list) throws Exception {
                CustomKeyListFragment.this.customKeyAdapter.setNewData(list);
            }
        });
        clearDisposable();
        addDisposable(subscribe);
    }
}
