package com.kkkcut.e20j.utils;

import android.content.Context;
import android.content.res.AssetManager;
import com.kkkcut.e20j.MyApplication;
import com.liying.core.MachineInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AssetVersionUtil {
    public static final String Firmware_Dir_Alpha = "firmware_alpha";
    public static final String Firmware_Dir_E9 = "firmware_e9";

    public static String getFirmwareDir() {
        return MachineInfo.isE9Standard(MyApplication.getInstance()) ? Firmware_Dir_E9 : Firmware_Dir_Alpha;
    }

    public static String getAssetsFrimVerstion(Context context) {
        String[] strArr = new String[0];
        try {
            strArr = MyApplication.getInstance().getAssets().list(getFirmwareDir());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (strArr.length == 0) {
            return null;
        }
        String[] split = strArr[0].replace(".bin", "").split("_");
        return split.length > 1 ? split[1] : "";
    }

    public static int getAssetsDbVersion(AssetManager assetManager, String str) {
        try {
            InputStream open = assetManager.open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            return new JSONObject(new String(bArr)).getInt("version");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getLocalDbVersion(File file) {
        if (!file.exists()) {
            return 0;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            return new JSONObject(new String(bArr)).getInt("version");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
