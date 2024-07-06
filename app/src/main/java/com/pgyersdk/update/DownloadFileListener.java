package com.pgyersdk.update;

import java.io.File;

/* loaded from: classes2.dex */
public interface DownloadFileListener {
    void beginDownload();

    void downloadSuccessful(File file);

    void onProgressUpdate(Integer... numArr);
}
