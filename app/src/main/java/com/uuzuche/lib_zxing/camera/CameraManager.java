package com.uuzuche.lib_zxing.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.view.SurfaceHolder;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class CameraManager {
    public static int FRAME_HEIGHT = -1;
    public static int FRAME_MARGINTOP = -1;
    public static int FRAME_WIDTH = -1;
    static final int SDK_INT;
    private static final String TAG = "CameraManager";
    private static CameraManager cameraManager;
    private final AutoFocusCallback autoFocusCallback;
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private final boolean useOneShotPreviewCallback;

    static {
        int i;
        try {
            i = Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException unused) {
            i = 10000;
        }
        SDK_INT = i;
    }

    public static void init(Context context) {
        if (cameraManager == null) {
            cameraManager = new CameraManager(context);
        }
    }

    public static CameraManager get() {
        return cameraManager;
    }

    private CameraManager(Context context) {
        this.context = context;
        CameraConfigurationManager cameraConfigurationManager = new CameraConfigurationManager(context);
        this.configManager = cameraConfigurationManager;
        boolean z = Integer.parseInt(Build.VERSION.SDK) > 3;
        this.useOneShotPreviewCallback = z;
        this.previewCallback = new PreviewCallback(cameraConfigurationManager, z);
        this.autoFocusCallback = new AutoFocusCallback();
    }

    public void openDriver(SurfaceHolder surfaceHolder) throws IOException {
        if (this.camera == null) {
            Camera open = Camera.open();
            this.camera = open;
            if (open == null) {
                throw new IOException();
            }
            open.setPreviewDisplay(surfaceHolder);
            if (!this.initialized) {
                this.initialized = true;
                this.configManager.initFromCameraParameters(this.camera);
            }
            this.configManager.setDesiredCameraParameters(this.camera);
            FlashlightManager.enableFlashlight();
        }
    }

    public void closeDriver() {
        if (this.camera != null) {
            FlashlightManager.disableFlashlight();
            this.camera.release();
            this.camera = null;
        }
    }

    public void startPreview() {
        Camera camera = this.camera;
        if (camera == null || this.previewing) {
            return;
        }
        camera.startPreview();
        this.previewing = true;
    }

    public void stopPreview() {
        Camera camera = this.camera;
        if (camera == null || !this.previewing) {
            return;
        }
        if (!this.useOneShotPreviewCallback) {
            camera.setPreviewCallback(null);
        }
        this.camera.stopPreview();
        this.previewCallback.setHandler(null, 0);
        this.autoFocusCallback.setHandler(null, 0);
        this.previewing = false;
    }

    public void requestPreviewFrame(Handler handler, int i) {
        if (this.camera == null || !this.previewing) {
            return;
        }
        this.previewCallback.setHandler(handler, i);
        if (this.useOneShotPreviewCallback) {
            this.camera.setOneShotPreviewCallback(this.previewCallback);
        } else {
            this.camera.setPreviewCallback(this.previewCallback);
        }
    }

    public void requestAutoFocus(Handler handler, int i) {
        if (this.camera == null || !this.previewing) {
            return;
        }
        this.autoFocusCallback.setHandler(handler, i);
        this.camera.autoFocus(this.autoFocusCallback);
    }

    public Rect getFramingRect() {
        try {
            Point screenResolution = this.configManager.getScreenResolution();
            if (this.camera == null) {
                return null;
            }
            int i = (screenResolution.x - FRAME_WIDTH) / 2;
            int i2 = FRAME_MARGINTOP;
            if (i2 == -1) {
                i2 = (screenResolution.y - FRAME_HEIGHT) / 2;
            }
            Rect rect = new Rect(i, i2, FRAME_WIDTH + i, FRAME_HEIGHT + i2);
            this.framingRect = rect;
            return rect;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Rect getFramingRectInPreview() {
        if (this.framingRectInPreview == null) {
            Rect rect = new Rect(getFramingRect());
            Point cameraResolution = this.configManager.getCameraResolution();
            Point screenResolution = this.configManager.getScreenResolution();
            rect.left = (rect.left * cameraResolution.y) / screenResolution.x;
            rect.right = (rect.right * cameraResolution.y) / screenResolution.x;
            rect.top = (rect.top * cameraResolution.x) / screenResolution.y;
            rect.bottom = (rect.bottom * cameraResolution.x) / screenResolution.y;
            this.framingRectInPreview = rect;
        }
        return this.framingRectInPreview;
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        Rect framingRectInPreview = getFramingRectInPreview();
        int previewFormat = this.configManager.getPreviewFormat();
        String previewFormatString = this.configManager.getPreviewFormatString();
        if (previewFormat == 16 || previewFormat == 17) {
            return new PlanarYUVLuminanceSource(bArr, i, i2, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height());
        }
        if ("yuv420p".equals(previewFormatString)) {
            return new PlanarYUVLuminanceSource(bArr, i, i2, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height());
        }
        throw new IllegalArgumentException("Unsupported picture format: " + previewFormat + '/' + previewFormatString);
    }

    public Context getContext() {
        return this.context;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public boolean isPreviewing() {
        return this.previewing;
    }

    public boolean isUseOneShotPreviewCallback() {
        return this.useOneShotPreviewCallback;
    }

    public PreviewCallback getPreviewCallback() {
        return this.previewCallback;
    }

    public AutoFocusCallback getAutoFocusCallback() {
        return this.autoFocusCallback;
    }

    public void setPreviewing(boolean z) {
        this.previewing = z;
    }
}
