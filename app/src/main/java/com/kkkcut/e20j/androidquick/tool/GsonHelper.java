package com.kkkcut.e20j.androidquick.tool;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GsonHelper {
    private static final String TAG = "GsonHelper";
    private static Gson gson = new Gson();

    public static Object getField(String str, String str2, int i) {
        if (!StringUtil.isEmpty(str) && !StringUtil.isEmpty(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(str2)) {
                    if (i == 0) {
                        return new String(jSONObject.getString(str2));
                    }
                    return new Integer(jSONObject.getInt(str2));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> T fromJson(String str, TypeToken<T> typeToken) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (T) gson.fromJson(str, typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (T) gson.fromJson(str, (Class) cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJsonString(Object obj) {
        Gson gson2 = gson;
        if (gson2 != null) {
            return gson2.toJson(obj);
        }
        return null;
    }
}
