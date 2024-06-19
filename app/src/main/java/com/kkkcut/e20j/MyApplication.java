package com.kkkcut.e20j;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.multidex.MultiDex;
import com.kkkcut.e20j.androidquick.autolayout.config.AutoLayoutConifg;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.cockroach.Cockroach;
import com.kkkcut.e20j.cockroach.ExceptionHandler;
import com.kkkcut.e20j.utils.lan.MultiLanguage;
import com.liying.core.CuttingMachine;
import com.liying.core.MachineInfo;
import com.liying.core.MachineType;
import com.tencent.mmkv.MMKVHandler;
import com.tencent.mmkv.MMKVLogLevel;
import com.tencent.mmkv.MMKVRecoverStrategic;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import java.lang.Thread;

/* loaded from: classes.dex */
public class MyApplication extends MultiLanguagesApp implements MMKVHandler {
    private static MyApplication INSTANCE = null;
    private static final String TAG = "MyApplication";
    private boolean isShowRealDepth;
    private String operaterName;
    private boolean serialInit;
    final Thread.UncaughtExceptionHandler sysExcepHandler = Thread.getDefaultUncaughtExceptionHandler();
    private ListCompositeDisposable listCompositeDisposable = new ListCompositeDisposable();

    @Override // com.tencent.mmkv.MMKVHandler
    public boolean wantLogRedirecting() {
        return false;
    }

    public String getOperatorName() {
        return this.operaterName;
    }

    public void setOperatorName(String str) {
        this.operaterName = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.MultiLanguagesApp, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override // com.kkkcut.e20j.MultiLanguagesApp, android.app.Application
    public void onCreate() {
        MachineType machineType;
        super.onCreate();
        Log.i(TAG, "onCreate: application");
        AutoLayoutConifg.getInstance().useDeviceSize();
        if (INSTANCE == null) {
            INSTANCE = this;
        }
        ToastUtil.register(this);
        SPUtils.init(this, "sec_phone");
        SQLiteDatabase.loadLibs(this);
        install();
        setUrl();
        if (MachineInfo.isE9Standard(this)) {
            machineType = MachineType.E9;
        } else {
            machineType = MachineType.Alpha;
        }
        CuttingMachine.getInstance().init(this, machineType, SPUtils.getBoolean(SpKeys.Not_detect_decoder_position));
    }

    private void setUrl() {
        if (MachineInfo.isE20Standard(this) || MachineInfo.isE9Standard(this)) {
            String string = SPUtils.getString(SpKeys.MACHINE_ID);
            if (TextUtils.isEmpty(string)) {
                String language = MultiLanguage.getSystemLocal().getLanguage();
                Log.i(TAG, "setUrl: " + language);
                RetrofitManager.getInstance().initBaseUrl(language.contains("zh"));
                return;
            }
            RetrofitManager.getInstance().initBaseUrl(MachineInfo.isChineseMachine(string));
            return;
        }
        RetrofitManager.getInstance().initBaseUrl(false);
    }

    public boolean isSerialInit() {
        return this.serialInit;
    }

    public void setSerialInit(boolean z) {
        this.serialInit = z;
    }

    public static synchronized MyApplication getInstance() {
        MyApplication myApplication;
        synchronized (MyApplication.class) {
            myApplication = INSTANCE;
        }
        return myApplication;
    }

    protected void addDisposable(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.add(disposable);
    }

    protected void clear() {
        if (this.listCompositeDisposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.clear();
    }

    private void install() {
        Cockroach.install(new ExceptionHandler() { // from class: com.kkkcut.e20j.MyApplication.1
            @Override // com.kkkcut.e20j.cockroach.ExceptionHandler
            protected void onUncaughtExceptionHappened(Thread thread, Throwable th) {
                Log.e("AndroidRuntime", "--->onUncaughtExceptionHappened:" + thread + "<---", th);
            }

            @Override // com.kkkcut.e20j.cockroach.ExceptionHandler
            protected void onBandageExceptionHappened(Throwable th) {
                th.printStackTrace();
            }

            @Override // com.kkkcut.e20j.cockroach.ExceptionHandler
            protected void onEnterSafeMode() {
                ToastUtil.showToast("Error");
            }

            @Override // com.kkkcut.e20j.cockroach.ExceptionHandler
            protected void onMayBeBlackScreen(Throwable th) {
                Thread thread = Looper.getMainLooper().getThread();
                Log.e("AndroidRuntime", "--->onUncaughtExceptionHappened:" + thread + "<---", th);
                MyApplication.this.sysExcepHandler.uncaughtException(thread, new RuntimeException("black screen"));
            }
        });
    }

    public boolean isShowRealDepth() {
        return this.isShowRealDepth;
    }

    public void setShowRealDepth(boolean z) {
        this.isShowRealDepth = z;
    }

    @Override // com.tencent.mmkv.MMKVHandler
    public MMKVRecoverStrategic onMMKVCRCCheckFail(String str) {
        Log.d(TAG, "onMMKVCRCCheckFail() called with: mmapID = [" + str + "]");
        return MMKVRecoverStrategic.OnErrorDiscard;
    }

    @Override // com.tencent.mmkv.MMKVHandler
    public MMKVRecoverStrategic onMMKVFileLengthError(String str) {
        Log.d(TAG, "onMMKVFileLengthError() called with: mmapID = [" + str + "]");
        return MMKVRecoverStrategic.OnErrorDiscard;
    }

    @Override // com.tencent.mmkv.MMKVHandler
    public void mmkvLog(MMKVLogLevel mMKVLogLevel, String str, int i, String str2, String str3) {
        Log.d(TAG, "mmkvLog() called with: mmkvLogLevel = [" + mMKVLogLevel + "], s = [" + str + "], i = [" + i + "], s1 = [" + str2 + "], s2 = [" + str3 + "]");
    }
}
