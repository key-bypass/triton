package com.kkkcut.e20j.ui.fragment;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.jakewharton.rxbinding3.view.RxView;
import com.kkkcut.e20j.DbBean.LocalDbVersion;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.dao.ResUpdateDaoManager;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.AppUpdateUtil;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import com.kkkcut.e20j.utils.ThemeUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class DataUpdateFragment extends BaseBackFragment {

    @BindView(R.id.bt_error_update)
    Button btErrorUpdate;

    @BindView(R.id.bt_imgs_update)
    Button btImgUpdate;

    @BindView(R.id.bt_main_db_update)
    Button btMainDbUpdate;

    @BindView(R.id.bt_res_update)
    Button btResUpdate;

    @BindView(R.id.bt_software_update)
    Button btSoftwareUpdate;

    @BindView(R.id.tv_name_app)
    TextView tvNameApp;

    @BindView(R.id.tv_name_err)
    TextView tvNameErr;

    @BindView(R.id.tv_name_imgs)
    TextView tvNameImgs;

    @BindView(R.id.tv_name_main_db)
    TextView tvNameMainDb;

    @BindView(R.id.tv_name_res)
    TextView tvNameRes;

    @BindView(R.id.tv_value_current_error)
    TextView tvValueCurrentError;

    @BindView(R.id.tv_value_current_imgs)
    TextView tvValueCurrentImg;

    @BindView(R.id.tv_value_current_main_db)
    TextView tvValueCurrentMainDb;

    @BindView(R.id.tv_value_current_res)
    TextView tvValueCurrentRes;

    @BindView(R.id.tv_value_current_software)
    TextView tvValueCurrentSoftware;

    @BindView(R.id.tv_value_new_error)
    TextView tvValueNewError;

    @BindView(R.id.tv_value_new_imgs)
    TextView tvValueNewImg;

    @BindView(R.id.tv_value_new_main_db)
    TextView tvValueNewMainDb;

    @BindView(R.id.tv_value_new_res)
    TextView tvValueNewRes;

    @BindView(R.id.tv_value_new_software)
    TextView tvValueNewSoftware;

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
                    DataUpdateFragment.this.tvNameMainDb.setText(DataUpdateFragment.this.getString(R.string.resup_data_res, localMainDbName));
                    String mainDbVersion = ResUpdateDaoManager.getInstance().getMainDbVersion(localMainDbName);
                    String svResVer = localDbVersion.getSvResVer();
                    if (TextUtils.isEmpty(mainDbVersion)) {
                        mainDbVersion = "0";
                    }
                    if (TextUtils.isEmpty(svResVer)) {
                        svResVer = "0";
                    }
                    DataUpdateFragment.this.tvValueCurrentMainDb.setText(mainDbVersion);
                    DataUpdateFragment.this.tvValueNewMainDb.setText(svResVer);
                    if (Float.parseFloat(svResVer) > Float.parseFloat(mainDbVersion)) {
                        DataUpdateFragment.this.tvValueNewMainDb.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.btMainDbUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.btMainDbUpdate.setVisibility(8);
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
                    DataUpdateFragment.this.tvValueCurrentRes.setText(resDbVersion);
                    DataUpdateFragment.this.tvValueNewRes.setText(svResVer2);
                    if (Float.parseFloat(svResVer2) > Float.parseFloat(resDbVersion)) {
                        DataUpdateFragment.this.tvValueNewRes.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.btResUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.btResUpdate.setVisibility(8);
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
                    DataUpdateFragment.this.tvValueCurrentError.setText(locResVer);
                    DataUpdateFragment.this.tvValueNewError.setText(svResVer3);
                    if (!ResUpdateUtils.localErrorDbExist() || Float.parseFloat(svResVer3) > Float.parseFloat(locResVer)) {
                        DataUpdateFragment.this.tvValueNewError.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.btErrorUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.btErrorUpdate.setVisibility(8);
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
                    DataUpdateFragment.this.tvValueCurrentImg.setText(locResVer2);
                    DataUpdateFragment.this.tvValueNewImg.setText(svResVer4);
                    if (Float.parseFloat(svResVer4) > Float.parseFloat(locResVer2)) {
                        DataUpdateFragment.this.tvValueNewImg.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.btImgUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.btImgUpdate.setVisibility(8);
                    }
                }
                if (TextUtils.equals(localDbVersion.getLocResName(), ResUpdateUtils.APP)) {
                    DataUpdateFragment.this.tvNameApp.setText(ResUpdateUtils.APP);
                    String versionName = AppUtil.getVersionName(DataUpdateFragment.this.getContext());
                    String svResVer5 = localDbVersion.getSvResVer();
                    if (TextUtils.isEmpty(versionName)) {
                        versionName = "0";
                    }
                    String str = TextUtils.isEmpty(svResVer5) ? "0" : svResVer5;
                    DataUpdateFragment.this.tvValueCurrentSoftware.setText(versionName);
                    DataUpdateFragment.this.tvValueNewSoftware.setText(str);
                    if (Float.parseFloat(str) > Float.parseFloat(versionName)) {
                        DataUpdateFragment.this.tvValueNewSoftware.setTextColor(DataUpdateFragment.this.getResources().getColor(R.color.color_ff205f));
                        DataUpdateFragment.this.btSoftwareUpdate.setVisibility(0);
                    } else {
                        DataUpdateFragment.this.btSoftwareUpdate.setVisibility(8);
                    }
                }
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.DataUpdateFragment.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ToastUtil.showToast(th.getMessage());
            }
        }));
        addDisposable(RxView.clicks(this.btSoftwareUpdate).throttleFirst(1L, TimeUnit.SECONDS).subscribe(new Consumer<Object>() { // from class: com.kkkcut.e20j.ui.fragment.DataUpdateFragment.4
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
                AppUpdateUtil.checkUpdate(DataUpdateFragment.this.getActivity());
            }
        }));
        update(this.btMainDbUpdate, this.tvValueCurrentMainDb, this.tvValueNewMainDb, ResUpdateDaoManager.getInstance().getMainDb(ResUpdateUtils.getLocalMainDbName()));
        update(this.btResUpdate, this.tvValueCurrentRes, this.tvValueNewRes, ResUpdateDaoManager.getInstance().getResDb());
        update(this.btErrorUpdate, this.tvValueCurrentError, this.tvValueNewError, ResUpdateDaoManager.getInstance().getErrorDb());
        update(this.btImgUpdate, this.tvValueCurrentImg, this.tvValueNewImg, ResUpdateDaoManager.getInstance().getImgListDb(), true);
    }

    private void update(View view, TextView textView, TextView textView2, LocalDbVersion localDbVersion) {
        update(view, textView, textView2, localDbVersion, false);
    }

    private void update(final View view, final TextView textView, final TextView textView2, final LocalDbVersion localDbVersion, final boolean z) {
        addDisposable(RxView.clicks(view).throttleFirst(1L, TimeUnit.SECONDS).subscribe(new Consumer<Object>() { // from class: com.kkkcut.e20j.ui.fragment.DataUpdateFragment.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
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
            }
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