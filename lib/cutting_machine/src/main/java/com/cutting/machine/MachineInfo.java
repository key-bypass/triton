package com.liying.core;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public class MachineInfo {
    private static MachineType machineType = MachineType.Beta;
    private static RegionType regionType = RegionType.foreign;

    /* loaded from: classes2.dex */
    public enum RegionType {
        china,
        foreign
    }

    public static MachineType getMachineType() {
        return machineType;
    }

    public static boolean isChineseMachine() {
        return regionType == RegionType.china;
    }

    public static boolean isChineseMachine(String str) {
        return str.equals("35") || str.equals("42") || str.equals("39") || str.equals("31") || str.equals("44") || str.equals("47") || str.equals("51") || str.equals("53") || str.equals("55");
    }

    public static boolean isBeta() {
        return machineType == MachineType.Beta;
    }

    public static boolean isE20() {
        return machineType == MachineType.Alpha;
    }

    public static boolean isE9() {
        return machineType == MachineType.E9;
    }

    public static void setMachineTypeAndRegion(String str) {
        if (str.equals("34") || str.equals("35") || str.equals("40") || str.equals("41") || str.equals("42") || str.equals("47")) {
            machineType = MachineType.Beta;
            if (str.equals("35") || str.equals("42")) {
                regionType = RegionType.china;
                return;
            } else {
                regionType = RegionType.foreign;
                return;
            }
        }
        if (str.equals("38") || str.equals("39") || str.equals("48") || str.equals("49") || str.equals("50") || str.equals("51") || str.equals("52") || str.equals("53") || str.equals("54") || str.equals("55")) {
            machineType = MachineType.E9;
            if (str.equals("38") || str.equals("48") || str.equals("50") || str.equals("52") || str.equals("54")) {
                regionType = RegionType.foreign;
                return;
            } else {
                regionType = RegionType.china;
                return;
            }
        }
        machineType = MachineType.Alpha;
        if (str.equals("31") || str.equals("44")) {
            regionType = RegionType.china;
        } else {
            regionType = RegionType.foreign;
        }
    }

    public static MachineType getMachineTypeAndRegion(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equals("34") || str.equals("35") || str.equals("40") || str.equals("41") || str.equals("42") || str.equals("47")) {
            machineType = MachineType.Beta;
        } else if (str.equals("38") || str.equals("39") || str.equals("48") || str.equals("49") || str.equals("50") || str.equals("51") || str.equals("52") || str.equals("53") || str.equals("54") || str.equals("55")) {
            machineType = MachineType.E9;
        } else {
            machineType = MachineType.Alpha;
        }
        return machineType;
    }

    public static void setMachineTypeAndRegion(Context context, String str) {
        setMachineTypeAndRegion(str);
        CuttingMachine.getInstance().init(context, getMachineType());
    }

    public static boolean isBetaUs(Context context) {
        String packageName = getPackageName(context);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        return packageName.contains("BlackWidowV2");
    }

    public static boolean isBeta(Context context) {
        String packageName = getPackageName(context);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        return packageName.contains("j01phone");
    }

    public static boolean isE20Standard(Context context) {
        String packageName = getPackageName(context);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        return packageName.endsWith(".standard");
    }

    public static boolean isE9Standard(Context context) {
        String packageName = getPackageName(context);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        return packageName.endsWith(".e9");
    }

    public static boolean isE20Neutral(Context context) {
        String packageName = getPackageName(context);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        return packageName.endsWith(".neutral");
    }

    public static boolean isE20Us(Context context) {
        String packageName = getPackageName(context);
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        return packageName.endsWith(".us");
    }

    public static synchronized String getPackageName(Context context) {
        String str;
        synchronized (MachineInfo.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return str;
    }
}
