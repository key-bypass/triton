package com.pgyersdk.update;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.widget.TextView;
import androidx.core.view.InputDeviceCompat;
import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.utils.LogUtils;
import com.pgyersdk.update.javabean.AppBean;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerUpdateListener.java */
/* renamed from: com.pgyersdk.update.i */
/* loaded from: classes2.dex */
public class PgyerUpdateListener implements UpdateManagerListener {

    /* renamed from: a */
    static volatile AlertDialog f727a;

    /* renamed from: b */
    private boolean f728b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PgyerUpdateListener(boolean z) {
        this.f728b = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m385b() {
        if (f727a != null) {
            f727a.dismiss();
            f727a = null;
        }
    }

    @Override // com.pgyersdk.update.UpdateManagerListener
    public void checkUpdateFailed(Exception exc) {
    }

    @Override // com.pgyersdk.update.UpdateManagerListener
    public void onNoUpdateAvailable() {
    }

    @Override // com.pgyersdk.update.UpdateManagerListener
    public void onUpdateAvailable(AppBean appBean) {
        if (f727a == null) {
            m384a(appBean);
            if (f727a != null) {
                f727a.show();
            }
        }
    }

    /* renamed from: a */
    private Dialog m384a(AppBean appBean) {
        String m151a = Strings.m151a(514);
        if (!appBean.getReleaseNote().equals("")) {
            m151a = appBean.getReleaseNote();
        }
        String downloadURL = appBean.getDownloadURL();
        if (PgyerActivityManager.getInstance().getCurrentActivity() == null) {
            LogUtils.m220d("PgyerSDK", "There is get current activity is null, please check your config");
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(PgyerActivityManager.getInstance().getCurrentActivity());
        builder.setTitle(Strings.m151a(InputDeviceCompat.SOURCE_DPAD));
        TextView textView = new TextView(PgyerActivityManager.getInstance().getCurrentActivity());
        textView.setText(Strings.m151a(InputDeviceCompat.SOURCE_DPAD));
        textView.setTextSize(22.0f);
        textView.setTextColor(Color.parseColor("#56bc94"));
        boolean z = false;
        textView.setPadding(30, 20, 0, 20);
        textView.setBackgroundColor(Color.parseColor("#56bc94"));
        builder.setMessage(m151a);
        if (!this.f728b && !appBean.isShouldForceToUpdate()) {
            z = true;
        }
        if (z) {
            builder.setNegativeButton(Strings.m151a(515), new DialogInterfaceOnClickListenerC2087f(this));
        }
        builder.setPositiveButton(Strings.m151a(516), new DialogInterfaceOnClickListenerC2088g(this, downloadURL));
        builder.setCancelable(z);
        f727a = builder.create();
        f727a.setOnDismissListener(new DialogInterfaceOnDismissListenerC2089h(this));
        return f727a;
    }
}
