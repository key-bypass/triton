package com.kkkcut.e20j.customView.indexlib.IndexBar.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.kkkcut.e20j.customView.indexlib.IndexBar.helper.IIndexBarDataHelper;
import com.kkkcut.e20j.customView.indexlib.IndexBar.helper.IndexBarDataHelperImpl;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class IndexBar extends View {
    public static String[] INDEX_STRING = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private static final String TAG = "zxt/IndexBar";
    private boolean isNeedRealIndex;
    private boolean isSourceDatasAlreadySorted;
    private IIndexBarDataHelper mDataHelper;
    private int mGapHeight;
    private int mHeaderViewCount;
    private int mHeight;
    private List<String> mIndexDatas;
    private LinearLayoutManager mLayoutManager;
    private onIndexPressedListener mOnIndexPressedListener;
    private Paint mPaint;
    private int mPressedBackground;
    private TextView mPressedShowTextView;
    private List<? extends BaseIndexPinyinBean> mSourceDatas;
    private int mWidth;

    /* loaded from: classes.dex */
    public interface onIndexPressedListener {
        void onIndexPressed(int i, String str);

        void onMotionEventEnd();
    }

    private void sortData() {
    }

    public IndexBar(Context context) {
        this(context, null);
    }

    public IndexBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IndexBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHeaderViewCount = 0;
        init(context, attributeSet, i);
    }

    public int getHeaderViewCount() {
        return this.mHeaderViewCount;
    }

    public IndexBar setHeaderViewCount(int i) {
        this.mHeaderViewCount = i;
        return this;
    }

    public boolean isSourceDatasAlreadySorted() {
        return this.isSourceDatasAlreadySorted;
    }

    public IndexBar setSourceDatasAlreadySorted(boolean z) {
        this.isSourceDatasAlreadySorted = z;
        return this;
    }

    public IIndexBarDataHelper getDataHelper() {
        return this.mDataHelper;
    }

    public IndexBar setDataHelper(IIndexBarDataHelper iIndexBarDataHelper) {
        this.mDataHelper = iIndexBarDataHelper;
        return this;
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        int applyDimension = (int) TypedValue.applyDimension(2, 16.0f, getResources().getDisplayMetrics());
        this.mPressedBackground = ViewCompat.MEASURED_STATE_MASK;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.IndexBar, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == 1) {
                applyDimension = obtainStyledAttributes.getDimensionPixelSize(index, applyDimension);
            } else if (index == 0) {
                this.mPressedBackground = obtainStyledAttributes.getColor(index, this.mPressedBackground);
            }
        }
        obtainStyledAttributes.recycle();
        initIndexDatas();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setTextSize(applyDimension);
        this.mPaint.setColor(ThemeUtils.getColor(context, com.kkkcut.e20j.us.R.attr.textColor_ffffff_333333));
        setmOnIndexPressedListener(new onIndexPressedListener() { // from class: com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar.1
            @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar.onIndexPressedListener
            public void onIndexPressed(int i3, String str) {
                int posByTag;
                if (IndexBar.this.mPressedShowTextView != null) {
                    IndexBar.this.mPressedShowTextView.setVisibility(0);
                    IndexBar.this.mPressedShowTextView.setText(str);
                }
                if (IndexBar.this.mLayoutManager == null || (posByTag = IndexBar.this.getPosByTag(str)) == -1) {
                    return;
                }
                IndexBar.this.mLayoutManager.scrollToPositionWithOffset(posByTag, 0);
            }

            @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar.onIndexPressedListener
            public void onMotionEventEnd() {
                if (IndexBar.this.mPressedShowTextView != null) {
                    IndexBar.this.mPressedShowTextView.setVisibility(8);
                }
            }
        });
        this.mDataHelper = new IndexBarDataHelperImpl();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        Rect rect = new Rect();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.mIndexDatas.size(); i5++) {
            String str = this.mIndexDatas.get(i5);
            this.mPaint.getTextBounds(str, 0, str.length(), rect);
            i4 = Math.max(rect.width(), i4);
            i3 = Math.max(rect.height(), i3);
        }
        int size3 = i3 * this.mIndexDatas.size();
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(i4, size);
        } else if (mode != 1073741824) {
            size = i4;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size3, size2);
        } else if (mode2 != 1073741824) {
            size2 = size3;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int paddingTop = getPaddingTop();
        for (int i = 0; i < this.mIndexDatas.size(); i++) {
            String str = this.mIndexDatas.get(i);
            Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
            canvas.drawText(str, (this.mWidth / 2) - (this.mPaint.measureText(str) / 2.0f), (this.mGapHeight * i) + paddingTop + ((int) (((this.mGapHeight - fontMetrics.bottom) - fontMetrics.top) / 2.0f)), this.mPaint);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            setBackgroundColor(this.mPressedBackground);
        } else if (action != 2) {
            setBackgroundResource(android.R.color.transparent);
            onIndexPressedListener onindexpressedlistener = this.mOnIndexPressedListener;
            if (onindexpressedlistener != null) {
                onindexpressedlistener.onMotionEventEnd();
            }
            return true;
        }
        int y = (int) ((motionEvent.getY() - getPaddingTop()) / this.mGapHeight);
        if (y < 0) {
            y = 0;
        } else if (y >= this.mIndexDatas.size()) {
            y = this.mIndexDatas.size() - 1;
        }
        if (this.mOnIndexPressedListener != null && y > -1 && y < this.mIndexDatas.size()) {
            this.mOnIndexPressedListener.onIndexPressed(y, this.mIndexDatas.get(y));
        }
        return true;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
        List<String> list = this.mIndexDatas;
        if (list == null || list.isEmpty()) {
            return;
        }
        computeGapHeight();
    }

    public onIndexPressedListener getmOnIndexPressedListener() {
        return this.mOnIndexPressedListener;
    }

    public void setmOnIndexPressedListener(onIndexPressedListener onindexpressedlistener) {
        this.mOnIndexPressedListener = onindexpressedlistener;
    }

    public IndexBar setmPressedShowTextView(TextView textView) {
        this.mPressedShowTextView = textView;
        return this;
    }

    public IndexBar setmLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
        return this;
    }

    public IndexBar setNeedRealIndex(boolean z) {
        this.isNeedRealIndex = z;
        initIndexDatas();
        return this;
    }

    private void initIndexDatas() {
        if (this.isNeedRealIndex) {
            this.mIndexDatas = new ArrayList();
        } else {
            this.mIndexDatas = Arrays.asList(INDEX_STRING);
        }
    }

    public IndexBar setmSourceDatas(List<? extends BaseIndexPinyinBean> list) {
        this.mSourceDatas = list;
        initSourceDatas();
        return this;
    }

    private void initSourceDatas() {
        List<? extends BaseIndexPinyinBean> list = this.mSourceDatas;
        if (list == null || list.isEmpty()) {
            return;
        }
        if (!this.isSourceDatasAlreadySorted) {
            this.mDataHelper.sortSourceDatas(this.mSourceDatas);
        } else {
            this.mDataHelper.convert(this.mSourceDatas);
            this.mDataHelper.fillInexTag(this.mSourceDatas);
        }
        if (this.isNeedRealIndex) {
            this.mDataHelper.getSortedIndexDatas(this.mSourceDatas, this.mIndexDatas);
            computeGapHeight();
        }
    }

    private void computeGapHeight() {
        this.mGapHeight = ((this.mHeight - getPaddingTop()) - getPaddingBottom()) / this.mIndexDatas.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPosByTag(String str) {
        List<? extends BaseIndexPinyinBean> list = this.mSourceDatas;
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
            return -1;
        }
        for (int i = 0; i < this.mSourceDatas.size(); i++) {
            if (str.equals(this.mSourceDatas.get(i).getBaseIndexTag())) {
                return i + getHeaderViewCount();
            }
        }
        return -1;
    }
}
