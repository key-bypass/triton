package com.kkkcut.e20j.androidquick.tool;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.kkkcut.e20j.utils.DesUtil;

import net.zetetic.database.sqlcipher.SQLiteDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FileUtil {
    public static final String FILE_EXTENSION_SEPARATOR = ".";
    private static String TAG = "FileUtils";
    public static final String URI_TYPE_FILE = "file";
    public static final String DIR = ".AndroidQuick";
    public static final String CUSPATH = Environment.getExternalStorageDirectory().getPath() + File.separator + DIR + File.separator;

    public static Boolean getSDCardStatus() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static String createSDCardDir() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        String str = Environment.getExternalStorageDirectory().getPath() + File.separator + DIR;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static boolean isExistFile(String str) {
        return new File(str).exists();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String ReadPropertiesFile(java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            java.lang.String r7 = java.io.File.separator
            r0.append(r7)
            java.lang.String r7 = "setting.properties"
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            java.io.File r0 = new java.io.File
            r0.<init>(r7)
            boolean r7 = r0.isDirectory()
            java.lang.String r1 = "\n"
            java.lang.String r2 = "The File doesn't not exist."
            java.lang.String r3 = ""
            if (r7 == 0) goto L2e
            java.lang.String r7 = com.kkkcut.e20j.androidquick.tool.FileUtil.TAG
            com.kkkcut.e20j.androidquick.tool.LogUtil.d(r7, r2)
            r0 = r3
            goto L6f
        L2e:
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.io.IOException -> L5d java.io.FileNotFoundException -> L69
            r7.<init>(r0)     // Catch: java.io.IOException -> L5d java.io.FileNotFoundException -> L69
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L5d java.io.FileNotFoundException -> L69
            r0.<init>(r7)     // Catch: java.io.IOException -> L5d java.io.FileNotFoundException -> L69
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.io.IOException -> L5d java.io.FileNotFoundException -> L69
            r4.<init>(r0)     // Catch: java.io.IOException -> L5d java.io.FileNotFoundException -> L69
            r0 = r3
        L3e:
            java.lang.String r5 = r4.readLine()     // Catch: java.io.IOException -> L5b java.io.FileNotFoundException -> L6a
            if (r5 == 0) goto L57
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L5b java.io.FileNotFoundException -> L6a
            r6.<init>()     // Catch: java.io.IOException -> L5b java.io.FileNotFoundException -> L6a
            r6.append(r0)     // Catch: java.io.IOException -> L5b java.io.FileNotFoundException -> L6a
            r6.append(r5)     // Catch: java.io.IOException -> L5b java.io.FileNotFoundException -> L6a
            r6.append(r1)     // Catch: java.io.IOException -> L5b java.io.FileNotFoundException -> L6a
            java.lang.String r0 = r6.toString()     // Catch: java.io.IOException -> L5b java.io.FileNotFoundException -> L6a
            goto L3e
        L57:
            r7.close()     // Catch: java.io.IOException -> L5b java.io.FileNotFoundException -> L6a
            goto L6f
        L5b:
            r7 = move-exception
            goto L5f
        L5d:
            r7 = move-exception
            r0 = r3
        L5f:
            java.lang.String r2 = com.kkkcut.e20j.androidquick.tool.FileUtil.TAG
            java.lang.String r7 = r7.getMessage()
            com.kkkcut.e20j.androidquick.tool.LogUtil.d(r2, r7)
            goto L6f
        L69:
            r0 = r3
        L6a:
            java.lang.String r7 = com.kkkcut.e20j.androidquick.tool.FileUtil.TAG
            com.kkkcut.e20j.androidquick.tool.LogUtil.d(r7, r2)
        L6f:
            java.lang.String[] r7 = r0.split(r1)
            r0 = 0
        L74:
            int r1 = r7.length
            if (r0 >= r1) goto L87
            r1 = r7[r0]
            boolean r2 = r1.contains(r8)
            if (r2 == 0) goto L84
            java.lang.String r7 = r1.replace(r8, r3)
            return r7
        L84:
            int r0 = r0 + 1
            goto L74
        L87:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.androidquick.tool.FileUtil.ReadPropertiesFile(java.lang.String, java.lang.String):java.lang.String");
    }

    public static String ReadPropertiesFile(String str) {
        File file = new File(str);
        String str2 = "";
        if (file.isDirectory()) {
            LogUtil.d("TestFile", "The File doesn't not exist.");
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    str2 = str2 + readLine + "\n";
                }
                fileInputStream.close();
            } catch (FileNotFoundException unused) {
                LogUtil.d(TAG, "The File doesn't not exist.");
            } catch (IOException e) {
                LogUtil.d(TAG, e.getMessage());
            }
        }
        return str2;
    }

    public static String getFileType(String str) {
        return str.substring(str.lastIndexOf(FILE_EXTENSION_SEPARATOR) + 1);
    }

    public static void installApk(Context context, String str) {
        LogUtil.d(TAG, "savePath " + str);
        File file = new File(str);
        if (file.exists()) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            intent.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    public static void assetsDataToSD(Context context, String str) throws IOException {
        assetsDataToSD(context, "card_base.zip", str);
    }

    public static void assetsDataToSD(Context context, String str, String str2) throws IOException {
        LogUtil.d(TAG, "path--> " + str2);
        if (!new File(str2).getParentFile().exists()) {
            new File(str2).getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        InputStream open = context.getAssets().open(str);
        byte[] bArr = new byte[1024];
        for (int read = open.read(bArr); read > 0; read = open.read(bArr)) {
            fileOutputStream.write(bArr, 0, read);
        }
        fileOutputStream.flush();
        open.close();
        fileOutputStream.close();
    }

    public static void insertCardRerousce(String str, String str2) {
        try {
            copyFile(new File(str), new File(str2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllFiles(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    getAllFiles(file2);
                } else {
                    System.out.println(file2);
                }
            }
        }
    }

    public static void copyFile(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        byte[] bArr = new byte[5120];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                bufferedOutputStream.write(bArr, 0, read);
            } else {
                bufferedOutputStream.flush();
                bufferedInputStream.close();
                bufferedOutputStream.close();
                fileOutputStream.close();
                fileInputStream.close();
                return;
            }
        }
    }

    public static String getMd5Str(String str) {
        try {
            File file = new File(str);
            String name = file.getName();
            if (!file.exists()) {
                return name;
            }
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    name = name + getMd5Str(file2.getAbsolutePath());
                }
                return name;
            }
            return name + file.getName();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void clearCookies(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();
    }

    private FileUtil() {
        throw new AssertionError();
    }

    public static StringBuilder readFile(String str, String str2) {
        File file = new File(str);
        Throwable th;
        Exception e;
        StringBuilder sb = new StringBuilder("");
        BufferedReader bufferedReader = null;
        if (!file.isFile()) {
            return null;
        }
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), str2));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            if (!sb.toString().equals("")) {
                                sb.append("\r\n");
                            }
                            sb.append(readLine);
                        } else {
                            IOUtil.close(bufferedReader2);
                            return sb;
                        }
                    } catch (IOException e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        throw new RuntimeException("IOException occurred. ", e);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        IOUtil.close(bufferedReader);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static boolean writeFile(String str, String str2, boolean z) {
        if (StringUtil.isEmpty(str2)) {
            return false;
        }
        Exception e = null;
        FileWriter fileWriter = null;
        try {
            try {
                makeDirs(str);
                FileWriter fileWriter2 = new FileWriter(str, z);
                try {
                    fileWriter2.write(str2);
                    IOUtil.close(fileWriter2);
                    return true;
                } catch (IOException e) {
                    e = e;
                    fileWriter = fileWriter2;
                    throw new RuntimeException("IOException occurred. ", e);
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    IOUtil.close(fileWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static boolean writeFile(String str, List<String> list, boolean z) {
        int i = 0;
        Exception e;
        Throwable th;
        if (list.size() == 0 || list == null) {
            return false;
        }
        FileWriter fileWriter = null;
        try {
            try {
                makeDirs(str);
                FileWriter fileWriter2 = new FileWriter(str, z);
                try {
                    for (String str2 : list) {
                        int i2 = i + 1;
                        if (i > 0) {
                            fileWriter2.write("\r\n");
                        }
                        fileWriter2.write(str2);
                        i = i2;
                    }
                    IOUtil.close(fileWriter2);
                    return true;
                } catch (IOException e) {
                    e = e;
                    throw new RuntimeException("IOException occurred. ", e);
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    IOUtil.close(fileWriter);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeFile(String str, String str2) {
        return writeFile(str, str2, false);
    }

    public static boolean writeFile(String str, List<String> list) {
        return writeFile(str, list, false);
    }

    public static boolean writeFile(String str, InputStream inputStream) {
        return writeFile(str, inputStream, false);
    }

    public static boolean writeFile(String str, InputStream inputStream, boolean z) {
        return writeFile(str != null ? new File(str) : null, inputStream, z);
    }

    public static boolean writeFile(File file, InputStream inputStream) throws IOException {
        return writeFile(file, inputStream, false);
    }

    public static boolean writeFile(File file, InputStream inputStream, boolean z) {
        FileOutputStream fileOutputStream = null;
        Exception e;
        Throwable th;
        try {
            try {
                makeDirs(file.getAbsolutePath());
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, z);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            fileOutputStream2.flush();
                            IOUtil.close(fileOutputStream2);
                            IOUtil.close(inputStream);
                            return true;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e = e;
                    throw new RuntimeException("FileNotFoundException occurred. ", e);
                } catch (IOException e2) {
                    e = e2;
                    throw new RuntimeException("IOException occurred. ", e);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    IOUtil.close(fileOutputStream);
                    IOUtil.close(inputStream);
                    throw th;
                }
            } catch (FileNotFoundException e3) {
                e = e3;
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void writeFile(InputStream inputStream, File file) throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file != null && file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bArr = new byte[131072];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
                return;
            }
        }
    }

    public static void moveFile(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new RuntimeException("Both sourceFilePath and destFilePath cannot be null.");
        }
        moveFile(new File(str), new File(str2));
    }

    public static void moveFile(File file, File file2) {
        if (file.renameTo(file2)) {
            return;
        }
        copyFile(file.getAbsolutePath(), file2.getAbsolutePath());
        deleteFile(file.getAbsolutePath());
    }

    public static boolean copyFile(String str, String str2) {
        try {
            return writeFile(str2, new FileInputStream(str));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        }
    }

    public static List<String> readFileToList(String str, String str2) {
        File file = new File(str);
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        if (!file.isFile()) {
            return null;
        }
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), str2));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            arrayList.add(readLine);
                        } else {
                            IOUtil.close(bufferedReader2);
                            return arrayList;
                        }
                    } catch (IOException e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        throw new RuntimeException("IOException occurred. ", e);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        IOUtil.close(bufferedReader);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static String getFileNameWithoutExtension(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
        }
        if (lastIndexOf == -1) {
            return str.substring(lastIndexOf2 + 1);
        }
        if (lastIndexOf2 < lastIndexOf) {
            return str.substring(lastIndexOf2 + 1, lastIndexOf);
        }
        return str.substring(lastIndexOf2 + 1);
    }

    public static String getFileName(String str) {
        int lastIndexOf;
        return (StringUtil.isEmpty(str) || (lastIndexOf = str.lastIndexOf(File.separator)) == -1) ? str : str.substring(lastIndexOf + 1);
    }

    public static String getFolderName(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf);
    }

    public static String getFileExtension(String str) {
        if (StringUtil.isBlank(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        return (lastIndexOf != -1 && str.lastIndexOf(File.separator) < lastIndexOf) ? str.substring(lastIndexOf + 1) : "";
    }

    public static boolean makeDirs(String str) {
        String folderName = getFolderName(str);
        if (StringUtil.isEmpty(folderName)) {
            return false;
        }
        File file = new File(folderName);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return file.mkdirs();
    }

    public static boolean makeFolders(String str) {
        return makeDirs(str);
    }

    public static boolean isFileExist(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isFile();
    }

    public static boolean isFolderExist(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isDirectory();
    }

    public static boolean deleteFile(String str) {
        if (StringUtil.isBlank(str)) {
            return true;
        }
        File file = new File(str);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                file2.delete();
            } else if (file2.isDirectory()) {
                deleteFile(file2.getAbsolutePath());
            }
        }
        return file.delete();
    }

    public static long getFileSize(String str) {
        if (StringUtil.isBlank(str)) {
            return -1L;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            return file.length();
        }
        return -1L;
    }

    public static boolean save2File(InputStream inputStream, String str) {
        File file = new File(str);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public static byte[] readFile4Bytes(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[(int) file.length()];
                fileInputStream.read(bArr);
                try {
                    fileInputStream.close();
                } catch (IOException unused) {
                }
                return bArr;
            } catch (IOException unused2) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (IOException unused5) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String readFileContent(String str) {
        try {
            return readFileContent(str, null, null, 1024);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:12|(2:13|14)|(3:55|56|(1:58)(8:59|17|18|(3:19|20|(1:22)(1:23))|24|25|26|27))|16|17|18|(4:19|20|(0)(0)|22)|24|25|26|27) */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0095, code lost:
    
        if (r3 == null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x006c, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x006d, code lost:
    
        r6 = r5;
        r5 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0054 A[Catch: all -> 0x0065, IOException -> 0x006a, LOOP:0: B:19:0x004e->B:22:0x0054, LOOP_END, TRY_LEAVE, TryCatch #9 {IOException -> 0x006a, all -> 0x0065, blocks: (B:20:0x004e, B:22:0x0054), top: B:19:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b A[EDGE_INSN: B:23:0x005b->B:24:0x005b BREAK  A[LOOP:0: B:19:0x004e->B:22:0x0054], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008d A[Catch: IOException -> 0x0098, TRY_ENTER, TryCatch #4 {IOException -> 0x0098, blocks: (B:24:0x005b, B:25:0x0061, B:32:0x008d, B:34:0x0092), top: B:13:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0092 A[Catch: IOException -> 0x0098, TRY_LEAVE, TryCatch #4 {IOException -> 0x0098, blocks: (B:24:0x005b, B:25:0x0061, B:32:0x008d, B:34:0x0092), top: B:13:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0080 A[Catch: IOException -> 0x0088, TryCatch #3 {IOException -> 0x0088, blocks: (B:47:0x007b, B:40:0x0080, B:42:0x0085), top: B:46:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0085 A[Catch: IOException -> 0x0088, TRY_LEAVE, TryCatch #3 {IOException -> 0x0088, blocks: (B:47:0x007b, B:40:0x0080, B:42:0x0085), top: B:46:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readFileContent(java.lang.String r5, java.lang.String r6, java.lang.String r7, int r8) {
        /*
            java.lang.String r0 = ""
            if (r5 == 0) goto L9d
            boolean r1 = r5.equals(r0)
            if (r1 == 0) goto Lc
            goto L9d
        Lc:
            if (r7 == 0) goto L14
            boolean r1 = r7.equals(r0)
            if (r1 == 0) goto L16
        L14:
            java.lang.String r7 = "\n"
        L16:
            java.io.File r1 = new java.io.File
            r1.<init>(r5)
            boolean r1 = r1.exists()
            if (r1 != 0) goto L22
            return r0
        L22:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>(r0)
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L89
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L76 java.io.IOException -> L89
            if (r6 == 0) goto L44
            java.lang.String r5 = r6.trim()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
            boolean r5 = r5.equals(r0)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
            if (r5 == 0) goto L3a
            goto L44
        L3a:
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
            java.lang.String r6 = r6.trim()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
            r5.<init>(r3, r6)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
            goto L49
        L44:
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
            r5.<init>(r3)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
        L49:
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L8b
            r6.<init>(r5, r8)     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L8b
        L4e:
            java.lang.String r8 = r6.readLine()     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L6a
            if (r8 == 0) goto L5b
            r1.append(r8)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L6a
            r1.append(r7)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L6a
            goto L4e
        L5b:
            r6.close()     // Catch: java.io.IOException -> L98
            r5.close()     // Catch: java.io.IOException -> L98
        L61:
            r3.close()     // Catch: java.io.IOException -> L98
            goto L98
        L65:
            r7 = move-exception
            r2 = r6
            r6 = r5
            r5 = r7
            goto L79
        L6a:
            r2 = r6
            goto L8b
        L6c:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
            goto L79
        L71:
            r5 = move-exception
            r6 = r2
            goto L79
        L74:
            r5 = r2
            goto L8b
        L76:
            r5 = move-exception
            r6 = r2
            r3 = r6
        L79:
            if (r2 == 0) goto L7e
            r2.close()     // Catch: java.io.IOException -> L88
        L7e:
            if (r6 == 0) goto L83
            r6.close()     // Catch: java.io.IOException -> L88
        L83:
            if (r3 == 0) goto L88
            r3.close()     // Catch: java.io.IOException -> L88
        L88:
            throw r5
        L89:
            r5 = r2
            r3 = r5
        L8b:
            if (r2 == 0) goto L90
            r2.close()     // Catch: java.io.IOException -> L98
        L90:
            if (r5 == 0) goto L95
            r5.close()     // Catch: java.io.IOException -> L98
        L95:
            if (r3 == 0) goto L98
            goto L61
        L98:
            java.lang.String r5 = r1.toString()
            return r5
        L9d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.androidquick.tool.FileUtil.readFileContent(java.lang.String, java.lang.String, java.lang.String, int):java.lang.String");
    }

    public static boolean copyAssetToSDCard(AssetManager assetManager, String str, String str2) {
        try {
            InputStream open = assetManager.open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            if (open == null) {
                return true;
            }
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    open.close();
                    return true;
                }
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public static void openFile(Context context, File file) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            intent.setDataAndType(Uri.fromFile(file), MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(file.getPath())));
            context.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(context, "打开失败.", 0).show();
        }
    }

    public static boolean checkFileSize(String str, int i) {
        File file = new File(str);
        return file.exists() && !file.isDirectory() && file.length() <= ((long) (i * 1024));
    }

    public static void openMedia(Context context, File file) {
        if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")) {
            viewPhoto(context, file);
        } else {
            openFile(context, file);
        }
    }

    public static void viewPhoto(Context context, String str) {
        viewPhoto(context, new File(str));
    }

    public static void viewPhoto(Context context, File file) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(file), "image/*");
            context.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(context, "打开失败.", 0).show();
        }
    }

    public static boolean saveStrToFile(String str, String str2) {
        return saveStrToFile(str, str2, "UTF-8");
    }

    public static boolean saveStrToFile(String str, String str2, String str3) {
        byte[] bytes;
        FileOutputStream fileOutputStream;
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
                th = th2;
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

    public static String uriToPath(Context context, Uri uri) {
        Cursor cursor;
        try {
            if (uri.getScheme().equalsIgnoreCase(URI_TYPE_FILE)) {
                return uri.getPath();
            }
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor.moveToFirst()) {
                    return cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                }
                return null;
            } catch (Exception unused) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
        } catch (Exception unused2) {
            cursor = null;
        }
    }

    public static void playSound(Context context, String str) {
        playSound(context, new File(str));
    }

    public static void playSound(Context context, File file) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(file), "audio/*");
            context.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(context, "打开失败.", 0).show();
        }
    }

    public static void playVideo(Context context, String str) {
        playVideo(context, new File(str));
    }

    public static void playVideo(Context context, File file) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(file), "video/*");
            context.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(context, "打开失败.", 0).show();
        }
    }

    public static void renameFile(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2)) {
                return;
            }
            new File(str).renameTo(new File(str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0037 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readStringFromAssets(android.content.res.AssetManager r2, java.lang.String r3) {
        /*
            r0 = 0
            java.io.InputStream r2 = r2.open(r3)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L25
            int r3 = r2.available()     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L21
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L21
            r2.read(r3)     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L21
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L21
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L21
            if (r2 == 0) goto L1d
            r2.close()     // Catch: java.io.IOException -> L19
            goto L1d
        L19:
            r2 = move-exception
            r2.printStackTrace()
        L1d:
            return r1
        L1e:
            r3 = move-exception
            r0 = r2
            goto L35
        L21:
            r3 = move-exception
            goto L27
        L23:
            r3 = move-exception
            goto L35
        L25:
            r3 = move-exception
            r2 = r0
        L27:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L1e
            if (r2 == 0) goto L34
            r2.close()     // Catch: java.io.IOException -> L30
            goto L34
        L30:
            r2 = move-exception
            r2.printStackTrace()
        L34:
            return r0
        L35:
            if (r0 == 0) goto L3f
            r0.close()     // Catch: java.io.IOException -> L3b
            goto L3f
        L3b:
            r2 = move-exception
            r2.printStackTrace()
        L3f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.androidquick.tool.FileUtil.readStringFromAssets(android.content.res.AssetManager, java.lang.String):java.lang.String");
    }

    public static boolean string2File(String str, String str2) {
        BufferedReader bufferedReader = null;
        try {
            try {
                File file = new File(str2);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                BufferedReader bufferedReader2 = new BufferedReader(new StringReader(str));
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    char[] cArr = new char[1024];
                    while (true) {
                        int read = bufferedReader2.read(cArr);
                        if (read != -1) {
                            bufferedWriter.write(cArr, 0, read);
                        } else {
                            bufferedWriter.flush();
                            bufferedReader2.close();
                            bufferedWriter.close();
                            try {
                                bufferedReader2.close();
                                return true;
                            } catch (IOException e) {
                                e.printStackTrace();
                                return true;
                            }
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    bufferedReader = bufferedReader2;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void readIoStringToFile(String str, String str2) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    File file = new File(str2);
                    if (file.exists()) {
                        file.delete();
                    }
                    String encrypt = DesUtil.encrypt(str, DesUtil.SERVER);
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        fileOutputStream2.write(encrypt.getBytes());
                        fileOutputStream2.flush();
                        fileOutputStream2.getFD().sync();
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0049 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.lang.Exception] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readIoToString(java.lang.String r4) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            int r1 = r4.available()     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L46
            byte[] r1 = new byte[r1]     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L46
            r4.read(r1)     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L46
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L46
            r2.<init>(r1)     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L46
            java.lang.String r0 = "12KK5SPL"
            java.lang.String r0 = com.kkkcut.e20j.utils.DesUtil.decrypt(r2, r0)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L46
            r4.close()     // Catch: java.lang.Exception -> L23
            goto L45
        L23:
            r4 = move-exception
            r4.printStackTrace()
            goto L45
        L28:
            r0 = move-exception
            goto L37
        L2a:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L37
        L2e:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L47
        L33:
            r4 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
        L37:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L46
            if (r4 == 0) goto L44
            r4.close()     // Catch: java.lang.Exception -> L40
            goto L44
        L40:
            r4 = move-exception
            r4.printStackTrace()
        L44:
            r0 = r2
        L45:
            return r0
        L46:
            r0 = move-exception
        L47:
            if (r4 == 0) goto L51
            r4.close()     // Catch: java.lang.Exception -> L4d
            goto L51
        L4d:
            r4 = move-exception
            r4.printStackTrace()
        L51:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.androidquick.tool.FileUtil.readIoToString(java.lang.String):java.lang.String");
    }

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
}
