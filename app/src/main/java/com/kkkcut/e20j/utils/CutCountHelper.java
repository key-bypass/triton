package com.kkkcut.e20j.utils;

import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.liying.core.bean.KeyInfo;
import com.liying.core.bean.KeyType;
import java.io.File;

/* loaded from: classes.dex */
public class CutCountHelper {
    private static final String fileDirPath = Environment.getExternalStorageDirectory() + "/alpha/";
    private static final String fileName = "cutData.sec";
    static ReadCutCountListener listener;
    private static CutCountHelper sInstance;

    /* loaded from: classes.dex */
    public interface ReadCutCountListener {
        void onReadFinish(String str);
    }

    public void addCutCount(KeyInfo keyInfo) {
        float f = 0.5f;
        switch (keyInfo.getType()) {
            case 2:
            case 3:
            case 4:
            case 5:
                break;
            case 6:
                String spaceWidthStr = keyInfo.getSpaceWidthStr();
                if (!TextUtils.isEmpty(spaceWidthStr) && spaceWidthStr.contains("-")) {
                    f = 0.25f;
                    break;
                }
                break;
            case 7:
                f = 0.083333336f;
                break;
            default:
                f = 1.0f;
                break;
        }
        new MyTask(null).execute(Float.valueOf(f));
    }

    public void addCutCount(KeyType keyType) {
        addCutCount(keyType, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.kkkcut.e20j.utils.CutCountHelper$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$bean$KeyType;

        static {
            int[] iArr = new int[KeyType.values().length];
            $SwitchMap$com$liying$core$bean$KeyType = iArr;
            try {
                iArr[KeyType.SINGLE_INSIDE_GROOVE_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_OUTSIDE_GROOVE_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_INSIDE_GROOVE_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_OUTSIDE_GROOVE_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.TWO_GROOVE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.THREE_GROOVE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DIMPLE_KEY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.LEFT_GROOVE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.RIGHT_GROOVE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SIDE_HOLE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.ANGLE_KEY.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0016, code lost:
    
        if (r4 == false) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addCutCount(com.liying.core.bean.KeyType r3, boolean r4) {
        /*
            r2 = this;
            int[] r0 = com.kkkcut.e20j.utils.CutCountHelper.AnonymousClass1.$SwitchMap$com$liying$core$bean$KeyType
            int r3 = r3.ordinal()
            r3 = r0[r3]
            r0 = 1048576000(0x3e800000, float:0.25)
            r1 = 1056964608(0x3f000000, float:0.5)
            switch(r3) {
                case 1: goto L18;
                case 2: goto L18;
                case 3: goto L18;
                case 4: goto L18;
                case 5: goto L18;
                case 6: goto L18;
                case 7: goto L16;
                case 8: goto L1a;
                case 9: goto L1a;
                case 10: goto L1a;
                case 11: goto L12;
                default: goto Lf;
            }
        Lf:
            r0 = 1065353216(0x3f800000, float:1.0)
            goto L1a
        L12:
            r0 = 1034594987(0x3daaaaab, float:0.083333336)
            goto L1a
        L16:
            if (r4 != 0) goto L1a
        L18:
            r0 = 1056964608(0x3f000000, float:0.5)
        L1a:
            com.kkkcut.e20j.utils.CutCountHelper$MyTask r3 = new com.kkkcut.e20j.utils.CutCountHelper$MyTask
            r4 = 0
            r3.<init>(r4)
            r4 = 1
            java.lang.Float[] r4 = new java.lang.Float[r4]
            r1 = 0
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            r4[r1] = r0
            r3.execute(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.utils.CutCountHelper.addCutCount(com.liying.core.bean.KeyType, boolean):void");
    }

    public void getCutCount(ReadCutCountListener readCutCountListener) {
        listener = readCutCountListener;
        new MyTask(null).execute(Float.valueOf(0.0f));
    }

    private CutCountHelper() {
    }

    public static CutCountHelper getInstance() {
        if (sInstance == null) {
            sInstance = new CutCountHelper();
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class MyTask extends AsyncTask<Float, Integer, String> {
        private MyTask() {
        }

        /* synthetic */ MyTask(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Float... fArr) {
            File file = new File(CutCountHelper.fileDirPath);
            if (!file.exists()) {
                file.mkdir();
            }
            File file2 = new File(file, CutCountHelper.fileName);
            String readIoToString = FileUtil.readIoToString(file2.getPath());
            float f = 0.0f;
            if (TextUtils.isEmpty(readIoToString)) {
                readIoToString = "0";
            }
            try {
                f = Float.parseFloat(readIoToString);
                f += fArr[0].floatValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FileUtil.readIoStringToFile(String.valueOf(f), file2.getPath());
            return String.valueOf((int) Math.ceil(f));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            if (CutCountHelper.listener != null) {
                CutCountHelper.listener.onReadFinish(str);
                CutCountHelper.listener = null;
            }
        }
    }
}
