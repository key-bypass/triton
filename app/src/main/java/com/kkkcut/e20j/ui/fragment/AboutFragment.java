package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import butterknife.BindView;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.us.R;
import com.liying.core.Command;
import com.liying.core.MachineInfo;
import com.liying.core.OperateType;
import com.liying.core.communication.OperationManager;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class AboutFragment extends BaseBackFragment {

    @BindView(R.id.tv_company)
    TextView tvCompany;

    @BindView(R.id.tv_db_version)
    TextView tvDbVersion;

    @BindView(R.id.tv_firmware)
    TextView tvFirmware;

    @BindView(R.id.tv_model_name)
    TextView tvModelName;

    @BindView(R.id.tv_serial)
    TextView tvSerial;

    @BindView(R.id.tv_soft_version)
    TextView tvSoftVersion;

    @BindView(R.id.tv_update_log)
    TextView tvUpdateLog;

    @BindView(R.id.webview)
    WebView webView;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_about_us;
    }

    public static AboutFragment newInstance(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("machineName", str);
        bundle.putString("series", str2);
        bundle.putString("companyStr", str3);
        AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.setArguments(bundle);
        return aboutFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.machine_info);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        String string = getArguments().getString("companyStr");
        if (TextUtils.isEmpty(string)) {
            string = "Copyright(c) 2020 Hunan Kukai Electromechanical co.,ltd.  All rights reserved";
        }
        if (MachineInfo.isE20Neutral(getContext())) {
            string = "";
        }
        this.tvCompany.setText(string);
        this.tvSoftVersion.setText(AppUtil.getVersionName(getContext()));
        getDbVersion();
        String string2 = getArguments().getString("machineName");
        if (!TextUtils.isEmpty(string2)) {
            this.tvModelName.setText(string2);
        }
        String string3 = getArguments().getString("series");
        if (!TextUtils.isEmpty(string3)) {
            this.tvSerial.setText(string3);
        }
        initUpdateLog();
        OperationManager.getInstance().sendOrder(Command.QueryFirmwareVersion(), OperateType.READ_FIRMWARE);
    }

    private void initUpdateLog() {
        addDisposable(Observable.fromCallable(new Callable<String>() { // from class: com.kkkcut.e20j.ui.fragment.AboutFragment.2
            @Override // java.util.concurrent.Callable
            public String call() throws Exception {
                return KeyInfoDaoManager.getInstance().getUpdateInfo();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.kkkcut.e20j.ui.fragment.AboutFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(String str) throws Exception {
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                if (!MachineInfo.isE9Standard(AboutFragment.this.getContext())) {
                    str = "<body style=\"margin:0;padding:0;background-color:#4b4d62;color:white;\">\n" + str + "</body>";
                }
                WebSettings settings = AboutFragment.this.webView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setDefaultTextEncodingName("UTF-8");
                AboutFragment.this.webView.loadData(str, "text/html; charset=UTF-8", null);
            }
        }));
    }

    private void getDbVersion() {
        addDisposable(Observable.fromCallable(new Callable<String>() { // from class: com.kkkcut.e20j.ui.fragment.AboutFragment.4
            @Override // java.util.concurrent.Callable
            public String call() throws Exception {
                String dbVersion = KeyInfoDaoManager.getInstance().getDbVersion();
                return TextUtils.isEmpty(dbVersion) ? "16.44" : dbVersion;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.kkkcut.e20j.ui.fragment.AboutFragment.3
            @Override // io.reactivex.functions.Consumer
            public void accept(String str) throws Exception {
                AboutFragment.this.tvDbVersion.setText(str);
            }
        }));
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        if (isVisible() && eventCenter.getEventCode() == 35) {
            String str = (String) eventCenter.getData();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.tvFirmware.setText(str);
        }
    }
}
