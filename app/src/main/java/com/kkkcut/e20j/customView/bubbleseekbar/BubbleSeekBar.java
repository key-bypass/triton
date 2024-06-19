package com.kkkcut.e20j.customView.bubbleseekbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.kkkcut.e20j.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;
import org.apache.poi.ss.formula.functions.Complex;

/* loaded from: classes.dex */
public class BubbleSeekBar extends View {
    static final int NONE = -1;
    private static final String TAG = "BubbleSeekBar";
    float dx;
    private boolean isAlwaysShowBubble;
    private boolean isAutoAdjustSectionMark;
    private boolean isFloatType;
    private boolean isHideBubble;
    private boolean isRtl;
    private boolean isSeekBySection;
    private boolean isSeekStepSection;
    private boolean isShowProgressInFloat;
    private boolean isShowSectionMark;
    private boolean isShowSectionText;
    private boolean isShowThumbText;
    private boolean isThumbOnDragging;
    private boolean isTouchToSeek;
    private boolean isTouchToSeekAnimEnd;
    private long mAlwaysShowBubbleDelay;
    private long mAnimDuration;
    private float mBubbleCenterRawSolidX;
    private float mBubbleCenterRawSolidY;
    private float mBubbleCenterRawX;
    private int mBubbleColor;
    private int mBubbleRadius;
    private int mBubbleTextColor;
    private int mBubbleTextSize;
    private BubbleView mBubbleView;
    private BubbleConfigBuilder mConfigBuilder;
    private float mDelta;
    private WindowManager.LayoutParams mLayoutParams;
    private float mLeft;
    private float mMax;
    private float mMin;
    private Paint mPaint;
    private int[] mPoint;
    private float mPreSecValue;
    private float mPreThumbCenterX;
    private float mProgress;
    private OnProgressChangedListener mProgressListener;
    private Rect mRectText;
    private float mRight;
    private int mSecondTrackColor;
    private int mSecondTrackSize;
    private int mSectionCount;
    private float mSectionOffset;
    private SparseArray<String> mSectionTextArray;
    private int mSectionTextColor;
    private int mSectionTextInterval;
    private int mSectionTextPosition;
    private int mSectionTextSize;
    private float mSectionValue;
    private int mTextSpace;
    private float mThumbCenterX;
    private int mThumbColor;
    private int mThumbRadius;
    private int mThumbRadiusOnDragging;
    private int mThumbTextColor;
    private int mThumbTextSize;
    private int mTrackColor;
    private float mTrackLength;
    private int mTrackSize;
    private WindowManager mWindowManager;
    private boolean triggerBubbleShowing;
    private boolean triggerSeekBySection;

    /* loaded from: classes.dex */
    public interface CustomSectionTextArray {
        SparseArray<String> onCustomize(int i, SparseArray<String> sparseArray);
    }

    /* loaded from: classes.dex */
    public interface OnProgressChangedListener {
        void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float f);

        void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z);

        void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z);
    }

    /* loaded from: classes.dex */
    public static abstract class OnProgressChangedListenerAdapter implements OnProgressChangedListener {
        @Override // com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
        public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float f) {
        }

        @Override // com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
        public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
        }

        @Override // com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.OnProgressChangedListener
        public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TextPosition {
        public static final int BELOW_SECTION_MARK = 2;
        public static final int BOTTOM_SIDES = 1;
        public static final int SIDES = 0;
    }

    public BubbleSeekBar(Context context) {
        this(context, null);
    }

    public BubbleSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSectionTextPosition = -1;
        this.mSectionTextArray = new SparseArray<>();
        this.mPoint = new int[2];
        this.isTouchToSeekAnimEnd = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BubbleSeekBar, i, 0);
        this.mMin = obtainStyledAttributes.getFloat(11, 0.0f);
        this.mMax = obtainStyledAttributes.getFloat(10, 100.0f);
        this.mProgress = obtainStyledAttributes.getFloat(12, this.mMin);
        this.isFloatType = obtainStyledAttributes.getBoolean(9, false);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(34, BubbleUtils.dp2px(2));
        this.mTrackSize = dimensionPixelSize;
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(15, dimensionPixelSize + BubbleUtils.dp2px(2));
        this.mSecondTrackSize = dimensionPixelSize2;
        this.mThumbRadius = obtainStyledAttributes.getDimensionPixelSize(28, dimensionPixelSize2 + BubbleUtils.dp2px(2));
        this.mThumbRadiusOnDragging = obtainStyledAttributes.getDimensionPixelSize(29, this.mSecondTrackSize * 2);
        this.mSectionCount = obtainStyledAttributes.getInteger(16, 10);
        this.mTrackColor = obtainStyledAttributes.getColor(33, ContextCompat.getColor(context, com.kkkcut.e20j.us.R.color.colorPrimary));
        int color = obtainStyledAttributes.getColor(14, ContextCompat.getColor(context, com.kkkcut.e20j.us.R.color.colorAccent));
        this.mSecondTrackColor = color;
        this.mThumbColor = obtainStyledAttributes.getColor(27, color);
        this.isShowSectionText = obtainStyledAttributes.getBoolean(25, false);
        this.mSectionTextSize = obtainStyledAttributes.getDimensionPixelSize(20, BubbleUtils.sp2px(14));
        this.mSectionTextColor = obtainStyledAttributes.getColor(17, this.mTrackColor);
        this.isSeekStepSection = obtainStyledAttributes.getBoolean(22, false);
        this.isSeekBySection = obtainStyledAttributes.getBoolean(21, false);
        int integer = obtainStyledAttributes.getInteger(19, -1);
        if (integer == 0) {
            this.mSectionTextPosition = 0;
        } else if (integer == 1) {
            this.mSectionTextPosition = 1;
        } else if (integer == 2) {
            this.mSectionTextPosition = 2;
        } else {
            this.mSectionTextPosition = -1;
        }
        this.mSectionTextInterval = obtainStyledAttributes.getInteger(18, 1);
        this.isShowThumbText = obtainStyledAttributes.getBoolean(26, false);
        this.mThumbTextSize = obtainStyledAttributes.getDimensionPixelSize(31, BubbleUtils.sp2px(14));
        this.mThumbTextColor = obtainStyledAttributes.getColor(30, this.mSecondTrackColor);
        this.mBubbleColor = obtainStyledAttributes.getColor(5, this.mSecondTrackColor);
        this.mBubbleTextSize = obtainStyledAttributes.getDimensionPixelSize(7, BubbleUtils.sp2px(14));
        this.mBubbleTextColor = obtainStyledAttributes.getColor(6, -1);
        this.isShowSectionMark = obtainStyledAttributes.getBoolean(24, false);
        this.isAutoAdjustSectionMark = obtainStyledAttributes.getBoolean(4, false);
        this.isShowProgressInFloat = obtainStyledAttributes.getBoolean(23, false);
        int integer2 = obtainStyledAttributes.getInteger(3, -1);
        this.mAnimDuration = integer2 < 0 ? 200L : integer2;
        this.isTouchToSeek = obtainStyledAttributes.getBoolean(32, false);
        this.isAlwaysShowBubble = obtainStyledAttributes.getBoolean(1, false);
        int integer3 = obtainStyledAttributes.getInteger(2, 0);
        this.mAlwaysShowBubbleDelay = integer3 < 0 ? 0L : integer3;
        this.isHideBubble = obtainStyledAttributes.getBoolean(8, false);
        this.isRtl = obtainStyledAttributes.getBoolean(13, false);
        setEnabled(obtainStyledAttributes.getBoolean(0, isEnabled()));
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mRectText = new Rect();
        this.mTextSpace = BubbleUtils.dp2px(2);
        initConfigByPriority();
        if (this.isHideBubble) {
            return;
        }
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        BubbleView bubbleView = new BubbleView(this, context);
        this.mBubbleView = bubbleView;
        bubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.gravity = BadgeDrawable.TOP_START;
        this.mLayoutParams.width = -2;
        this.mLayoutParams.height = -2;
        this.mLayoutParams.format = -3;
        this.mLayoutParams.flags = 524328;
        if (BubbleUtils.isMIUI() || Build.VERSION.SDK_INT >= 25) {
            this.mLayoutParams.type = 2;
        } else {
            this.mLayoutParams.type = 2005;
        }
        calculateRadiusOfBubble();
    }

    private void initConfigByPriority() {
        if (this.mMin == this.mMax) {
            this.mMin = 0.0f;
            this.mMax = 100.0f;
        }
        float f = this.mMin;
        float f2 = this.mMax;
        if (f > f2) {
            this.mMax = f;
            this.mMin = f2;
        }
        float f3 = this.mProgress;
        float f4 = this.mMin;
        if (f3 < f4) {
            this.mProgress = f4;
        }
        float f5 = this.mProgress;
        float f6 = this.mMax;
        if (f5 > f6) {
            this.mProgress = f6;
        }
        int i = this.mSecondTrackSize;
        int i2 = this.mTrackSize;
        if (i < i2) {
            this.mSecondTrackSize = i2 + BubbleUtils.dp2px(2);
        }
        int i3 = this.mThumbRadius;
        int i4 = this.mSecondTrackSize;
        if (i3 <= i4) {
            this.mThumbRadius = i4 + BubbleUtils.dp2px(2);
        }
        int i5 = this.mThumbRadiusOnDragging;
        int i6 = this.mSecondTrackSize;
        if (i5 <= i6) {
            this.mThumbRadiusOnDragging = i6 * 2;
        }
        if (this.mSectionCount <= 0) {
            this.mSectionCount = 10;
        }
        float f7 = this.mMax - this.mMin;
        this.mDelta = f7;
        float f8 = f7 / this.mSectionCount;
        this.mSectionValue = f8;
        if (f8 < 1.0f) {
            this.isFloatType = true;
        }
        if (this.isFloatType) {
            this.isShowProgressInFloat = true;
        }
        int i7 = this.mSectionTextPosition;
        if (i7 != -1) {
            this.isShowSectionText = true;
        }
        if (this.isShowSectionText) {
            if (i7 == -1) {
                this.mSectionTextPosition = 0;
            }
            if (this.mSectionTextPosition == 2) {
                this.isShowSectionMark = false;
            }
        }
        if (this.mSectionTextInterval < 1) {
            this.mSectionTextInterval = 1;
        }
        initSectionTextArray();
        if (this.isSeekStepSection) {
            this.isSeekBySection = false;
            this.isAutoAdjustSectionMark = false;
        }
        if (this.isAutoAdjustSectionMark && !this.isShowSectionMark) {
            this.isAutoAdjustSectionMark = false;
        }
        if (this.isSeekBySection) {
            float f9 = this.mMin;
            this.mPreSecValue = f9;
            if (this.mProgress != f9) {
                this.mPreSecValue = this.mSectionValue;
            }
            this.isShowSectionMark = true;
            this.isAutoAdjustSectionMark = true;
        }
        if (this.isHideBubble) {
            this.isAlwaysShowBubble = false;
        }
        if (this.isAlwaysShowBubble) {
            setProgress(this.mProgress);
        }
        this.mThumbTextSize = (this.isFloatType || this.isSeekBySection || (this.isShowSectionText && this.mSectionTextPosition == 2)) ? this.mSectionTextSize : this.mThumbTextSize;
    }

    private void calculateRadiusOfBubble() {
        String float2String;
        String float2String2;
        this.mPaint.setTextSize(this.mBubbleTextSize);
        if (this.isShowProgressInFloat) {
            float2String = float2String(this.isRtl ? this.mMax : this.mMin);
        } else if (this.isRtl) {
            float2String = this.isFloatType ? float2String(this.mMax) : String.valueOf((int) this.mMax);
        } else {
            float2String = this.isFloatType ? float2String(this.mMin) : String.valueOf((int) this.mMin);
        }
        this.mPaint.getTextBounds(float2String, 0, float2String.length(), this.mRectText);
        int width = (this.mRectText.width() + (this.mTextSpace * 2)) >> 1;
        if (this.isShowProgressInFloat) {
            float2String2 = float2String(this.isRtl ? this.mMin : this.mMax);
        } else if (this.isRtl) {
            float2String2 = this.isFloatType ? float2String(this.mMin) : String.valueOf((int) this.mMin);
        } else {
            float2String2 = this.isFloatType ? float2String(this.mMax) : String.valueOf((int) this.mMax);
        }
        this.mPaint.getTextBounds(float2String2, 0, float2String2.length(), this.mRectText);
        int width2 = (this.mRectText.width() + (this.mTextSpace * 2)) >> 1;
        int dp2px = BubbleUtils.dp2px(14);
        this.mBubbleRadius = dp2px;
        this.mBubbleRadius = Math.max(dp2px, Math.max(width, width2)) + this.mTextSpace;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initSectionTextArray() {
        /*
            r8 = this;
            int r0 = r8.mSectionTextPosition
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 != r1) goto L9
            r0 = 1
            goto La
        L9:
            r0 = 0
        La:
            int r4 = r8.mSectionTextInterval
            if (r4 <= r3) goto L14
            int r4 = r8.mSectionCount
            int r4 = r4 % r1
            if (r4 != 0) goto L14
            goto L15
        L14:
            r3 = 0
        L15:
            int r1 = r8.mSectionCount
            if (r2 > r1) goto L75
            boolean r4 = r8.isRtl
            if (r4 == 0) goto L26
            float r5 = r8.mMax
            float r6 = r8.mSectionValue
            float r7 = (float) r2
            float r6 = r6 * r7
            float r5 = r5 - r6
            goto L2e
        L26:
            float r5 = r8.mMin
            float r6 = r8.mSectionValue
            float r7 = (float) r2
            float r6 = r6 * r7
            float r5 = r5 + r6
        L2e:
            if (r0 == 0) goto L4d
            if (r3 == 0) goto L52
            int r1 = r8.mSectionTextInterval
            int r1 = r2 % r1
            if (r1 != 0) goto L72
            if (r4 == 0) goto L43
            float r1 = r8.mMax
            float r4 = r8.mSectionValue
            float r5 = (float) r2
            float r4 = r4 * r5
            float r1 = r1 - r4
            goto L4b
        L43:
            float r1 = r8.mMin
            float r4 = r8.mSectionValue
            float r5 = (float) r2
            float r4 = r4 * r5
            float r1 = r1 + r4
        L4b:
            r5 = r1
            goto L52
        L4d:
            if (r2 == 0) goto L52
            if (r2 == r1) goto L52
            goto L72
        L52:
            android.util.SparseArray<java.lang.String> r1 = r8.mSectionTextArray
            boolean r4 = r8.isFloatType
            if (r4 == 0) goto L5d
            java.lang.String r4 = r8.float2String(r5)
            goto L6f
        L5d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            int r5 = (int) r5
            r4.append(r5)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
        L6f:
            r1.put(r2, r4)
        L72:
            int r2 = r2 + 1
            goto L15
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.initSectionTextArray():void");
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.mThumbRadiusOnDragging * 2;
        if (this.isShowThumbText) {
            this.mPaint.setTextSize(this.mThumbTextSize);
            this.mPaint.getTextBounds(Complex.SUPPORTED_SUFFIX, 0, 1, this.mRectText);
            i3 += this.mRectText.height();
        }
        if (this.isShowSectionText && this.mSectionTextPosition >= 1) {
            this.mPaint.setTextSize(this.mSectionTextSize);
            this.mPaint.getTextBounds(Complex.SUPPORTED_SUFFIX, 0, 1, this.mRectText);
            i3 = Math.max(i3, (this.mThumbRadiusOnDragging * 2) + this.mRectText.height());
        }
        setMeasuredDimension(resolveSize(BubbleUtils.dp2px(180), i), i3 + (this.mTextSpace * 2));
        this.mLeft = getPaddingLeft() + this.mThumbRadiusOnDragging;
        this.mRight = (getMeasuredWidth() - getPaddingRight()) - this.mThumbRadiusOnDragging;
        if (this.isShowSectionText) {
            this.mPaint.setTextSize(this.mSectionTextSize);
            int i4 = this.mSectionTextPosition;
            if (i4 == 0) {
                String str = this.mSectionTextArray.get(0);
                this.mPaint.getTextBounds(str, 0, str.length(), this.mRectText);
                this.mLeft += this.mRectText.width() + this.mTextSpace;
                String str2 = this.mSectionTextArray.get(this.mSectionCount);
                this.mPaint.getTextBounds(str2, 0, str2.length(), this.mRectText);
                this.mRight -= this.mRectText.width() + this.mTextSpace;
            } else if (i4 >= 1) {
                String str3 = this.mSectionTextArray.get(0);
                this.mPaint.getTextBounds(str3, 0, str3.length(), this.mRectText);
                this.mLeft = getPaddingLeft() + Math.max(this.mThumbRadiusOnDragging, this.mRectText.width() / 2.0f) + this.mTextSpace;
                String str4 = this.mSectionTextArray.get(this.mSectionCount);
                this.mPaint.getTextBounds(str4, 0, str4.length(), this.mRectText);
                this.mRight = ((getMeasuredWidth() - getPaddingRight()) - Math.max(this.mThumbRadiusOnDragging, this.mRectText.width() / 2.0f)) - this.mTextSpace;
            }
        } else if (this.isShowThumbText && this.mSectionTextPosition == -1) {
            this.mPaint.setTextSize(this.mThumbTextSize);
            String str5 = this.mSectionTextArray.get(0);
            this.mPaint.getTextBounds(str5, 0, str5.length(), this.mRectText);
            this.mLeft = getPaddingLeft() + Math.max(this.mThumbRadiusOnDragging, this.mRectText.width() / 2.0f) + this.mTextSpace;
            String str6 = this.mSectionTextArray.get(this.mSectionCount);
            this.mPaint.getTextBounds(str6, 0, str6.length(), this.mRectText);
            this.mRight = ((getMeasuredWidth() - getPaddingRight()) - Math.max(this.mThumbRadiusOnDragging, this.mRectText.width() / 2.0f)) - this.mTextSpace;
        }
        float f = this.mRight - this.mLeft;
        this.mTrackLength = f;
        this.mSectionOffset = (f * 1.0f) / this.mSectionCount;
        if (this.isHideBubble) {
            return;
        }
        this.mBubbleView.measure(i, i2);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.isHideBubble) {
            return;
        }
        locatePositionInWindow();
    }

    private void locatePositionInWindow() {
        Window window;
        getLocationInWindow(this.mPoint);
        if (this.isRtl) {
            this.mBubbleCenterRawSolidX = (this.mPoint[0] + this.mRight) - (this.mBubbleView.getMeasuredWidth() / 2.0f);
        } else {
            this.mBubbleCenterRawSolidX = (this.mPoint[0] + this.mLeft) - (this.mBubbleView.getMeasuredWidth() / 2.0f);
        }
        this.mBubbleCenterRawX = calculateCenterRawXofBubbleView();
        float measuredHeight = this.mPoint[1] - this.mBubbleView.getMeasuredHeight();
        this.mBubbleCenterRawSolidY = measuredHeight;
        this.mBubbleCenterRawSolidY = measuredHeight - BubbleUtils.dp2px(30);
        if (BubbleUtils.isMIUI()) {
            this.mBubbleCenterRawSolidY -= BubbleUtils.dp2px(4);
        }
        Context context = getContext();
        if (!(context instanceof Activity) || (window = ((Activity) context).getWindow()) == null || (window.getAttributes().flags & 1024) == 0) {
            return;
        }
        Resources system = Resources.getSystem();
        this.mBubbleCenterRawSolidY += system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0244, code lost:
    
        if (r2 != r17.mMax) goto L83;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onDraw(android.graphics.Canvas r18) {
        /*
            Method dump skipped, instructions count: 727
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new Runnable() { // from class: com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.1
            @Override // java.lang.Runnable
            public void run() {
                BubbleSeekBar.this.requestLayout();
            }
        });
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        if (this.isHideBubble || !this.isAlwaysShowBubble) {
            return;
        }
        if (i != 0) {
            hideBubble();
        } else if (this.triggerBubbleShowing) {
            showBubble();
        }
        super.onVisibilityChanged(view, i);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        hideBubble();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
    
        if (r0 != 3) goto L104;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004d  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            Method dump skipped, instructions count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean isThumbTouched(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float f = (this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin);
        float f2 = this.isRtl ? this.mRight - f : this.mLeft + f;
        float measuredHeight = getMeasuredHeight() / 2.0f;
        return ((motionEvent.getX() - f2) * (motionEvent.getX() - f2)) + ((motionEvent.getY() - measuredHeight) * (motionEvent.getY() - measuredHeight)) <= (this.mLeft + ((float) BubbleUtils.dp2px(8))) * (this.mLeft + ((float) BubbleUtils.dp2px(8)));
    }

    private boolean isTrackTouched(MotionEvent motionEvent) {
        return isEnabled() && motionEvent.getX() >= ((float) getPaddingLeft()) && motionEvent.getX() <= ((float) (getMeasuredWidth() - getPaddingRight())) && motionEvent.getY() >= ((float) getPaddingTop()) && motionEvent.getY() <= ((float) (getMeasuredHeight() - getPaddingBottom()));
    }

    private float calThumbCxWhenSeekStepSection(float f) {
        float f2 = this.mLeft;
        if (f <= f2) {
            return f2;
        }
        float f3 = this.mRight;
        if (f >= f3) {
            return f3;
        }
        float f4 = 0.0f;
        int i = 0;
        while (i <= this.mSectionCount) {
            float f5 = this.mSectionOffset;
            f4 = (i * f5) + this.mLeft;
            if (f4 <= f && f - f4 <= f5) {
                break;
            }
            i++;
        }
        float f6 = f - f4;
        float f7 = this.mSectionOffset;
        return f6 <= f7 / 2.0f ? f4 : ((i + 1) * f7) + this.mLeft;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoAdjustSection() {
        int i = 0;
        float f = 0.0f;
        while (i <= this.mSectionCount) {
            float f2 = this.mSectionOffset;
            f = (i * f2) + this.mLeft;
            float f3 = this.mThumbCenterX;
            if (f <= f3 && f3 - f <= f2) {
                break;
            } else {
                i++;
            }
        }
        boolean z = BigDecimal.valueOf((double) this.mThumbCenterX).setScale(1, 4).floatValue() == f;
        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator valueAnimator = null;
        if (!z) {
            float f4 = this.mThumbCenterX;
            float f5 = f4 - f;
            float f6 = this.mSectionOffset;
            valueAnimator = f5 <= f6 / 2.0f ? ValueAnimator.ofFloat(f4, f) : ValueAnimator.ofFloat(f4, ((i + 1) * f6) + this.mLeft);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.5
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    BubbleSeekBar.this.mThumbCenterX = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                    BubbleSeekBar bubbleSeekBar = BubbleSeekBar.this;
                    bubbleSeekBar.mProgress = bubbleSeekBar.calculateProgress();
                    if (BubbleSeekBar.this.isHideBubble || BubbleSeekBar.this.mBubbleView.getParent() == null) {
                        BubbleSeekBar.this.processProgress();
                    } else {
                        BubbleSeekBar bubbleSeekBar2 = BubbleSeekBar.this;
                        bubbleSeekBar2.mBubbleCenterRawX = bubbleSeekBar2.calculateCenterRawXofBubbleView();
                        BubbleSeekBar.this.mLayoutParams.x = (int) (BubbleSeekBar.this.mBubbleCenterRawX + 0.5f);
                        BubbleSeekBar.this.mWindowManager.updateViewLayout(BubbleSeekBar.this.mBubbleView, BubbleSeekBar.this.mLayoutParams);
                        BubbleSeekBar.this.mBubbleView.setProgressText(BubbleSeekBar.this.isShowProgressInFloat ? String.valueOf(BubbleSeekBar.this.getProgressFloat()) : String.valueOf(BubbleSeekBar.this.getProgress()));
                    }
                    BubbleSeekBar.this.invalidate();
                    if (BubbleSeekBar.this.mProgressListener != null) {
                        OnProgressChangedListener onProgressChangedListener = BubbleSeekBar.this.mProgressListener;
                        BubbleSeekBar bubbleSeekBar3 = BubbleSeekBar.this;
                        onProgressChangedListener.onProgressChanged(bubbleSeekBar3, bubbleSeekBar3.getProgress(), BubbleSeekBar.this.getProgressFloat(), true);
                    }
                }
            });
        }
        if (!this.isHideBubble) {
            BubbleView bubbleView = this.mBubbleView;
            Property property = View.ALPHA;
            float[] fArr = new float[1];
            fArr[0] = this.isAlwaysShowBubble ? 1.0f : 0.0f;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(bubbleView, (Property<BubbleView, Float>) property, fArr);
            if (z) {
                animatorSet.setDuration(this.mAnimDuration).play(ofFloat);
            } else {
                animatorSet.setDuration(this.mAnimDuration).playTogether(valueAnimator, ofFloat);
            }
        } else if (!z) {
            animatorSet.setDuration(this.mAnimDuration).playTogether(valueAnimator);
        }
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!BubbleSeekBar.this.isHideBubble && !BubbleSeekBar.this.isAlwaysShowBubble) {
                    BubbleSeekBar.this.hideBubble();
                }
                BubbleSeekBar bubbleSeekBar = BubbleSeekBar.this;
                bubbleSeekBar.mProgress = bubbleSeekBar.calculateProgress();
                BubbleSeekBar.this.isThumbOnDragging = false;
                BubbleSeekBar.this.isTouchToSeekAnimEnd = true;
                BubbleSeekBar.this.invalidate();
                if (BubbleSeekBar.this.mProgressListener != null) {
                    OnProgressChangedListener onProgressChangedListener = BubbleSeekBar.this.mProgressListener;
                    BubbleSeekBar bubbleSeekBar2 = BubbleSeekBar.this;
                    onProgressChangedListener.getProgressOnFinally(bubbleSeekBar2, bubbleSeekBar2.getProgress(), BubbleSeekBar.this.getProgressFloat(), true);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (!BubbleSeekBar.this.isHideBubble && !BubbleSeekBar.this.isAlwaysShowBubble) {
                    BubbleSeekBar.this.hideBubble();
                }
                BubbleSeekBar bubbleSeekBar = BubbleSeekBar.this;
                bubbleSeekBar.mProgress = bubbleSeekBar.calculateProgress();
                BubbleSeekBar.this.isThumbOnDragging = false;
                BubbleSeekBar.this.isTouchToSeekAnimEnd = true;
                BubbleSeekBar.this.invalidate();
            }
        });
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBubble() {
        BubbleView bubbleView = this.mBubbleView;
        if (bubbleView == null || bubbleView.getParent() != null) {
            return;
        }
        this.mLayoutParams.x = (int) (this.mBubbleCenterRawX + 0.5f);
        this.mLayoutParams.y = (int) (this.mBubbleCenterRawSolidY + 0.5f);
        Log.i(TAG, "mLayoutParams.x: " + this.mLayoutParams.x);
        Log.i(TAG, "mLayoutParams.y: " + this.mLayoutParams.y);
        this.mBubbleView.setAlpha(0.0f);
        this.mBubbleView.setVisibility(0);
        this.mBubbleView.animate().alpha(1.0f).setDuration(this.isTouchToSeek ? 0L : this.mAnimDuration).setListener(new AnimatorListenerAdapter() { // from class: com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.7
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BubbleSeekBar.this.mWindowManager.addView(BubbleSeekBar.this.mBubbleView, BubbleSeekBar.this.mLayoutParams);
            }
        }).start();
        this.mBubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideBubble() {
        BubbleView bubbleView = this.mBubbleView;
        if (bubbleView == null) {
            return;
        }
        bubbleView.setVisibility(8);
        if (this.mBubbleView.getParent() != null) {
            this.mWindowManager.removeViewImmediate(this.mBubbleView);
        }
    }

    private String float2String(float f) {
        return String.valueOf(formatFloat(f));
    }

    private float formatFloat(float f) {
        return BigDecimal.valueOf(f).setScale(1, 4).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float calculateCenterRawXofBubbleView() {
        if (this.isRtl) {
            return this.mBubbleCenterRawSolidX - ((this.mTrackLength * (this.mProgress - this.mMin)) / this.mDelta);
        }
        return this.mBubbleCenterRawSolidX + ((this.mTrackLength * (this.mProgress - this.mMin)) / this.mDelta);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float calculateProgress() {
        float f;
        float f2;
        if (this.isRtl) {
            f = ((this.mRight - this.mThumbCenterX) * this.mDelta) / this.mTrackLength;
            f2 = this.mMin;
        } else {
            f = ((this.mThumbCenterX - this.mLeft) * this.mDelta) / this.mTrackLength;
            f2 = this.mMin;
        }
        return f + f2;
    }

    public void correctOffsetWhenContainerOnScrolling() {
        if (this.isHideBubble) {
            return;
        }
        locatePositionInWindow();
        if (this.mBubbleView.getParent() != null) {
            if (this.isAlwaysShowBubble) {
                this.mLayoutParams.y = (int) (this.mBubbleCenterRawSolidY + 0.5f);
                this.mWindowManager.updateViewLayout(this.mBubbleView, this.mLayoutParams);
                return;
            }
            postInvalidate();
        }
    }

    public float getMin() {
        return this.mMin;
    }

    public float getMax() {
        return this.mMax;
    }

    public void setProgress(float f) {
        this.mProgress = f;
        OnProgressChangedListener onProgressChangedListener = this.mProgressListener;
        if (onProgressChangedListener != null) {
            onProgressChangedListener.onProgressChanged(this, getProgress(), getProgressFloat(), false);
            this.mProgressListener.getProgressOnFinally(this, getProgress(), getProgressFloat(), false);
        }
        if (!this.isHideBubble) {
            this.mBubbleCenterRawX = calculateCenterRawXofBubbleView();
        }
        if (this.isAlwaysShowBubble) {
            hideBubble();
            postDelayed(new Runnable() { // from class: com.kkkcut.e20j.customView.bubbleseekbar.BubbleSeekBar.8
                @Override // java.lang.Runnable
                public void run() {
                    BubbleSeekBar.this.showBubble();
                    BubbleSeekBar.this.triggerBubbleShowing = true;
                }
            }, this.mAlwaysShowBubbleDelay);
        }
        if (this.isSeekBySection) {
            this.triggerSeekBySection = false;
        }
        postInvalidate();
    }

    public int getProgress() {
        return Math.round(processProgress());
    }

    public float getProgressFloat() {
        return formatFloat(processProgress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float processProgress() {
        float f = this.mProgress;
        if (!this.isSeekBySection || !this.triggerSeekBySection) {
            return f;
        }
        float f2 = this.mSectionValue / 2.0f;
        if (this.isTouchToSeek) {
            if (f == this.mMin || f == this.mMax) {
                return f;
            }
            for (int i = 0; i <= this.mSectionCount; i++) {
                float f3 = this.mSectionValue;
                float f4 = i * f3;
                if (f4 < f && f4 + f3 >= f) {
                    return f2 + f4 > f ? f4 : f4 + f3;
                }
            }
        }
        float f5 = this.mPreSecValue;
        if (f >= f5) {
            if (f < f2 + f5) {
                return f5;
            }
            float f6 = f5 + this.mSectionValue;
            this.mPreSecValue = f6;
            return f6;
        }
        if (f >= f5 - f2) {
            return f5;
        }
        float f7 = f5 - this.mSectionValue;
        this.mPreSecValue = f7;
        return f7;
    }

    public OnProgressChangedListener getOnProgressChangedListener() {
        return this.mProgressListener;
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.mProgressListener = onProgressChangedListener;
    }

    public void setTrackColor(int i) {
        if (this.mTrackColor != i) {
            this.mTrackColor = i;
            invalidate();
        }
    }

    public void setSecondTrackColor(int i) {
        if (this.mSecondTrackColor != i) {
            this.mSecondTrackColor = i;
            invalidate();
        }
    }

    public void setThumbColor(int i) {
        if (this.mThumbColor != i) {
            this.mThumbColor = i;
            invalidate();
        }
    }

    public void setBubbleColor(int i) {
        if (this.mBubbleColor != i) {
            this.mBubbleColor = i;
            BubbleView bubbleView = this.mBubbleView;
            if (bubbleView != null) {
                bubbleView.invalidate();
            }
        }
    }

    public void setCustomSectionTextArray(CustomSectionTextArray customSectionTextArray) {
        this.mSectionTextArray = customSectionTextArray.onCustomize(this.mSectionCount, this.mSectionTextArray);
        for (int i = 0; i <= this.mSectionCount; i++) {
            if (this.mSectionTextArray.get(i) == null) {
                this.mSectionTextArray.put(i, "");
            }
        }
        this.isShowThumbText = false;
        requestLayout();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void config(BubbleConfigBuilder bubbleConfigBuilder) {
        this.mMin = bubbleConfigBuilder.min;
        this.mMax = bubbleConfigBuilder.max;
        this.mProgress = bubbleConfigBuilder.progress;
        this.isFloatType = bubbleConfigBuilder.floatType;
        this.mTrackSize = bubbleConfigBuilder.trackSize;
        this.mSecondTrackSize = bubbleConfigBuilder.secondTrackSize;
        this.mThumbRadius = bubbleConfigBuilder.thumbRadius;
        this.mThumbRadiusOnDragging = bubbleConfigBuilder.thumbRadiusOnDragging;
        this.mTrackColor = bubbleConfigBuilder.trackColor;
        this.mSecondTrackColor = bubbleConfigBuilder.secondTrackColor;
        this.mThumbColor = bubbleConfigBuilder.thumbColor;
        this.mSectionCount = bubbleConfigBuilder.sectionCount;
        this.isShowSectionMark = bubbleConfigBuilder.showSectionMark;
        this.isAutoAdjustSectionMark = bubbleConfigBuilder.autoAdjustSectionMark;
        this.isShowSectionText = bubbleConfigBuilder.showSectionText;
        this.mSectionTextSize = bubbleConfigBuilder.sectionTextSize;
        this.mSectionTextColor = bubbleConfigBuilder.sectionTextColor;
        this.mSectionTextPosition = bubbleConfigBuilder.sectionTextPosition;
        this.mSectionTextInterval = bubbleConfigBuilder.sectionTextInterval;
        this.isShowThumbText = bubbleConfigBuilder.showThumbText;
        this.mThumbTextSize = bubbleConfigBuilder.thumbTextSize;
        this.mThumbTextColor = bubbleConfigBuilder.thumbTextColor;
        this.isShowProgressInFloat = bubbleConfigBuilder.showProgressInFloat;
        this.mAnimDuration = bubbleConfigBuilder.animDuration;
        this.isTouchToSeek = bubbleConfigBuilder.touchToSeek;
        this.isSeekStepSection = bubbleConfigBuilder.seekStepSection;
        this.isSeekBySection = bubbleConfigBuilder.seekBySection;
        this.mBubbleColor = bubbleConfigBuilder.bubbleColor;
        this.mBubbleTextSize = bubbleConfigBuilder.bubbleTextSize;
        this.mBubbleTextColor = bubbleConfigBuilder.bubbleTextColor;
        this.isAlwaysShowBubble = bubbleConfigBuilder.alwaysShowBubble;
        this.mAlwaysShowBubbleDelay = bubbleConfigBuilder.alwaysShowBubbleDelay;
        this.isHideBubble = bubbleConfigBuilder.hideBubble;
        this.isRtl = bubbleConfigBuilder.rtl;
        initConfigByPriority();
        calculateRadiusOfBubble();
        OnProgressChangedListener onProgressChangedListener = this.mProgressListener;
        if (onProgressChangedListener != null) {
            onProgressChangedListener.onProgressChanged(this, getProgress(), getProgressFloat(), false);
            this.mProgressListener.getProgressOnFinally(this, getProgress(), getProgressFloat(), false);
        }
        this.mConfigBuilder = null;
        requestLayout();
    }

    public BubbleConfigBuilder getConfigBuilder() {
        if (this.mConfigBuilder == null) {
            this.mConfigBuilder = new BubbleConfigBuilder(this);
        }
        this.mConfigBuilder.min = this.mMin;
        this.mConfigBuilder.max = this.mMax;
        this.mConfigBuilder.progress = this.mProgress;
        this.mConfigBuilder.floatType = this.isFloatType;
        this.mConfigBuilder.trackSize = this.mTrackSize;
        this.mConfigBuilder.secondTrackSize = this.mSecondTrackSize;
        this.mConfigBuilder.thumbRadius = this.mThumbRadius;
        this.mConfigBuilder.thumbRadiusOnDragging = this.mThumbRadiusOnDragging;
        this.mConfigBuilder.trackColor = this.mTrackColor;
        this.mConfigBuilder.secondTrackColor = this.mSecondTrackColor;
        this.mConfigBuilder.thumbColor = this.mThumbColor;
        this.mConfigBuilder.sectionCount = this.mSectionCount;
        this.mConfigBuilder.showSectionMark = this.isShowSectionMark;
        this.mConfigBuilder.autoAdjustSectionMark = this.isAutoAdjustSectionMark;
        this.mConfigBuilder.showSectionText = this.isShowSectionText;
        this.mConfigBuilder.sectionTextSize = this.mSectionTextSize;
        this.mConfigBuilder.sectionTextColor = this.mSectionTextColor;
        this.mConfigBuilder.sectionTextPosition = this.mSectionTextPosition;
        this.mConfigBuilder.sectionTextInterval = this.mSectionTextInterval;
        this.mConfigBuilder.showThumbText = this.isShowThumbText;
        this.mConfigBuilder.thumbTextSize = this.mThumbTextSize;
        this.mConfigBuilder.thumbTextColor = this.mThumbTextColor;
        this.mConfigBuilder.showProgressInFloat = this.isShowProgressInFloat;
        this.mConfigBuilder.animDuration = this.mAnimDuration;
        this.mConfigBuilder.touchToSeek = this.isTouchToSeek;
        this.mConfigBuilder.seekStepSection = this.isSeekStepSection;
        this.mConfigBuilder.seekBySection = this.isSeekBySection;
        this.mConfigBuilder.bubbleColor = this.mBubbleColor;
        this.mConfigBuilder.bubbleTextSize = this.mBubbleTextSize;
        this.mConfigBuilder.bubbleTextColor = this.mBubbleTextColor;
        this.mConfigBuilder.alwaysShowBubble = this.isAlwaysShowBubble;
        this.mConfigBuilder.alwaysShowBubbleDelay = this.mAlwaysShowBubbleDelay;
        this.mConfigBuilder.hideBubble = this.isHideBubble;
        this.mConfigBuilder.rtl = this.isRtl;
        return this.mConfigBuilder;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("save_instance", super.onSaveInstanceState());
        bundle.putFloat(NotificationCompat.CATEGORY_PROGRESS, this.mProgress);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mProgress = bundle.getFloat(NotificationCompat.CATEGORY_PROGRESS);
            super.onRestoreInstanceState(bundle.getParcelable("save_instance"));
            BubbleView bubbleView = this.mBubbleView;
            if (bubbleView != null) {
                bubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
            }
            setProgress(this.mProgress);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BubbleView extends View {
        private Paint mBubblePaint;
        private Path mBubblePath;
        private RectF mBubbleRectF;
        private String mProgressText;
        private Rect mRect;

        BubbleView(BubbleSeekBar bubbleSeekBar, Context context) {
            this(bubbleSeekBar, context, null);
        }

        BubbleView(BubbleSeekBar bubbleSeekBar, Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        BubbleView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.mProgressText = "";
            Paint paint = new Paint();
            this.mBubblePaint = paint;
            paint.setAntiAlias(true);
            this.mBubblePaint.setTextAlign(Paint.Align.CENTER);
            this.mBubblePath = new Path();
            this.mBubbleRectF = new RectF();
            this.mRect = new Rect();
        }

        @Override // android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            setMeasuredDimension(BubbleSeekBar.this.mBubbleRadius * 3, BubbleSeekBar.this.mBubbleRadius * 3);
            this.mBubbleRectF.set((getMeasuredWidth() / 2.0f) - BubbleSeekBar.this.mBubbleRadius, 0.0f, (getMeasuredWidth() / 2.0f) + BubbleSeekBar.this.mBubbleRadius, BubbleSeekBar.this.mBubbleRadius * 2);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.mBubblePath.reset();
            float measuredWidth = getMeasuredWidth() / 2.0f;
            float measuredHeight = getMeasuredHeight() - (BubbleSeekBar.this.mBubbleRadius / 3.0f);
            this.mBubblePath.moveTo(measuredWidth, measuredHeight);
            float measuredWidth2 = (float) ((getMeasuredWidth() / 2.0f) - ((Math.sqrt(3.0d) / 2.0d) * BubbleSeekBar.this.mBubbleRadius));
            float f = BubbleSeekBar.this.mBubbleRadius * 1.5f;
            this.mBubblePath.quadTo(measuredWidth2 - BubbleUtils.dp2px(2), f - BubbleUtils.dp2px(2), measuredWidth2, f);
            this.mBubblePath.arcTo(this.mBubbleRectF, 150.0f, 240.0f);
            this.mBubblePath.quadTo(((float) ((getMeasuredWidth() / 2.0f) + ((Math.sqrt(3.0d) / 2.0d) * BubbleSeekBar.this.mBubbleRadius))) + BubbleUtils.dp2px(2), f - BubbleUtils.dp2px(2), measuredWidth, measuredHeight);
            this.mBubblePath.close();
            this.mBubblePaint.setColor(BubbleSeekBar.this.mBubbleColor);
            canvas.drawPath(this.mBubblePath, this.mBubblePaint);
            this.mBubblePaint.setTextSize(BubbleSeekBar.this.mBubbleTextSize);
            this.mBubblePaint.setColor(BubbleSeekBar.this.mBubbleTextColor);
            Paint paint = this.mBubblePaint;
            String str = this.mProgressText;
            paint.getTextBounds(str, 0, str.length(), this.mRect);
            Paint.FontMetrics fontMetrics = this.mBubblePaint.getFontMetrics();
            canvas.drawText(this.mProgressText, getMeasuredWidth() / 2.0f, (BubbleSeekBar.this.mBubbleRadius + ((fontMetrics.descent - fontMetrics.ascent) / 2.0f)) - fontMetrics.descent, this.mBubblePaint);
        }

        void setProgressText(String str) {
            if (str == null || this.mProgressText.equals(str)) {
                return;
            }
            this.mProgressText = str;
            invalidate();
        }
    }
}
