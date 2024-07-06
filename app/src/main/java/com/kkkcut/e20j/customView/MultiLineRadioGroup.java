package com.kkkcut.e20j.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MultiLineRadioGroup extends ViewGroup implements View.OnClickListener {
    public final int CENTER;
    public final int LEFT;
    public final int RIGHT;
    private int childCount;
    private int childMarginHorizontal;
    private int childMarginVertical;
    private int childResId;
    private List<String> childValues;
    private int childValuesId;
    private boolean forceLayout;
    private int gravity;
    private OnCheckedChangedListener listener;
    private int mLastCheckedPosition;
    private int mX;
    private int mY;
    private int rowNumber;
    private boolean singleChoice;
    private List<CheckBox> viewList;

    /* loaded from: classes.dex */
    public interface OnCheckedChangedListener {
        void onItemChecked(MultiLineRadioGroup multiLineRadioGroup, int i, boolean z);
    }

    public MultiLineRadioGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.LEFT = 1;
        this.CENTER = 0;
        this.RIGHT = 2;
        this.childMarginHorizontal = 0;
        this.childMarginVertical = 0;
        this.childResId = 0;
        this.childCount = 0;
        this.childValuesId = 0;
        this.singleChoice = false;
        this.mLastCheckedPosition = -1;
        this.rowNumber = 0;
        this.gravity = 1;
        this.viewList = new ArrayList();
        this.childValues = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MultiLineRadioGroup);
        this.childMarginHorizontal = obtainStyledAttributes.getDimensionPixelSize(2, 15);
        this.childMarginVertical = obtainStyledAttributes.getDimensionPixelSize(3, 5);
        this.childResId = obtainStyledAttributes.getResourceId(1, 0);
        this.childCount = obtainStyledAttributes.getInt(0, 0);
        this.singleChoice = obtainStyledAttributes.getBoolean(6, true);
        this.childValuesId = obtainStyledAttributes.getResourceId(4, 0);
        this.gravity = obtainStyledAttributes.getInt(5, 1);
        if (this.childResId == 0) {
            throw new RuntimeException("The attr 'child_layout' must be specified!");
        }
        if (this.childValuesId != 0) {
            for (String str : getResources().getStringArray(this.childValuesId)) {
                this.childValues.add(str);
            }
        }
        if (this.childCount > 0) {
            boolean z = this.childValues != null;
            for (int i2 = 0; i2 < this.childCount; i2++) {
                CheckBox child = getChild();
                this.viewList.add(child);
                addView(child);
                if (z && i2 < this.childValues.size()) {
                    child.setText(this.childValues.get(i2));
                } else {
                    this.childValues.add(child.getText().toString());
                }
                child.setTag(Integer.valueOf(i2));
                child.setOnClickListener(this);
            }
        } else {
            Log.d("tag", "childCount is 0");
        }
        obtainStyledAttributes.recycle();
    }

    public MultiLineRadioGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiLineRadioGroup(Context context) {
        this(context, null, 0);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        this.childCount = childCount;
        int i4 = 0;
        if (childCount > 0) {
            int i5 = 0;
            int i6 = 0;
            i3 = 0;
            int i7 = 0;
            while (i5 < this.childCount) {
                View childAt = getChildAt(i5);
                measureChild(childAt, i, i2);
                if (childAt.getMeasuredWidth() + (this.childMarginHorizontal * 2) + i7 + getPaddingLeft() + getPaddingRight() > getMeasuredWidth()) {
                    i6++;
                    i7 = 0;
                }
                int measuredHeight = childAt.getMeasuredHeight();
                i7 += childAt.getMeasuredWidth() + (this.childMarginHorizontal * 2);
                i5++;
                i3 = measuredHeight;
            }
            this.rowNumber = i6;
            i4 = i6;
        } else {
            i3 = 0;
        }
        int i8 = this.childMarginVertical;
        setMeasuredDimension(getMeasuredWidth(), ((i4 + 1) * (i3 + i8)) + i8 + getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!z && !this.forceLayout) {
            Log.d("tag", "onLayout:unChanged");
            return;
        }
        int childCount = getChildCount();
        this.childCount = childCount;
        int[] iArr = new int[this.rowNumber + 1];
        if (childCount > 0) {
            if (this.gravity != 1) {
                for (int i5 = 0; i5 < this.childCount; i5++) {
                    View childAt = getChildAt(i5);
                    if (childAt.getMeasuredWidth() + (this.childMarginHorizontal * 2) + this.mX + getPaddingLeft() + getPaddingRight() > getWidth()) {
                        if (this.gravity == 0) {
                            iArr[this.mY] = (getWidth() - this.mX) / 2;
                        } else {
                            iArr[this.mY] = getWidth() - this.mX;
                        }
                        this.mY++;
                        this.mX = 0;
                    }
                    this.mX += childAt.getMeasuredWidth() + (this.childMarginHorizontal * 2);
                    if (i5 == this.childCount - 1) {
                        if (this.gravity == 0) {
                            iArr[this.mY] = (getWidth() - this.mX) / 2;
                        } else {
                            iArr[this.mY] = getWidth() - this.mX;
                        }
                    }
                }
                this.mY = 0;
                this.mX = 0;
            }
            for (int i6 = 0; i6 < this.childCount; i6++) {
                View childAt2 = getChildAt(i6);
                if (childAt2.getMeasuredWidth() + (this.childMarginHorizontal * 2) + this.mX + getPaddingLeft() + getPaddingRight() > getWidth()) {
                    this.mY++;
                    this.mX = 0;
                }
                int paddingLeft = this.mX + this.childMarginHorizontal + getPaddingLeft();
                int i7 = this.mY;
                int i8 = paddingLeft + iArr[i7];
                int measuredHeight = (i7 * childAt2.getMeasuredHeight()) + ((this.mY + 1) * this.childMarginVertical);
                childAt2.layout(i8, measuredHeight, childAt2.getMeasuredWidth() + i8, childAt2.getMeasuredHeight() + measuredHeight);
                this.mX += childAt2.getMeasuredWidth() + (this.childMarginHorizontal * 2);
            }
        }
        this.mY = 0;
        this.mX = 0;
        this.forceLayout = false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (this.singleChoice) {
                int intValue = ((Integer) view.getTag()).intValue();
                boolean isChecked = ((CheckBox) view).isChecked();
                int i = this.mLastCheckedPosition;
                if (i != -1 && i != intValue) {
                    this.viewList.get(i).setChecked(false);
                }
                if (isChecked) {
                    this.mLastCheckedPosition = intValue;
                } else {
                    this.mLastCheckedPosition = -1;
                }
                OnCheckedChangedListener onCheckedChangedListener = this.listener;
                if (onCheckedChangedListener != null) {
                    onCheckedChangedListener.onItemChecked(this, intValue, isChecked);
                    return;
                }
                return;
            }
            int intValue2 = ((Integer) view.getTag()).intValue();
            CheckBox checkBox = (CheckBox) view;
            OnCheckedChangedListener onCheckedChangedListener2 = this.listener;
            if (onCheckedChangedListener2 != null) {
                onCheckedChangedListener2.onItemChecked(this, intValue2, checkBox.isChecked());
            }
        } catch (Exception unused) {
        }
    }

    public void setOnCheckChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
        this.listener = onCheckedChangedListener;
    }

    public boolean setItemChecked(int i) {
        if (i < 0 || i >= this.viewList.size()) {
            return false;
        }
        if (this.singleChoice) {
            int i2 = this.mLastCheckedPosition;
            if (i == i2) {
                return true;
            }
            if (i2 >= 0 && i2 < this.viewList.size()) {
                this.viewList.get(this.mLastCheckedPosition).setChecked(false);
            }
            this.mLastCheckedPosition = i;
        }
        this.viewList.get(i).setChecked(true);
        return true;
    }

    public void setGravity(int i) {
        this.gravity = i;
        this.forceLayout = true;
        requestLayout();
    }

    public boolean isSingleChoice() {
        return this.singleChoice;
    }

    public void setChoiceMode(boolean z) {
        this.singleChoice = z;
        if (!z || getCheckedValues().size() <= 1) {
            return;
        }
        clearChecked();
    }

    public int[] getCheckedItems() {
        int i;
        if (this.singleChoice && (i = this.mLastCheckedPosition) >= 0 && i < this.viewList.size()) {
            return new int[]{this.mLastCheckedPosition};
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        for (int i2 = 0; i2 < this.viewList.size(); i2++) {
            if (this.viewList.get(i2).isChecked()) {
                sparseIntArray.put(i2, i2);
            }
        }
        if (sparseIntArray.size() == 0) {
            return null;
        }
        int[] iArr = new int[sparseIntArray.size()];
        for (int i3 = 0; i3 < sparseIntArray.size(); i3++) {
            iArr[i3] = sparseIntArray.keyAt(i3);
        }
        return iArr;
    }

    public List<String> getCheckedValues() {
        int i;
        ArrayList arrayList = new ArrayList();
        if (this.singleChoice && (i = this.mLastCheckedPosition) >= 0 && i < this.viewList.size()) {
            arrayList.add(this.viewList.get(this.mLastCheckedPosition).getText().toString());
            return arrayList;
        }
        for (int i2 = 0; i2 < this.viewList.size(); i2++) {
            if (this.viewList.get(i2).isChecked()) {
                arrayList.add(this.viewList.get(i2).getText().toString());
            }
        }
        return arrayList;
    }

    public int append(String str) {
        CheckBox child = getChild();
        child.setText(str);
        child.setTag(Integer.valueOf(this.childCount));
        child.setOnClickListener(this);
        this.viewList.add(child);
        addView(child);
        this.childValues.add(str);
        this.childCount++;
        this.forceLayout = true;
        postInvalidate();
        return this.childCount - 1;
    }

    public void addAll(List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            append(it.next());
        }
    }

    public boolean remove(int i) {
        if (i < 0 || i >= this.viewList.size()) {
            return false;
        }
        CheckBox remove = this.viewList.remove(i);
        removeView(remove);
        this.childValues.remove(remove.getText().toString());
        this.childCount--;
        this.forceLayout = true;
        int i2 = this.mLastCheckedPosition;
        if (i <= i2) {
            if (i2 == i) {
                this.mLastCheckedPosition = -1;
            } else {
                this.mLastCheckedPosition = i2 - 1;
            }
        }
        while (i < this.viewList.size()) {
            this.viewList.get(i).setTag(Integer.valueOf(i));
            i++;
        }
        postInvalidate();
        return true;
    }

    public boolean insert(int i, String str) {
        if (i < 0 || i > this.viewList.size()) {
            return false;
        }
        CheckBox child = getChild();
        child.setText(str);
        child.setTag(Integer.valueOf(i));
        child.setOnClickListener(this);
        this.viewList.add(i, child);
        addView(child, i);
        this.childValues.add(i, str);
        this.childCount++;
        this.forceLayout = true;
        int i2 = this.mLastCheckedPosition;
        if (i <= i2) {
            this.mLastCheckedPosition = i2 + 1;
        }
        while (i < this.viewList.size()) {
            this.viewList.get(i).setTag(Integer.valueOf(i));
            i++;
        }
        postInvalidate();
        return true;
    }

    private CheckBox getChild() {
        View inflate = LayoutInflater.from(getContext()).inflate(this.childResId, (ViewGroup) this, false);
        if (!(inflate instanceof CheckBox)) {
            throw new RuntimeException("The attr child_layout's root must be a CheckBox!");
        }
        return (CheckBox) inflate;
    }

    public void clearChecked() {
        int i;
        if (this.singleChoice && (i = this.mLastCheckedPosition) >= 0 && i < this.viewList.size()) {
            this.viewList.get(this.mLastCheckedPosition).setChecked(false);
            this.mLastCheckedPosition = -1;
            return;
        }
        for (CheckBox checkBox : this.viewList) {
            if (checkBox.isChecked()) {
                checkBox.setChecked(false);
            }
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        List<CheckBox> list = this.viewList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<CheckBox> it = this.viewList.iterator();
        while (it.hasNext()) {
            it.next().setEnabled(z);
        }
    }
}
