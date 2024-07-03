package com.kkkcut.e20j.pyger;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.update.DownloadFileListener;

import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDownloadListener.java */
/* renamed from: com.pgyersdk.update.e */
/* loaded from: classes2.dex */
public class C2086e implements DownloadFileListener {

    /* renamed from: a */
    private ProgressDialog f718a;

    /* renamed from: b */
    private Dialog f719b;

    /* renamed from: c */
    private boolean f720c;

    /* renamed from: d */
    private Activity f721d;

    /* renamed from: e */
    private Activity f722e;

    public C2086e(boolean z) {
        this.f720c = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m380a(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uriForFile = FileProvider.getUriForFile(PgyerProvider.f436a, PgyerProvider.f436a.getApplicationContext().getPackageName() + ".fileProvider", file);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(1);
            intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        PgyerProvider.f436a.startActivity(intent);
    }

    /* renamed from: b */
    private Dialog m381b() {
        this.f722e = PgyerActivityManager.getInstance().getCurrentActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f722e);
        builder.setTitle(Strings.m151a(256));
        builder.setMessage(Strings.m151a(257));
        builder.setNegativeButton(Strings.m151a(258), new DialogInterfaceOnClickListenerC2084c(this));
        builder.setPositiveButton(Strings.m151a(259), new DialogInterfaceOnClickListenerC2085d(this));
        AlertDialog create = builder.create();
        this.f719b = create;
        return create;
    }

    @Override // com.pgyersdk.update.DownloadFileListener
    public void downloadFailed() {
        m382a();
        if (this.f720c) {
            m381b().show();
        }
    }

    @Override // com.pgyersdk.update.DownloadFileListener
    public void downloadSuccessful(File file) {
        m380a(file);
        m382a();
    }

    @Override // com.pgyersdk.update.DownloadFileListener
    public void onProgressUpdate(Integer... numArr) {
        Activity currentActivity = PgyerActivityManager.getInstance().getCurrentActivity();
        this.f721d = currentActivity;
        try {
            if (this.f718a == null && currentActivity != null) {
                ProgressDialog progressDialog = new ProgressDialog(this.f721d);
                this.f718a = progressDialog;
                progressDialog.setProgressStyle(1);
                this.f718a.setMessage(Strings.m151a(260));
                this.f718a.setCancelable(false);
                this.f718a.show();
            }
            this.f718a.setProgress(numArr[0].intValue());
        } catch (Exception unused) {
            m382a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m382a() {
        Activity activity;
        Activity activity2;
        ProgressDialog progressDialog = this.f718a;
        if (progressDialog != null) {
            if (progressDialog.isShowing() && (activity2 = this.f721d) != null && !activity2.isFinishing()) {
                this.f718a.dismiss();
            }
            this.f718a = null;
        }
        Dialog dialog = this.f719b;
        if (dialog != null) {
            if (dialog.isShowing() && (activity = this.f722e) != null && activity.isFinishing()) {
                this.f719b.dismiss();
            }
            this.f719b = null;
        }
    }
}
