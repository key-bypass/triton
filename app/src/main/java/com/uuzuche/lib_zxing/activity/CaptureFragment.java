package com.uuzuche.lib_zxing.activity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.CaptureActivityHandler;
import com.uuzuche.lib_zxing.decoding.InactivityTimer;
import com.uuzuche.lib_zxing.view.ViewfinderView;
import com.uuzuche.lib_zxing.R;


import java.io.IOException;
import java.util.Vector;

/* loaded from: classes2.dex */
public class CaptureFragment extends Fragment implements SurfaceHolder.Callback {
    private static final float BEEP_VOLUME = 0.1f;
    private static final long VIBRATE_DURATION = 200;
    private CodeUtils.AnalyzeCallback analyzeCallback;
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() { // from class: com.uuzuche.lib_zxing.activity.CaptureFragment.1
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
    CameraInitCallBack callBack;
    private Camera camera;
    private String characterSet;
    private Vector<BarcodeFormat> decodeFormats;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private boolean vibrate;
    private ViewfinderView viewfinderView;

    /* loaded from: classes2.dex */
    public interface CameraInitCallBack {
        void callBack(Exception exc);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CameraManager.init(getActivity().getApplication());
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(getActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        Bundle arguments = getArguments();
        View inflate = (arguments == null || (i = arguments.getInt(CodeUtils.LAYOUT_ID)) == -1) ? null : layoutInflater.inflate(i, (ViewGroup) null);
        if (inflate == null) {
            inflate = layoutInflater.inflate(R.layout.fragment_capture, (ViewGroup) null);
        }
        this.viewfinderView = (ViewfinderView) inflate.findViewById(R.id.viewfinder_view);
        SurfaceView surfaceView = inflate.findViewById(R.id.preview_view);
        this.surfaceView = surfaceView;
        this.surfaceHolder = surfaceView.getHolder();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.hasSurface) {
            initCamera(this.surfaceHolder);
        } else {
            this.surfaceHolder.addCallback(this);
            this.surfaceHolder.setType(3);
        }
        this.decodeFormats = null;
        this.characterSet = null;
        this.playBeep = true;
        FragmentActivity activity = getActivity();
        getActivity();
        if (((AudioManager) activity.getSystemService(Context.AUDIO_SERVICE)).getRingerMode() != 2) {
            this.playBeep = false;
        }
        initBeepSound();
        this.vibrate = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
            this.handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.inactivityTimer.shutdown();
    }

    public void handleDecode(Result result, Bitmap bitmap) {
        this.inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        if (result == null || TextUtils.isEmpty(result.getText())) {
            CodeUtils.AnalyzeCallback analyzeCallback = this.analyzeCallback;
            if (analyzeCallback != null) {
                analyzeCallback.onAnalyzeFailed();
                return;
            }
            return;
        }
        CodeUtils.AnalyzeCallback analyzeCallback2 = this.analyzeCallback;
        if (analyzeCallback2 != null) {
            analyzeCallback2.onAnalyzeSuccess(bitmap, result.getText());
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            this.camera = CameraManager.get().getCamera();
            CameraInitCallBack cameraInitCallBack = this.callBack;
            if (cameraInitCallBack != null) {
                cameraInitCallBack.callBack(null);
            }
            if (this.handler == null) {
                this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.characterSet, this.viewfinderView);
            }
        } catch (Exception e) {
            CameraInitCallBack cameraInitCallBack2 = this.callBack;
            if (cameraInitCallBack2 != null) {
                cameraInitCallBack2.callBack(e);
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.hasSurface) {
            return;
        }
        this.hasSurface = true;
        initCamera(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.hasSurface = false;
        Camera camera = this.camera;
        if (camera == null || camera == null || !CameraManager.get().isPreviewing()) {
            return;
        }
        if (!CameraManager.get().isUseOneShotPreviewCallback()) {
            this.camera.setPreviewCallback(null);
        }
        this.camera.stopPreview();
        var manager = CameraManager.get();
        manager.getPreviewCallback().setHandler(null, 0);
        manager.getAutoFocusCallback().setHandler(null, 0);
        manager.setPreviewing(false);
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }

    private void initBeepSound() {
        if (this.playBeep && this.mediaPlayer == null) {
            getActivity().setVolumeControlStream(3);
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mediaPlayer = mediaPlayer;
            mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setOnCompletionListener(this.beepListener);
            AssetFileDescriptor openRawResourceFd = getResources().openRawResourceFd(R.raw.beep);
            try {
                this.mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
                openRawResourceFd.close();
                this.mediaPlayer.setVolume(0.1f, 0.1f);
                this.mediaPlayer.prepare();
            } catch (IOException unused) {
                this.mediaPlayer = null;
            }
        }
    }

    private void playBeepSoundAndVibrate() {
        MediaPlayer mediaPlayer;
        if (this.playBeep && (mediaPlayer = this.mediaPlayer) != null) {
            mediaPlayer.start();
        }
        if (this.vibrate) {
            FragmentActivity activity = getActivity();
            getActivity();
            ((Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(VIBRATE_DURATION);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.uuzuche.lib_zxing.activity.CaptureFragment$1 */
    /* loaded from: classes2.dex */
    public class C21031 implements MediaPlayer.OnCompletionListener {
        C21031() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    }

    public CodeUtils.AnalyzeCallback getAnalyzeCallback() {
        return this.analyzeCallback;
    }

    public void setAnalyzeCallback(CodeUtils.AnalyzeCallback analyzeCallback) {
        this.analyzeCallback = analyzeCallback;
    }

    public void setCameraInitCallBack(CameraInitCallBack cameraInitCallBack) {
        this.callBack = cameraInitCallBack;
    }
}
