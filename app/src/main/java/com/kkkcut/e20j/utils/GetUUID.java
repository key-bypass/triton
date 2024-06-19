package com.kkkcut.e20j.utils;

import java.util.Iterator;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class GetUUID {
    public static String getUUID() {
        Iterator<String> it = execRootCmd("cat /proc/cpuinfo").iterator();
        String str = null;
        while (it.hasNext()) {
            String next = it.next();
            if (next.contains("Serial")) {
                str = next.substring(next.indexOf(":") + 1).trim();
            }
        }
        return replaceBlank(str);
    }

    public static String replaceBlank(String str) {
        return str != null ? Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("") : "";
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x0084 -> B:17:0x0087). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.Vector<java.lang.String> execRootCmd(java.lang.String r6) {
        /*
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6e
            java.lang.String r3 = "su"
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6e
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6e
            java.io.OutputStream r4 = r2.getOutputStream()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6e
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6e
            java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L67
            java.io.InputStream r5 = r2.getInputStream()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L67
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L67
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            r1.<init>()     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            r1.append(r6)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            java.lang.String r6 = "\n"
            r1.append(r6)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            java.lang.String r6 = r1.toString()     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            r3.writeBytes(r6)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            r3.flush()     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            java.lang.String r6 = "exit\n"
            r3.writeBytes(r6)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            r3.flush()     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
        L41:
            java.lang.String r6 = r4.readLine()     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            if (r6 == 0) goto L50
            java.lang.String r1 = "result"
            android.util.Log.w(r1, r6)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            r0.add(r6)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            goto L41
        L50:
            r2.waitFor()     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            r3.close()     // Catch: java.io.IOException -> L57
            goto L5b
        L57:
            r6 = move-exception
            r6.printStackTrace()
        L5b:
            r4.close()     // Catch: java.io.IOException -> L83
            goto L87
        L5f:
            r6 = move-exception
            goto L65
        L61:
            r6 = move-exception
            goto L69
        L63:
            r6 = move-exception
            r4 = r1
        L65:
            r1 = r3
            goto L89
        L67:
            r6 = move-exception
            r4 = r1
        L69:
            r1 = r3
            goto L70
        L6b:
            r6 = move-exception
            r4 = r1
            goto L89
        L6e:
            r6 = move-exception
            r4 = r1
        L70:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L88
            if (r1 == 0) goto L7d
            r1.close()     // Catch: java.io.IOException -> L79
            goto L7d
        L79:
            r6 = move-exception
            r6.printStackTrace()
        L7d:
            if (r4 == 0) goto L87
            r4.close()     // Catch: java.io.IOException -> L83
            goto L87
        L83:
            r6 = move-exception
            r6.printStackTrace()
        L87:
            return r0
        L88:
            r6 = move-exception
        L89:
            if (r1 == 0) goto L93
            r1.close()     // Catch: java.io.IOException -> L8f
            goto L93
        L8f:
            r0 = move-exception
            r0.printStackTrace()
        L93:
            if (r4 == 0) goto L9d
            r4.close()     // Catch: java.io.IOException -> L99
            goto L9d
        L99:
            r0 = move-exception
            r0.printStackTrace()
        L9d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.utils.GetUUID.execRootCmd(java.lang.String):java.util.Vector");
    }
}
