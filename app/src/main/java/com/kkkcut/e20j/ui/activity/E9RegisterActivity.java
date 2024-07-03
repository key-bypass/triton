package com.kkkcut.e20j.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.Constant;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.GsonHelper;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.bean.gsonBean.ConfigurationE9;
import com.kkkcut.e20j.bean.gsonBean.RegistrationRes;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.dialog.LoadingDialog;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.AssetVersionUtil;
import com.kkkcut.e20j.utils.GetUUID;
import com.kkkcut.e20j.utils.UnifiedErrorUtil;
import com.kkkcut.e20j.utils.lan.LocalManageUtil;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.ListCompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/* loaded from: classes.dex */
public class E9RegisterActivity extends BaseCustomKeyBoardActivity {
    private static final String TAG = "RegisterActivity";
    EditText etSn;
    EditText etRegCode;
    TextView tvCpu;

    private ListCompositeDisposable listCompositeDisposable = new ListCompositeDisposable();
    private LoadingDialog loadingDialog;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected int getContentViewLayoutID() {
        return R.layout.activity_register;
    }

    @Override // com.kkkcut.e20j.base.BaseFActivity, me.yokeyword.fragmentation.ISupportActivity
    public void onBackPressedSupport() {
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void initViewsAndEvents() {
        bindToEditor(this.etSn, 0);
        bindToEditor(this.etRegCode, 0);
        this.etSn.postDelayed(new Runnable() { // from class: com.kkkcut.e20j.ui.activity.E9RegisterActivity.1
            @Override // java.lang.Runnable
            public void run() {
                E9RegisterActivity.this.etSn.requestFocus();
            }
        }, 300L);
        String uuid = GetUUID.getUUID();
        if (TextUtils.isEmpty(uuid)) {
            return;
        }
        this.tvCpu.setText(uuid);
    }

    public void onViewClicked() {
        registration();
    }

    private void registration() {
        String trim = this.etSn.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtil.showToast(R.string.not_null);
            return;
        }
        if (trim.length() != 10) {
            ToastUtil.showToast(R.string.serial_number_is_not_correct);
            return;
        }
        String trim2 = this.etRegCode.getText().toString().trim();
        if (TextUtils.isEmpty(trim2)) {
            ToastUtil.showToast(R.string.not_null);
            return;
        }
        if (trim2.length() != 8) {
            ToastUtil.showToast(R.string.registration_code_is_not_correct);
            return;
        }
        String uuid = GetUUID.getUUID();
        if (TextUtils.isEmpty(uuid)) {
            ToastUtil.showToast(R.string.cpu_id_not_found);
        } else {
            showLoading();
            addDisposable(((Apis) RetrofitManager.getInstance().createApi(Apis.class)).register(TUitls.register(uuid, trim, trim2)).subscribeOn(Schedulers.io()).flatMap(new Function<RegistrationRes, ObservableSource<ConfigurationE9>>() { // from class: com.kkkcut.e20j.ui.activity.E9RegisterActivity.5
                @Override // io.reactivex.functions.Function
                public ObservableSource<ConfigurationE9> apply(RegistrationRes registrationRes) throws Exception {
                    Log.i(E9RegisterActivity.TAG, "first---" + registrationRes.getMsg() + ":" + registrationRes.getCode());
                    if ("0".equals(registrationRes.getCode())) {
                        String configurationFile = registrationRes.getConfigurationFile();
                        SPUtils.put(SpKeys.CONFIGURATION_FILE, configurationFile);
                        return (ObservableSource<ConfigurationE9>) ((Apis) RetrofitManager.getInstance().createApi(Apis.class)).getConfigE9(TUitls.getconfig(configurationFile));
                    }
                    throw new Exception(registrationRes.getMsg() + ":" + registrationRes.getCode());
                }
            }).doOnNext(new Consumer<ConfigurationE9>() { // from class: com.kkkcut.e20j.ui.activity.E9RegisterActivity.4
                @Override // io.reactivex.functions.Consumer
                public void accept(ConfigurationE9 configurationE9) throws Exception {
                    if (configurationE9 == null) {
                        return;
                    }
                    String id = configurationE9.getId();
                    SPUtils.put(SpKeys.MACHINE_ID, id);
                    if (MachineInfo.isE20Standard(E9RegisterActivity.this)) {
                        RetrofitManager.getInstance().initBaseUrl(MachineInfo.isChineseMachine(id));
                    } else {
                        RetrofitManager.getInstance().initBaseUrl(false);
                    }
                    if (MachineInfo.isChineseMachine(id)) {
                        LocalManageUtil.saveSelectLanguage(E9RegisterActivity.this, "zh");
                    } else {
                        LocalManageUtil.saveSelectLanguage(E9RegisterActivity.this, "en");
                    }
                    Log.i(E9RegisterActivity.TAG, "database:" + configurationE9.getDatabase());
                    FileUtil.readIoStringToFile(GsonHelper.toJsonString(configurationE9), Constant.CONFIG_PATH);
                    SPUtils.put("series", E9RegisterActivity.this.etSn.getText().toString().trim());
                    int assetsDbVersion = AssetVersionUtil.getAssetsDbVersion(E9RegisterActivity.this.getAssets(), Constant.CONFIG_UPDATA);
                    Log.i(E9RegisterActivity.TAG, "asset配置文件版本: " + assetsDbVersion);
                    SPUtils.put(SpKeys.CONFIG_UPDATE, assetsDbVersion);
                    SPUtils.sync();
                    Thread.sleep(3000L);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ConfigurationE9>() { // from class: com.kkkcut.e20j.ui.activity.E9RegisterActivity.2
                @Override // io.reactivex.functions.Consumer
                public void accept(ConfigurationE9 configurationE9) throws Exception {
                    E9RegisterActivity.this.dismiss();
                    E9RegisterActivity.this.finish();
                }
            }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.activity.E9RegisterActivity.3
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    E9RegisterActivity.this.dismiss();
                    ToastUtil.showToast(UnifiedErrorUtil.unifiedError(th).getMessage());
                }
            }));
        }
    }

    private void showLoading() {
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(this);
        }
        if (this.loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismiss() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    public void addDisposable(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.add(disposable);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void clear() {
        if (this.listCompositeDisposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.clear();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 66) {
            if (this.etSn.hasFocus()) {
                this.etRegCode.requestFocus();
                return true;
            }
            registration();
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.base.BaseFActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onRegCodeChanged(CharSequence charSequence) {
        if (charSequence.length() == 8) {
            hideSoftKeyboard();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onSnChanged(CharSequence charSequence) {
        if (charSequence.length() == 10) {
            this.etRegCode.requestFocus();
        }
    }
}
