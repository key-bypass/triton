package com.kkkcut.e20j.androidquick.tool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ReflectUtil {
    public static final String LOG_FAIL_REASON = "fail_reason:";
    private static final String TAG = "GaramutHelperReflect";

    public static Class<?> getClass(String str) {
        try {
            Class<?> cls = Class.forName(str);
            LogUtil.d(TAG, "getClass->cls:" + cls);
            return cls;
        } catch (ClassNotFoundException e) {
            LogUtil.d(TAG, "getClass->className:" + str + "," + LOG_FAIL_REASON + e);
            e.printStackTrace();
            return null;
        }
    }

    public static Class<?> getClass(Class<?> cls, String str) {
        for (Class<?> cls2 : cls.getClasses()) {
            LogUtil.d(TAG, "getClass->getClasses " + cls2.toString());
            if (cls2.getName().equals(str)) {
                return cls2;
            }
        }
        for (Class<?> cls3 : cls.getDeclaredClasses()) {
            LogUtil.d(TAG, "getClass->getDeclaredClasses " + cls3.toString());
            if (cls3.getName().equals(str)) {
                return cls3;
            }
        }
        return null;
    }

    public static Method getMethod(Class<?> cls, String str) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        LogUtil.d(TAG, "getMethod->getMethodsDeclared " + declaredMethods.toString());
        for (Method method : declaredMethods) {
            if (method.getName().equals(str)) {
                return method;
            }
        }
        Method[] methods = cls.getMethods();
        LogUtil.d(TAG, "getMethod->getMethodsBase " + methods.toString());
        for (Method method2 : methods) {
            if (method2.getName().equals(str)) {
                return method2;
            }
        }
        LogUtil.d(TAG, "getMethod->fail");
        return null;
    }

    public static Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getDeclaredMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            LogUtil.d(TAG, "getMethod->fail fail_reason:" + e.toString());
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Object getObject(String str) {
        return getObject(getClass(str));
    }

    public static Object getObject(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LogUtil.d(TAG, "getObject->fail fail_reason:" + e.toString());
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            LogUtil.d(TAG, "getObject->fail fail_reason:" + e2.toString());
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            LogUtil.d(TAG, "getObject->fail fail_reason:" + e3.toString());
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            LogUtil.d(TAG, "getObject->fail fail_reason:" + e4.toString());
            return null;
        }
    }

    public static Object invoke(Object obj, Method method, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }
}
