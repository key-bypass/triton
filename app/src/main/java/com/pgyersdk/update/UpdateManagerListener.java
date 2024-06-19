package com.pgyersdk.update;

import com.pgyersdk.update.javabean.AppBean;

/* loaded from: classes2.dex */
public interface UpdateManagerListener {
    void checkUpdateFailed(Exception exc);

    void onNoUpdateAvailable();

    void onUpdateAvailable(AppBean appBean);
}
