package com.cutting.machine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class FileUtil {
    public static String getFileContent(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        String sb2 = sb.toString();
                        fileInputStream.close();
                        return sb2;
                    }
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean saveStrToFile(String str, String str2) {
        try {
            return saveStrToFile(str, str2, "UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean saveStrToFile(String str, String str2, String str3) throws Throwable {
        byte[] bytes = null;
        FileOutputStream fileOutputStream = null;
        if (str != null && !"".equals(str)) {
            FileOutputStream fileOutputStream2 = null;
            try {
                File file = new File(str2);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (str3 != null && !"".equals(str3)) {
                    bytes = str.getBytes(str3);
                } else {
                    bytes = str.getBytes();
                }
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileOutputStream.write(bytes, 0, bytes.length);
                fileOutputStream.flush();
                try {
                    fileOutputStream.close();
                } catch (Exception unused2) {
                }
                return true;
            } catch (Exception unused3) {
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception unused4) {
                    }
                }
                return false;
            } catch (Throwable th2) {
                var th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception unused5) {
                    }
                }
                throw th;
            }
        }
        return false;
    }
}
