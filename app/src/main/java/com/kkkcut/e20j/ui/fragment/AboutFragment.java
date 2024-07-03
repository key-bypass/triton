package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.cutting.machine.Command;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.OperateType;
import com.cutting.machine.communication.OperationManager;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.us.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class AboutFragment extends BaseBackFragment {

    TextView tvCompany;

    TextView tvDbVersion;

    TextView tvFirmware;

    TextView tvModelName;

    TextView tvSerial;

    TextView tvSoftVersion;

    TextView tvUpdateLog;

    WebView webView;

    @Override // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
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

    @Override // com.kkkcut.e20j.p005ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.machine_info);
    }

    @Override // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
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
        addDisposable((Disposable) Observable.fromCallable(new Callable<String>() { // from class: com.kkkcut.e20j.ui.fragment.AboutFragment.2
            @Override // java.util.concurrent.Callable
            public String call() {
                return KeyInfoDaoManager.getInstance().getUpdateInfo();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.kkkcut.e20j.ui.fragment.AboutFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(String str) {
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
        addDisposable((Disposable) Observable.fromCallable(() -> {
                String dbVersion = KeyInfoDaoManager.getInstance().getDbVersion();
                return TextUtils.isEmpty(dbVersion) ? "16.44" : dbVersion;

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe( str -> {
                AboutFragment.this.tvDbVersion.setText(str);

        }));
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    protected void onEventComing(EventCenter<?> eventCenter) {
        if (isVisible() && eventCenter.getEventCode() == 35) {
            String str = (String) eventCenter.getData();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.tvFirmware.setText(str);
        }
    }
}