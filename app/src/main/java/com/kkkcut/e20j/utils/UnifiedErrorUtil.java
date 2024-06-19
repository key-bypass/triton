package com.kkkcut.e20j.utils;

import com.google.gson.JsonSyntaxException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import retrofit2.HttpException;

/* loaded from: classes.dex */
public class UnifiedErrorUtil {
    public static Throwable unifiedError(Throwable th) {
        if ((th instanceof UnknownHostException) || (th instanceof HttpException)) {
            return new Throwable("Network Unavailable", th.getCause());
        }
        if ((th instanceof SocketTimeoutException) || (th instanceof SocketException)) {
            return new Throwable("Time Out", th.getCause());
        }
        return ((th instanceof IllegalArgumentException) || (th instanceof JsonSyntaxException)) ? new Throwable("Data Error", th.getCause()) : th;
    }
}
