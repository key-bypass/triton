package com.pgyersdk.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/* compiled from: SimpleMultipartEntity.java */
/* renamed from: com.pgyersdk.f.i */
/* loaded from: classes2.dex */
public class SimpleMultipartEntity {

    /* renamed from: a */
    private static final char[] f534a = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* renamed from: e */
    private String f538e;

    /* renamed from: c */
    private boolean f536c = false;

    /* renamed from: b */
    private boolean f535b = false;

    /* renamed from: d */
    private ByteArrayOutputStream f537d = new ByteArrayOutputStream();

    public SimpleMultipartEntity() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            char[] cArr = f534a;
            stringBuffer.append(cArr[random.nextInt(cArr.length)]);
        }
        this.f538e = stringBuffer.toString();
    }

    /* renamed from: a */
    public String m225a() {
        return this.f538e;
    }

    /* renamed from: b */
    public long m229b() {
        m232e();
        return this.f537d.toByteArray().length;
    }

    /* renamed from: c */
    public ByteArrayOutputStream m230c() {
        m232e();
        return this.f537d;
    }

    /* renamed from: d */
    public void m231d() throws IOException {
        if (!this.f536c) {
            this.f537d.write(("--" + this.f538e + "\r\n").getBytes());
        }
        this.f536c = true;
    }

    /* renamed from: e */
    public void m232e() {
        if (this.f535b) {
            return;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = this.f537d;
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n--");
            sb.append(this.f538e);
            sb.append("--\r\n");
            byteArrayOutputStream.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f535b = true;
    }

    /* renamed from: a */
    public void m226a(String str, String str2) throws IOException {
        m231d();
        this.f537d.write(("Content-Disposition: form-data; name=\"" + str + "\"\r\n").getBytes());
        this.f537d.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes());
        this.f537d.write("Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes());
        if (str2 != null) {
            this.f537d.write(str2.getBytes());
        } else {
            this.f537d.write("".getBytes());
        }
        this.f537d.write(("\r\n--" + this.f538e + "\r\n").getBytes());
    }

    /* renamed from: a */
    public void m228a(String str, String str2, InputStream inputStream, boolean z) throws IOException {
        m227a(str, str2, inputStream, "application/octet-stream", z);
    }

    /* renamed from: a */
    public void m227a(String str, String str2, InputStream inputStream, String str3, boolean z) throws IOException {
        m231d();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Content-Type: ");
            sb.append(str3);
            sb.append("\r\n");
            String sb2 = sb.toString();
            ByteArrayOutputStream byteArrayOutputStream = this.f537d;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Content-Disposition: form-data; name=\"");
            sb3.append(str);
            sb3.append("\"; filename=\"");
            sb3.append(str2);
            sb3.append("\"\r\n");
            byteArrayOutputStream.write(sb3.toString().getBytes());
            this.f537d.write(sb2.getBytes());
            this.f537d.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                } else {
                    this.f537d.write(bArr, 0, read);
                }
            }
            this.f537d.flush();
            if (z) {
                m232e();
            } else {
                ByteArrayOutputStream byteArrayOutputStream2 = this.f537d;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("\r\n--");
                sb4.append(this.f538e);
                sb4.append("\r\n");
                byteArrayOutputStream2.write(sb4.toString().getBytes());
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            throw th;
        }
    }
}
