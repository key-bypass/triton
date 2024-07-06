package com.pgyersdk;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.pgyersdk.utils.Utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* compiled from: DeviceHelper.java */
/* renamed from: com.pgyersdk.d.a */
/* loaded from: classes2.dex */
public class C2033a {

    /* renamed from: a */
    public static CountDownLatch f494a = null;

    /* renamed from: b */
    private static boolean f495b = false;

    /* renamed from: c */
    static String f496c = "";

    /* renamed from: d */
    public static a f497d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DeviceHelper.java */
    /* renamed from: com.pgyersdk.d.a$a */
    /* loaded from: classes2.dex */
    public class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("level", 0);
                C2033a.f496c = ((intExtra * 100) / intent.getIntExtra("scale", 100)) + "%";
                C2033a.f494a.countDown();
            }
        }
    }

    /* renamed from: a */
    public static Map<String, String> m167a(Context context, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (Utils.m245a().m252a(context, "android.permission.ACCESS_WIFI_STATE")) {
            map.put("wifi_permission", "true");
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            map.put("wifi_mac_address", connectionInfo.getMacAddress());
            if (wifiManager.getWifiState() == 3) {
                map.put("wifi_state", "true");
                map.put("wifi_ip_address", m165a(connectionInfo.getIpAddress()));
                map.put("wifi_ssid", connectionInfo.getSSID());
                map.put("wifi_bssid", connectionInfo.getBSSID());
                map.put("wifi_rssi", connectionInfo.getRssi() + "dB");
                map.put("wifi_link_speed", connectionInfo.getLinkSpeed() + "Mbps");
                List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
                if (configuredNetworks == null) {
                    map.put("wifi_state", "false");
                    return map;
                }
                int size = configuredNetworks.size();
                for (int i = 0; i < size; i++) {
                    map.put("wifi_config_" + i, configuredNetworks.get(i).toString());
                }
            } else {
                map.put("wifi_state", "false");
            }
            return map;
        }
        map.put("wifi_permission", "false");
        return map;
    }

    /* renamed from: b */
    public static String[] m170b() {
        long blockSizeLong;
        long blockCountLong;
        long availableBlocksLong;
        String[] strArr = {"0", "0"};
        if ("mounted".equals(Environment.getExternalStorageState())) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (Build.VERSION.SDK_INT < 18) {
                blockSizeLong = statFs.getBlockSize();
                blockCountLong = statFs.getBlockCount();
                availableBlocksLong = statFs.getAvailableBlocks();
            } else {
                blockSizeLong = statFs.getBlockSizeLong();
                blockCountLong = statFs.getBlockCountLong();
                availableBlocksLong = statFs.getAvailableBlocksLong();
            }
            strArr[0] = Utils.m245a().m250a(blockCountLong * blockSizeLong);
            strArr[1] = Utils.m245a().m250a(blockSizeLong * availableBlocksLong);
        }
        return strArr;
    }

    /* renamed from: c */
    public static DisplayMetrics m171c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* renamed from: d */
    public static Map<String, String> m173d(Context context) {
        HashMap hashMap = new HashMap();
        if (Utils.m245a().m252a(context, "android.permission.INTERNET") && Utils.m245a().m252a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            hashMap.put("network_permission", "true");
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                hashMap.put("network_state", "true");
                hashMap.put("network_type", activeNetworkInfo.getTypeName());
                if (activeNetworkInfo.getType() == 0) {
                    hashMap.put("network_subtype", activeNetworkInfo.getSubtypeName());
                    hashMap.put("network_apn", activeNetworkInfo.getExtraInfo());
                } else if (activeNetworkInfo.getType() == 1) {
                    m167a(context, hashMap);
                }
            } else {
                hashMap.put("network_state", "false");
            }
            return hashMap;
        }
        hashMap.put("network_permission", "false");
        return hashMap;
    }

    /* renamed from: e */
    public static String[] m174e(Context context) {
        long j;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        if (Build.VERSION.SDK_INT < 16) {
            j = 0;
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    if (readLine != null && readLine.contains("MemTotal")) {
                        j = Long.parseLong(Pattern.compile("[^0-9]").matcher(readLine).replaceAll("").trim()) * 1024;
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                Utils.m245a().m251a("DeviceHelper", e);
            }
        } else {
            j = memoryInfo.totalMem;
        }
        return new String[]{Utils.m245a().m250a(j), Utils.m245a().m250a(memoryInfo.availMem)};
    }

    /* renamed from: f */
    public static String[] m175f(Context context) {
        DisplayMetrics m171c = m171c(context);
        return new String[]{m171c.widthPixels + "*" + m171c.heightPixels, String.valueOf(m171c.density), String.valueOf(m171c.densityDpi)};
    }

    /* renamed from: h */
    public static synchronized void m176h(Context context) {
        synchronized (C2033a.class) {
            a aVar = f497d;
            if (aVar != null && context != null) {
                try {
                    context.unregisterReceiver(aVar);
                    f497d = null;
                    f495b = false;
                } catch (Exception unused) {
                }
            }
        }
    }

    /* renamed from: g */
    public synchronized void m177g(Context context) {
        if (f495b) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        a aVar = new a();
        f497d = aVar;
        context.registerReceiver(aVar, intentFilter);
        f495b = true;
    }

    /* renamed from: c */
    public static boolean m172c() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /* renamed from: b */
    public static String m169b(Context context) {
        int intValue = Integer.valueOf(m175f(context)[2]).intValue();
        return intValue < 160 ? "mdpi" : (intValue < 160 || intValue > 240) ? (intValue <= 240 || intValue > 320) ? (intValue <= 320 || intValue > 480) ? "xxxhdpi" : "xxhdpi" : "xhdpi" : "hdpi";
    }

    /* renamed from: a */
    public static String[] m168a() {
        long blockSizeLong;
        long blockCountLong;
        long availableBlocksLong;
        String[] strArr = {"0", "0"};
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT < 18) {
            blockSizeLong = statFs.getBlockSize();
            blockCountLong = statFs.getBlockCount();
            availableBlocksLong = statFs.getAvailableBlocks();
        } else {
            blockSizeLong = statFs.getBlockSizeLong();
            blockCountLong = statFs.getBlockCountLong();
            availableBlocksLong = statFs.getAvailableBlocksLong();
        }
        strArr[0] = Utils.m245a().m250a(blockCountLong * blockSizeLong);
        strArr[1] = Utils.m245a().m250a(blockSizeLong * availableBlocksLong);
        return strArr;
    }

    /* renamed from: a */
    private static String m165a(int i) {
        return (i & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >> 8) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >> 16) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >> 24) & 255);
    }

    /* renamed from: a */
    public static String m166a(Context context) {
        f494a = new CountDownLatch(1);
        new C2033a().m177g(context);
        try {
            f494a.await(5L, TimeUnit.SECONDS);
            m176h(context);
            return f496c;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
