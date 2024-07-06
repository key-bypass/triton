package com.kkkcut.e20j.androidquick.network;

import com.kkkcut.e20j.androidquick.network.gsonconvert.CustomGsonConverterFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;


/* loaded from: classes.dex */
public class RetrofitManager {
    private static String COMMON = "AppService.ashx/";
    private static String LOG_UPLOAD = "AppServiceFile.ashx/";
    public static String MAIN_FOR = "https://appinterface.kukai361.com:4433/E20PADJ01/";
    public static String MIAN_CHINA = "https://appinterfacecn.kkkcut.cn:4433/E20PADJ01/";
    private static final String TAG = "RetrofitManager";
    private static String USER_DATA_UPLOAD = "AppServiceAPPOperatingFile.ashx/";
    private static OkHttpClient okHttpClient;
    private static RetrofitManager sInstance;
    private static Retrofit singleton;
    private String mainUrl;

    private RetrofitManager() {
        init();
    }

    public static RetrofitManager getInstance() {
        if (sInstance == null) {
            sInstance = new RetrofitManager();
        }
        return sInstance;
    }

    private void init() {
        initOkHttp();
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        var httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.sslSocketFactory(SSLSocketClient.getNoSSLSocketFactory(), (X509TrustManager) SSLSocketClient.getTrustManager()[0]);
        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    public void initBaseUrl(boolean z) {
        if (z) {
            this.mainUrl = MIAN_CHINA;
        } else {
            this.mainUrl = MAIN_FOR;
        }
    }

    public String getCommonUrl() {
        return this.mainUrl + COMMON;
    }

    public String getLogUrl() {
        return this.mainUrl + LOG_UPLOAD;
    }

    public String getUserDataUrl() {
        return this.mainUrl + USER_DATA_UPLOAD;
    }

    public <T> T createApi(Class<T> cls) {
        if (singleton == null) {
            synchronized (RetrofitManager.class) {
                if (singleton == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(getCommonUrl()).client(okHttpClient).addConverterFactory(CustomGsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create());
                    singleton = builder.build();
                }
            }
        }
        return (T) singleton.create(cls);
    }
}
