package com.kkkcut.e20j.androidquick.ui.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class EasyPermissions {
    private static final String TAG = "EasyPermissions";

    /* loaded from: classes.dex */
    public interface DialogCallback {
        void onGranted();
    }

    /* loaded from: classes.dex */
    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {
        void onPermissionsDenied(int i, List<String> list);

        void onPermissionsGranted(int i, List<String> list);
    }

    /* loaded from: classes.dex */
    public interface PermissionWithDialogCallbacks extends PermissionCallbacks {
        void onDialog(int i, int i2, DialogCallback dialogCallback);
    }

    public static String[] findNoPermission(Context context, String... strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            Log.w(TAG, "findNoPermission: API version < M, returning true by default");
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!(ContextCompat.checkSelfPermission(context, str) == 0)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void requestPermissions(Object obj, int i, String... strArr) {
        requestPermissions(obj, 0, i, false, strArr);
    }

    public static void requestPermissions(Object obj, int i, int i2, String... strArr) {
        requestPermissions(obj, i, i2, true, strArr);
    }

    public static void requestPermissions(final Object obj, int i, final int i2, boolean z, final String... strArr) {
        checkCallingObjectSuitability(obj);
        boolean z2 = false;
        for (String str : strArr) {
            z2 = z2 || shouldShowRequestPermissionRationale(obj, str);
        }
        if (z2) {
            if (getActivity(obj) != null && z && (obj instanceof PermissionWithDialogCallbacks)) {
                ((PermissionWithDialogCallbacks) obj).onDialog(i2, i, new DialogCallback() { // from class: com.kkkcut.e20j.androidquick.ui.permission.EasyPermissions.1
                    @Override // com.kkkcut.e20j.androidquick.ui.permission.EasyPermissions.DialogCallback
                    public void onGranted() {
                        EasyPermissions.executePermissionsRequest(obj, strArr, i2);
                    }
                });
                return;
            }
            return;
        }
        executePermissionsRequest(obj, strArr, i2);
    }

    public static boolean somePermissionPermanentlyDenied(Object obj, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (permissionPermanentlyDenied(obj, it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean permissionPermanentlyDenied(Object obj, String str) {
        return !shouldShowRequestPermissionRationale(obj, str);
    }

    public static void onRequestPermissionsResult(int i, String[] strArr, int[] iArr, Object... objArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            if (iArr[i2] == 0) {
                arrayList.add(str);
            } else {
                arrayList2.add(str);
            }
        }
        for (Object obj : objArr) {
            if (!arrayList.isEmpty() && (obj instanceof PermissionCallbacks)) {
                ((PermissionCallbacks) obj).onPermissionsGranted(i, arrayList);
            }
            if (!arrayList2.isEmpty() && (obj instanceof PermissionCallbacks)) {
                ((PermissionCallbacks) obj).onPermissionsDenied(i, arrayList2);
            }
            if (!arrayList.isEmpty() && arrayList2.isEmpty()) {
                runAnnotatedMethods(obj, i);
            }
        }
    }

    public static void goSettingsPermissions(Object obj, int i, int i2) {
        goSettingsPermissions(obj, 0, i, i2, false);
    }

    public static void goSettingsPermissions(Object obj, int i, int i2, int i3) {
        goSettingsPermissions(obj, i, i2, i3, true);
    }

    public static void goSettingsPermissions(final Object obj, int i, int i2, final int i3, boolean z) {
        checkCallingObjectSuitability(obj);
        final Activity activity = getActivity(obj);
        if (activity != null && z && (obj instanceof PermissionWithDialogCallbacks)) {
            ((PermissionWithDialogCallbacks) obj).onDialog(i2, i, new DialogCallback() { // from class: com.kkkcut.e20j.androidquick.ui.permission.EasyPermissions.2
                @Override // com.kkkcut.e20j.androidquick.ui.permission.EasyPermissions.DialogCallback
                public void onGranted() {
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
                    EasyPermissions.startForResult(obj, intent, i3);
                }
            });
        }
    }

    private static boolean shouldShowRequestPermissionRationale(Object obj, String str) {
        if (obj instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) obj, str);
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).shouldShowRequestPermissionRationale(str);
        }
        if (obj instanceof android.app.Fragment) {
            return ((android.app.Fragment) obj).shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void executePermissionsRequest(Object obj, String[] strArr, int i) {
        checkCallingObjectSuitability(obj);
        if (obj instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) obj, strArr, i);
        } else if (obj instanceof Fragment) {
            ((Fragment) obj).requestPermissions(strArr, i);
        } else if (obj instanceof android.app.Fragment) {
            ((android.app.Fragment) obj).requestPermissions(strArr, i);
        }
    }

    private static Activity getActivity(Object obj) {
        if (obj instanceof Activity) {
            return (Activity) obj;
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).getActivity();
        }
        if (obj instanceof android.app.Fragment) {
            return ((android.app.Fragment) obj).getActivity();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startForResult(Object obj, Intent intent, int i) {
        if (obj instanceof Activity) {
            ((Activity) obj).startActivityForResult(intent, i);
        } else if (obj instanceof Fragment) {
            ((Fragment) obj).startActivityForResult(intent, i);
        } else if (obj instanceof android.app.Fragment) {
            ((android.app.Fragment) obj).startActivityForResult(intent, i);
        }
    }

    private static void checkCallingObjectSuitability(Object obj) {
        boolean z = obj instanceof Activity;
        boolean z2 = obj instanceof Fragment;
        boolean z3 = obj instanceof android.app.Fragment;
        boolean z4 = Build.VERSION.SDK_INT >= 23;
        if (z2 || z) {
            return;
        }
        if (z3 && z4) {
            return;
        }
        if (z3) {
            throw new IllegalArgumentException("Target SDK needs to be greater than 23 if caller is android.app.Fragment");
        }
        throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
    }

    private static void runAnnotatedMethods(Object obj, int i) {
        Class<?> cls = obj.getClass();
        if (isUsingAndroidAnnotations(obj)) {
            cls = cls.getSuperclass();
        }
        while (cls != null) {
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(AfterPermissionGranted.class) && ((AfterPermissionGranted) method.getAnnotation(AfterPermissionGranted.class)).value() == i) {
                    if (method.getParameterTypes().length > 0) {
                        throw new RuntimeException("Cannot CALIBRATE method " + method.getName() + " because it is non-void method and/or has input parameters.");
                    }
                    try {
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        method.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e) {
                        Log.e(TAG, "runDefaultMethod:IllegalAccessException", e);
                    } catch (InvocationTargetException e2) {
                        Log.e(TAG, "runDefaultMethod:InvocationTargetException", e2);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
    }

    private static boolean isUsingAndroidAnnotations(Object obj) {
        if (!obj.getClass().getSimpleName().endsWith("_")) {
            return false;
        }
        try {
            return Class.forName("org.androidannotations.api.view.HasViews").isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
