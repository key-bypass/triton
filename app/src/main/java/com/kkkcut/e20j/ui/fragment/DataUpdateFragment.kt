package com.kkkcut.e20j.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.jakewharton.rxbinding3.view.RxView;
import com.kkkcut.e20j.DbBean.LocalDbVersion;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.dao.ResUpdateDaoManager;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.us.databinding.FragmentUpdateBinding;
import com.kkkcut.e20j.utils.AppUpdateUtil;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import com.kkkcut.e20j.utils.ThemeUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class DataUpdateFragment extends BaseBackFragment {
    private FragmentUpdateBinding binding;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_update;
    }

    public static DataUpdateFragment newInstance() {
        return new DataUpdateFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        addDisposable(Observable.create(new ObservableOnSubscribe<LocalDbVersion>() { // from class: com.kkkcut.e20j.ui.fragment.DataUpdateFragment.3
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<LocalDbVersion> observableEmitter) throws Exception {
                Iterator<LocalDbVersion> it = ResUpdateDaoManager.getInstance().getLocalDbList().iterator();
                while (it.hasNext()) {
                    observableEmitter.onNext(it.next());
                }
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LocalDbVersion>() { // from class: com.kkkcut.e20j.ui.fragment.DataUpdateFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(LocalDbVersion localDbVersion) throws Exception {
                String localMainDbName = ResUpdateUtils.getLocalMainDbName();
                if (TextUtils.equals(localDbVersion.getSvResName(), localMainDbName)) {
                    DataUpdateFragment.this.binding.tvNameMainDb.setText(DataUpdateFragment.this.getString(R.string.resup_data_res, localMainDbName));
                    String mainDbVersion = ResUpdateDaoManager.getInstance().getMainDbVersion(localMainDbName);
                    String svResVer = localDbVersion.getSvResVer();
                    if (TextUtils.isEmpty(mainDbVersion)) {
                        mainDbVersion = "0";
                    }
                    if (TextUtils.isEmpty(svResVer)) {
                        svResVer = "0";
                    }
                    DataUpdateFragment.this.binding.tvValueCurrentMainDb.setText(mainDbVersion);
                    DataUpdateFragment.this.binding.tvValueNewMainDb.setText(svResVer);
                    if (Float.parseFloat(svResVer) > Float.parseFloat(mainDbVersion)) {
                        DataUpdateFragment.this.binding.tvValueNewMainDb.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.binding.btMainDbUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.binding.btMainDbUpdate.setVisibility(8);
                    }
                }
                if (TextUtils.equals(localDbVersion.getSvResName(), ResUpdateUtils.RES)) {
                    String resDbVersion = ResUpdateDaoManager.getInstance().getResDbVersion();
                    String svResVer2 = localDbVersion.getSvResVer();
                    if (TextUtils.isEmpty(resDbVersion)) {
                        resDbVersion = "0";
                    }
                    if (TextUtils.isEmpty(svResVer2)) {
                        svResVer2 = "0";
                    }
                    DataUpdateFragment.this.binding.tvValueCurrentRes.setText(resDbVersion);
                    DataUpdateFragment.this.binding.tvValueNewRes.setText(svResVer2);
                    if (Float.parseFloat(svResVer2) > Float.parseFloat(resDbVersion)) {
                        DataUpdateFragment.this.binding.tvValueNewRes.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.binding.btResUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.binding.btResUpdate.setVisibility(8);
                    }
                }
                if (TextUtils.equals(localDbVersion.getSvResName(), ResUpdateUtils.ERROR_CODE)) {
                    String locResVer = localDbVersion.getLocResVer();
                    String svResVer3 = localDbVersion.getSvResVer();
                    if (TextUtils.isEmpty(locResVer)) {
                        locResVer = "0";
                    }
                    if (TextUtils.isEmpty(svResVer3)) {
                        svResVer3 = "0";
                    }
                    DataUpdateFragment.this.binding.tvValueCurrentError.setText(locResVer);
                    DataUpdateFragment.this.binding.tvValueNewError.setText(svResVer3);
                    if (!ResUpdateUtils.localErrorDbExist() || Float.parseFloat(svResVer3) > Float.parseFloat(locResVer)) {
                        DataUpdateFragment.this.binding.tvValueNewError.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.binding.btErrorUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.binding.btErrorUpdate.setVisibility(8);
                    }
                }
                if (TextUtils.equals(localDbVersion.getLocResName(), ResUpdateUtils.IMAGE_DB)) {
                    String locResVer2 = localDbVersion.getLocResVer();
                    String svResVer4 = localDbVersion.getSvResVer();
                    if (TextUtils.isEmpty(locResVer2)) {
                        locResVer2 = "0";
                    }
                    if (TextUtils.isEmpty(svResVer4)) {
                        svResVer4 = "0";
                    }
                    DataUpdateFragment.this.binding.tvValueCurrentImgs.setText(locResVer2);
                    DataUpdateFragment.this.binding.tvValueNewImgs.setText(svResVer4);
                    if (Float.parseFloat(svResVer4) > Float.parseFloat(locResVer2)) {
                        DataUpdateFragment.this.binding.tvValueNewImgs.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.binding.btImgsUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.binding.btImgsUpdate.setVisibility(8);
                    }
                }
                if (TextUtils.equals(localDbVersion.getLocResName(), ResUpdateUtils.APP)) {
                    DataUpdateFragment.this.binding.tvNameApp.setText(ResUpdateUtils.APP);
                    String versionName = AppUtil.getVersionName(DataUpdateFragment.this.getContext());
                    String svResVer5 = localDbVersion.getSvResVer();
                    if (TextUtils.isEmpty(versionName)) {
                        versionName = "0";
                    }
                    String str = TextUtils.isEmpty(svResVer5) ? "0" : svResVer5;
                    DataUpdateFragment.this.binding.tvValueCurrentSoftware.setText(versionName);
                    DataUpdateFragment.this.binding.tvValueNewSoftware.setText(str);
                    if (Float.parseFloat(str) > Float.parseFloat(versionName)) {
                        DataUpdateFragment.this.binding.tvValueNewSoftware.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.binding.btSoftwareUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.binding.btSoftwareUpdate.setVisibility(8);
                    }
                }
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.DataUpdateFragment.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ToastUtil.showToast(th.getMessage());
            }
        }));
        addDisposable((Disposable) RxView.clicks(this.binding.btSoftwareUpdate).throttleFirst(1L, TimeUnit.SECONDS).subscribe(obj -> {
            AppUpdateUtil.checkUpdate(DataUpdateFragment.this.getActivity());
        }));
        update(this.binding.btMainDbUpdate, this.binding.tvValueCurrentMainDb, this.binding.tvValueNewMainDb, ResUpdateDaoManager.getInstance().getMainDb(ResUpdateUtils.getLocalMainDbName()));
        update(this.binding.btResUpdate, this.binding.tvValueCurrentRes, this.binding.tvValueNewRes, ResUpdateDaoManager.getInstance().getResDb());
        update(this.binding.btErrorUpdate, this.binding.tvValueCurrentError, this.binding.tvValueNewError, ResUpdateDaoManager.getInstance().getErrorDb());
        update(this.binding.btImgsUpdate, this.binding.tvValueCurrentImgs, this.binding.tvValueNewImgs, ResUpdateDaoManager.getInstance().getImgListDb(), true);
    }

    private void update(View view, TextView textView, TextView textView2, LocalDbVersion localDbVersion) {
        update(view, textView, textView2, localDbVersion, false);
    }

    private void update(final View view, final TextView textView, final TextView textView2, final LocalDbVersion localDbVersion, final boolean z) {
        addDisposable((Disposable) RxView.clicks(view).throttleFirst(1L, TimeUnit.SECONDS).subscribe(obj -> {
            ResUpdateUtils.download(DataUpdateFragment.this.getContext(), localDbVersion, new ResUpdateUtils.DataBaseUpdateListener() { // from class: com.kkkcut.e20j.ui.fragment.DataUpdateFragment.5.1
                private ProgressDialog progressDialog;

                @Override // com.kkkcut.e20j.utils.ResUpdateUtils.DataBaseUpdateListener
                public void start() {
                    this.progressDialog = DataUpdateFragment.this.getProgressDialog(localDbVersion);
                }

                @Override // com.kkkcut.e20j.utils.ResUpdateUtils.DataBaseUpdateListener
                public void progress(int i) {
                    this.progressDialog.setProgress(i);
                }

                @Override // com.kkkcut.e20j.utils.ResUpdateUtils.DataBaseUpdateListener
                public void finish() {
                    view.setVisibility(8);
                    this.progressDialog.dismiss();
                    textView.setTextColor(ThemeUtils.getColor(DataUpdateFragment.this.getContext(), R.attr.textColor_ffffff_333333));
                    textView.setText(localDbVersion.getSvResVer());
                    textView2.setTextColor(ThemeUtils.getColor(DataUpdateFragment.this.getContext(), R.attr.textColor_ffffff_333333));
                    ToastUtil.showToast(R.string.update_finish);
                }

                @Override // com.kkkcut.e20j.utils.ResUpdateUtils.DataBaseUpdateListener
                public void error(Throwable th) {
                    this.progressDialog.dismiss();
                    ToastUtil.showToast(th.getMessage());
                }
            }, z);

        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProgressDialog getProgressDialog(LocalDbVersion localDbVersion) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        String svResName = localDbVersion.getSvResName();
        String localMainDbName = ResUpdateUtils.getLocalMainDbName();
        if (TextUtils.equals(localDbVersion.getSvResName(), localMainDbName)) {
            svResName = getString(R.string.resup_data_res, localMainDbName);
        } else if (TextUtils.equals(localDbVersion.getSvResName(), ResUpdateUtils.RES)) {
            svResName = getString(R.string.resup_resdb);
        } else if (TextUtils.equals(localDbVersion.getSvResName(), ResUpdateUtils.ERROR_CODE)) {
            svResName = getString(R.string.resup_error_code);
        } else if (TextUtils.equals(localDbVersion.getSvResName(), ResUpdateUtils.IMAGE_DB)) {
            svResName = getString(R.string.resup_imglist);
        }
        progressDialog.setProgressStyle(1);
        progressDialog.setTitle(svResName);
        progressDialog.setMessage(getString(R.string.downloading));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.ResUpdate);
    }
}
