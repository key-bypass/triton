package com.pgyersdk.p016f;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: HttpURLConnectionBuilder.java */
/* renamed from: com.pgyersdk.f.d */
/* loaded from: classes2.dex */
public class C2039d {

    /* renamed from: a */
    private final String f526a;

    /* renamed from: b */
    private String f527b;

    /* renamed from: c */
    private String f528c;

    /* renamed from: d */
    private C2044i f529d;

    /* renamed from: e */
    private int f530e = 120000;

    /* renamed from: f */
    private final Map<String, String> f531f = new HashMap();

    public C2039d(String str) {
        this.f526a = str;
    }

    /* renamed from: a */
    public C2039d m207a(String str) {
        this.f527b = str;
        return this;
    }

    /* renamed from: a */
    public C2039d m209a(Map<String, String> map, Context context, File file, List<Uri> list) {
        try {
            C2044i c2044i = new C2044i();
            this.f529d = c2044i;
            c2044i.m231d();
            for (String str : map.keySet()) {
                this.f529d.m226a(str, map.get(str));
            }
            if (file != null) {
                this.f529d.m227a("voice", file.getName(), new FileInputStream(file.getAbsolutePath()), "audio/wav", false);
            }
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Uri uri = list.get(i);
                    boolean z = true;
                    if (i != list.size() - 1) {
                        z = false;
                    }
                    InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                    this.f529d.m228a("image[]", uri.getLastPathSegment(), openInputStream, z);
                }
            }
            this.f529d.m232e();
            StringBuilder sb = new StringBuilder();
            sb.append("multipart/form-data; boundary=");
            sb.append(this.f529d.m225a());
            m208a("Content-Type", sb.toString());
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public C2039d m208a(String str, String str2) {
        this.f531f.put(str, str2);
        return this;
    }

    /* renamed from: a */
    public HttpURLConnection m210a() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f526a).openConnection();
            httpURLConnection.setConnectTimeout(this.f530e);
            httpURLConnection.setReadTimeout(this.f530e);
            if (Build.VERSION.SDK_INT <= 9) {
                httpURLConnection.setRequestProperty("Connection", "close");
            }
            if (!TextUtils.isEmpty(this.f527b)) {
                httpURLConnection.setRequestMethod(this.f527b);
                if (!TextUtils.isEmpty(this.f528c) || this.f527b.equalsIgnoreCase("POST") || this.f527b.equalsIgnoreCase("PUT")) {
                    httpURLConnection.setDoOutput(true);
                }
            }
            for (String str : this.f531f.keySet()) {
                httpURLConnection.setRequestProperty(str, this.f531f.get(str));
            }
            if (!TextUtils.isEmpty(this.f528c)) {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
                bufferedWriter.write(this.f528c);
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            C2044i c2044i = this.f529d;
            if (c2044i != null) {
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(c2044i.m229b()));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(this.f529d.m230c().toByteArray());
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            return httpURLConnection;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
