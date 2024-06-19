package com.pgyersdk.update;

import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p016f.C2036a;
import com.pgyersdk.p016f.C2038c;
import com.pgyersdk.p016f.C2047l;
import com.pgyersdk.p016f.C2048m;
import java.io.File;

/* loaded from: classes2.dex */
public class PgyUpdateManager {

    /* renamed from: a */
    static PgyUpdateManager f704a;

    /* renamed from: b */
    private static UpdateManagerListener f705b;

    /* renamed from: c */
    private static DownloadFileListener f706c;

    /* renamed from: d */
    static AsyncTaskC2082a f707d;

    /* renamed from: e */
    private boolean f708e;

    /* renamed from: f */
    private boolean f709f;

    /* loaded from: classes2.dex */
    public static class Builder {
        DownloadFileListener downloadFileListener;
        UpdateManagerListener updateManagerListener;
        boolean isForced = false;
        boolean userCanRetry = true;
        boolean deleteHistroyApk = true;

        public PgyUpdateManager register() {
            if (!C2047l.m236a()) {
                return null;
            }
            if (this.updateManagerListener == null) {
                this.updateManagerListener = new C2090i(this.isForced);
            }
            if (this.downloadFileListener == null) {
                this.downloadFileListener = new C2086e(this.userCanRetry);
            }
            PgyUpdateManager.f704a = new PgyUpdateManager(this.updateManagerListener, this.downloadFileListener, this.isForced, this.userCanRetry, this.deleteHistroyApk);
            return PgyUpdateManager.f704a;
        }

        public Builder setDeleteHistroyApk(boolean z) {
            this.deleteHistroyApk = z;
            return this;
        }

        public Builder setDownloadFileListener(DownloadFileListener downloadFileListener) {
            this.downloadFileListener = downloadFileListener;
            return this;
        }

        public Builder setForced(boolean z) {
            this.isForced = z;
            return this;
        }

        public Builder setUpdateManagerListener(UpdateManagerListener updateManagerListener) {
            this.updateManagerListener = updateManagerListener;
            return this;
        }

        public Builder setUserCanRetry(boolean z) {
            this.userCanRetry = z;
            return this;
        }
    }

    /* renamed from: a */
    private void m369a(boolean z) {
        if (z && C2047l.m244e()) {
            C2038c.m196a().m201a(new File(C2038c.m196a().m204c(PgyerProvider.f436a)));
        }
    }

    public static void downLoadApk(String str) {
        if (C2047l.m244e()) {
            if (C2048m.m249b()) {
                C2036a.m194a(new AsyncTaskC2083b(str, f706c));
            } else {
                f706c.downloadFailed();
            }
        }
    }

    public static void installApk(File file) {
        C2086e.m380a(file);
    }

    @Deprecated
    public static void register() {
        if (C2047l.m236a()) {
            new Builder().setForced(false).setUserCanRetry(true).register();
        }
    }

    public static void unRegister() {
        if (f704a != null) {
            UpdateManagerListener updateManagerListener = f705b;
            if (updateManagerListener != null && (updateManagerListener instanceof C2090i)) {
                ((C2090i) updateManagerListener).m385b();
            }
            f705b = null;
            DownloadFileListener downloadFileListener = f706c;
            if (downloadFileListener != null && (downloadFileListener instanceof C2086e)) {
                ((C2086e) downloadFileListener).m382a();
            }
            f706c = null;
            AsyncTaskC2082a asyncTaskC2082a = f707d;
            if (asyncTaskC2082a != null) {
                asyncTaskC2082a.cancel(true);
                f707d = null;
            }
        }
    }

    private PgyUpdateManager(UpdateManagerListener updateManagerListener, DownloadFileListener downloadFileListener, boolean z, boolean z2, boolean z3) {
        f705b = updateManagerListener;
        f706c = downloadFileListener;
        this.f708e = z;
        this.f709f = z2;
        m369a(z3);
        m368a();
    }

    @Deprecated
    public static void register(UpdateManagerListener updateManagerListener) {
        if (C2047l.m236a()) {
            new Builder().setUpdateManagerListener(updateManagerListener).register();
        }
    }

    /* renamed from: a */
    private void m368a() {
        AsyncTaskC2082a asyncTaskC2082a = f707d;
        if (asyncTaskC2082a != null) {
            asyncTaskC2082a.cancel(true);
        }
        if (C2048m.m249b()) {
            AsyncTaskC2082a asyncTaskC2082a2 = new AsyncTaskC2082a(f705b);
            f707d = asyncTaskC2082a2;
            C2036a.m194a(asyncTaskC2082a2);
            return;
        }
        f705b.checkUpdateFailed(new IllegalArgumentException("net work unavailable"));
    }
}
