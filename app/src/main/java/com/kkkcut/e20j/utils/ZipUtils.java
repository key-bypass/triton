package com.kkkcut.e20j.utils;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* loaded from: classes.dex */
public final class ZipUtils {
    private static final int BUFFER_LEN = 8192;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean zipFiles(Collection<String> collection, String str) throws IOException {
        return zipFiles(collection, str, (String) null);
    }

    public static boolean zipFiles(Collection<String> collection, String str, String str2) throws IOException {
        ZipOutputStream zipOutputStream = null;
        if (collection == null || str == null) {
            return false;
        }
        ZipOutputStream zipOutputStream2 = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
        } catch (Throwable th) {
            th = th;
        }
        try {
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                if (!zipFile(getFileByPath(it.next()), "", zipOutputStream, str2)) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                    return false;
                }
            }
            zipOutputStream.finish();
            zipOutputStream.close();
            return true;
        } catch (Throwable th2) {
            zipOutputStream2 = zipOutputStream;
            if (zipOutputStream2 != null) {
                zipOutputStream2.finish();
                zipOutputStream2.close();
            }
            throw th2;
        }
    }

    public static boolean zipFiles(Collection<File> collection, File file) throws IOException {
        return zipFiles(collection, file, (String) null);
    }

    public static boolean zipFiles(Collection<File> collection, File file, String str) throws IOException {
        ZipOutputStream zipOutputStream = null;
        if (collection == null || file == null) {
            return false;
        }
        ZipOutputStream zipOutputStream2 = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
        } catch (Throwable th) {
            th = th;
        }
        try {
            Iterator<File> it = collection.iterator();
            while (it.hasNext()) {
                if (!zipFile(it.next(), "", zipOutputStream, str)) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                    return false;
                }
            }
            zipOutputStream.finish();
            zipOutputStream.close();
            return true;
        } catch (Throwable th2) {
            zipOutputStream2 = zipOutputStream;
            if (zipOutputStream2 != null) {
                zipOutputStream2.finish();
                zipOutputStream2.close();
            }
            throw th2;
        }
    }

    public static boolean zipFile(String str, String str2) throws IOException {
        return zipFile(getFileByPath(str), getFileByPath(str2), (String) null);
    }

    public static boolean zipFile(String str, String str2, String str3) throws IOException {
        return zipFile(getFileByPath(str), getFileByPath(str2), str3);
    }

    public static boolean zipFile(File file, File file2) throws IOException {
        return zipFile(file, file2, (String) null);
    }

    public static boolean zipFile(File file, File file2, String str) throws IOException {
        ZipOutputStream zipOutputStream = null;
        if (file == null || file2 == null) {
            return false;
        }
        ZipOutputStream zipOutputStream2 = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean zipFile = zipFile(file, "", zipOutputStream, str);
            zipOutputStream.close();
            return zipFile;
        } catch (Throwable th2) {

            zipOutputStream2 = zipOutputStream;
            if (zipOutputStream2 != null) {
                zipOutputStream2.close();
            }
            throw th2;
        }
    }

    private static boolean zipFile(File file, String str, ZipOutputStream zipOutputStream, String str2) throws IOException {
        StringBuilder sb = new StringBuilder();
        Throwable th = null;
        sb.append(str);
        sb.append(isSpace(str) ? "" : File.separator);
        sb.append(file.getName());
        String sb2 = sb.toString();
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(sb2 + '/');
                zipEntry.setComment(str2);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.closeEntry();
                return true;
            }
            for (File file2 : listFiles) {
                if (!zipFile(file2, sb2, zipOutputStream, str2)) {
                    return false;
                }
            }
            return true;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                ZipEntry zipEntry2 = new ZipEntry(sb2);
                zipEntry2.setComment(str2);
                zipOutputStream.putNextEntry(zipEntry2);
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = bufferedInputStream2.read(bArr, 0, 8192);
                    if (read != -1) {
                        zipOutputStream.write(bArr, 0, read);
                    } else {
                        zipOutputStream.closeEntry();
                        bufferedInputStream2.close();
                        return true;
                    }
                }
            } catch (Throwable th4) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return false;
    }

    public static List<File> unzipFile(String str, String str2) throws IOException {
        return unzipFileByKeyword(str, str2, (String) null);
    }

    public static List<File> unzipFile(File file, File file2) throws IOException {
        return unzipFileByKeyword(file, file2, (String) null);
    }

    public static List<File> unzipFileByKeyword(String str, String str2, String str3) throws IOException {
        return unzipFileByKeyword(getFileByPath(str), getFileByPath(str2), str3);
    }

    public static List<File> unzipFileByKeyword(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        try {
            if (!isSpace(str)) {
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    String name = nextElement.getName();
                    if (name.contains("../")) {
                        Log.e("ZipUtils", "it's dangerous!");
                        return arrayList;
                    }
                    if (name.contains(str) && !unzipChildFile(file2, arrayList, zipFile, nextElement)) {
                        return arrayList;
                    }
                }
                return arrayList;
            }
            while (entries.hasMoreElements()) {
                ZipEntry nextElement2 = entries.nextElement();
                if (nextElement2.getName().contains("../")) {
                    Log.e("ZipUtils", "it's dangerous!");
                    return arrayList;
                }
                if (!unzipChildFile(file2, arrayList, zipFile, nextElement2)) {
                    return arrayList;
                }
            }
            return arrayList;
        } finally {
            zipFile.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean unzipChildFile(java.io.File r2, java.util.List<java.io.File> r3, java.util.zip.ZipFile r4, java.util.zip.ZipEntry r5) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = r5.getName()
            r0.<init>(r2, r1)
            r3.add(r0)
            boolean r2 = r5.isDirectory()
            if (r2 == 0) goto L17
            boolean r2 = createOrExistsDir(r0)
            return r2
        L17:
            boolean r2 = createOrExistsFile(r0)
            r3 = 0
            if (r2 != 0) goto L1f
            return r3
        L1f:
            r2 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L4f
            java.io.InputStream r4 = r4.getInputStream(r5)     // Catch: java.lang.Throwable -> L4f
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L4f
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L4c
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L4c
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L4c
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L4c
            r2 = 8192(0x2000, float:1.148E-41)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L4a
        L37:
            int r5 = r1.read(r2)     // Catch: java.lang.Throwable -> L4a
            r0 = -1
            if (r5 == r0) goto L42
            r4.write(r2, r3, r5)     // Catch: java.lang.Throwable -> L4a
            goto L37
        L42:
            r1.close()
            r4.close()
            r2 = 1
            return r2
        L4a:
            r2 = move-exception
            goto L53
        L4c:
            r3 = move-exception
            r4 = r2
            goto L52
        L4f:
            r3 = move-exception
            r4 = r2
            r1 = r4
        L52:
            r2 = r3
        L53:
            if (r1 == 0) goto L58
            r1.close()
        L58:
            if (r4 == 0) goto L5d
            r4.close()
        L5d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.utils.ZipUtils.unzipChildFile(java.io.File, java.util.List, java.util.zip.ZipFile, java.util.zip.ZipEntry):boolean");
    }

    public static List<String> getFilesPath(String str) throws IOException {
        return getFilesPath(getFileByPath(str));
    }

    public static List<String> getFilesPath(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            arrayList.add(entries.nextElement().getName());
        }
        zipFile.close();
        return arrayList;
    }

    public static List<String> getComments(String str) throws IOException {
        return getComments(getFileByPath(str));
    }

    public static List<String> getComments(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            arrayList.add(entries.nextElement().getComment());
        }
        zipFile.close();
        return arrayList;
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean isSpace(String str) {
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
}
