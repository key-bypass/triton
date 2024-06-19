package com.kkkcut.e20j.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.us.R;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;

/* loaded from: classes.dex */
public class AppUpdateUtil {
    public static void checkUpdate(Context context) {
        showUpdateServerDialog(context);
    }

    private static void showUpdateServerDialog(final Context context) {
        new AlertDialog.Builder(context).setTitle(R.string.please_select_upgrade_server).setItems(new String[]{context.getString(R.string.main_server)}, new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.utils.AppUpdateUtil$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AppUpdateUtil.lambda$showUpdateServerDialog$0(context, dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showUpdateServerDialog$0(Context context, DialogInterface dialogInterface, int i) {
        if (i == 0) {
            tryPgyUpgrade(context);
        } else {
            tryPgyUpgrade(context);
        }
    }

    private static void tryPgyUpgrade(Context context) {
        try {
            PgyCheckUpgrade(context);
        } catch (Exception e) {
            ToastUtil.showToast(e.getMessage());
        }
    }

    private static void PgyCheckUpgrade(final Context context) {
        new PgyUpdateManager.Builder().setForced(false).setUserCanRetry(true).setDeleteHistroyApk(true).setUpdateManagerListener(new UpdateManagerListener() { // from class: com.kkkcut.e20j.utils.AppUpdateUtil.1
            @Override // com.pgyersdk.update.UpdateManagerListener
            public void onNoUpdateAvailable() {
                Log.d("pgyer", "there is no new version");
                ToastUtil.showToast(context.getString(R.string.no_new_version));
            }

            @Override // com.pgyersdk.update.UpdateManagerListener
            public void onUpdateAvailable(final AppBean appBean) {
                int parseInt = Integer.parseInt(appBean.getVersionCode());
                Log.d("pgyer", "there is new version can updatenew versionCode is " + parseInt);
                if (Integer.parseInt(AppUtil.getVersionCode(context)) >= parseInt) {
                    ToastUtil.showToast(context.getString(R.string.no_new_version));
                    return;
                }
                new AlertDialog.Builder(context).setTitle(R.string.have_new_version).setMessage(context.getString(R.string.version) + appBean.getVersionName()).setNegativeButton(R.string.next_time, (DialogInterface.OnClickListener) null).setPositiveButton(context.getString(R.string.upgrade), new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.utils.AppUpdateUtil.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PgyUpdateManager.downLoadApk(appBean.getDownloadURL());
                    }
                }).create().show();
            }

            @Override // com.pgyersdk.update.UpdateManagerListener
            public void checkUpdateFailed(Exception exc) {
                Log.e("pgyer", "check update failed ", exc);
                ToastUtil.showToast(exc.getMessage());
            }
        }).register();
    }
}
