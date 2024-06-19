package com.pgyersdk.update;

import android.os.AsyncTask;
import android.os.Build;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p016f.C2038c;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: DownloadFileTask.java */
/* renamed from: com.pgyersdk.update.b */
/* loaded from: classes2.dex */
public class AsyncTaskC2083b extends AsyncTask<Void, Integer, Long> {

    /* renamed from: a */
    public static String f712a;

    /* renamed from: b */
    private DownloadFileListener f713b;

    /* renamed from: c */
    private String f714c;

    /* renamed from: d */
    private String f715d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsyncTaskC2083b(String str, DownloadFileListener downloadFileListener) {
        f712a = str;
        this.f713b = downloadFileListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Long doInBackground(Void... voidArr) {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                httpURLConnection = m375a(new URL(f712a), 6);
                httpURLConnection.connect();
                int contentLength = httpURLConnection.getContentLength();
                File m374a = m374a();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                FileOutputStream fileOutputStream = new FileOutputStream(m374a);
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    j += read;
                    publishProgress(Integer.valueOf(Math.round((((float) j) * 100.0f) / contentLength)));
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();
                Long valueOf = Long.valueOf(j);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return valueOf;
            } catch (Exception e) {
                e.printStackTrace();
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return 0L;
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private void m376a(HttpURLConnection httpURLConnection) {
        httpURLConnection.addRequestProperty("User-Agent", "Pgyer/Android");
        httpURLConnection.setInstanceFollowRedirects(true);
        if (Build.VERSION.SDK_INT <= 9) {
            httpURLConnection.setRequestProperty("connection", "close");
        }
    }

    /* renamed from: a */
    private HttpURLConnection m375a(URL url, int i) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        m376a(httpURLConnection);
        int responseCode = httpURLConnection.getResponseCode();
        if ((responseCode != 301 && responseCode != 302 && responseCode != 303) || i == 0) {
            return httpURLConnection;
        }
        URL url2 = new URL(httpURLConnection.getHeaderField("Location"));
        if (!url.getProtocol().equals(url2.getProtocol())) {
            httpURLConnection.disconnect();
            return m375a(url2, i - 1);
        }
        return httpURLConnection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onProgressUpdate(Integer... numArr) {
        DownloadFileListener downloadFileListener = this.f713b;
        if (downloadFileListener != null) {
            downloadFileListener.onProgressUpdate(numArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Long l) {
        if (l.longValue() > 0) {
            DownloadFileListener downloadFileListener = this.f713b;
            if (downloadFileListener != null) {
                downloadFileListener.downloadSuccessful(new File(this.f715d, this.f714c));
                return;
            }
            return;
        }
        DownloadFileListener downloadFileListener2 = this.f713b;
        if (downloadFileListener2 != null) {
            downloadFileListener2.downloadFailed();
        }
    }

    /* renamed from: a */
    private File m374a() {
        File file;
        File file2 = new File(C2038c.m196a().m204c(PgyerProvider.f436a));
        this.f715d = file2.getAbsolutePath();
        try {
            file = File.createTempFile("apk-", ".apk", file2);
            try {
                this.f714c = file.getName();
            } catch (IOException e) {
                e = e;
                e.printStackTrace();
                return file;
            }
        } catch (IOException e2) {
            e = e2;
            file = null;
        }
        return file;
    }
}
