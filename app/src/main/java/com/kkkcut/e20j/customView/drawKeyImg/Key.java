package com.kkkcut.e20j.customView.drawKeyImg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.LogUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.OperateType;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.clamp.MachineData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class Key extends View {
    private static final int ANGLE = 100;
    private static final String TAG = "Key";
    private List<List<String>> allDepthNames;
    private List<List<Integer>> allDepths;
    private List<List<Integer>> allSpaceWidths;
    private List<List<Integer>> allSpaces;
    protected DashPathEffect dashPathEffect;
    protected int decimalColorDefault;
    private String[] decimalKeyboard;
    protected int decimalRectColor;
    protected int decimalTextSize;
    protected Flag flag;
    protected int integerColorDefault;
    protected int integerRectColor;
    protected int integerTextSize;
    private boolean isInputModel;
    protected int keyColor;
    private KeyInfo keyinfo;
    private OnKeyboardChangedListener listener;
    protected int marginBig;
    protected int marginSmall;
    protected int markColor;
    private int maxSpace;
    private int minSpace;
    protected int rectHeight;
    protected int rectWidth;
    private boolean showDecimal;
    protected int textColorExistDecimal;
    protected int textColorNoDecimal;
    protected int textColorSelect;
    protected int textRectSelect;
    protected int textSizeNoInput;
    protected int twoRectSpace;

    /* loaded from: classes.dex */
    public interface OnKeyboardChangedListener {
        void keyBoardChanged(List<String> list);
    }

    private int getSpaceLeft(int i, int i2, int i3) {
        return i == 0 ? i2 - i3 : i2 + i3;
    }

    private int getSpaceRight(int i, int i2, int i3) {
        return i == 0 ? i2 + i3 : i2 - i3;
    }

    public abstract void drawKeyView(Canvas canvas);

    abstract List<List<Rect>> getDecimalRectContainer();

    abstract List<List<Rect>> getIntegerRectContainer();

    public abstract float getRatio();

    public abstract int getToothAmount();

    public abstract ArrayList<String[]> getToothCode();

    protected abstract void onDecimalRectClick(int i, int i2);

    protected abstract void onIntegerRectClick(int i, int i2);

    public abstract void setToothAmount(int i);

    public abstract void setToothCode(String str);

    public void setToothCodeDefault() {
    }

    public boolean isShowDecimal() {
        return this.showDecimal;
    }

    public void setShowDecimal(boolean z) {
        this.showDecimal = z;
        if (this.flag.isDicimal()) {
            moveToUp();
        } else {
            invalidate();
        }
    }

    public boolean isInputModel() {
        return this.isInputModel;
    }

    public void setInputModel(boolean z) {
        this.isInputModel = z;
        invalidate();
    }

    public List<List<Integer>> getAllDepths() {
        return this.allDepths;
    }

    public List<List<Integer>> getAllSpaces() {
        return this.allSpaces;
    }

    public List<List<Integer>> getAllSpaceWidths() {
        return this.allSpaceWidths;
    }

    public List<List<String>> getAllDepthNames() {
        return this.allDepthNames;
    }

    public int getMaxSpace() {
        return this.maxSpace;
    }

    public int getMinSpace() {
        return this.minSpace;
    }

    public int getMaxDepth() {
        int i = 0;
        for (List<Integer> list : getAllDepths()) {
            i = Math.max(Math.max(i, list.get(0).intValue()), list.get(list.size() - 1).intValue());
        }
        return i;
    }

    public KeyInfo getKeyinfo() {
        return this.keyinfo;
    }

    public Key(Context context, KeyInfo keyInfo) {
        super(context);
        this.allDepths = new ArrayList();
        this.allSpaces = new ArrayList();
        this.allSpaceWidths = new ArrayList();
        this.allDepthNames = new ArrayList();
        this.minSpace = Integer.MAX_VALUE;
        this.keyColor = Color.parseColor("#ffffff");
        this.markColor = Color.parseColor("#ffffff");
        this.integerColorDefault = Color.parseColor("#ffffff");
        this.textColorSelect = -1;
        this.textRectSelect = Color.parseColor("#ff205f");
        this.integerRectColor = Color.parseColor("#ffffff");
        this.decimalRectColor = Color.parseColor("#f7be5b");
        this.decimalColorDefault = Color.parseColor("#ff205f");
        this.textColorExistDecimal = Color.parseColor("#ff205f");
        this.textColorNoDecimal = Color.parseColor("#ffffff");
        this.marginBig = AutoUtils.getPercentHeightSize(64);
        this.marginSmall = AutoUtils.getPercentHeightSize(24);
        this.rectWidth = AutoUtils.getPercentWidthSize(22);
        this.rectHeight = AutoUtils.getPercentHeightSize(33);
        this.twoRectSpace = AutoUtils.getPercentWidthSize(32);
        this.flag = new Flag();
        this.integerTextSize = AutoUtils.getPercentHeightSize(32);
        this.textSizeNoInput = AutoUtils.getPercentHeightSize(24);
        this.decimalTextSize = AutoUtils.getPercentHeightSize(25);
        this.dashPathEffect = new DashPathEffect(new float[]{6.0f, 3.0f}, 0.0f);
        this.decimalKeyboard = new String[]{".0", ".1", ".2", ".3", ".4", ".5", ".6", ".7", ".8", ".9"};
        initColor(context);
        this.keyinfo = keyInfo;
        initBaseData(keyInfo);
        setLayerType(1, null);
    }

    private void initColor(Context context) {
        this.keyColor = ThemeUtils.getColor(context, R.attr.color_white_blueDark);
        this.markColor = ThemeUtils.getColor(context, R.attr.color_white_blueDark);
        this.integerColorDefault = ThemeUtils.getColor(context, R.attr.color_white_blueDark);
        this.textColorSelect = -1;
        this.textRectSelect = ThemeUtils.getColor(context, R.attr.color_red_blueDark);
        this.integerRectColor = ThemeUtils.getColor(context, R.attr.color_white_blueDark);
        this.decimalRectColor = ThemeUtils.getColor(context, R.attr.color_blueLight_blueLight);
        this.decimalColorDefault = ThemeUtils.getColor(context, R.attr.color_red_txt333333);
        this.textColorExistDecimal = Color.parseColor("#ff205f");
        this.textColorNoDecimal = ThemeUtils.getColor(context, R.attr.color_white_blueDark);
    }

    public ArrayList<String[]> getToothCodeNoDecimal() {
        ArrayList<String[]> toothCode = getToothCode();
        for (int i = 0; i < toothCode.size(); i++) {
            String[] strArr = toothCode.get(i);
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr[i2] = getToothCodeRound(strArr[i2], getAllDepthNames().get(i));
            }
        }
        return toothCode;
    }

    public void initBaseData(KeyInfo keyInfo) {
        for (String str : keyInfo.getDepthStr().split(";")) {
            ArrayList arrayList = new ArrayList();
            for (String str2 : str.split(",")) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str2)));
            }
            this.allDepths.add(arrayList);
        }
        for (String str3 : keyInfo.getSpaceStr().split(";")) {
            ArrayList arrayList2 = new ArrayList();
            for (String str4 : str3.split(",")) {
                arrayList2.add(Integer.valueOf(Integer.parseInt(str4)));
            }
            this.allSpaces.add(arrayList2);
        }
        for (String str5 : keyInfo.getDepthName().split(";")) {
            ArrayList arrayList3 = new ArrayList();
            for (String str6 : str5.split(",")) {
                arrayList3.add(str6);
            }
            this.allDepthNames.add(arrayList3);
        }
        for (String str7 : keyInfo.getSpaceWidthStr().split(";")) {
            ArrayList arrayList4 = new ArrayList();
            for (String str8 : str7.split(",")) {
                arrayList4.add(Integer.valueOf(Integer.parseInt(str8)));
            }
            this.allSpaceWidths.add(arrayList4);
        }
        if (keyInfo.getAlign() == 0) {
            for (List<Integer> list : this.allSpaces) {
                this.maxSpace = Math.max(this.maxSpace, list.get(list.size() - 1).intValue());
                this.minSpace = Math.min(this.minSpace, list.get(0).intValue());
            }
            return;
        }
        for (List<Integer> list2 : this.allSpaces) {
            this.maxSpace = Math.max(this.maxSpace, list2.get(0).intValue());
            this.minSpace = Math.min(this.minSpace, list2.get(list2.size() - 1).intValue());
        }
    }

    public String getToothCodeDec(String str) {
        return str.contains(FileUtil.FILE_EXTENSION_SEPARATOR) ? str.substring(str.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR), str.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR) + 2) : ".0";
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        try {
            drawKeyView(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeSingleTooth(String str) {
        int rowPosition = this.flag.getRowPosition();
        int column = this.flag.getColumn();
        String str2 = "";
        if (str.equals(getContext().getResources().getString(R.string.clear))) {
            for (int i = 0; i < getToothCode().size(); i++) {
                for (int i2 = 0; i2 < getToothCode().get(i).length; i2++) {
                    str2 = getToothCode().get(i).length - 1 == i2 ? str2 + "?;" : str2 + "?,";
                }
            }
            setToothCode(str2);
            goFirst();
            return;
        }
        String str3 = "?";
        if (str.equals("?")) {
            String[] split = getToothCode().get(rowPosition)[column].split("\\.");
            if (split.length > 1) {
                str3 = this.flag.isDicimal() ? split[0] + ".0" : "?." + split[1];
            }
            if (!TextUtils.isEmpty(str3)) {
                for (int i3 = 0; i3 < getToothCode().size(); i3++) {
                    for (int i4 = 0; i4 < getToothCode().get(i3).length; i4++) {
                        if (i3 == rowPosition && i4 == column) {
                            str2 = getToothCode().get(i3).length - 1 == i4 ? str2 + str3 + ";" : str2 + str3 + ",";
                        } else if (getToothCode().get(i3).length - 1 == i4) {
                            str2 = str2 + getToothCode().get(i3)[i4] + ";";
                        } else {
                            str2 = str2 + getToothCode().get(i3)[i4] + ",";
                        }
                    }
                }
            }
            setToothCode(str2);
            moveToLeft();
            return;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] split2 = getToothCode().get(rowPosition)[column].split("\\.");
        if (str.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            str = split2[0] + str;
        }
        if (!TextUtils.isEmpty(str)) {
            for (int i5 = 0; i5 < getToothCode().size(); i5++) {
                for (int i6 = 0; i6 < getToothCode().get(i5).length; i6++) {
                    if (i5 == rowPosition && i6 == column) {
                        str2 = getToothCode().get(i5).length - 1 == i6 ? str2 + str + ";" : str2 + str + ",";
                    } else if (getToothCode().get(i5).length - 1 == i6) {
                        str2 = str2 + getToothCode().get(i5)[i6] + ";";
                    } else {
                        str2 = str2 + getToothCode().get(i5)[i6] + ",";
                    }
                }
            }
        }
        Log.i(TAG, "changeSingleTooth: " + str2);
        setToothCode(str2);
        moveToRight();
    }

    public String changeSingleDepth(int i, int i2, float f, boolean z, String str) {
        String codeByDepth;
        List<List<String>> allDepthNames = getAllDepthNames();
        List<List<Integer>> allDepths = getAllDepths();
        if (!TextUtils.isEmpty(str) && ("1".equals(str) || "11".equals(str))) {
            codeByDepth = getCodeByDepthForDimple(allDepths.get(i), allDepthNames.get(i), f, str);
        } else {
            codeByDepth = getCodeByDepth(allDepths.get(i), allDepthNames.get(i), f, z);
        }
        String str2 = "";
        if (!TextUtils.isEmpty(codeByDepth)) {
            for (int i3 = 0; i3 < getToothCode().size(); i3++) {
                for (int i4 = 0; i4 < getToothCode().get(i3).length; i4++) {
                    if (i3 == i && i4 == i2) {
                        str2 = getToothCode().get(i3).length - 1 == i4 ? str2 + codeByDepth + ";" : str2 + codeByDepth + ",";
                    } else if (getToothCode().get(i3).length - 1 == i4) {
                        str2 = str2 + getToothCode().get(i3)[i4] + ";";
                    } else {
                        str2 = str2 + getToothCode().get(i3)[i4] + ",";
                    }
                }
            }
        }
        setToothCodeAndInvalidate(str2);
        return str2;
    }

    public String getCodeByDepthForDimple(List<Integer> list, List<String> list2, float f, String str) {
        if (list.size() < 2) {
            return list2.get(0);
        }
        float f2 = 1.0f;
        if (list.get(0).intValue() > list.get(1).intValue()) {
            if (f > list.get(0).intValue()) {
                return list2.get(0);
            }
            if (f <= list.get(list.size() - 1).intValue()) {
                return list2.get(list2.size() - 1);
            }
            int i = 0;
            while (i < list.size() - 1) {
                if (f <= list.get(i).intValue()) {
                    int i2 = i + 1;
                    if (f > list.get(i2).intValue()) {
                        float intValue = ((int) ((((list.get(i).intValue() - f) * f2) / (list.get(i).intValue() - list.get(i2).intValue())) * 10.0f)) / 10.0f;
                        if (str.equals("1")) {
                            if (intValue <= 0.2f) {
                                return list2.get(i);
                            }
                            if (intValue <= 0.7f) {
                                return list2.get(i) + ".5";
                            }
                            return list2.get(i2);
                        }
                        if (str.equals("11")) {
                            if (intValue <= 0.4f) {
                                return list2.get(i);
                            }
                            return list2.get(i2);
                        }
                    } else {
                        continue;
                    }
                }
                i++;
                f2 = 1.0f;
            }
        } else {
            if (f < list.get(0).intValue()) {
                return list2.get(0);
            }
            if (f >= list.get(list.size() - 1).intValue()) {
                return list2.get(list2.size() - 1);
            }
            int i3 = 0;
            for (int i4 = 1; i3 < list.size() - i4; i4 = 1) {
                if (f >= list.get(i3).intValue()) {
                    int i5 = i3 + 1;
                    if (f < list.get(i5).intValue()) {
                        float intValue2 = ((int) (((((list.get(i3).intValue() * 1.0f) - f) * 1.0f) / (list.get(i3).intValue() - list.get(i5).intValue())) * 10.0f)) / 10.0f;
                        if (str.equals("1")) {
                            if (intValue2 <= 0.2f) {
                                return list2.get(i3);
                            }
                            if (intValue2 <= 0.7f) {
                                return list2.get(i3) + ".5";
                            }
                            return list2.get(i5);
                        }
                        if (str.equals("11")) {
                            if (intValue2 <= 0.4f) {
                                return list2.get(i3);
                            }
                            return list2.get(i5);
                        }
                        i3++;
                    }
                }
                i3++;
            }
        }
        return list2.get(0);
    }

    public void moveToLeft() {
        int column = this.flag.getColumn();
        int rowPosition = this.flag.getRowPosition();
        if (column != 0) {
            this.flag.setColumn(column - 1);
        } else if (rowPosition == 0) {
            if (this.flag.isDicimal()) {
                this.flag.setColumn(this.allSpaces.get(rowPosition).size() - 1);
                this.flag.setDicimal(false);
                showIntegerKeyboard(this.allDepthNames.get(0));
            }
        } else if (this.flag.isDicimal()) {
            this.flag.setColumn(this.allSpaces.get(rowPosition).size() - 1);
            this.flag.setDicimal(false);
            showIntegerKeyboard(this.allDepthNames.get(rowPosition));
        } else {
            if (this.showDecimal) {
                this.flag.setDicimal(true);
                showDecimalKeyboard();
            } else {
                showIntegerKeyboard(this.allDepthNames.get(rowPosition - 1));
            }
            int i = rowPosition - 1;
            this.flag.setRowPosition(i);
            this.flag.setColumn(this.allSpaces.get(i).size() - 1);
        }
        invalidate();
    }

    public void moveToUp() {
        int rowPosition = this.flag.getRowPosition();
        int column = this.flag.getColumn();
        if (this.flag.isDicimal()) {
            this.flag.setDicimal(false);
            showIntegerKeyboard(this.allDepthNames.get(rowPosition));
        } else {
            int size = rowPosition == 0 ? this.allSpaces.size() - 1 : rowPosition - 1;
            if (this.showDecimal) {
                this.flag.setDicimal(true);
                showDecimalKeyboard();
            } else {
                showIntegerKeyboard(this.allDepthNames.get(size));
            }
            this.flag.setRowPosition(size);
            if (this.allSpaces.get(size).size() - 1 >= column) {
                this.flag.setColumn(column);
            } else {
                this.flag.setColumn(this.allSpaces.get(size).size() - 1);
            }
        }
        invalidate();
    }

    public String getCutOrderDepthStr(OperateType operateType) {
        ArrayList<String[]> toothCode = getToothCode();
        String str = "";
        if (this.keyinfo.getType() == 2) {
            Iterator<Index> it = getSortSpace().iterator();
            while (it.hasNext()) {
                Index next = it.next();
                if (operateType == OperateType.CUT) {
                    Log.i(TAG, "getCutOrderDepthStr: ");
                    str = str + getDepthByCode(getAllDepths().get(next.getRow()), getAllDepthNames().get(next.getRow()), toothCode.get(next.getRow())[next.getColumn()]) + ",";
                } else {
                    str = str + "0,";
                }
            }
            return str.substring(0, str.length() - 1);
        }
        for (int i = 0; i < toothCode.size(); i++) {
            for (int i2 = 0; i2 < toothCode.get(i).length; i2++) {
                if (i2 == toothCode.get(i).length - 1) {
                    str = operateType == OperateType.CUT ? str + getDepthByCode(getAllDepths().get(i), getAllDepthNames().get(i), toothCode.get(i)[i2]) + ";" : str + "0;";
                } else if (operateType == OperateType.CUT) {
                    str = str + getDepthByCode(getAllDepths().get(i), getAllDepthNames().get(i), toothCode.get(i)[i2]) + ",";
                } else {
                    str = str + "0,";
                }
            }
        }
        return str;
    }

    public ArrayList<Index> getSortSpace() {
        List<List<Integer>> allSpaces = getAllSpaces();
        ArrayList<Index> arrayList = new ArrayList<>();
        for (int i = 0; i < allSpaces.size(); i++) {
            List<Integer> list = allSpaces.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                arrayList.add(new Index(list.get(i2).intValue(), i, i2));
            }
        }
        Collections.sort(arrayList, new Comparator<Index>() { // from class: com.kkkcut.e20j.customView.drawKeyImg.Key.1
            @Override // java.util.Comparator
            public int compare(Index index, Index index2) {
                if (Key.this.keyinfo.getAlign() == 0) {
                    return index.getData() - index2.getData();
                }
                return index2.getData() - index.getData();
            }
        });
        return arrayList;
    }

    public String getHu162tSideBDepthStr(OperateType operateType) {
        ArrayList<String[]> toothCode = getToothCode();
        String str = "";
        for (int i = 0; i < toothCode.size(); i++) {
            int length = toothCode.get(i).length;
            for (int i2 = 0; i2 < length; i2++) {
                String str2 = toothCode.get(i)[i2];
                if (length == 8 || (length != 9 ? i2 > 3 : i2 > 2)) {
                    str2 = "1";
                }
                if (i2 == length - 1) {
                    str = operateType == OperateType.CUT ? str + str2 + ";" : str + "0;";
                } else if (operateType == OperateType.CUT) {
                    str = str + str2 + ",";
                } else {
                    str = str + "0,";
                }
            }
        }
        return str;
    }

    public String getHu162tSideCDepthStr(OperateType operateType) {
        ArrayList<String[]> toothCode = getToothCode();
        String str = "";
        for (int i = 0; i < toothCode.size(); i++) {
            int length = toothCode.get(i).length;
            for (int i2 = 0; i2 < length; i2++) {
                String str2 = toothCode.get(i)[i2];
                if (length == 8) {
                    str2 = "1";
                } else if (length != 9 ? i2 > 3 : i2 > 2) {
                    str2 = "2.5";
                }
                if (i2 == length - 1) {
                    str = operateType == OperateType.CUT ? str + str2 + ";" : str + "0;";
                } else if (operateType == OperateType.CUT) {
                    str = str + str2 + ",";
                } else {
                    str = str + "0,";
                }
            }
        }
        return str;
    }

    /* loaded from: classes.dex */
    public static class Index {
        int column;
        int data;
        int row;

        public Index(int i, int i2, int i3) {
            this.data = i;
            this.row = i2;
            this.column = i3;
        }

        public int getData() {
            return this.data;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }

        public String toString() {
            return "Index{data=" + this.data + ", row=" + this.row + ", colum=" + this.column + '}';
        }
    }

    public String getCutOrderDepthStr(OperateType operateType, int i) {
        String str;
        int depthByCode;
        int depthByCode2;
        ArrayList<String[]> toothCode = getToothCode();
        String str2 = "";
        if (this.keyinfo.getType() == 2) {
            Iterator<Index> it = getSortSpace().iterator();
            while (it.hasNext()) {
                Index next = it.next();
                str2 = str2 + getDepthByCode(getAllDepths().get(next.getRow()), getAllDepthNames().get(next.getRow()), toothCode.get(next.getRow())[next.getColumn()]) + ",";
            }
            return str2.substring(0, str2.length() - 1);
        }
        for (int i2 = 0; i2 < toothCode.size(); i2++) {
            for (int i3 = 0; i3 < toothCode.get(i2).length; i3++) {
                if (i3 == toothCode.get(i2).length - 1) {
                    if (operateType == OperateType.CUT) {
                        if (!TextUtils.isEmpty(this.keyinfo.getReadBittingRule()) && "3".equals(this.keyinfo.getReadBittingRule()) && i == 1) {
                            if (i3 < 3 && toothCode.get(i2).length == 9) {
                                float parseFloat = Float.parseFloat(toothCode.get(i2)[i3]);
                                if (parseFloat > 4.0f) {
                                    depthByCode2 = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), "4");
                                } else {
                                    depthByCode2 = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), String.valueOf(5.0d - parseFloat));
                                }
                            } else if (i3 < 4 && toothCode.get(i2).length == 10) {
                                float parseFloat2 = Float.parseFloat(toothCode.get(i2)[i3]);
                                if (parseFloat2 > 4.0f) {
                                    depthByCode2 = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), "4");
                                } else {
                                    depthByCode2 = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), String.valueOf(5.0d - parseFloat2));
                                }
                            } else {
                                depthByCode2 = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), "2.5");
                            }
                        } else {
                            depthByCode2 = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), toothCode.get(i2)[i3]);
                        }
                        str = str2 + depthByCode2 + ";";
                    } else {
                        str = str2 + "0;";
                    }
                } else if (operateType == OperateType.CUT) {
                    if (!TextUtils.isEmpty(this.keyinfo.getReadBittingRule()) && "3".equals(this.keyinfo.getReadBittingRule()) && i == 1) {
                        if (i3 < 3 && toothCode.get(i2).length == 9) {
                            float parseFloat3 = Float.parseFloat(toothCode.get(i2)[i3]);
                            if (parseFloat3 > 4.0f) {
                                depthByCode = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), "4");
                            } else {
                                depthByCode = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), String.valueOf(5.0d - parseFloat3));
                            }
                        } else if (i3 < 4 && toothCode.get(i2).length == 10) {
                            float parseFloat4 = Float.parseFloat(toothCode.get(i2)[i3]);
                            if (parseFloat4 > 4.0f) {
                                depthByCode = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), "4");
                            } else {
                                depthByCode = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), String.valueOf(5.0d - parseFloat4));
                            }
                        } else {
                            depthByCode = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), "2.5");
                        }
                    } else {
                        depthByCode = getDepthByCode(getAllDepths().get(i2), getAllDepthNames().get(i2), toothCode.get(i2)[i3]);
                    }
                    str = str2 + depthByCode + ",";
                } else {
                    str = str2 + "0,";
                }
                str2 = str;
            }
        }
        return str2;
    }

    public String getDoorIgnitionFrontDepthStr(OperateType operateType, boolean z) {
        ArrayList<String[]> toothCode = getToothCode();
        String str = "";
        for (int i = 0; i < toothCode.size(); i++) {
            int length = toothCode.get(i).length;
            for (int i2 = 0; i2 < length; i2++) {
                String str2 = toothCode.get(i)[i2];
                if (z) {
                    if (length == 8) {
                        if (i2 == 1 || i2 == 3 || i2 == 4 || i2 == 6) {
                            str2 = String.valueOf(6 - Integer.parseInt(str2));
                        }
                    } else if (length == 9) {
                        if (i2 == 1 || i2 == 2) {
                            str2 = String.valueOf(5 - Integer.parseInt(str2));
                        } else if (i2 == 3 || i2 == 6 || i2 == 8) {
                            str2 = String.valueOf(6 - Integer.parseInt(str2));
                        }
                    } else if (i2 == 0 || i2 == 1) {
                        str2 = String.valueOf(5 - Integer.parseInt(str2));
                    } else if (i2 == 4 || i2 == 6 || i2 == 9) {
                        str2 = String.valueOf(6 - Integer.parseInt(str2));
                    }
                } else if (length != 9) {
                    if (i2 == 0 || i2 == 1) {
                        str2 = String.valueOf(5 - Integer.parseInt(str2));
                    }
                    if (i2 == 4 || i2 == 6 || i2 == 9) {
                        str2 = String.valueOf(6 - Integer.parseInt(str2));
                    }
                } else if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 6 || i2 == 8) {
                    str2 = String.valueOf(6 - Integer.parseInt(str2));
                }
                if (i2 == length - 1) {
                    str = operateType == OperateType.CUT ? str + str2 + ";" : str + "0;";
                } else if (operateType == OperateType.CUT) {
                    str = str + str2 + ",";
                } else {
                    str = str + "0,";
                }
            }
        }
        Log.i(TAG, "getDoorIgnitionFrontDepthStr: " + str);
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0036, code lost:
    
        if (r5 > 2) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0049, code lost:
    
        if (r5 > 3) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0067, code lost:
    
        if (r5 > 2) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0078, code lost:
    
        if (r5 > 3) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public String getDoorIgnitionSideBDepthStr(OperateType r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.customView.drawKeyImg.Key.getDoorIgnitionSideBDepthStr(com.cutting.machine.OperateType, boolean):java.lang.String");
    }

    public String getDoorIgnitionSideCDepthStr(OperateType operateType, boolean z) {
        String str;
        String valueOf;
        String str2;
        ArrayList<String[]> toothCode = getToothCode();
        String str3 = "";
        for (int i = 0; i < toothCode.size(); i++) {
            int length = toothCode.get(i).length;
            int i2 = 0;
            while (i2 < length) {
                String str4 = toothCode.get(i)[i2];
                String str5 = "4";
                if (z) {
                    if (length == 8) {
                        str5 = "1";
                    } else if (length == 9) {
                        if (i2 == 1 || i2 == 2) {
                            str4 = String.valueOf(5 - Integer.parseInt(str4));
                        } else if (i2 > 2) {
                            str4 = "2.5";
                        }
                        if (i2 < 3) {
                            int parseInt = Integer.parseInt(str4);
                            if (parseInt <= 4.0f) {
                                valueOf = String.valueOf(5.0d - parseInt);
                                str5 = valueOf;
                            }
                        }
                        str5 = str4;
                    } else {
                        if (i2 == 0 || i2 == 1) {
                            str4 = String.valueOf(5 - Integer.parseInt(str4));
                        } else if (i2 > 3) {
                            str4 = "2.5";
                        }
                        if (i2 < 4) {
                            int parseInt2 = Integer.parseInt(str4);
                            if (parseInt2 <= 4.0f) {
                                valueOf = String.valueOf(5.0d - parseInt2);
                                str5 = valueOf;
                            }
                        }
                        str5 = str4;
                    }
                } else if (length == 9) {
                    if (i2 == 1 || i2 == 2) {
                        str4 = String.valueOf(6 - Integer.parseInt(str4));
                    }
                    str = i2 <= 2 ? str4 : "2.5";
                    if (i2 < 3) {
                        int parseInt3 = Integer.parseInt(str);
                        if (parseInt3 <= 4.0f) {
                            valueOf = String.valueOf(5.0d - parseInt3);
                            str5 = valueOf;
                        }
                    }
                    str5 = str;
                } else {
                    if (i2 == 0 || i2 == 1) {
                        str4 = String.valueOf(5 - Integer.parseInt(str4));
                    }
                    str = i2 <= 3 ? str4 : "2.5";
                    if (i2 < 4) {
                        int parseInt4 = Integer.parseInt(str);
                        if (parseInt4 <= 4.0f) {
                            str5 = String.valueOf(5.0d - parseInt4);
                        }
                    }
                    str5 = str;
                }
                Log.i(TAG, "singleTooth: " + str5);
                if (i2 == length - 1) {
                    str2 = operateType == OperateType.CUT ? str3 + str5 + ";" : str3 + "0;";
                } else if (operateType == OperateType.CUT) {
                    str2 = str3 + str5 + ",";
                } else {
                    str2 = str3 + "0,";
                }
                str3 = str2;
                i2++;
            }
        }
        return str3;
    }

    public void setToothCodeAndInvalidate(String str) {
        setToothCode(str);
        invalidate();
    }

    public void moveToRight() {
        int column = this.flag.getColumn();
        int rowPosition = this.flag.getRowPosition();
        if (column == this.allSpaces.get(rowPosition).size() - 1) {
            this.flag.setColumn(0);
            if (this.flag.isDicimal()) {
                if (rowPosition == this.allSpaces.size() - 1) {
                    this.flag.setRowPosition(0);
                    showIntegerKeyboard(this.allDepthNames.get(0));
                } else {
                    int i = rowPosition + 1;
                    this.flag.setRowPosition(i);
                    showIntegerKeyboard(this.allDepthNames.get(i));
                }
                this.flag.setDicimal(false);
            } else if (this.showDecimal) {
                this.flag.setDicimal(true);
                showDecimalKeyboard();
            } else if (rowPosition == this.allSpaces.size() - 1) {
                this.flag.setRowPosition(0);
                showIntegerKeyboard(this.allDepthNames.get(0));
            } else {
                int i2 = rowPosition + 1;
                this.flag.setRowPosition(i2);
                showIntegerKeyboard(this.allDepthNames.get(i2));
            }
        } else {
            this.flag.setColumn(column + 1);
        }
        invalidate();
    }

    public void moveToDown() {
        boolean z;
        int rowPosition = this.flag.getRowPosition();
        int column = this.flag.getColumn();
        if (this.flag.isDicimal() || !(z = this.showDecimal)) {
            int i = rowPosition == this.allSpaces.size() - 1 ? 0 : rowPosition + 1;
            showIntegerKeyboard(this.allDepthNames.get(i));
            this.flag.setRowPosition(i);
            this.flag.setDicimal(false);
            if (this.allSpaces.get(i).size() - 1 >= column) {
                this.flag.setColumn(column);
            } else {
                this.flag.setColumn(this.allSpaces.get(i).size() - 1);
            }
        } else if (z) {
            this.flag.setDicimal(true);
            showDecimalKeyboard();
        }
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        List<List<Rect>> integerRectContainer = getIntegerRectContainer();
        for (int i = 0; i < integerRectContainer.size(); i++) {
            for (int i2 = 0; i2 < integerRectContainer.get(i).size(); i2++) {
                Rect rect = integerRectContainer.get(i).get(i2);
                if (rect.left < x && rect.right > x && rect.top < y && rect.bottom > y) {
                    onIntegerRectClick(i, i2);
                    invalidate();
                    return true;
                }
            }
        }
        List<List<Rect>> decimalRectContainer = getDecimalRectContainer();
        for (int i3 = 0; i3 < decimalRectContainer.size(); i3++) {
            for (int i4 = 0; i4 < decimalRectContainer.get(i3).size(); i4++) {
                Rect rect2 = decimalRectContainer.get(i3).get(i4);
                if (rect2.left < x && rect2.right > x && rect2.top < y && rect2.bottom > y) {
                    onDecimalRectClick(i3, i4);
                    invalidate();
                    return true;
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getToothCodeRound(String str, List<String> list) {
        if (!str.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            return str;
        }
        String str2 = str.split("\\.")[0];
        float parseFloat = Float.parseFloat("0." + str.split("\\.")[1]);
        int indexOf = list.indexOf(str2);
        if (parseFloat < 0.5f) {
            return indexOf == -1 ? "?" : list.get(indexOf);
        }
        if (indexOf == -1) {
            return list.get(0);
        }
        if (indexOf == list.size() - 1) {
            return list.get(list.size() - 1);
        }
        return list.get(indexOf + 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getToothCodeAt(String[] strArr, int i) {
        return i > strArr.length + (-1) ? "?" : strArr[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getToothCodeInt(String str) {
        return str.contains(FileUtil.FILE_EXTENSION_SEPARATOR) ? str.split("\\.")[0] : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getYInGraph(List<Integer> list, List<String> list2, String str) {
        float intValue;
        float ratio;
        if (str.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            String[] split = str.split("\\.");
            String str2 = split[0];
            float parseFloat = Float.parseFloat("0." + split[1]);
            if (!"?".equals(str2)) {
                int i = 0;
                while (true) {
                    if (i < list2.size()) {
                        if (!list2.get(i).equals(str2)) {
                            i++;
                        } else if (i != list2.size() - 1) {
                            intValue = (int) (list.get(i).intValue() + ((list.get(i + 1).intValue() - list.get(i).intValue()) * parseFloat));
                            ratio = getRatio();
                        } else {
                            intValue = list.get(list2.size() - 1).intValue();
                            ratio = getRatio();
                        }
                    } else {
                        intValue = list.get(0).intValue();
                        ratio = getRatio();
                        break;
                    }
                }
            } else {
                var r8 = this.keyinfo.getThick();
                int intValue2 = (int) ((list.get(0).intValue() - r8) + ((list.get(1).intValue() - list.get(0).intValue()) * parseFloat));
                if ((this.keyinfo.getType() == 0 || this.keyinfo.getType() == 1 || this.keyinfo.getType() == 4 || this.keyinfo.getType() == 3 || this.keyinfo.getType() == 9) && intValue2 > this.keyinfo.getWidth()) {
                    intValue2 = this.keyinfo.getWidth();
                }
                intValue = intValue2;
                ratio = getRatio();
            }
        } else if ("?".equals(str)) {
            intValue = list.get(0).intValue() - (list.get(1).intValue() - list.get(0).intValue());
            ratio = getRatio();
        } else {
            int indexOf = list2.indexOf(str);
            if (indexOf == -1) {
                intValue = list.get(0).intValue();
                ratio = getRatio();
            } else {
                intValue = list.get(indexOf).intValue();
                ratio = getRatio();
            }
        }
        return intValue * ratio;
    }

    protected int getDepthByCode(List<Integer> list, List<String> list2, String str) {
        if (list.size() == 0) {
            return 0;
        }
        if (list.size() == 1) {
            return list.get(0).intValue();
        }
        if ("?".equals(str) || "?.0".equals(str)) {
            return donotCut(list);
        }
        if (str.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            String[] split = str.split("\\.");
            String str2 = split[0];
            float parseFloat = Float.parseFloat("0." + split[1]);
            if ("?".equals(str2)) {
                var r8 = this.keyinfo.getThick();
                return (int) ((list.get(0).intValue() - r8) + ((list.get(1).intValue() - list.get(0).intValue()) * parseFloat));
            }
            int indexOf = list2.indexOf(str2);
            if (indexOf >= 0) {
                if (indexOf == list.size() - 1) {
                    return (int) (list.get(indexOf).intValue() + ((list.get(list.size() - 1).intValue() - list.get(list.size() - 2).intValue()) * parseFloat));
                }
                return (int) (list.get(indexOf).intValue() + ((list.get(indexOf + 1).intValue() - list.get(indexOf).intValue()) * parseFloat));
            }
            return donotCut(list);
        }
        int indexOf2 = list2.indexOf(str);
        if (indexOf2 == -1) {
            return donotCut(list);
        }
        return list.get(indexOf2).intValue();
    }

    private int donotCut(List<Integer> list) {
        if (this.keyinfo.getType() == 0 || this.keyinfo.getType() == 1 || this.keyinfo.getType() == 4 || this.keyinfo.getType() == 3 || this.keyinfo.getType() == 9) {
            return this.keyinfo.getWidth();
        }
        if (this.keyinfo.getType() == 5 || this.keyinfo.getType() == 2) {
            return Math.max(list.get(0).intValue(), list.get(list.size() - 1).intValue());
        }
        if (this.keyinfo.getType() == 6) {
            return this.keyinfo.getThick() == 0 ? Math.max(list.get(0).intValue(), list.get(list.size() - 1).intValue()) : this.keyinfo.getThick();
        }
        return 0;
    }

    public String getCodeByDepth(List<Integer> list, List<String> list2, String str, String str2) {
        if (list.size() < 2) {
            return list2.get(0);
        }
        float parseFloat = Float.parseFloat(str);
        int i = 1;
        float f = 1.0f;
        if (list.get(0).intValue() > list.get(1).intValue()) {
            if (parseFloat > list.get(0).intValue()) {
                return list2.get(0);
            }
            if (parseFloat <= list.get(list.size() - 1).intValue()) {
                return list2.get(list2.size() - 1);
            }
            int i2 = 0;
            while (i2 < list.size() - i) {
                if (parseFloat <= list.get(i2).intValue()) {
                    int i3 = i2 + 1;
                    if (parseFloat > list.get(i3).intValue()) {
                        float intValue = ((int) ((((list.get(i2).intValue() - parseFloat) * f) / (list.get(i2).intValue() - list.get(i3).intValue())) * 10.0f)) / 10.0f;
                        if (str2.equals("1")) {
                            if (intValue <= 0.2f) {
                                return list2.get(i2);
                            }
                            if (intValue <= 0.7f) {
                                return list2.get(i2) + ".5";
                            }
                            return list2.get(i3);
                        }
                        if (str2.equals("11")) {
                            if (intValue <= 0.4f) {
                                return list2.get(i2);
                            }
                            return list2.get(i3);
                        }
                    } else {
                        continue;
                    }
                }
                i2++;
                i = 1;
                f = 1.0f;
            }
        } else {
            if (parseFloat < list.get(0).intValue()) {
                return list2.get(0);
            }
            if (parseFloat >= list.get(list.size() - 1).intValue()) {
                return list2.get(list2.size() - 1);
            }
            int i4 = 0;
            for (int i5 = 1; i4 < list.size() - i5; i5 = 1) {
                if (parseFloat >= list.get(i4).intValue()) {
                    int i6 = i4 + 1;
                    if (parseFloat < list.get(i6).intValue()) {
                        float intValue2 = ((int) (((((list.get(i4).intValue() * 1.0f) - parseFloat) * 1.0f) / (list.get(i4).intValue() - list.get(i6).intValue())) * 10.0f)) / 10.0f;
                        if (str2.equals("1")) {
                            if (intValue2 <= 0.2f) {
                                return list2.get(i4);
                            }
                            if (intValue2 <= 0.7f) {
                                return list2.get(i4) + ".5";
                            }
                            return list2.get(i6);
                        }
                        if (str2.equals("11")) {
                            if (intValue2 <= 0.4f) {
                                return list2.get(i4);
                            }
                            return list2.get(i6);
                        }
                        i4++;
                    }
                }
                i4++;
            }
        }
        return list2.get(0);
    }

    public String getCodeByDepth(List<Integer> list, List<String> list2, float f, boolean z) {
        if (list.size() < 2) {
            return list2.get(0);
        }
        if (list.get(0).intValue() > list.get(1).intValue()) {
            if (f > list.get(0).intValue()) {
                if (z) {
                    return list2.get(0);
                }
                float round = Math.round((1.0f - ((f - list.get(0).intValue()) / (list.get(0).intValue() - list.get(1).intValue()))) * 10.0f) / 10.0f;
                if (round > 0.95f) {
                    return list2.get(0);
                }
                if (round < 0.5f) {
                    return "?.5";
                }
                String valueOf = String.valueOf(round);
                return "?" + valueOf.substring(valueOf.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
            }
            if (f <= list.get(list.size() - 1).intValue()) {
                if (z) {
                    return list2.get(list2.size() - 1);
                }
                float round2 = Math.round(((list.get(list.size() - 1).intValue() - f) / (list.get(list.size() - 2).intValue() - list.get(list.size() - 1).intValue())) * 10.0f) / 10.0f;
                if (round2 < 0.5f) {
                    String valueOf2 = String.valueOf(round2);
                    return list2.get(list.size() - 1) + valueOf2.substring(valueOf2.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
                }
                return list2.get(list.size() - 1) + ".4";
            }
            for (int i = 0; i < list.size() - 1; i++) {
                if (f <= list.get(i).intValue()) {
                    int i2 = i + 1;
                    if (f > list.get(i2).intValue()) {
                        float round3 = Math.round(((list.get(i).intValue() - f) / (list.get(i).intValue() - list.get(i2).intValue())) * 10.0f) / 10.0f;
                        if (z) {
                            if (round3 >= 0.5f) {
                                return list2.get(i2);
                            }
                            return list2.get(i);
                        }
                        double d = round3;
                        if (d > 0.95d) {
                            return list2.get(i2);
                        }
                        if (d < 0.05d) {
                            return list2.get(i);
                        }
                        String valueOf3 = String.valueOf(round3);
                        return list2.get(i) + valueOf3.substring(valueOf3.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
                    }
                }
            }
        } else {
            if (f < list.get(0).intValue()) {
                if (z) {
                    return list2.get(0);
                }
                float round4 = Math.round((1.0f - ((f - list.get(0).intValue()) / (list.get(0).intValue() - list.get(1).intValue()))) * 10.0f) / 10.0f;
                if (round4 > 0.95f) {
                    return list2.get(0);
                }
                if (round4 < 0.5f) {
                    return "?.5";
                }
                String valueOf4 = String.valueOf(round4);
                return "?" + valueOf4.substring(valueOf4.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
            }
            if (f >= list.get(list.size() - 1).intValue()) {
                if (z) {
                    return list2.get(list2.size() - 1);
                }
                float round5 = Math.round(((list.get(list.size() - 1).intValue() - f) / (list.get(list.size() - 2).intValue() - list.get(list.size() - 1).intValue())) * 10.0f) / 10.0f;
                if (round5 < 0.5f) {
                    String valueOf5 = String.valueOf(round5);
                    return list2.get(list.size() - 1) + valueOf5.substring(valueOf5.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
                }
                return list2.get(list.size() - 1) + ".4";
            }
            for (int i3 = 0; i3 < list.size() - 1; i3++) {
                if (f >= list.get(i3).intValue()) {
                    int i4 = i3 + 1;
                    if (f < list.get(i4).intValue()) {
                        float intValue = ((list.get(i3).intValue() * 1.0f) - f) / (list.get(i3).intValue() - list.get(i4).intValue());
                        if (z) {
                            if (intValue >= 0.5f) {
                                return list2.get(i4);
                            }
                            return list2.get(i3);
                        }
                        double d2 = intValue;
                        if (d2 > 0.95d) {
                            return list2.get(i4);
                        }
                        if (d2 < 0.05d) {
                            return list2.get(i3);
                        }
                        String valueOf6 = String.valueOf(intValue);
                        return list2.get(i3) + valueOf6.substring(valueOf6.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
                    }
                }
            }
        }
        return list2.get(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getHalfSpaceWidth(Integer num) {
        return (num.intValue() / 2) * getRatio();
    }

    public void goFirst() {
        this.flag.setRowPosition(0);
        this.flag.setColumn(0);
        this.flag.setDicimal(false);
        showIntegerKeyboard(this.allDepthNames.get(0));
        invalidate();
    }

    public void deleteSingle() {
        changeSingleTooth("");
    }

    public void showIntegerKeyboard(List<String> list) {
        OnKeyboardChangedListener onKeyboardChangedListener = this.listener;
        if (onKeyboardChangedListener != null) {
            onKeyboardChangedListener.keyBoardChanged(list);
        }
    }

    public void showDecimalKeyboard() {
        if (this.listener != null) {
            this.listener.keyBoardChanged(new ArrayList(Arrays.asList(this.decimalKeyboard)));
        }
    }

    public void setOnKeyboardChangedListener(OnKeyboardChangedListener onKeyboardChangedListener) {
        this.listener = onKeyboardChangedListener;
    }

    public boolean isInteger(String str) {
        if (!str.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            return !str.equals("?");
        }
        String str2 = str.split("\\.")[1];
        if ("0".equals(str2) && !str.contains("?")) {
            return true;
        }
        double parseFloat = Float.parseFloat("0." + str2);
        return parseFloat >= 0.95d || parseFloat < 0.05d;
    }

    public List<List<DestPoint>> getDestCutPoint(KeyAlignInfo keyAlignInfo, String str, int i) {
        switch (this.keyinfo.getType()) {
            case 0:
                List<List<DestPoint>> doubleSideKeyPoint = getDoubleSideKeyPoint();
                fixSpaceWidth(doubleSideKeyPoint);
                return doubleSideKeyPoint;
            case 1:
                return getSingleSideKeyPoint(keyAlignInfo, str);
            case 2:
                if (this.keyinfo.getGroove() == 0) {
                    return getDoubleInsideGrooveKeyPoint(keyAlignInfo);
                }
                return getPeuceotKeyPoint(keyAlignInfo);
            case 3:
                List<List<DestPoint>> singleOutsideGrooveKeyPoint = getSingleOutsideGrooveKeyPoint(keyAlignInfo, str);
                fixSpaceWidth(singleOutsideGrooveKeyPoint);
                return singleOutsideGrooveKeyPoint;
            case 4:
                List<List<DestPoint>> doubleOutsideGrooveKeyPoint = getDoubleOutsideGrooveKeyPoint(keyAlignInfo);
                fixSpaceWidth(doubleOutsideGrooveKeyPoint);
                return doubleOutsideGrooveKeyPoint;
            case 5:
                return getSingleInsideGrooveKeyPoint(keyAlignInfo, str);
            case 6:
                return getDimpleKeyPoint(i);
            case 7:
            default:
                return null;
            case 8:
                return getTubularKeyPoint();
        }
    }

    private List<List<DestPoint>> getDimpleKeyPoint(int i) {
        ArrayList arrayList = new ArrayList();
        String spaceWidthStr = this.keyinfo.getSpaceWidthStr();
        boolean contains = spaceWidthStr.contains("-");
        String[] split = spaceWidthStr.split(";");
        ArrayList<String[]> toothCode = getToothCode();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < toothCode.size(); i2++) {
            if ((i == 0 || i == 1) && contains) {
                if (split[i2].contains("-")) {
                    String[] split2 = this.keyinfo.getRow_pos().split(";");
                    for (int i3 = 0; i3 < toothCode.get(i2).length; i3++) {
                        arrayList2.add(new DestPoint(this.allSpaces.get(i2).get(i3).intValue(), getDepthByCode(this.allDepths.get(i2), this.allDepthNames.get(i2), toothCode.get(i2)[i3]), Integer.parseInt(split2[i2])));
                    }
                }
            } else if (!split[i2].contains("-")) {
                String[] split3 = this.keyinfo.getRow_pos().split(";");
                for (int i4 = 0; i4 < toothCode.get(i2).length; i4++) {
                    arrayList2.add(new DestPoint(this.allSpaces.get(i2).get(i4).intValue(), getDepthByCode(this.allDepths.get(i2), this.allDepthNames.get(i2), toothCode.get(i2)[i4]), Integer.parseInt(split3[i2])));
                }
            }
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    private List<List<DestPoint>> getSingleSideKeyPoint(KeyAlignInfo keyAlignInfo, String str) {
        int i;
        int i2;
        int cutterRadiusSize2;
        int cutterRadiusSize22;
        int i3;
        ArrayList arrayList;
        int i4;
        double cos;
        int sin;
        int i5;
        double cos2;
        int spaceLeft;
        Key key = this;
        ArrayList arrayList2 = new ArrayList();
        ArrayList<String[]> toothCode = getToothCode();
        ArrayList arrayList3 = new ArrayList();
        int i6 = 0;
        while (i6 < toothCode.size()) {
            int i7 = 0;
            while (i7 < toothCode.get(i6).length) {
                int depthByCode = key.getDepthByCode(key.allDepths.get(i6), key.allDepthNames.get(i6), toothCode.get(i6)[i7]);
                int intValue = key.allSpaces.get(i6).get(i7).intValue();
                int intValue2 = key.allSpaceWidths.get(i6).get(i7).intValue() / 2;
                int align = key.keyinfo.getAlign();
                int spaceLeft2 = key.getSpaceLeft(align, intValue, intValue2);
                int spaceRight = key.getSpaceRight(align, intValue, intValue2);
                if (i7 == 0) {
                    if (key.keyinfo.getIcCard() == 1047) {
                        spaceLeft = key.getSpaceLeft(align, spaceLeft2, 100);
                    } else {
                        spaceLeft = key.getSpaceLeft(align, spaceLeft2, 250);
                    }
                    if (spaceLeft < ToolSizeManager.getCutterRadiusSize() + 10) {
                        spaceLeft = ToolSizeManager.getCutterRadiusSize() + 10;
                    }
                    arrayList3.add(new DestPoint(spaceLeft, getKeyinfo().getWidth(), true));
                }
                if (align == 0) {
                    i = intValue;
                    i2 = intValue2;
                    cutterRadiusSize2 = spaceLeft2 + ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.1d));
                    cutterRadiusSize22 = spaceRight - ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.1d));
                } else {
                    i = intValue;
                    i2 = intValue2;
                    cutterRadiusSize2 = spaceLeft2 - ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.1d));
                    cutterRadiusSize22 = spaceRight + ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.1d));
                }
                if (align == 0 && cutterRadiusSize2 > cutterRadiusSize22) {
                    cutterRadiusSize2 = i;
                    cutterRadiusSize22 = cutterRadiusSize2;
                }
                if (align != 1 || cutterRadiusSize2 >= cutterRadiusSize22) {
                    i3 = cutterRadiusSize2;
                    i = cutterRadiusSize22;
                } else {
                    i3 = i;
                }
                arrayList3.add(new DestPoint(i3, depthByCode, true));
                arrayList3.add(new DestPoint(i, depthByCode, true));
                if (SPUtils.getInt(SpKeys.SINGLEKEY_CUT_SHAPE, 1) == 1 || i7 >= toothCode.get(i6).length - 1) {
                    arrayList = arrayList2;
                    i4 = 1;
                } else {
                    int depthByCode2 = key.getDepthByCode(key.allDepths.get(i6), key.allDepthNames.get(i6), toothCode.get(i6)[i7]);
                    int spaceLeft3 = align == 0 ? key.getSpaceLeft(align, key.allSpaces.get(i6).get(i7 + 1).intValue(), i2) + ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.22d)) : key.getSpaceLeft(align, key.allSpaces.get(i6).get(i7 + 1).intValue(), i2) - ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.22d));
                    int depthByCode3 = key.getDepthByCode(key.allDepths.get(i6), key.allDepthNames.get(i6), toothCode.get(i6)[i7 + 1]);
                    double d = i - spaceLeft3;
                    arrayList = arrayList2;
                    double d2 = depthByCode2 - depthByCode3;
                    double sin2 = (Math.sin(Math.toRadians(40.0d) + Math.abs(Math.atan((d2 * 1.0d) / d))) * Math.sqrt(Math.pow(d, 2.0d) + Math.pow(d2, 2.0d))) / Math.sin(Math.toRadians(100.0d));
                    if (depthByCode2 < depthByCode3) {
                        if (align == 0) {
                            cos2 = i + (Math.cos(Math.toRadians(40.0d)) * sin2);
                        } else {
                            cos2 = i - (Math.cos(Math.toRadians(40.0d)) * sin2);
                        }
                        i5 = (int) cos2;
                        sin = (int) (depthByCode2 + (sin2 * Math.sin(Math.toRadians(40.0d))));
                    } else {
                        if (align == 0) {
                            cos = spaceLeft3 - (Math.cos(Math.toRadians(40.0d)) * sin2);
                        } else {
                            cos = spaceLeft3 + (Math.cos(Math.toRadians(40.0d)) * sin2);
                        }
                        sin = (int) (depthByCode3 + (sin2 * Math.sin(Math.toRadians(40.0d))));
                        i5 = (int) cos;
                    }
                    Log.i(TAG, "jagSpace: " + i5 + "   jagDepth: " + sin);
                    i4 = 1;
                    arrayList3.add(new DestPoint(i5, sin, true));
                }
                if (i7 == toothCode.get(i6).length - i4) {
                    String cutSharpenType = getKeyinfo().getCutSharpenType();
                    if ((!TextUtils.isEmpty(cutSharpenType) ? Integer.parseInt(cutSharpenType.split(",")[0]) : 0) == 0) {
                        key = this;
                        int spaceRight2 = key.getSpaceRight(align, i, 250);
                        if (spaceRight2 < 0) {
                            spaceRight2 = 0;
                        }
                        arrayList3.add(new DestPoint(spaceRight2, getKeyinfo().getWidth(), true));
                        i7++;
                        arrayList2 = arrayList;
                    }
                }
                key = this;
                i7++;
                arrayList2 = arrayList;
            }
            i6++;
            arrayList2 = arrayList2;
        }
        ArrayList arrayList4 = arrayList2;
        arrayList4.add(arrayList3);
        return arrayList4;
    }

    private List<List<DestPoint>> getTubularKeyPoint() {
        ArrayList arrayList = new ArrayList();
        ArrayList<String[]> toothCode = getToothCode();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < toothCode.size(); i++) {
            for (int i2 = 0; i2 < toothCode.get(i).length; i2++) {
                arrayList2.add(new DestPoint(this.allSpaces.get(i).get(i2).intValue(), getDepthByCode(this.allDepths.get(i), this.allDepthNames.get(i), toothCode.get(i)[i2])));
            }
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    private void fixSpaceWidth(List<List<DestPoint>> list) {
        int i;
        int cutterRadiusSize2;
        int cutterRadiusSize22;
        double cos = 0;
        int cutterRadiusSize23 = 0;
        double d;
        for (List<DestPoint> list2 : list) {
            for (int i2 = 1; i2 < list2.size() - 1; i2++) {
                DestPoint destPoint = list2.get(i2 - 1);
                DestPoint destPoint2 = list2.get(i2);
                int depth = destPoint2.getDepth() - destPoint.getDepth();
                int space = destPoint2.getSpace() - destPoint.getSpace();
                if (depth < 0) {
                    if (this.keyinfo.getAlign() == 1) {
                        cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2();
                        d = cutterRadiusSize2 * 0.2d;
                        i = (int) d;
                        Log.i(TAG, "fixSpaceWidth: " + i);
                        destPoint2.setSpace(destPoint2.getSpace() - i);
                    } else {
                        cutterRadiusSize22 = ToolSizeManager.getCutterRadiusSize2();
                        i = -((int) (cutterRadiusSize22 * 0.2d));
                        Log.i(TAG, "fixSpaceWidth: " + i);
                        destPoint2.setSpace(destPoint2.getSpace() - i);
                    }
                } else {
                    if (depth > 0) {
                        cos = Math.cos((3.141592653589793d - Math.atan((depth * 1.0d) / space)) / 2.0d);
                        cutterRadiusSize23 = ToolSizeManager.getCutterRadiusSize2();
                    } else {
                        DestPoint destPoint3 = list2.get(i2 + 1);
                        int depth2 = destPoint3.getDepth() - destPoint2.getDepth();
                        int space2 = destPoint3.getSpace() - destPoint2.getSpace();
                        if (depth2 < 0) {
                            cos = Math.cos((3.141592653589793d - Math.atan((depth2 * 1.0d) / space2)) / 2.0d);
                            cutterRadiusSize23 = ToolSizeManager.getCutterRadiusSize2();
                        } else if (depth2 > 0) {
                            if (this.keyinfo.getAlign() == 1) {
                                cutterRadiusSize22 = ToolSizeManager.getCutterRadiusSize2();
                                i = -((int) (cutterRadiusSize22 * 0.2d));
                                Log.i(TAG, "fixSpaceWidth: " + i);
                                destPoint2.setSpace(destPoint2.getSpace() - i);
                            } else {
                                cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2();
                                d = cutterRadiusSize2 * 0.2d;
                                i = (int) d;
                                Log.i(TAG, "fixSpaceWidth: " + i);
                                destPoint2.setSpace(destPoint2.getSpace() - i);
                            }
                        } else {
                            i = 0;
                            Log.i(TAG, "fixSpaceWidth: " + i);
                            destPoint2.setSpace(destPoint2.getSpace() - i);
                        }
                    }
                    d = ((int) (cos * cutterRadiusSize23)) * 0.85d;
                    i = (int) d;
                    Log.i(TAG, "fixSpaceWidth: " + i);
                    destPoint2.setSpace(destPoint2.getSpace() - i);
                }
            }
        }
        for (List<DestPoint> list3 : list) {
            int i3 = 0;
            while (i3 < list3.size() - 1) {
                DestPoint destPoint4 = list3.get(i3);
                i3++;
                DestPoint destPoint5 = list3.get(i3);
                int space3 = destPoint4.getSpace();
                int space4 = destPoint5.getSpace();
                if (getKeyinfo().getAlign() == 0) {
                    if (space3 > space4) {
                        int i4 = (space3 + space4) / 2;
                        destPoint4.setSpace(i4);
                        destPoint5.setSpace(i4);
                    }
                } else if (space4 > space3) {
                    int i5 = (space3 + space4) / 2;
                    destPoint4.setSpace(i5);
                    destPoint5.setSpace(i5);
                }
                Log.i(TAG, "fixSpaceWidth: " + destPoint4.getSpace());
            }
        }
    }

    public List<List<DestPoint>> getAngleKeyCutPoint(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (String str2 : str.split(",")) {
            int parseInt = Integer.parseInt(str2);
            int intValue = (getAllSpaceWidths().get(0).get(0).intValue() / 2) - ToolSizeManager.getCutterRadiusSize();
            int align = this.keyinfo.getAlign();
            arrayList2.add(new DestPoint(getSpaceLeft(align, parseInt, intValue), 1000 - ToolSizeManager.getCutterRadiusSize(), true));
            arrayList2.add(new DestPoint(getSpaceLeft(align, parseInt, intValue), -ToolSizeManager.getCutterRadiusSize(), true));
            arrayList2.add(new DestPoint(getSpaceRight(align, parseInt, intValue), -ToolSizeManager.getCutterRadiusSize(), true));
            arrayList2.add(new DestPoint(getSpaceRight(align, parseInt, intValue), 1000 - ToolSizeManager.getCutterRadiusSize(), true));
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [int, boolean] */
    private List<List<DestPoint>> getPeuceotKeyPoint(KeyAlignInfo keyAlignInfo) {
        ArrayList arrayList;
        ArrayList<String[]> arrayList2;
        int r3;
        int i;
        ArrayList arrayList3 = new ArrayList();
        ArrayList<String[]> toothCode = getToothCode();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList<Index> sortSpace = getSortSpace();
        int i2 = 0;
        while (i2 < sortSpace.size()) {
            Index index = sortSpace.get(i2);
            int row = index.getRow();
            int column = index.getColumn();
            int data = index.getData();
            int depthByCode = getDepthByCode(this.allDepths.get(row), this.allDepthNames.get(row), toothCode.get(row)[column]);
            int intValue = this.allSpaceWidths.get(row).get(column).intValue() / 2;
            int align = this.keyinfo.getAlign();
            int spaceLeft = getSpaceLeft(align, data, intValue);
            int spaceRight = getSpaceRight(align, data, intValue);
            if (i2 == 0) {
                int innerCutX = getKeyinfo().getInnerCutX() + 50;
                int innerCutY = getKeyinfo().getInnerCutY();
                int spaceLeft2 = getSpaceLeft(align, spaceLeft, innerCutX);
                if (innerCutY == -1 || innerCutY == 0) {
                    innerCutY = depthByCode;
                }
                if (row == 0) {
                    arrayList2 = toothCode;
                    r3 = 1;
                    arrayList4.add(new DestPoint(spaceLeft2, innerCutY, true));
                    arrayList = arrayList3;
                    arrayList5.add(new DestPoint(spaceLeft2, calculateAnotherSideDepth(getKeyinfo(), keyAlignInfo, innerCutY), true));
                } else {
                    arrayList = arrayList3;
                    arrayList2 = toothCode;
                    r3 = 1;
                    arrayList5.add(new DestPoint(spaceLeft2, innerCutY, true));
                    arrayList4.add(new DestPoint(spaceLeft2, calculateAnotherSideDepth(getKeyinfo(), keyAlignInfo, innerCutY), true));
                }
            } else {
                arrayList = arrayList3;
                arrayList2 = toothCode;
                r3 = 1;
            }
            if (row == 0) {
                arrayList4.add(new DestPoint(spaceLeft, depthByCode, r3 == 0));
                arrayList4.add(new DestPoint(spaceRight, depthByCode, r3 == 0));
                arrayList5.add(new DestPoint(spaceLeft, calculateAnotherSideDepth(getKeyinfo(), keyAlignInfo, depthByCode), r3 == 0));
                arrayList5.add(new DestPoint(spaceRight, calculateAnotherSideDepth(getKeyinfo(), keyAlignInfo, depthByCode), r3 == 0));
            } else {
                arrayList5.add(new DestPoint(spaceLeft, depthByCode, r3 == 0));
                arrayList5.add(new DestPoint(spaceRight, depthByCode, r3 == 0));
                arrayList4.add(new DestPoint(spaceLeft, calculateAnotherSideDepth(getKeyinfo(), keyAlignInfo, depthByCode),  r3 == 0));
                arrayList4.add(new DestPoint(spaceRight, calculateAnotherSideDepth(getKeyinfo(), keyAlignInfo, depthByCode),  r3 == 0));
            }
            if (i2 == sortSpace.size() - r3) {
                if (align == 0) {
                    i = (int) (Math.abs(keyAlignInfo.getShoulder() - keyAlignInfo.getTip()) * MachineData.dYScale);
                } else {
                    i = 0;
                }
                int nose = this.keyinfo.getNose();
                int guide = this.keyinfo.getGuide();
                if (nose != 0) {
                    int i3 = nose - 10;
                    arrayList4.add(new DestPoint(getSpaceLeft(align, i, i3), -10, true));
                    arrayList5.add(new DestPoint(getSpaceLeft(align, i, i3), -10, true));
                } else if (guide != 0) {
                    arrayList4.add(new DestPoint(i, guide, true));
                    arrayList5.add(new DestPoint(i, guide, true));
                } else {
                    arrayList4.add(new DestPoint(i, 0, true));
                    arrayList5.add(new DestPoint(i, 0, true));
                    i2++;
                    toothCode = arrayList2;
                    arrayList3 = arrayList;
                }
            }
            i2++;
            toothCode = arrayList2;
            arrayList3 = arrayList;
        }
        arrayList3.add(arrayList4);
        arrayList3.add(arrayList5);
        return arrayList3;
    }

    private List<List<DestPoint>> getDoubleSideKeyPoint() {
        int spaceLeft;
        ArrayList arrayList = new ArrayList();
        ArrayList<String[]> toothCode = getToothCode();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < toothCode.size(); i++) {
            for (int i2 = 0; i2 < toothCode.get(i).length; i2++) {
                int depthByCode = getDepthByCode(this.allDepths.get(i), this.allDepthNames.get(i), toothCode.get(i)[i2]);
                int intValue = this.allSpaces.get(i).get(i2).intValue();
                int intValue2 = this.allSpaceWidths.get(i).get(i2).intValue() / 2;
                int align = this.keyinfo.getAlign();
                int spaceLeft2 = getSpaceLeft(align, intValue, intValue2);
                int spaceRight = getSpaceRight(align, intValue, intValue2);
                if (align == 0 && spaceLeft2 > spaceRight) {
                    spaceRight = intValue;
                    spaceLeft2 = spaceRight;
                }
                if (align != 1 || spaceLeft2 >= spaceRight) {
                    intValue = spaceLeft2;
                } else {
                    spaceRight = intValue;
                }
                if (i2 == 0) {
                    if (this.keyinfo.getIcCard() == 1047) {
                        spaceLeft = getSpaceLeft(align, intValue, 100);
                    } else {
                        spaceLeft = getSpaceLeft(align, intValue, 250);
                    }
                    if (spaceLeft < ToolSizeManager.getCutterRadiusSize() + 10) {
                        spaceLeft = ToolSizeManager.getCutterRadiusSize() + 10;
                    }
                    if (toothCode.size() == 1) {
                        arrayList2.add(new DestPoint(spaceLeft, getKeyinfo().getWidth(), true));
                        arrayList3.add(new DestPoint(spaceLeft, getKeyinfo().getWidth(), true));
                    } else if (i == 0) {
                        arrayList2.add(new DestPoint(spaceLeft, getKeyinfo().getWidth() + ToolSizeManager.getCutterRadiusSize(), true));
                    } else {
                        arrayList3.add(new DestPoint(spaceLeft, getKeyinfo().getWidth(), true));
                    }
                }
                if (toothCode.size() == 1) {
                    arrayList2.add(new DestPoint(intValue, depthByCode, true));
                    arrayList2.add(new DestPoint(spaceRight, depthByCode, true));
                    arrayList3.add(new DestPoint(intValue, depthByCode, true));
                    arrayList3.add(new DestPoint(spaceRight, depthByCode, true));
                } else if (i == 0) {
                    arrayList2.add(new DestPoint(intValue, depthByCode, true));
                    arrayList2.add(new DestPoint(spaceRight, depthByCode, true));
                } else {
                    arrayList3.add(new DestPoint(intValue, depthByCode, true));
                    arrayList3.add(new DestPoint(spaceRight, depthByCode, true));
                }
                if (i2 == toothCode.get(i).length - 1) {
                    String cutSharpenType = getKeyinfo().getCutSharpenType();
                    if ((!TextUtils.isEmpty(cutSharpenType) ? Integer.parseInt(cutSharpenType.split(",")[0]) : 0) == 0) {
                        int spaceRight2 = getSpaceRight(align, spaceRight, 250);
                        if (spaceRight2 < 0) {
                            spaceRight2 = 0;
                        }
                        if (toothCode.size() == 1) {
                            arrayList2.add(new DestPoint(spaceRight2, getKeyinfo().getWidth(), true));
                            arrayList3.add(new DestPoint(spaceRight2, getKeyinfo().getWidth(), true));
                        } else if (i == 0) {
                            arrayList2.add(new DestPoint(spaceRight2, getKeyinfo().getWidth(), true));
                        } else {
                            arrayList3.add(new DestPoint(spaceRight2, getKeyinfo().getWidth(), true));
                        }
                    }
                }
            }
        }
        arrayList.add(arrayList2);
        arrayList.add(arrayList3);
        return arrayList;
    }

    private int fixSpaceWidthLeft(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (this.keyinfo.getType() == 0) {
            i6 = (int) (((((ToolSizeManager.getCutterSize() - 200) * 1.0f) / 100.0f) * 20.0f) + 40.0f);
            i5 = (int) (((((ToolSizeManager.getCutterSize() - 200) * 1.0f) / 100.0f) * 20.0f) + 40.0f);
        } else {
            i5 = 30;
            i6 = 20;
        }
        int i7 = i4 - i3;
        return i7 > 0 ? i == 0 ? i2 + i6 : i2 - i6 : i7 < 0 ? i == 0 ? i2 - i5 : i2 + i5 : i2;
    }

    private int fixSpaceWidthRight(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (this.keyinfo.getType() == 0) {
            i6 = (int) (((((ToolSizeManager.getCutterSize() - 200) * 1.0f) / 100.0f) * 20.0f) + 40.0f);
            i5 = (int) (((((ToolSizeManager.getCutterSize() - 200) * 1.0f) / 100.0f) * 20.0f) + 40.0f);
        } else {
            i5 = 30;
            i6 = 20;
        }
        int i7 = i4 - i3;
        return i7 > 0 ? i == 0 ? i2 - i6 : i2 + i6 : i7 < 0 ? i == 0 ? i2 + i5 : i2 - i5 : i2;
    }

    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r3v37 */
    private List<List<DestPoint>> getSingleOutsideGrooveKeyPoint(KeyAlignInfo keyAlignInfo, String str) {
        ArrayList<String[]> arrayList;
        int i;
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            arrayList = getToothCode();
        } else {
            String[] split = str.split(";")[0].split(",");
            ArrayList<String[]> arrayList3 = new ArrayList<>();
            arrayList3.add(split);
            arrayList = arrayList3;
        }
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        int i2 = 0;
        while (i2 < arrayList.size()) {
            int i3 = 0;
            boolean r3 = z;
            while (i3 < arrayList.get(i2).length) {
                int depthByCode = getDepthByCode(this.allDepths.get(i2), this.allDepthNames.get(i2), arrayList.get(i2)[i3]);
                int intValue = this.allSpaces.get(i2).get(i3).intValue();
                int intValue2 = this.allSpaceWidths.get(i2).get(i3).intValue() / 2;
                int align = this.keyinfo.getAlign();
                int spaceLeft = getSpaceLeft(align, intValue, intValue2);
                int spaceRight = getSpaceRight(align, intValue, intValue2);
                if (align == 0 && spaceLeft > spaceRight) {
                    spaceRight = intValue;
                    spaceLeft = spaceRight;
                }
                if (align == 1 && spaceLeft < spaceRight) {
                    spaceRight = intValue;
                    spaceLeft = spaceRight;
                }
                if (i3 == 0) {
                    if (align == 1) {
                        if (this.keyinfo.getIcCard() == 1311) {
                            arrayList5.add(new DestPoint(spaceLeft + 200, keyAlignInfo.getDecodeWidth() - 380, true, r3));
                        } else if (this.keyinfo.getIcCard() == 1372) {
                            arrayList4.add(new DestPoint(spaceLeft + 200, keyAlignInfo.getDecodeWidth() - 380, true, r3));
                        } else if (this.keyinfo.getSide() == 0) {
                            Iterator<Integer> it = this.allDepths.get(this.keyinfo.getSide()).iterator();
                            int i4 = 0;
                            while (it.hasNext()) {
                                i4 = Math.max(i4, it.next().intValue());
                            }
                            if (this.keyinfo.getIcCard() == 998) {
                                arrayList5.add(new DestPoint((spaceLeft + 280) - ToolSizeManager.getCutterRadiusSize(), i4, true, false));
                            } else if (this.keyinfo.getIcCard() == 810) {
                                arrayList5.add(new DestPoint((intValue + 230) - ToolSizeManager.getCutterRadiusSize(), i4, true, false));
                            } else {
                                arrayList5.add(new DestPoint(spaceLeft + 250, keyAlignInfo.getDecodeWidth(), true, false));
                            }
                        } else {
                            arrayList4.add(new DestPoint(spaceLeft + 150, keyAlignInfo.getDecodeWidth(), true, false));
                        }
                    } else {
                        arrayList5.add(new DestPoint(spaceLeft - 50, keyAlignInfo.getDecodeWidth(), true));
                    }
                }
                if (this.keyinfo.getSide() == 0) {
                    arrayList5.add(new DestPoint(spaceLeft, depthByCode, true));
                    arrayList5.add(new DestPoint(spaceRight, depthByCode, true));
                } else {
                    arrayList4.add(new DestPoint(spaceLeft, depthByCode, true));
                    arrayList4.add(new DestPoint(spaceRight, depthByCode, true));
                }
                if (i3 == arrayList.get(i2).length - 1) {
                    int nose = this.keyinfo.getNose();
                    int guide = this.keyinfo.getGuide();
                    if (align == 0) {
                        i = (int) (Math.abs(keyAlignInfo.getShoulder() - keyAlignInfo.getTip()) * MachineData.dYScale);
                    } else {
                        i = 0;
                    }
                    if (this.keyinfo.getSide() == 0) {
                        if (nose != 0) {
                            arrayList5.add(new DestPoint(getSpaceLeft(align, i, nose - 20), -20, true, false));
                            arrayList5.add(new DestPoint(0, -20, true, false));
                        } else if (guide != 0) {
                            arrayList5.add(new DestPoint(0, guide, true, false));
                        } else {
                            arrayList5.add(new DestPoint(0, 0, true, false));
                        }
                    } else if (nose != 0) {
                        arrayList4.add(new DestPoint(getSpaceLeft(align, i, nose - 20), -20, true, false));
                        arrayList4.add(new DestPoint(0, -20, true, false));
                    } else if (guide != 0) {
                        arrayList4.add(new DestPoint(0, guide, true, false));
                    } else {
                        arrayList4.add(new DestPoint(0, 0, true, false));
                    }
                }
                i3++;
                r3 = false;
            }
            i2++;
            z = false;
        }
        arrayList2.add(arrayList4);
        arrayList2.add(arrayList5);
        return arrayList2;
    }

    private List<List<DestPoint>> getDoubleOutsideGrooveKeyPoint(KeyAlignInfo keyAlignInfo) {
        int i;
        int i2;
        int i3;
        ArrayList arrayList;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        ArrayList arrayList2 = new ArrayList();
        ArrayList<String[]> toothCode = getToothCode();
        for (int i10 = 0; i10 < toothCode.size(); i10++) {
            ArrayList arrayList3 = new ArrayList();
            int i11 = 0;
            while (i11 < toothCode.get(i10).length) {
                int depthByCode = getDepthByCode(this.allDepths.get(i10), this.allDepthNames.get(i10), toothCode.get(i10)[i11]);
                int intValue = this.allSpaces.get(i10).get(i11).intValue();
                int intValue2 = this.allSpaceWidths.get(i10).get(i11).intValue() / 2;
                int align = this.keyinfo.getAlign();
                if (align == 0) {
                    i = intValue - intValue2;
                    i2 = intValue2 + intValue;
                    i3 = -250;
                } else {
                    i = intValue + intValue2;
                    i2 = intValue - intValue2;
                    i3 = 250;
                }
                if (i11 == 0) {
                    String exCut = getKeyinfo().getExCut();
                    if (!TextUtils.isEmpty(exCut)) {
                        String[] split = exCut.split(",");
                        int width = (getKeyinfo().getWidth() + Integer.parseInt(split[1])) / 2;
                        int parseInt = Integer.parseInt(split[2]);
                        if (getKeyinfo().getAlign() == 0) {
                            i7 = intValue - 250;
                            i8 = i - parseInt;
                            i9 = i8 - 200;
                        } else {
                            i7 = intValue + 250;
                            i8 = parseInt + i;
                            i9 = i8 + 200;
                        }
                        i4 = intValue;
                        arrayList = arrayList2;
                        i5 = 1;
                        arrayList3.add(new DestPoint(i9, getKeyinfo().getWidth(), true));
                        arrayList3.add(new DestPoint(i8, width, true));
                        arrayList3.add(new DestPoint(i7, width, true));
                    } else {
                        arrayList = arrayList2;
                        i4 = intValue;
                        i5 = 1;
                        arrayList3.add(new DestPoint(i3 + i, getKeyinfo().getWidth(), true));
                    }
                } else {
                    arrayList = arrayList2;
                    i4 = intValue;
                    i5 = 1;
                }
                if (align == 0 && i > i2) {
                    i2 = i4;
                    i = i2;
                }
                if (align != i5 || i >= i2) {
                    i6 = i;
                } else {
                    i6 = i4;
                    i2 = i6;
                }
                LogUtil.i(TAG, "spaceLeft: " + i6 + "--depth:" + depthByCode);
                LogUtil.i(TAG, "spaceRight: " + i2 + "--depth:" + depthByCode);
                arrayList3.add(new DestPoint(i6, depthByCode, true));
                arrayList3.add(new DestPoint(i2, depthByCode, true));
                if (i11 == toothCode.get(i10).length - 1) {
                    int i12 = -ToolSizeManager.getCutterRadiusSize();
                    if (align == 0) {
                        i12 = ((int) (Math.abs(keyAlignInfo.getShoulder() - keyAlignInfo.getTip()) * MachineData.dYScale)) + ToolSizeManager.getCutterRadiusSize();
                    }
                    arrayList3.add(new DestPoint(i12, (this.keyinfo.getWidth() / 2) - 30, true));
                }
                i11++;
                arrayList2 = arrayList;
            }
            arrayList2.add(arrayList3);
        }
        return arrayList2;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x025a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private List<List<DestPoint>> getSingleInsideGrooveKeyPoint(KeyAlignInfo r20, String r21) {
        /*
            Method dump skipped, instructions count: 882
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.customView.drawKeyImg.Key.getSingleInsideGrooveKeyPoint(com.cutting.machine.KeyAlignInfo, java.lang.String):java.util.List");
    }

    private List<List<DestPoint>> getSingleInsideGrooveKeyPoint2(KeyAlignInfo keyAlignInfo) {
        int i;
        int i2;
        ArrayList arrayList = new ArrayList();
        ArrayList<String[]> toothCode = getToothCode();
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < toothCode.size(); i3++) {
            for (int i4 = 0; i4 < toothCode.get(i3).length; i4++) {
                int depthByCode = getDepthByCode(this.allDepths.get(i3), this.allDepthNames.get(i3), toothCode.get(i3)[i4]);
                int intValue = this.allSpaces.get(i3).get(i4).intValue();
                int intValue2 = this.allSpaceWidths.get(i3).get(i4).intValue() / 2;
                int align = this.keyinfo.getAlign();
                if (align == 0) {
                    i = intValue - intValue2;
                    i2 = intValue + intValue2;
                } else {
                    i = intValue + intValue2;
                    i2 = intValue - intValue2;
                }
                if (i4 == 0) {
                    int innerCutX = getKeyinfo().getInnerCutX();
                    int innerCutY = getKeyinfo().getInnerCutY();
                    int i5 = innerCutX != 0 ? align == 0 ? i - innerCutX : innerCutX + i : i;
                    if (innerCutY == -1 || innerCutY == 0) {
                        innerCutY = depthByCode;
                    }
                    arrayList2.add(new DestPoint(i5, innerCutY, true));
                }
                arrayList2.add(new DestPoint(i, depthByCode, true));
                arrayList2.add(new DestPoint(i2, depthByCode, true));
                if (i4 == toothCode.get(i3).length - 1) {
                    int abs = align == 0 ? (int) (Math.abs(keyAlignInfo.getShoulder() - keyAlignInfo.getTip()) * MachineData.dYScale) : 0;
                    this.keyinfo.getNose();
                    this.keyinfo.getGuide();
                    arrayList2.add(new DestPoint(abs, 0, true));
                }
            }
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4, types: [int, boolean] */
    private List<List<DestPoint>> getDoubleInsideGrooveKeyPoint(KeyAlignInfo keyAlignInfo) {
        int i;
        int i2;
        int depthByCode;
        int depthByCode2;
        boolean r12;
        int max;
        ArrayList arrayList = new ArrayList();
        ArrayList<String[]> toothCode = getToothCode();
        for (int i3 = 0; i3 < toothCode.size(); i3++) {
            ArrayList arrayList2 = new ArrayList();
            for (int i4 = 0; i4 < toothCode.get(i3).length; i4++) {
                int depthByCode3 = getDepthByCode(this.allDepths.get(i3), this.allDepthNames.get(i3), toothCode.get(i3)[i4]);
                int intValue = this.allSpaces.get(i3).get(i4).intValue();
                int intValue2 = this.allSpaceWidths.get(i3).get(i4).intValue() / 2;
                int align = this.keyinfo.getAlign();
                if (align == 0) {
                    i = intValue - intValue2;
                    i2 = intValue + intValue2;
                } else {
                    i = intValue + intValue2;
                    i2 = intValue - intValue2;
                }
                if (i4 == 0) {
                    depthByCode = this.keyinfo.getWidth();
                    depthByCode2 = getDepthByCode(this.allDepths.get(i3), this.allDepthNames.get(i3), toothCode.get(i3)[i4 + 1]);
                } else if (i4 == toothCode.get(i3).length - 1) {
                    depthByCode = getDepthByCode(this.allDepths.get(i3), this.allDepthNames.get(i3), toothCode.get(i3)[i4 - 1]);
                    depthByCode2 = 0;
                } else {
                    depthByCode = getDepthByCode(this.allDepths.get(i3), this.allDepthNames.get(i3), toothCode.get(i3)[i4 - 1]);
                    depthByCode2 = getDepthByCode(this.allDepths.get(i3), this.allDepthNames.get(i3), toothCode.get(i3)[i4 + 1]);
                }
                int fixSpaceWidthLeft = fixSpaceWidthLeft(align, i, depthByCode3, depthByCode);
                int fixSpaceWidthRight = fixSpaceWidthRight(align, i2, depthByCode3, depthByCode2);
                if (align == 0 && fixSpaceWidthLeft > fixSpaceWidthRight) {
                    fixSpaceWidthRight = intValue;
                    fixSpaceWidthLeft = fixSpaceWidthRight;
                }
                if (align != 1 || fixSpaceWidthLeft >= fixSpaceWidthRight) {
                    intValue = fixSpaceWidthLeft;
                } else {
                    fixSpaceWidthRight = intValue;
                }
                if (i4 == 0) {
                    int width = (this.keyinfo.getWidth() / 2) - ToolSizeManager.getCutterRadiusSize();
                    int intValue3 = this.allSpaces.get(0).get(0).intValue();
                    int intValue4 = this.allSpaces.get(1).get(0).intValue();
                    if (align == 0) {
                        max = Math.min(intValue3, intValue4);
                    } else {
                        max = Math.max(intValue3, intValue4);
                    }
                    int spaceLeft = getSpaceLeft(align, max, intValue2 + 300);
                    r12 = true;
                    arrayList2.add(new DestPoint(spaceLeft, width, true, false));
                } else {
                    r12 = true;
                }
                arrayList2.add(new DestPoint(intValue, depthByCode3, r12));
                arrayList2.add(new DestPoint(fixSpaceWidthRight, depthByCode3, r12));
                if (i4 == toothCode.get(i3).length - (r12 ? 1 : 0 )) {
                    int abs = align == 0 ? (int) (Math.abs(keyAlignInfo.getShoulder() - keyAlignInfo.getTip()) * MachineData.dYScale) : 0;
                    int nose = this.keyinfo.getNose();
                    int guide = this.keyinfo.getGuide();
                    if (nose != 0) {
                        arrayList2.add(new DestPoint(getSpaceLeft(align, abs, nose - ToolSizeManager.getCutterRadiusSize()), -ToolSizeManager.getCutterRadiusSize(), true, false));
                        arrayList2.add(new DestPoint(abs, -ToolSizeManager.getCutterRadiusSize(), true, false));
                    } else if (guide != 0) {
                        arrayList2.add(new DestPoint(abs, guide, true, false));
                    } else {
                        arrayList2.add(new DestPoint(abs, 0, true, false));
                    }
                }
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private int calculateAnotherSideDepth(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, int i) {
        int decodeWidth = keyAlignInfo.getDecodeWidth();
        if (decodeWidth != 0) {
            return (decodeWidth - i) - this.keyinfo.getGroove();
        }
        return (keyInfo.getWidth() - i) - this.keyinfo.getGroove();
    }

    private void addHeadPoints(List<DestPoint> list) {
        if (this.keyinfo.getType() == 0) {
            list.add(new DestPoint(list.get(list.size() - 1).getSpace() - 100, getKeyinfo().getWidth(), true));
            return;
        }
        if (this.keyinfo.getType() == 3) {
            int nose = this.keyinfo.getNose();
            int guide = this.keyinfo.getGuide();
            if (nose != 0) {
                list.add(new DestPoint(nose - ToolSizeManager.getCutterRadiusSize(), 0, true));
                return;
            } else {
                if (guide != 0) {
                    list.add(new DestPoint(0, guide, true));
                    return;
                }
                return;
            }
        }
        if (this.keyinfo.getType() == 5) {
            int nose2 = this.keyinfo.getNose();
            int guide2 = this.keyinfo.getGuide();
            if (nose2 != 0) {
                list.add(new DestPoint(nose2 - ToolSizeManager.getCutterRadiusSize(), 0, true));
            } else if (guide2 != 0) {
                list.add(new DestPoint(0, guide2, true));
            }
        }
    }
}
