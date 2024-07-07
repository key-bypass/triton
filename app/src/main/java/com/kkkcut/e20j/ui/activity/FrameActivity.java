package com.kkkcut.e20j.ui.activity;

import static io.reactivex.rxjava3.schedulers.Schedulers.io;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kkkcut.e20j.Constant;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.LogUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.androidquick.ui.receiver.NetStateReceiver;
import com.kkkcut.e20j.bean.gsonBean.CertificationRes;
import com.kkkcut.e20j.driver.communication.OnSerialMessageComingListener;
import com.kkkcut.e20j.driver.pl2303.UsbSerialManager;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.dialog.CertificationDialog;
import com.kkkcut.e20j.ui.dialog.ErrorDialog;
import com.kkkcut.e20j.ui.dialog.ForbidDialog;
import com.kkkcut.e20j.ui.dialog.LoadingDialog;
import com.kkkcut.e20j.ui.dialog.OperatorRemindDialog;
import com.kkkcut.e20j.ui.fragment.CalibrationFragment;
import com.kkkcut.e20j.ui.fragment.MainE9Fragment;
import com.kkkcut.e20j.ui.fragment.MainFragment;
import com.kkkcut.e20j.ui.fragment.MessageFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.AssetVersionUtil;
import com.kkkcut.e20j.utils.GetUUID;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.kkkcut.e20j.utils.ZipUtils;
import com.cutting.machine.Command;
import com.cutting.machine.DialogBtnCallBack;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.OperateType;
import com.cutting.machine.communication.OperationManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.yokeyword.fragmentation.ISupportFragment;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FrameActivity extends BaseCustomKeyBoardActivity implements OnSerialMessageComingListener {
    protected static String TAG = "FrameActivity";
    private CertificationDialog certificationDialog;
    private ErrorDialog errorDialog;

    ImageView ivHome;

    ImageView ivMenu;

    ImageView ivMessage;
    private LoadingDialog loadingDialog;

    TextView tvBack;

    TextView tvLogo;

    TextView tvTitle;

    LinearLayout layout;
    private long DOUBLE_CLICK_TIME = 0;
    long[] mHits = new long[10];

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected int getContentViewLayoutID() {
        return R.layout.activity_frame;
    }

    @Override // com.kkkcut.e20j.base.BaseActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected boolean isLoadDefaultTitleBar() {
        return false;
    }

    @Override // com.kkkcut.e20j.driver.communication.OnSerialMessageComingListener
    public void onSerialMessageComing(String str, OperateType operateType) {
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.layout = findViewById(R.id.activity_frame);
        this.ivHome = findViewById(R.id.iv_home);
        this.ivMenu = findViewById(R.id.iv_menu);
        this.ivMessage = findViewById(R.id.iv_message);
        this.tvBack = findViewById(R.id.tv_back);
        this.tvLogo = findViewById(R.id.tv_logo);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void initViewsAndEvents() {
        if (MachineInfo.isE9Standard(this)) {
            loadRootFragment(R.id.content_frame, MainE9Fragment.newInstance());
        } else {
            loadRootFragment(R.id.content_frame, MainFragment.newInstance());
        }
        NetStateReceiver.registerNetworkStateReceiver(this.mContext);
        checkCodeDb();
        if (!AppUtil.isApkInDebug(this)) {
            showLoadingDialog(getString(R.string.waitting));
        }
        UsbSerialManager.getInstance().init(this);
        if (SPUtils.getInt(SpKeys.CERTIFICATION_STATUS, 0) != 0) {
            showCertificationDialog(getString(R.string.please_connect_network_for_authentication_device_has_been_locked), false, new CertificationDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.1
                @Override // com.kkkcut.e20j.ui.dialog.CertificationDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        FrameActivity.this.startCertificationFromNet();
                    }
                }
            });
        } else {
            int i = SPUtils.getInt(SpKeys.TOTAL_CUT_NUMBER, 200);
            int i2 = SPUtils.getInt(SpKeys.CUT_NUMBER);
            if (i2 < i * 0.3d && i2 > 0) {
                showCertificationDialog(getString(R.string.please_connect_to_the_network_for_authentication_is_about_to_be_locked), true, new CertificationDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.2
                    @Override // com.kkkcut.e20j.ui.dialog.CertificationDialog.DialogBtnCallBack
                    public void onDialogButClick(boolean z) {
                        if (z) {
                            FrameActivity.this.startCertificationFromNet();
                        }
                    }
                });
            }
        }
        if (SPUtils.getBoolean(SpKeys.TABETS_OPERATION_REMIND, false) || MachineInfo.isE9Standard(this)) {
            return;
        }
        showOperatorRemind(0);
    }

    private void showOperatorRemind(int i) {
        OperatorRemindDialog operatorRemindDialog = new OperatorRemindDialog(this);
        operatorRemindDialog.show();
        operatorRemindDialog.setRemindText(getString(R.string.caution));
        operatorRemindDialog.setType(i);
        operatorRemindDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void checkHaveNewMessage() {
        this.ivMessage.setImageResource(ThemeUtils.getResId(this, R.attr.icon_message));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCertificationFromNet() {
        String string = SPUtils.getString("series");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        addDisposable(((Apis) RetrofitManager.getInstance().createApi(Apis.class)).certification(TUitls.certification(GetUUID.getUUID(), string, "XX", "0.0.0.0")).subscribeOn(io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CertificationRes>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.3
            @Override // io.reactivex.functions.Consumer
            public void accept(CertificationRes certificationRes) throws Exception {
                String str;
                Log.i(FrameActivity.TAG, "accept: " + certificationRes.getMsg());
                String cutNuber = certificationRes.getCutNuber();
                if ("0".equals(certificationRes.getCode())) {
                    SPUtils.put(SpKeys.CUT_NUMBER, Integer.parseInt(cutNuber));
                    SPUtils.put(SpKeys.TOTAL_CUT_NUMBER, Integer.parseInt(cutNuber));
                    SPUtils.put(SpKeys.CERTIFICATION_STATUS, 0);
                    FrameActivity.this.dissmissCertificationDislog();
                    return;
                }
                SPUtils.put(SpKeys.CERTIFICATION_STATUS, 1);
                FrameActivity.this.dissmissCertificationDislog();
                String msg = certificationRes.getMsg();
                if (!TextUtils.isEmpty(msg) && msg.contains("Parameter")) {
                    EventBus.getDefault().post(new EventCenter(40));
                    return;
                }
                String[] split = msg.split("\\n");
                if (MachineInfo.isChineseMachine()) {
                    str = split[0];
                } else if (split.length == 2) {
                    str = split[1];
                } else {
                    str = split[0];
                }
                FrameActivity.this.showCertificationFailedDialog(str);
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.4
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                Log.i(FrameActivity.TAG, "error: " + th.getMessage());
            }
        }));
    }

    private void checkCodeDb() {
        addDisposable(Observable.zip(getAssetsVersionObservable(), getLocalVersionObservable(), new BiFunction<Integer, Integer, Boolean>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.7
            @Override // io.reactivex.functions.BiFunction
            public Boolean apply(Integer num, Integer num2) throws Exception {
                return Boolean.valueOf(num.intValue() > num2.intValue());
            }
        }).subscribeOn(io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    FrameActivity.this.updateCodeDatabase();
                    LogUtil.d(FrameActivity.TAG, "升级");
                } else {
                    LogUtil.d(FrameActivity.TAG, "齿号数据库无需升级");
                }
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.6
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCodeDatabase() {
        addDisposable(Observable.fromCallable(new Callable<Boolean>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.11
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(FileUtil.copyAssetToSDCard(FrameActivity.this.getAssets(), Constant.ZIP_NAME_CODE_DB, Constant.CODE_DB_ZIP_PATH));
            }
        }).subscribeOn(io()).map(new Function<Boolean, Boolean>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.10
            @Override // io.reactivex.functions.Function
            public Boolean apply(Boolean bool) throws Exception {
                File file = new File(Constant.CODE_DATABASE_PATH);
                if (!file.exists()) {
                    file.mkdir();
                }
                List<File> unzipFile = ZipUtils.unzipFile(Constant.CODE_DB_ZIP_PATH, Constant.CODE_DATABASE_PATH);
                if (unzipFile != null && unzipFile.size() > 0) {
                    return true;
                }
                return false;
            }
        }).map(new Function<Boolean, Boolean>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.9
            @Override // io.reactivex.functions.Function
            public Boolean apply(Boolean bool) throws Exception {
                return Boolean.valueOf(FileUtil.copyAssetToSDCard(FrameActivity.this.getAssets(), Constant.JSON_CODE_DB_UPDATE, new File(FrameActivity.this.getFilesDir(), Constant.JSON_CODE_DB_UPDATE).getPath()));
            }
        }).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.8
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    LogUtil.d(FrameActivity.TAG, "齿号数据库升级完成");
                }
            }
        }));
    }

    private Observable<Integer> getAssetsVersionObservable() {
        return Observable.fromCallable(new Callable<Integer>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                return Integer.valueOf(FrameActivity.this.getAssetsDbVersion());
            }
        });
    }

    private Observable<Integer> getLocalVersionObservable() {
        return Observable.fromCallable(new Callable<Integer>() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.13
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                return Integer.valueOf(FrameActivity.this.getLocalDbVersion());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLocalDbVersion() {
        File file = new File(getFilesDir(), Constant.JSON_CODE_DB_UPDATE);
        if (!file.exists()) {
            return 0;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            return new JSONObject(new String(bArr)).getInt("version");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAssetsDbVersion() {
        try {
            InputStream open = getAssets().open(Constant.JSON_CODE_DB_UPDATE);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            return new JSONObject(new String(bArr)).getInt("version");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void showLogo() {
        this.tvLogo.setVisibility(0);
        this.ivMenu.setVisibility(0);
        this.ivMessage.setVisibility(0);
        this.tvBack.setVisibility(8);
        this.ivHome.setVisibility(8);
        setTittle("");
    }

    public void hideLogo() {
        this.tvLogo.setVisibility(8);
        this.ivMenu.setVisibility(8);
        this.ivMessage.setVisibility(8);
        this.tvBack.setVisibility(0);
        this.ivHome.setVisibility(0);
    }

    public void setMachineName(String str) {
        if (this.tvLogo == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.tvLogo.setText(str);
    }

    public void setLogo(int i) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.tvLogo.setCompoundDrawablePadding(20);
        this.tvLogo.setCompoundDrawables(drawable, null, null, null);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 82) {
            return true;
        }
        if (keyEvent.getKeyCode() == 4) {
            ISupportFragment topFragment = getTopFragment();
            if ((topFragment instanceof MainFragment) || (topFragment instanceof MainE9Fragment)) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.DOUBLE_CLICK_TIME > 1500) {
                    ToastUtil.showToast(R.string.press_again_to_exit);
                    this.DOUBLE_CLICK_TIME = currentTimeMillis;
                } else {
                    UsbSerialManager.getInstance().stop();
                    System.exit(0);
                }
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.kkkcut.e20j.base.BaseActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void onEventComing(EventCenter<?> eventCenter) {
        int eventCode = eventCenter.getEventCode();
        if (eventCode == 12) {
            if (AppUtil.isApkInDebug(this)) {
                return;
            }
            showErrorDialog(R.drawable.error_1, getString(R.string.no_device_found), getString(R.string.retry), new DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.14
                @Override // com.cutting.machine.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        UsbSerialManager.getInstance().init(FrameActivity.this);
                    }
                }
            });
            return;
        }
        if (eventCode == 14) {
            if (AppUtil.isApkInDebug(this)) {
                return;
            }
            showErrorDialog(R.drawable.error_1, getString(R.string.device_not_connected), getString(R.string.retry), new DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.15
                @Override // com.cutting.machine.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        UsbSerialManager.getInstance().init(FrameActivity.this);
                    }
                }
            });
            return;
        }
        if (eventCode == 19) {
            ForbidDialog forbidDialog = new ForbidDialog(this);
            forbidDialog.setRemindImg(R.drawable.error_1);
            forbidDialog.setTip(getString(R.string.init_failed_restart_machine));
            forbidDialog.show();
            return;
        }
        if (eventCode == 20) {
            MyApplication.getInstance().setSerialInit(true);
            dissmissLoadingDialog();
            return;
        }
        if (eventCode == 41) {
            AppUtil.isApkInDebug(this);
            return;
        }
        if (eventCode == 42) {
            checkHaveNewMessage();
            return;
        }
        switch (eventCode) {
            case 35:
                String assetsFrimVerstion = AssetVersionUtil.getAssetsFrimVerstion(MyApplication.getInstance());
                String str = (String) eventCenter.getData();
                if (TextUtils.isEmpty(assetsFrimVerstion)) {
                    OperationManager.getInstance().sendOrder(Command.OpenDoorCuttingSettings(1));
                    return;
                } else if (!TextUtils.equals(assetsFrimVerstion, str)) {
                    FirmwareUpdateActivity.start(this, str, assetsFrimVerstion);
                    return;
                } else {
                    OperationManager.getInstance().sendOrder(Command.OpenDoorCuttingSettings(SPUtils.getBoolean(SpKeys.COVER, true) ? 1 : 0));
                    return;
                }
            case 36:
                showCertificationDialog(getString(R.string.please_connect_network_for_authentication_device_has_been_locked), false, new CertificationDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.16
                    @Override // com.kkkcut.e20j.ui.dialog.CertificationDialog.DialogBtnCallBack
                    public void onDialogButClick(boolean z) {
                        if (z) {
                            FrameActivity.this.startCertificationFromNet();
                        }
                    }
                });
                return;
            case 37:
                startCertificationFromNet();
                return;
            default:
                return;
        }
    }

    private void showCertificationDialog(String str, boolean z, CertificationDialog.DialogBtnCallBack dialogBtnCallBack) {
        if (AppUtil.isApkInDebug(this)) {
            return;
        }
        if (this.certificationDialog == null) {
            this.certificationDialog = new CertificationDialog(this);
        }
        this.certificationDialog.setTip(str);
        this.certificationDialog.setClickDismiss(z);
        this.certificationDialog.setConfirmStr(getString(R.string.authenticate));
        this.certificationDialog.setDialogBtnCallback(dialogBtnCallBack);
        if (this.certificationDialog.isShowing()) {
            return;
        }
        this.certificationDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dissmissCertificationDislog() {
        CertificationDialog certificationDialog = this.certificationDialog;
        if (certificationDialog == null || !certificationDialog.isShowing()) {
            return;
        }
        this.certificationDialog.dismiss();
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_home /* 2131362304 */:
                goHome();
                return;
            case R.id.iv_menu /* 2131362319 */:
                EventBus.getDefault().post(new EventCenter(10));
                return;
            case R.id.iv_message /* 2131362320 */:
                goMessage();
                return;
            case R.id.tv_back /* 2131362887 */:
                onBack();
                return;
            case R.id.tv_title /* 2131363010 */:
                long[] jArr = this.mHits;
                System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
                long[] jArr2 = this.mHits;
                jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
                if (this.mHits[0] >= SystemClock.uptimeMillis() - 2000) {
                    File file = new File(Constant.CONFIG_PATH);
                    if (file.exists() && file.delete()) {
                        ToastUtil.showToast(R.string.configuration_file_delete_success);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    private void goMessage() {
        start(MessageFragment.newInstance());
    }

    private void goCalibrate() {
        start(CalibrationFragment.newInstance());
    }

    public void goHome() {
        ISupportFragment findFragment;
        if (MachineInfo.isE9Standard(this)) {
            findFragment = findFragment(MainE9Fragment.class);
        } else {
            findFragment = findFragment(MainFragment.class);
        }
        if (findFragment != null) {
            start(findFragment, 2);
        } else {
            start(MainFragment.newInstance());
        }
    }

    public void onBack() {
        onBackPressed();
    }

    public void setTittle(String str) {
        TextView textView = this.tvTitle;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setTittle(int i) {
        TextView textView = this.tvTitle;
        if (textView != null) {
            textView.setText(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCertificationFailedDialog(String str) {
        ForbidDialog forbidDialog = new ForbidDialog(this);
        forbidDialog.setRemindImg(R.drawable.error_1);
        forbidDialog.setTip(str);
        forbidDialog.show();
    }

    private void showErrorDialog(int i, String str, String str2, DialogBtnCallBack dialogBtnCallBack) {
        if (this.errorDialog == null) {
            this.errorDialog = new ErrorDialog(this);
        }
        this.errorDialog.setRemindImg(i);
        this.errorDialog.setTip(str);
        this.errorDialog.setConfirmStr(str2);
        this.errorDialog.setDialogBtnCallback(dialogBtnCallBack);
        if (this.errorDialog.isShowing()) {
            return;
        }
        this.errorDialog.show();
    }

    @Override // com.kkkcut.e20j.base.BaseFActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
        UsbSerialManager.getInstance().stop();
        NetStateReceiver.unRegisterNetworkStateReceiver(this.mContext);
    }

    public TextView getTvBack() {
        return this.tvBack;
    }

    @Override // com.kkkcut.e20j.ui.activity.BaseCustomKeyBoardActivity
    public void showLoadingDialog(String str) {
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(this);
        }
        if (!StringUtil.isEmpty(str)) {
            this.loadingDialog.setTip(str);
        }
        this.loadingDialog.setCancelable(false);
        if (!this.loadingDialog.isShowing()) {
            this.loadingDialog.show();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity.17
            @Override // java.lang.Runnable
            public void run() {
                FrameActivity.this.dissmissLoadingDialog();
            }
        }, 15000L);
    }

    @Override // com.kkkcut.e20j.ui.activity.BaseCustomKeyBoardActivity
    public void dissmissLoadingDialog() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.dismiss();
    }
}
