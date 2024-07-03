package com.kkkcut.e20j.net;

import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.bean.gsonBean.CertificationRes;
import com.kkkcut.e20j.bean.gsonBean.CodeFindToothRes;
import com.kkkcut.e20j.bean.gsonBean.Configuration;
import com.kkkcut.e20j.bean.gsonBean.ConfigurationE9;
import com.kkkcut.e20j.bean.gsonBean.GetTestData;
import com.kkkcut.e20j.bean.gsonBean.LackToothRes;
import com.kkkcut.e20j.bean.gsonBean.RegistrationRes;
import com.kkkcut.e20j.bean.gsonBean.UploadCustomKey;
import com.kkkcut.e20j.bean.gsonBean.UploadTestData;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import io.reactivex.rxjava3.core.Observable;

/* loaded from: classes.dex */
public interface Apis {
    @FormUrlEncoded
    @POST(FileUtil.FILE_EXTENSION_SEPARATOR)
    Observable<CertificationRes> certification(@Field("T") String str);

    @FormUrlEncoded
    @POST(FileUtil.FILE_EXTENSION_SEPARATOR)
    Observable<Configuration> getConfig(@Field("T") String str);

    @FormUrlEncoded
    @POST(FileUtil.FILE_EXTENSION_SEPARATOR)
    Observable<ConfigurationE9> getConfigE9(@Field("T") String str);

    @FormUrlEncoded
    @POST(FileUtil.FILE_EXTENSION_SEPARATOR)
    Observable<GetTestData> getTestData(@Field("T") String str);

    @FormUrlEncoded
    @POST(FileUtil.FILE_EXTENSION_SEPARATOR)
    Observable<CodeFindToothRes> getToothByCode(@Field("T") String str);

    @FormUrlEncoded
    @POST(FileUtil.FILE_EXTENSION_SEPARATOR)
    Observable<LackToothRes> lackTooth(@Field("T") String str);

    @FormUrlEncoded
    @POST(FileUtil.FILE_EXTENSION_SEPARATOR)
    Observable<UploadTestData> postTestData(@Field("T") String str);

    @FormUrlEncoded
    @POST(FileUtil.FILE_EXTENSION_SEPARATOR)
    Observable<RegistrationRes> register(@Field("T") String str);

    @FormUrlEncoded
    @POST(FileUtil.FILE_EXTENSION_SEPARATOR)
    Observable<UploadCustomKey> uploadCustomkey(@Field("T") String str);
}
