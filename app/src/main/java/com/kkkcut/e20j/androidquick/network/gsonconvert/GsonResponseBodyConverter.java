package com.kkkcut.e20j.androidquick.network.gsonconvert;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.kkkcut.e20j.utils.DesUtil;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/* loaded from: classes.dex */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final String TAG = "GsonResponseBody";
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GsonResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    @Override // retrofit2.Converter
    public T convert(ResponseBody responseBody) throws IOException {
        try {
            return this.adapter.fromJson(DesUtil.decrypt(responseBody.string(), DesUtil.SERVER));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return this.adapter.read(this.gson.newJsonReader(responseBody.charStream()));
            } finally {
                responseBody.close();
            }
        }
    }
}
