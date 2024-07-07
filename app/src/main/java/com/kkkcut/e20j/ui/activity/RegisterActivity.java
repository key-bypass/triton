package com.kkkcut.e20j.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.kkkcut.e20j.Constant;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.GsonHelper;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.bean.gsonBean.Configuration;
import com.kkkcut.e20j.bean.gsonBean.RegistrationRes;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.dialog.LoadingDialog;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.us.databinding.ActivityRegisterBinding;
import com.kkkcut.e20j.utils.AssetVersionUtil;
import com.kkkcut.e20j.utils.GetUUID;
import com.kkkcut.e20j.utils.UnifiedErrorUtil;
import com.kkkcut.e20j.utils.lan.LocalManageUtil;
import com.cutting.machine.MachineInfo;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.ListCompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;


/* loaded from: classes.dex */
public class RegisterActivity extends BaseCustomKeyBoardActivity {
    private static final String TAG = "RegisterActivity";

    private ActivityRegisterBinding binding;

    private ListCompositeDisposable listCompositeDisposable = new ListCompositeDisposable();
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle, persistableBundle);
        this.binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected int getContentViewLayoutID() {
        return R.layout.activity_register;
    }

    @Override // com.kkkcut.e20j.base.BaseFActivity, me.yokeyword.fragmentation.ISupportActivity
    public void onBackPressedSupport() {
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void initViewsAndEvents() {
        bindToEditor(this.binding.etSn, 0);
        bindToEditor(this.binding.etRegCode, 0);
        this.binding.etSn.postDelayed(new Runnable() { // from class: com.kkkcut.e20j.ui.activity.RegisterActivity.1
            @Override // java.lang.Runnable
            public void run() {
                RegisterActivity.this.binding.etSn.requestFocus();
            }
        }, 300L);
        String uuid = GetUUID.getUUID();
        if (TextUtils.isEmpty(uuid)) {
            return;
        }
        this.binding.tvCpu.setText(uuid);
    }

    public void onViewClicked() {
        registration();
    }

    private void registration() {
        String trim = this.binding.etSn.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtil.showToast(R.string.not_null);
            return;
        }
        if (trim.length() != 10) {
            ToastUtil.showToast(R.string.serial_number_is_not_correct);
            return;
        }
        String trim2 = this.binding.etRegCode.getText().toString().trim();
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
            addDisposable(((Apis) RetrofitManager.getInstance().createApi(Apis.class)).register(TUitls.register(uuid, trim, trim2)).subscribeOn(io()).flatMap(new Function<RegistrationRes, ObservableSource<Configuration>>() { // from class: com.kkkcut.e20j.ui.activity.RegisterActivity.5
                @Override // io.reactivex.functions.Function
                public ObservableSource<Configuration> apply(RegistrationRes registrationRes) throws Exception {
                    Log.i(RegisterActivity.TAG, "first---" + registrationRes.getMsg() + ":" + registrationRes.getCode());
                    if ("0".equals(registrationRes.getCode())) {
                        String configurationFile = registrationRes.getConfigurationFile();
                        SPUtils.put(SpKeys.CONFIGURATION_FILE, configurationFile);
                        return ((Apis) RetrofitManager.getInstance().createApi(Apis.class)).getConfig(TUitls.getconfig(configurationFile));
                    }
                    throw new Exception(registrationRes.getMsg() + ":" + registrationRes.getCode());
                }
            }).doOnNext(new Consumer<Configuration>() { // from class: com.kkkcut.e20j.ui.activity.RegisterActivity.4
                @Override // io.reactivex.functions.Consumer
                public void accept(Configuration configuration) throws Exception {
                    if (configuration == null) {
                        return;
                    }
                    String id = configuration.getId();
                    SPUtils.put(SpKeys.MACHINE_ID, id);
                    if (MachineInfo.isE20Standard(RegisterActivity.this)) {
                        RetrofitManager.getInstance().initBaseUrl(MachineInfo.isChineseMachine(id));
                    } else {
                        RetrofitManager.getInstance().initBaseUrl(false);
                    }
                    if (MachineInfo.isChineseMachine(id)) {
                        LocalManageUtil.saveSelectLanguage(RegisterActivity.this, "zh");
                    } else {
                        LocalManageUtil.saveSelectLanguage(RegisterActivity.this, "en");
                    }
                    Log.i(RegisterActivity.TAG, "database:" + configuration.getDatabase());
                    FileUtil.readIoStringToFile(GsonHelper.toJsonString(configuration), Constant.CONFIG_PATH);
                    SPUtils.put("series", RegisterActivity.this.binding.etSn.getText().toString().trim());
                    int assetsDbVersion = AssetVersionUtil.getAssetsDbVersion(RegisterActivity.this.getAssets(), Constant.CONFIG_UPDATA);
                    Log.i(RegisterActivity.TAG, "asset配置文件版本: " + assetsDbVersion);
                    SPUtils.put(SpKeys.CONFIG_UPDATE, assetsDbVersion);
                    SPUtils.sync();
                    Thread.sleep(3000L);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Configuration>() { // from class: com.kkkcut.e20j.ui.activity.RegisterActivity.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Configuration configuration) throws Exception {
                    RegisterActivity.this.dismiss();
                    RegisterActivity.this.finish();
                }
            }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.activity.RegisterActivity.3
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    RegisterActivity.this.dismiss();
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
            if (this.binding.etSn.hasFocus()) {
                this.binding.etRegCode.requestFocus();
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
            this.binding.etRegCode.requestFocus();
        }
    }
}
