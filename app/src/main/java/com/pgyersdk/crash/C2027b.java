package com.pgyersdk.crash;

import com.pgyersdk.crash.PgyCrashManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyCrashManager.java */
/* renamed from: com.pgyersdk.crash.b */
/* loaded from: classes2.dex */
public /* synthetic */ class C2027b {

    /* renamed from: a */
    static final /* synthetic */ int[] f489a;

    static {
        int[] iArr = new int[PgyCrashManager.EnumC2024a.values().length];
        f489a = iArr;
        try {
            iArr[PgyCrashManager.EnumC2024a.CRASH.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f489a[PgyCrashManager.EnumC2024a.EXCEPTION.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
