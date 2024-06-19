package com.pgyersdk.p008b.p011c;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import com.pgyersdk.p008b.p009a.C2006d;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FieldHelper.java */
/* renamed from: com.pgyersdk.b.c.a */
/* loaded from: classes2.dex */
public class C2011a {
    /* renamed from: a */
    public static List<C2012b> m114a(Activity activity) {
        Object m113a;
        Object[] objArr;
        WindowManager.LayoutParams[] layoutParamsArr;
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 17) {
            m113a = m113a("mGlobal", activity.getWindowManager());
        } else {
            m113a = m113a("mWindowManager", activity.getWindowManager());
        }
        Object m113a2 = m113a("mRoots", m113a);
        Object m113a3 = m113a("mParams", m113a);
        if (Build.VERSION.SDK_INT >= 19) {
            objArr = ((List) m113a2).toArray();
            List list = (List) m113a3;
            layoutParamsArr = (WindowManager.LayoutParams[]) list.toArray(new WindowManager.LayoutParams[list.size()]);
        } else {
            objArr = (Object[]) m113a2;
            layoutParamsArr = (WindowManager.LayoutParams[]) m113a3;
        }
        for (int i = 0; i < objArr.length; i++) {
            View view = (View) m113a("mView", objArr[i]);
            if (view.getVisibility() == 0) {
                arrayList.add(new C2012b(view, layoutParamsArr[i]));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static Object m113a(String str, Object obj) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception e) {
            throw new C2006d(e);
        }
    }
}
