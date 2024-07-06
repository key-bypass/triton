package com.uuzuche.lib_zxing.decoding;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.view.ViewfinderResultPointCallback;
import com.uuzuche.lib_zxing.view.ViewfinderView;


import java.util.Vector;

/* loaded from: classes2.dex */
public final class CaptureActivityHandler extends Handler {
    private static final String TAG = "CaptureActivityHandler";
    private final DecodeThread decodeThread;
    private final CaptureFragment fragment;
    private State state;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(CaptureFragment captureFragment, Vector<BarcodeFormat> vector, String str, ViewfinderView viewfinderView) {
        this.fragment = captureFragment;
        DecodeThread decodeThread = new DecodeThread(captureFragment, vector, str, new ViewfinderResultPointCallback(viewfinderView));
        this.decodeThread = decodeThread;
        decodeThread.start();
        this.state = State.SUCCESS;
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == com.uuzuche.lib_zxing.R.id.auto_focus) {
            if (this.state == State.PREVIEW) {
                CameraManager.get().requestAutoFocus(this, com.uuzuche.lib_zxing.R.id.auto_focus);
                return;
            }
            return;
        }
        if (message.what == com.uuzuche.lib_zxing.R.id.restart_preview) {
            Log.d(TAG, "Got restart preview message");
            restartPreviewAndDecode();
            return;
        }
        if (message.what == com.uuzuche.lib_zxing.R.id.decode_succeeded) {
            Log.d(TAG, "Got decode succeeded message");
            this.state = State.SUCCESS;
            Bundle data = message.getData();
            this.fragment.handleDecode((Result) message.obj, data == null ? null : data.getParcelable(DecodeThread.BARCODE_BITMAP));
            return;
        }
        if (message.what == com.uuzuche.lib_zxing.R.id.decode_failed) {
            this.state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), com.uuzuche.lib_zxing.R.id.decode);
            return;
        }
        if (message.what == com.uuzuche.lib_zxing.R.id.return_scan_result) {
            Log.d(TAG, "Got return scan result message");
            this.fragment.getActivity().setResult(-1, (Intent) message.obj);
            this.fragment.getActivity().finish();
        } else if (message.what == com.uuzuche.lib_zxing.R.id.launch_product_query) {
            Log.d(TAG, "Got product query message");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String) message.obj));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            this.fragment.getActivity().startActivity(intent);
        }
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        CameraManager.get().stopPreview();
        Message.obtain(this.decodeThread.getHandler(), com.uuzuche.lib_zxing.R.id.quit).sendToTarget();
        try {
            this.decodeThread.join();
        } catch (InterruptedException unused) {
        }
        removeMessages(com.uuzuche.lib_zxing.R.id.decode_succeeded);
        removeMessages(com.uuzuche.lib_zxing.R.id.decode_failed);
    }

    private void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), com.uuzuche.lib_zxing.R.id.decode);
            CameraManager.get().requestAutoFocus(this, com.uuzuche.lib_zxing.R.id.auto_focus);
            this.fragment.drawViewfinder();
        }
    }
}
