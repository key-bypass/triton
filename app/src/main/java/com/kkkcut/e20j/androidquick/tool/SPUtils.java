package com.kkkcut.e20j.androidquick.tool;

import android.content.Context;
import android.content.SharedPreferences;
import com.getkeepsafe.relinker.ReLinker;
import com.kkkcut.e20j.utils.DatabaseFileUtils;
import com.tencent.mmkv.MMKV;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class SPUtils {
    private static final String TAG = "SPUtils";
    private static MMKV sp;
    private static SPUtils spUtils;

    public static void init(Context context, String str) {
        if (spUtils == null) {
            synchronized (SPUtils.class) {
                spUtils = new SPUtils(context, str);
            }
        }
    }

    private SPUtils(final Context context, String str) {
        MMKV.initialize(context.getFilesDir().getAbsolutePath() + "/mmkv", new MMKV.LibLoader() { // from class: com.kkkcut.e20j.androidquick.tool.SPUtils.1
            @Override // com.tencent.mmkv.MMKV.LibLoader
            public void loadLibrary(String str2) {
                ReLinker.loadLibrary(context, str2);
            }
        });
        sp = MMKV.mmkvWithID(str, 2, DatabaseFileUtils.KEY);
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        sp.importFromSharedPreferences(sharedPreferences);
        sharedPreferences.edit().clear().commit();
    }

    public static void put(String str, String str2) {
        sp.edit().putString(str, str2);
    }

    public static String getString(String str) {
        return getString(str, "");
    }

    public static String getString(String str, String str2) {
        return sp.getString(str, str2);
    }

    public static void put(String str, int i) {
        sp.edit().putInt(str, i);
    }

    public static int getInt(String str) {
        return getInt(str, -1);
    }

    public static int getInt(String str, int i) {
        return sp.getInt(str, i);
    }

    public static void put(String str, long j) {
        sp.edit().putLong(str, j);
    }

    public static long getLong(String str) {
        return getLong(str, -1L);
    }

    public static long getLong(String str, long j) {
        return sp.getLong(str, j);
    }

    public static void put(String str, float f) {
        sp.edit().putFloat(str, f);
    }

    public static float getFloat(String str) {
        return getFloat(str, -1.0f);
    }

    public static float getFloat(String str, float f) {
        return sp.getFloat(str, f);
    }

    public static void put(String str, boolean z) {
        sp.edit().putBoolean(str, z);
    }

    public static boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public static boolean getBoolean(String str, boolean z) {
        return sp.getBoolean(str, z);
    }

    public static void put(String str, Set<String> set) {
        sp.edit().putStringSet(str, set);
    }

    public static Set<String> getStringSet(String str) {
        return getStringSet(str, Collections.emptySet());
    }

    public static Set<String> getStringSet(String str, Set<String> set) {
        return sp.getStringSet(str, set);
    }

    public static Map<String, ?> getAll() {
        return sp.getAll();
    }

    public static boolean contains(String str) {
        return sp.contains(str);
    }

    public static void remove(String str) {
        sp.edit().remove(str);
    }

    public static void clear() {
        sp.edit().clear();
    }

    public static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void sync() {
        sp.sync();
    }
}
