package com.uuzuve.lib_zxing.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.camera.BitmapLuminanceSource;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.DecodeFormatManager;


import java.util.Hashtable;
import java.util.Vector;

/* loaded from: classes2.dex */
public class CodeUtils {
    public static final String LAYOUT_ID = "layout_id";
    public static final int RESULT_FAILED = 2;
    public static final String RESULT_STRING = "result_string";
    public static final int RESULT_SUCCESS = 1;
    public static final String RESULT_TYPE = "result_type";

    /* loaded from: classes2.dex */
    public interface AnalyzeCallback {
        void onAnalyzeFailed();

        void onAnalyzeSuccess(Bitmap bitmap, String str);
    }

    public static void analyzeBitmap(String str, AnalyzeCallback analyzeCallback) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i = (int) (options.outHeight / 400.0f);
        options.inSampleSize = i > 0 ? i : 1;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        Hashtable hashtable = new Hashtable(2);
        Vector vector = new Vector();
        if (vector.isEmpty()) {
            vector = new Vector();
            vector.addAll(DecodeFormatManager.ONE_D_FORMATS);
            vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            vector.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }
        hashtable.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        multiFormatReader.setHints(hashtable);
        Result result = null;
        try {
            result = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(decodeFile))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            if (analyzeCallback != null) {
                analyzeCallback.onAnalyzeSuccess(decodeFile, result.getText());
            }
        } else if (analyzeCallback != null) {
            analyzeCallback.onAnalyzeFailed();
        }
    }

    public static Bitmap createImage(String str, int i, int i2, Bitmap bitmap) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Bitmap scaleLogo = getScaleLogo(bitmap, i, i2);
            int i7 = i / 2;
            int i8 = i2 / 2;
            if (scaleLogo != null) {
                int width = scaleLogo.getWidth();
                int height = scaleLogo.getHeight();
                i5 = width;
                i6 = height;
                i3 = (i - width) / 2;
                i4 = (i2 - height) / 2;
            } else {
                i3 = i7;
                i4 = i8;
                i5 = 0;
                i6 = 0;
            }
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hashtable.put(EncodeHintType.MARGIN, 0);
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashtable);
            int[] iArr = new int[i * i2];
            for (int i9 = 0; i9 < i2; i9++) {
                for (int i10 = 0; i10 < i; i10++) {
                    int i11 = ViewCompat.MEASURED_STATE_MASK;
                    if (i10 >= i3 && i10 < i3 + i5 && i9 >= i4 && i9 < i4 + i6) {
                        int pixel = scaleLogo.getPixel(i10 - i3, i9 - i4);
                        if (pixel != 0) {
                            i11 = pixel;
                        } else if (!encode.get(i10, i9)) {
                            i11 = -1;
                        }
                        iArr[(i9 * i) + i10] = i11;
                    } else if (encode.get(i10, i9)) {
                        iArr[(i9 * i) + i10] = -16777216;
                    } else {
                        iArr[(i9 * i) + i10] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap getScaleLogo(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        float min = Math.min(((i * 1.0f) / 5.0f) / bitmap.getWidth(), ((i2 * 1.0f) / 5.0f) / bitmap.getHeight());
        matrix.postScale(min, min);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void setFragmentArgs(CaptureFragment captureFragment, int i) {
        if (captureFragment == null || i == -1) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(LAYOUT_ID, i);
        captureFragment.setArguments(bundle);
    }

    public static void isLightEnable(boolean z) {
        if (z) {
            Camera camera = CameraManager.get().getCamera();
            if (camera != null) {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFlashMode("torch");
                camera.setParameters(parameters);
                return;
            }
            return;
        }
        Camera camera2 = CameraManager.get().getCamera();
        if (camera2 != null) {
            Camera.Parameters parameters2 = camera2.getParameters();
            parameters2.setFlashMode("off");
            camera2.setParameters(parameters2);
        }
    }
}
