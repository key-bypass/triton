package com.cutting.machine;

import android.text.TextUtils;

import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.MachineData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class KeyBlankCutStepsGenerateUtil {
    private static final int ANGLE = 100;

    private static int getSpaceLeft(int i, int i2, int i3) {
        return i == 0 ? i2 - i3 : i2 + i3;
    }

    private static int getSpaceRight(int i, int i2, int i3) {
        return i == 0 ? i2 + i3 : i2 - i3;
    }

    public static List<List<DestPoint>> getDestCutPoint(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, String str, int i, int i2) {
        switch (keyInfo.getType()) {
            case 0:
                List<List<DestPoint>> doubleSideKeyPoint = getDoubleSideKeyPoint(keyInfo);
                fixSpaceWidth(keyInfo, doubleSideKeyPoint);
                return doubleSideKeyPoint;
            case 1:
                return getSingleSideKeyPoint(keyInfo, keyAlignInfo, i2 != 1);
            case 2:
                if (keyInfo.getGroove() == 0) {
                    return getDoubleInsideGrooveKeyPoint(keyInfo, keyAlignInfo);
                }
                return getPeuceotKeyPoint(keyInfo, keyAlignInfo);
            case 3:
                return getSingleOutsideGrooveKeyPoint(keyInfo, keyAlignInfo, str);
            case 4:
                return getDoubleOutsideGrooveKeyPoint(keyInfo, keyAlignInfo);
            case 5:
                return getSingleInsideGrooveKeyPoint(keyInfo, keyAlignInfo, str);
            case 6:
                return getDimpleKeyPoint(keyInfo, i);
            case 7:
            default:
                return null;
            case 8:
                return getTubularKeyPoint(keyInfo);
        }
    }

    private static List<List<DestPoint>> getDimpleKeyPoint(KeyInfo keyInfo, int i) {
        boolean z;
        KeyInfo keyInfo2 = keyInfo;
        int i2 = i;
        List<List<String>> toothCodeArray = keyInfo.getToothCodeArray();
        List<List<Integer>> depthList = keyInfo.getDepthList();
        List<List<String>> depthNameList = keyInfo.getDepthNameList();
        List<List<Integer>> spaceList = keyInfo.getSpaceList();
        ArrayList arrayList = new ArrayList();
        String spaceWidthStr = keyInfo.getSpaceWidthStr();
        boolean contains = spaceWidthStr.contains("-");
        String[] split = spaceWidthStr.split(";");
        ArrayList arrayList2 = new ArrayList();
        int i3 = 0;
        while (i3 < toothCodeArray.size()) {
            if ((i2 == 0 || i2 == 1) && contains) {
                if (split[i3].contains("-")) {
                    String[] split2 = keyInfo.getRow_pos().split(";");
                    int i4 = 0;
                    while (i4 < toothCodeArray.get(i3).size()) {
                        arrayList2.add(new DestPoint(spaceList.get(i3).get(i4).intValue(), keyInfo2.getDepthByCode(depthList.get(i3), depthNameList.get(i3), toothCodeArray.get(i3).get(i4)), Integer.parseInt(split2[i3])));
                        i4++;
                        contains = contains;
                        split2 = split2;
                    }
                }
                z = contains;
            } else {
                z = contains;
                if (!split[i3].contains("-")) {
                    String[] split3 = keyInfo.getRow_pos().split(";");
                    int i5 = 0;
                    while (i5 < toothCodeArray.get(i3).size()) {
                        arrayList2.add(new DestPoint(spaceList.get(i3).get(i5).intValue(), keyInfo2.getDepthByCode(depthList.get(i3), depthNameList.get(i3), toothCodeArray.get(i3).get(i5)), Integer.parseInt(split3[i3])));
                        i5++;
                        keyInfo2 = keyInfo;
                    }
                }
            }
            i3++;
            keyInfo2 = keyInfo;
            i2 = i;
            contains = z;
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r6v37 */
    private static List<List<DestPoint>> getSingleSideKeyPoint(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, boolean z) {
        ArrayList arrayList;
        List<List<Integer>> list;
        String str;
        List<Integer> list2;
        int i;
        int i2;
        KeyInfo keyInfo2;
        List<List<Integer>> list3;
        int depthByCode;
        int depthByCode2;
        int i3;
        int fixSpaceWidthLeft;
        int fixSpaceWidthRight;
        int i4;
        List<List<String>> list4;
        List<List<Integer>> list5;
        List<List<String>> list6;
        int i5;
        int i6;
        List<Integer> list7;
        boolean r6;
        int i7;
        List<Integer> list8;
        int i8;
        int spaceLeft;
        double cos;
        int i9;
        double d;
        double sin;
        double cos2;
        int i10;
        int i11;
        int i12;
        KeyInfo keyInfo3 = keyInfo;
        List<List<String>> toothCodeArray = keyInfo.getToothCodeArray();
        List<List<Integer>> depthList = keyInfo.getDepthList();
        List<List<String>> depthNameList = keyInfo.getDepthNameList();
        List<List<Integer>> spaceWidthList = keyInfo.getSpaceWidthList();
        List<List<Integer>> spaceList = keyInfo.getSpaceList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i13 = 0;
        while (i13 < spaceList.size()) {
            List<Integer> list9 = spaceList.get(i13);
            int i14 = 0;
            while (i14 < list9.size()) {
                int depthByCode3 = keyInfo3.getDepthByCode(depthList.get(i13), depthNameList.get(i13), toothCodeArray.get(i13).get(i14));
                int intValue = list9.get(i14).intValue();
                int intValue2 = spaceWidthList.get(i13).get(i14).intValue() / 2;
                int align = keyInfo.getAlign();
                int spaceLeft2 = getSpaceLeft(align, intValue, intValue2);
                List<List<Integer>> list10 = spaceWidthList;
                int spaceRight = getSpaceRight(align, intValue, intValue2);
                if (align != 0 || spaceLeft2 <= spaceRight) {
                    arrayList = arrayList2;
                } else {
                    arrayList = arrayList2;
                    spaceRight = intValue;
                    spaceLeft2 = spaceRight;
                }
                if (align == 1 && spaceLeft2 < spaceRight) {
                    spaceRight = intValue;
                    spaceLeft2 = spaceRight;
                }
                if (i14 == 0) {
                    String exCut = keyInfo.getExCut();
                    if (TextUtils.isEmpty(exCut)) {
                        list = spaceList;
                        str = ",";
                        list2 = list9;
                        i = depthByCode3;
                    } else {
                        list2 = list9;
                        i = depthByCode3;
                        if (keyInfo.getClampBean().getClampNum().equals("S1")) {
                            list = spaceList;
                            str = ",";
                        } else {
                            String[] split = exCut.split(",");
                            int parseInt = Integer.parseInt(split[1]);
                            int parseInt2 = Integer.parseInt(split[2]);
                            if (keyInfo.getAlign() == 0) {
                                i10 = spaceLeft2 - parseInt2;
                                i12 = i10 - 200;
                                str = ",";
                                i11 = intValue - 250;
                                i2 = intValue;
                            } else {
                                i10 = parseInt2 + spaceLeft2;
                                i2 = intValue;
                                str = ",";
                                i11 = intValue + 250;
                                i12 = i10 + 200;
                            }
                            list = spaceList;
                            arrayList3.add(new DestPoint(i12, keyInfo.getWidth(), true));
                            arrayList3.add(new DestPoint(i10, parseInt, true));
                            arrayList3.add(new DestPoint(i11, parseInt, true));
                        }
                    }
                    i2 = intValue;
                    int spaceLeft3 = getSpaceLeft(align, spaceLeft2, 250);
                    if (spaceLeft3 < ToolSizeManager.getCutterRadiusSize() + 10) {
                        spaceLeft3 = ToolSizeManager.getCutterRadiusSize() + 10;
                    }
                    arrayList3.add(new DestPoint(spaceLeft3, keyInfo.getWidth(), true));
                } else {
                    list = spaceList;
                    str = ",";
                    list2 = list9;
                    i = depthByCode3;
                    i2 = intValue;
                }
                if (z && keyInfo.getCutDepth() == 0) {
                    if (align == 0) {
                        fixSpaceWidthLeft = spaceLeft2 + ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.1d));
                        fixSpaceWidthRight = spaceRight - ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.1d));
                    } else {
                        fixSpaceWidthLeft = spaceLeft2 - ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.1d));
                        fixSpaceWidthRight = spaceRight + ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.1d));
                    }
                    keyInfo2 = keyInfo;
                    i3 = i;
                    list3 = list;
                } else {
                    if (i14 == 0) {
                        int width = keyInfo.getWidth();
                        keyInfo2 = keyInfo;
                        depthByCode2 = keyInfo2.getDepthByCode(depthList.get(i13), depthNameList.get(i13), toothCodeArray.get(i13).get(i14 + 1));
                        i3 = i;
                        depthByCode = width;
                        list3 = list;
                    } else {
                        keyInfo2 = keyInfo;
                        list3 = list;
                        if (i14 == list3.get(i13).size() - 1) {
                            depthByCode = keyInfo2.getDepthByCode(depthList.get(i13), depthNameList.get(i13), toothCodeArray.get(i13).get(i14 - 1));
                            depthByCode2 = keyInfo.getWidth();
                        } else {
                            depthByCode = keyInfo2.getDepthByCode(depthList.get(i13), depthNameList.get(i13), toothCodeArray.get(i13).get(i14 - 1));
                            depthByCode2 = keyInfo2.getDepthByCode(depthList.get(i13), depthNameList.get(i13), toothCodeArray.get(i13).get(i14 + 1));
                        }
                        i3 = i;
                    }
                    fixSpaceWidthLeft = fixSpaceWidthLeft(keyInfo2, spaceLeft2, i3, depthByCode);
                    fixSpaceWidthRight = fixSpaceWidthRight(keyInfo2, spaceRight, i3, depthByCode2);
                }
                if (align == 0 && fixSpaceWidthLeft > fixSpaceWidthRight) {
                    fixSpaceWidthRight = i2;
                    fixSpaceWidthLeft = fixSpaceWidthRight;
                }
                if (align != 1 || fixSpaceWidthLeft >= fixSpaceWidthRight) {
                    i4 = fixSpaceWidthLeft;
                } else {
                    fixSpaceWidthRight = i2;
                    i4 = fixSpaceWidthRight;
                }
                arrayList3.add(new DestPoint(i4, i3, true));
                arrayList3.add(new DestPoint(fixSpaceWidthRight, i3, true));
                if (z && keyInfo.getCutDepth() == 0 && i14 < toothCodeArray.get(i13).size() - 1) {
                    int depthByCode4 = keyInfo2.getDepthByCode(depthList.get(i13), depthNameList.get(i13), toothCodeArray.get(i13).get(i14));
                    if (align == 0) {
                        list8 = list2;
                        i7 = fixSpaceWidthRight;
                        i8 = depthByCode4;
                        spaceLeft = getSpaceLeft(align, list8.get(i14 + 1).intValue(), intValue2) + ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.22d));
                    } else {
                        i7 = fixSpaceWidthRight;
                        list8 = list2;
                        i8 = depthByCode4;
                        spaceLeft = getSpaceLeft(align, list8.get(i14 + 1).intValue(), intValue2) - ((int) (ToolSizeManager.getCutterRadiusSize2() * 0.22d));
                    }
                    int depthByCode5 = keyInfo2.getDepthByCode(depthList.get(i13), depthNameList.get(i13), toothCodeArray.get(i13).get(i14 + 1));
                    double d2 = i7 - spaceLeft;
                    list4 = toothCodeArray;
                    list5 = depthList;
                    list6 = depthNameList;
                    list7 = list8;
                    i6 = i14;
                    double d3 = i8 - depthByCode5;
                    double sin2 = (Math.sin(Math.toRadians(40.0d) + Math.abs(Math.atan((d3) / d2))) * Math.sqrt(Math.pow(d2, 2.0d) + Math.pow(d3, 2.0d))) / Math.sin(Math.toRadians(100.0d));
                    int i15 = i8;
                    if (i15 < depthByCode5) {
                        if (align == 0) {
                            i5 = i7;
                            cos2 = i5 + (Math.cos(Math.toRadians(40.0d)) * sin2);
                        } else {
                            i5 = i7;
                            cos2 = i5 - (Math.cos(Math.toRadians(40.0d)) * sin2);
                        }
                        i9 = (int) cos2;
                        d = i15;
                        sin = Math.sin(Math.toRadians(40.0d));
                    } else {
                        i5 = i7;
                        if (align == 0) {
                            cos = spaceLeft - (Math.cos(Math.toRadians(40.0d)) * sin2);
                        } else {
                            cos = spaceLeft + (Math.cos(Math.toRadians(40.0d)) * sin2);
                        }
                        i9 = (int) cos;
                        d = depthByCode5;
                        sin = Math.sin(Math.toRadians(40.0d));
                    }
                    r6 = true;
                    arrayList3.add(new DestPoint(i9, (int) (d + (sin2 * sin)), true));
                } else {
                    list4 = toothCodeArray;
                    list5 = depthList;
                    list6 = depthNameList;
                    i5 = fixSpaceWidthRight;
                    i6 = i14;
                    list7 = list2;
                    r6 = true;
                }
                int i16 = i6;
                if (i16 == list3.get(i13).size() - i6) {
                    String extTopCut = keyInfo.getExtTopCut();
                    if (TextUtils.isEmpty(extTopCut)) {
                        int spaceRight2 = getSpaceRight(align, i5, 250);
                        if (spaceRight2 < 0) {
                            spaceRight2 = 0;
                        }
                        arrayList3.add(new DestPoint(spaceRight2, keyInfo.getWidth(), true));
                    } else {
                        String[] split2 = extTopCut.split(str);
                        arrayList3.add(new DestPoint(Integer.parseInt(split2[i6]), Integer.parseInt(split2[0]), (boolean) r6));
                        int i17 = -ToolSizeManager.getCutterRadiusSize();
                        if (align == 0) {
                            i17 = ((int) (Math.abs(keyAlignInfo.getShoulder() - keyAlignInfo.getTip()) * MachineData.dYScale)) + ToolSizeManager.getCutterRadiusSize();
                        }
                        arrayList3.add(new DestPoint(i17, Integer.parseInt(split2[0]), true));
                        i14 = i16 + 1;
                        toothCodeArray = list4;
                        depthList = list5;
                        spaceWidthList = list10;
                        arrayList2 = arrayList;
                        depthNameList = list6;
                        list9 = list7;
                        spaceList = list3;
                        keyInfo3 = keyInfo2;
                    }
                }
                i14 = i16 + 1;
                toothCodeArray = list4;
                depthList = list5;
                spaceWidthList = list10;
                arrayList2 = arrayList;
                depthNameList = list6;
                list9 = list7;
                spaceList = list3;
                keyInfo3 = keyInfo2;
            }
            i13++;
            arrayList2 = arrayList2;
            spaceList = spaceList;
            keyInfo3 = keyInfo3;
        }
        ArrayList arrayList4 = arrayList2;
        arrayList4.add(arrayList3);
        return arrayList4;
    }

    private static List<List<DestPoint>> getTubularKeyPoint(KeyInfo keyInfo) {
        List<List<String>> toothCodeArray = keyInfo.getToothCodeArray();
        List<List<Integer>> depthList = keyInfo.getDepthList();
        List<List<String>> depthNameList = keyInfo.getDepthNameList();
        keyInfo.getSpaceWidthList();
        List<List<Integer>> spaceList = keyInfo.getSpaceList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < toothCodeArray.size(); i++) {
            for (int i2 = 0; i2 < toothCodeArray.get(i).size(); i2++) {
                arrayList2.add(new DestPoint(spaceList.get(i).get(i2).intValue(), keyInfo.getDepthByCode(depthList.get(i), depthNameList.get(i), toothCodeArray.get(i).get(i2))));
            }
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    private static void fixSpaceWidth(KeyInfo keyInfo, List<List<DestPoint>> list) {
        int i;
        int cutterRadiusSize2;
        int cutterRadiusSize22;
        double cos = 0;
        int cutterRadiusSize23 = 0;
        for (List<DestPoint> list2 : list) {
            for (int i2 = 1; i2 < list2.size() - 1; i2++) {
                DestPoint destPoint = list2.get(i2 - 1);
                DestPoint destPoint2 = list2.get(i2);
                int depth = destPoint2.getDepth() - destPoint.getDepth();
                int space = destPoint2.getSpace() - destPoint.getSpace();
                double d = 0.22d;
                if (depth < 0) {
                    if (keyInfo.getAlign() == 1) {
                        cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2();
                        cos = cutterRadiusSize2;
                        i = (int) (cos * d);
                        destPoint2.setSpace(destPoint2.getSpace() - i);
                    } else {
                        cutterRadiusSize22 = ToolSizeManager.getCutterRadiusSize2();
                        i = -((int) (cutterRadiusSize22 * 0.22d));
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
                            if (keyInfo.getAlign() == 1) {
                                cutterRadiusSize22 = ToolSizeManager.getCutterRadiusSize2();
                                i = -((int) (cutterRadiusSize22 * 0.22d));
                                destPoint2.setSpace(destPoint2.getSpace() - i);
                            } else {
                                cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2();
                                cos = cutterRadiusSize2;
                                i = (int) (cos * d);
                                destPoint2.setSpace(destPoint2.getSpace() - i);
                            }
                        } else {
                            i = 0;
                            destPoint2.setSpace(destPoint2.getSpace() - i);
                        }
                    }
                    d = cutterRadiusSize23;
                    i = (int) (cos * d);
                    destPoint2.setSpace(destPoint2.getSpace() - i);
                }
            }
        }
    }

    public static List<List<DestPoint>> getAngleKeyCutPoint(KeyInfo keyInfo, String str) {
        List<List<Integer>> spaceWidthList = keyInfo.getSpaceWidthList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (String str2 : str.split(",")) {
            int parseInt = Integer.parseInt(str2);
            int intValue = (spaceWidthList.get(0).get(0).intValue() / 2) - ToolSizeManager.getCutterRadiusSize();
            int align = keyInfo.getAlign();
            arrayList2.add(new DestPoint(getSpaceLeft(align, parseInt, intValue), 1000 - ToolSizeManager.getCutterRadiusSize(), true));
            arrayList2.add(new DestPoint(getSpaceLeft(align, parseInt, intValue), -ToolSizeManager.getCutterRadiusSize(), true));
            arrayList2.add(new DestPoint(getSpaceRight(align, parseInt, intValue), -ToolSizeManager.getCutterRadiusSize(), true));
            arrayList2.add(new DestPoint(getSpaceRight(align, parseInt, intValue), 1000 - ToolSizeManager.getCutterRadiusSize(), true));
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    private static List<List<DestPoint>> getPeuceotKeyPoint(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo) {
        List<List<Integer>> list;
        List<List<String>> list2;
        List<List<Integer>> list3;
        boolean r5;
        int i;
        List<List<String>> toothCodeArray = keyInfo.getToothCodeArray();
        List<List<Integer>> depthList = keyInfo.getDepthList();
        List<List<String>> depthNameList = keyInfo.getDepthNameList();
        List<List<Integer>> spaceWidthList = keyInfo.getSpaceWidthList();
        List<List<Integer>> spaceList = keyInfo.getSpaceList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList<Index> sortSpace = getSortSpace(spaceList, keyInfo.getAlign());
        int i2 = 0;
        while (i2 < sortSpace.size()) {
            Index index = sortSpace.get(i2);
            int row = index.getRow();
            int column = index.getColumn();
            int data = index.getData();
            List<List<String>> list4 = toothCodeArray;
            int depthByCode = keyInfo.getDepthByCode(depthList.get(row), depthNameList.get(row), toothCodeArray.get(row).get(column));
            int intValue = spaceWidthList.get(row).get(column).intValue() / 2;
            int align = keyInfo.getAlign();
            int spaceLeft = getSpaceLeft(align, data, intValue);
            int spaceRight = getSpaceRight(align, data, intValue);
            if (i2 == 0) {
                int innerCutX = keyInfo.getInnerCutX() + 50;
                list = depthList;
                int innerCutY = keyInfo.getInnerCutY();
                int spaceLeft2 = getSpaceLeft(align, spaceLeft, innerCutX);
                list2 = depthNameList;
                if (innerCutY == -1 || innerCutY == 0) {
                    innerCutY = depthByCode;
                }
                if (row == 0) {
                    list3 = spaceWidthList;
                    r5 = true;
                    arrayList2.add(new DestPoint(spaceLeft2, innerCutY, true));
                    arrayList3.add(new DestPoint(spaceLeft2, calculateAnotherSideDepth(keyInfo, keyAlignInfo, innerCutY), true));
                } else {
                    list3 = spaceWidthList;
                    r5 = true;
                    arrayList3.add(new DestPoint(spaceLeft2, innerCutY, true));
                    arrayList2.add(new DestPoint(spaceLeft2, calculateAnotherSideDepth(keyInfo, keyAlignInfo, innerCutY), true));
                }
            } else {
                list = depthList;
                list2 = depthNameList;
                list3 = spaceWidthList;
                r5 = true;
            }
            if (row == 0) {
                arrayList2.add(new DestPoint(spaceLeft, depthByCode, (boolean) r5));
                arrayList2.add(new DestPoint(spaceRight, depthByCode, (boolean) r5));
                arrayList3.add(new DestPoint(spaceLeft, calculateAnotherSideDepth(keyInfo, keyAlignInfo, depthByCode), (boolean) r5));
                arrayList3.add(new DestPoint(spaceRight, calculateAnotherSideDepth(keyInfo, keyAlignInfo, depthByCode), (boolean) r5));
            } else {
                arrayList3.add(new DestPoint(spaceLeft, depthByCode, (boolean) r5));
                arrayList3.add(new DestPoint(spaceRight, depthByCode, (boolean) r5));
                arrayList2.add(new DestPoint(spaceLeft, calculateAnotherSideDepth(keyInfo, keyAlignInfo, depthByCode), (boolean) r5));
                arrayList2.add(new DestPoint(spaceRight, calculateAnotherSideDepth(keyInfo, keyAlignInfo, depthByCode), (boolean) r5));
            }
            if (i2 == sortSpace.size() - (r5 ? 1 : 0)) {
                if (align == 0) {
                    i = (int) (Math.abs(keyAlignInfo.getShoulder() - keyAlignInfo.getTip()) * MachineData.dYScale);
                } else {
                    i = 0;
                }
                int nose = keyInfo.getNose();
                int guide = keyInfo.getGuide();
                if (nose != 0) {
                    int i3 = nose - 10;
                    arrayList2.add(new DestPoint(getSpaceLeft(align, i, i3), -10, true));
                    arrayList3.add(new DestPoint(getSpaceLeft(align, i, i3), -10, true));
                } else if (guide != 0) {
                    arrayList2.add(new DestPoint(i, guide, true));
                    arrayList3.add(new DestPoint(i, guide, true));
                } else {
                    arrayList2.add(new DestPoint(i, 0, true));
                    arrayList3.add(new DestPoint(i, 0, true));
                    i2++;
                    depthList = list;
                    toothCodeArray = list4;
                    depthNameList = list2;
                    spaceWidthList = list3;
                }
            }
            i2++;
            depthList = list;
            toothCodeArray = list4;
            depthNameList = list2;
            spaceWidthList = list3;
        }
        arrayList.add(arrayList2);
        arrayList.add(arrayList3);
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [int, boolean] */
    private static List<List<DestPoint>> getDoubleSideKeyPoint(KeyInfo keyInfo) {
        List<List<Integer>> list;
        List<List<String>> list2;
        List<List<Integer>> list3;
        boolean r2;
        int spaceLeft;
        List<List<String>> toothCodeArray = keyInfo.getToothCodeArray();
        List<List<Integer>> depthList = keyInfo.getDepthList();
        List<List<String>> depthNameList = keyInfo.getDepthNameList();
        List<List<Integer>> spaceWidthList = keyInfo.getSpaceWidthList();
        List<List<Integer>> spaceList = keyInfo.getSpaceList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        while (i < toothCodeArray.size()) {
            int i2 = 0;
            while (i2 < toothCodeArray.get(i).size()) {
                int depthByCode = keyInfo.getDepthByCode(depthList.get(i), depthNameList.get(i), toothCodeArray.get(i).get(i2));
                int intValue = spaceList.get(i).get(i2).intValue();
                int intValue2 = spaceWidthList.get(i).get(i2).intValue() / 2;
                int align = keyInfo.getAlign();
                int spaceLeft2 = getSpaceLeft(align, intValue, intValue2);
                int spaceRight = getSpaceRight(align, intValue, intValue2);
                if (align != 0 || spaceLeft2 <= spaceRight) {
                    list = depthList;
                } else {
                    list = depthList;
                    spaceLeft2 = intValue;
                    spaceRight = spaceLeft2;
                }
                if (align != 1 || spaceLeft2 >= spaceRight) {
                    intValue = spaceLeft2;
                } else {
                    spaceRight = intValue;
                }
                if (i2 == 0) {
                    if (keyInfo.getIcCard() == 1047) {
                        spaceLeft = getSpaceLeft(align, intValue, 100);
                    } else {
                        spaceLeft = getSpaceLeft(align, intValue, 250);
                    }
                    if (spaceLeft < ToolSizeManager.getCutterRadiusSize() + 10) {
                        spaceLeft = ToolSizeManager.getCutterRadiusSize() + 10;
                    }
                    list2 = depthNameList;
                    r2 = true;
                    if (toothCodeArray.size() == 1) {
                        list3 = spaceWidthList;
                        arrayList2.add(new DestPoint(spaceLeft, keyInfo.getWidth(), true));
                        arrayList3.add(new DestPoint(spaceLeft, keyInfo.getWidth(), true));
                    } else {
                        list3 = spaceWidthList;
                        if (i == 0) {
                            arrayList2.add(new DestPoint(spaceLeft, keyInfo.getWidth() + ToolSizeManager.getCutterRadiusSize(), true));
                        } else {
                            arrayList3.add(new DestPoint(spaceLeft, keyInfo.getWidth(), true));
                        }
                    }
                } else {
                    list2 = depthNameList;
                    list3 = spaceWidthList;
                    r2 = true;
                }
                if (toothCodeArray.size() == (r2 ? 1 : 0)) {
                    arrayList2.add(new DestPoint(intValue, depthByCode, (boolean) r2));
                    arrayList2.add(new DestPoint(spaceRight, depthByCode, (boolean) r2));
                    arrayList3.add(new DestPoint(intValue, depthByCode, (boolean) r2));
                    arrayList3.add(new DestPoint(spaceRight, depthByCode, (boolean) r2));
                } else if (i == 0) {
                    arrayList2.add(new DestPoint(intValue, depthByCode, (boolean) r2));
                    arrayList2.add(new DestPoint(spaceRight, depthByCode, (boolean) r2));
                } else {
                    arrayList3.add(new DestPoint(intValue, depthByCode, (boolean) r2));
                    arrayList3.add(new DestPoint(spaceRight, depthByCode, (boolean) r2));
                }
                if (i2 == toothCodeArray.get(i).size() - (r2 ? 1 : 0)) {
                    String cutSharpenType = keyInfo.getCutSharpenType();
                    if ((!TextUtils.isEmpty(cutSharpenType) ? Integer.parseInt(cutSharpenType.split(",")[0]) : 0) == 0) {
                        int spaceRight2 = getSpaceRight(align, spaceRight, 250);
                        if (spaceRight2 < 0) {
                            spaceRight2 = 0;
                        }
                        if (toothCodeArray.size() == 1) {
                            arrayList2.add(new DestPoint(spaceRight2, keyInfo.getWidth(), true));
                            arrayList3.add(new DestPoint(spaceRight2, keyInfo.getWidth(), true));
                        } else if (i == 0) {
                            arrayList2.add(new DestPoint(spaceRight2, keyInfo.getWidth(), true));
                        } else {
                            arrayList3.add(new DestPoint(spaceRight2, keyInfo.getWidth(), true));
                        }
                    }
                }
                i2++;
                depthList = list;
                depthNameList = list2;
                spaceWidthList = list3;
            }
            i++;
            depthNameList = depthNameList;
        }
        arrayList.add(arrayList2);
        arrayList.add(arrayList3);
        return arrayList;
    }

    private static int fixSpaceWidthLeft(KeyInfo keyInfo, int i, int i2, int i3) {
        int i4;
        int i5;
        if (keyInfo.getType() == 0) {
            i5 = (int) (((((ToolSizeManager.getCutterSize() - 200) * 1.0f) / 100.0f) * 20.0f) + 40.0f);
            i4 = (int) (((((ToolSizeManager.getCutterSize() - 200) * 1.0f) / 100.0f) * 20.0f) + 40.0f);
        } else {
            i4 = 30;
            i5 = 20;
        }
        int i6 = i3 - i2;
        return i6 > 0 ? keyInfo.getAlign() == 0 ? i + i5 : i - i5 : i6 < 0 ? keyInfo.getAlign() == 0 ? i - i4 : i + i4 : i;
    }

    private static int fixSpaceWidthRight(KeyInfo keyInfo, int i, int i2, int i3) {
        int i4;
        int i5;
        if (keyInfo.getType() == 0) {
            i5 = (int) (((((ToolSizeManager.getCutterSize() - 200) * 1.0f) / 100.0f) * 20.0f) + 40.0f);
            i4 = (int) (((((ToolSizeManager.getCutterSize() - 200) * 1.0f) / 100.0f) * 20.0f) + 40.0f);
        } else {
            i4 = 30;
            i5 = 20;
        }
        int i6 = i3 - i2;
        return i6 > 0 ? keyInfo.getAlign() == 0 ? i - i5 : i + i5 : i6 < 0 ? keyInfo.getAlign() == 0 ? i + i4 : i - i4 : i;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02c4  */
    /* JADX WARN: Type inference failed for: r14v13, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v18 */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v20 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static List<List<DestPoint>> getSingleOutsideGrooveKeyPoint(KeyInfo r23, KeyAlignInfo r24, String r25) {
        /*
            Method dump skipped, instructions count: 941
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.KeyBlankCutStepsGenerateUtil.getSingleOutsideGrooveKeyPoint(com.cutting.machine.bean.KeyInfo, com.cutting.machine.KeyAlignInfo, java.lang.String):java.util.List");
    }

    private static List<List<DestPoint>> getDoubleOutsideGrooveKeyPoint(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo) {
        int i;
        int i2;
        int i3;
        List<List<Integer>> list;
        List<List<Integer>> list2;
        ArrayList arrayList;
        int i4;
        int i5;
        int i6;
        int depthByCode;
        int depthByCode2;
        int i7;
        int shoulder;
        int i8;
        int i9;
        int i10;
        List<List<String>> toothCodeArray = keyInfo.getToothCodeArray();
        List<List<Integer>> depthList = keyInfo.getDepthList();
        List<List<String>> depthNameList = keyInfo.getDepthNameList();
        List<List<Integer>> spaceWidthList = keyInfo.getSpaceWidthList();
        List<List<Integer>> spaceList = keyInfo.getSpaceList();
        ArrayList arrayList2 = new ArrayList();
        int i11 = 0;
        while (i11 < toothCodeArray.size()) {
            ArrayList arrayList3 = new ArrayList();
            int i12 = 0;
            while (i12 < toothCodeArray.get(i11).size()) {
                int depthByCode3 = keyInfo.getDepthByCode(depthList.get(i11), depthNameList.get(i11), toothCodeArray.get(i11).get(i12));
                int intValue = spaceList.get(i11).get(i12).intValue();
                int intValue2 = spaceWidthList.get(i11).get(i12).intValue() / 2;
                int align = keyInfo.getAlign();
                if (align == 0) {
                    i = intValue - intValue2;
                    i2 = intValue2 + intValue;
                    i3 = -250;
                } else {
                    i = intValue + intValue2;
                    i2 = intValue - intValue2;
                    i3 = 250;
                }
                int i13 = i;
                if (i12 == 0) {
                    String exCut = keyInfo.getExCut();
                    if (!TextUtils.isEmpty(exCut)) {
                        list = spaceWidthList;
                        String[] split = exCut.split(",");
                        int width = (keyInfo.getWidth() + Integer.parseInt(split[1])) / 2;
                        int parseInt = Integer.parseInt(split[2]);
                        if (keyInfo.getAlign() == 0) {
                            list2 = spaceList;
                            i8 = i13 - parseInt;
                            i9 = intValue - 250;
                            i10 = i8 - 200;
                        } else {
                            list2 = spaceList;
                            i8 = parseInt + i13;
                            i9 = intValue + 250;
                            i10 = i8 + 200;
                        }
                        arrayList = arrayList2;
                        i4 = intValue;
                        i6 = align;
                        i5 = i2;
                        arrayList3.add(new DestPoint(i10, keyInfo.getWidth(), true));
                        arrayList3.add(new DestPoint(i8, width, true));
                        arrayList3.add(new DestPoint(i9, width, true));
                    } else {
                        list = spaceWidthList;
                        list2 = spaceList;
                        arrayList = arrayList2;
                        i4 = intValue;
                        i5 = i2;
                        i6 = align;
                        arrayList3.add(new DestPoint(i13 + i3, keyInfo.getWidth(), true));
                    }
                } else {
                    list = spaceWidthList;
                    list2 = spaceList;
                    arrayList = arrayList2;
                    i4 = intValue;
                    i5 = i2;
                    i6 = align;
                }
                if (i12 == 0) {
                    depthByCode = keyInfo.getWidth();
                    depthByCode2 = keyInfo.getDepthByCode(depthList.get(i11), depthNameList.get(i11), toothCodeArray.get(i11).get(i12 + 1));
                } else if (i12 == toothCodeArray.get(i11).size() - 1) {
                    depthByCode = keyInfo.getDepthByCode(depthList.get(i11), depthNameList.get(i11), toothCodeArray.get(i11).get(i12 - 1));
                    depthByCode2 = 0;
                } else {
                    depthByCode = keyInfo.getDepthByCode(depthList.get(i11), depthNameList.get(i11), toothCodeArray.get(i11).get(i12 - 1));
                    depthByCode2 = keyInfo.getDepthByCode(depthList.get(i11), depthNameList.get(i11), toothCodeArray.get(i11).get(i12 + 1));
                }
                int fixSpaceWidthLeft = fixSpaceWidthLeft(keyInfo, i13, depthByCode3, depthByCode);
                int fixSpaceWidthRight = fixSpaceWidthRight(keyInfo, i5, depthByCode3, depthByCode2);
                if (i6 == 0 && fixSpaceWidthLeft > fixSpaceWidthRight) {
                    fixSpaceWidthLeft = i4;
                    fixSpaceWidthRight = fixSpaceWidthLeft;
                }
                int i14 = i6;
                if (i14 != 1 || fixSpaceWidthLeft >= fixSpaceWidthRight) {
                    i7 = fixSpaceWidthLeft;
                } else {
                    fixSpaceWidthRight = i4;
                    i7 = fixSpaceWidthRight;
                }
                arrayList3.add(new DestPoint(i7, depthByCode3, true));
                arrayList3.add(new DestPoint(fixSpaceWidthRight, depthByCode3, true));
                if (i12 == toothCodeArray.get(i11).size() - 1) {
                    int i15 = -ToolSizeManager.getCutterRadiusSize();
                    if (i14 == 0) {
                        int tip = keyAlignInfo.getTip();
                        if (ClampManager.getInstance().getCurrentClamp().getClampStr().contains("S1-A")) {
                            shoulder = keyAlignInfo.getShoulder1();
                        } else {
                            shoulder = keyAlignInfo.getShoulder();
                        }
                        i15 = ((int) (Math.abs(shoulder - tip) * MachineData.dYScale)) + ToolSizeManager.getCutterRadiusSize();
                    }
                    arrayList3.add(new DestPoint(i15, (keyInfo.getWidth() / 2) - 20, true));
                }
                i12++;
                spaceWidthList = list;
                spaceList = list2;
                arrayList2 = arrayList;
            }
            arrayList2.add(arrayList3);
            i11++;
            spaceWidthList = spaceWidthList;
        }
        return arrayList2;
    }

    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [int, boolean] */
    private static List<List<DestPoint>> getSingleInsideGrooveKeyPoint(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, String str) {
        int i;
        int i2;
        ArrayList arrayList;
        ArrayList arrayList2;
        int i3;
        int depthByCode;
        List<List<Integer>> list;
        int depthByCode2;
        int i4;
        ArrayList arrayList3;
        boolean r5;
        boolean z;
        boolean z2;
        List<List<String>> arrayList4 = new ArrayList<>();
        List<List<Integer>> depthList = keyInfo.getDepthList();
        List<List<String>> depthNameList = keyInfo.getDepthNameList();
        List<List<Integer>> spaceWidthList = keyInfo.getSpaceWidthList();
        List<List<Integer>> spaceList = keyInfo.getSpaceList();
        if (TextUtils.isEmpty(str)) {
            arrayList4 = keyInfo.getToothCodeArray();
        } else {
            for (String str2 : str.split(";")) {
                String[] split = str2.split(",");
                ArrayList arrayList5 = new ArrayList();
                for (String str3 : split) {
                    arrayList5.add(str3.trim());
                }
                arrayList4.add(arrayList5);
            }
        }
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        int i5 = 0;
        while (i5 < arrayList4.size()) {
            int i6 = 0;
            while (i6 < arrayList4.get(i5).size()) {
                int depthByCode3 = keyInfo.getDepthByCode(depthList.get(i5), depthNameList.get(i5), arrayList4.get(i5).get(i6));
                int intValue = spaceList.get(i5).get(i6).intValue();
                int intValue2 = spaceWidthList.get(i5).get(i6).intValue() / 2;
                int align = keyInfo.getAlign();
                if (align == 0) {
                    i = intValue - intValue2;
                    i2 = intValue2 + intValue;
                } else {
                    i = intValue + intValue2;
                    i2 = intValue - intValue2;
                }
                List<List<Integer>> list2 = spaceList;
                int i7 = i;
                List<List<Integer>> list3 = spaceWidthList;
                if (i6 == 0) {
                    i3 = intValue;
                    arrayList = arrayList6;
                    arrayList2 = arrayList7;
                    int i8 = i6 + 1;
                    int depthByCode4 = keyInfo.getDepthByCode(depthList.get(i5), depthNameList.get(i5), arrayList4.get(i5).get(i8));
                    list = depthList;
                    depthByCode2 = keyInfo.getDepthByCode(depthList.get(i5), depthNameList.get(i5), arrayList4.get(i5).get(i8));
                    depthByCode = depthByCode4;
                } else {
                    arrayList = arrayList6;
                    arrayList2 = arrayList7;
                    i3 = intValue;
                    if (i6 == arrayList4.get(i5).size() - 1) {
                        depthByCode = keyInfo.getDepthByCode(depthList.get(i5), depthNameList.get(i5), arrayList4.get(i5).get(i6 - 1));
                        list = depthList;
                        depthByCode2 = 0;
                    } else {
                        depthByCode = keyInfo.getDepthByCode(depthList.get(i5), depthNameList.get(i5), arrayList4.get(i5).get(i6 - 1));
                        list = depthList;
                        depthByCode2 = keyInfo.getDepthByCode(depthList.get(i5), depthNameList.get(i5), arrayList4.get(i5).get(i6 + 1));
                    }
                }
                int fixSpaceWidthLeft = fixSpaceWidthLeft(keyInfo, i7, depthByCode3, depthByCode);
                int fixSpaceWidthRight = fixSpaceWidthRight(keyInfo, i2, depthByCode3, depthByCode2);
                if (align == 0 && fixSpaceWidthLeft > fixSpaceWidthRight) {
                    fixSpaceWidthRight = i3;
                    fixSpaceWidthLeft = fixSpaceWidthRight;
                }
                if (align != 1 || fixSpaceWidthLeft >= fixSpaceWidthRight) {
                    i4 = fixSpaceWidthLeft;
                } else {
                    fixSpaceWidthRight = i3;
                    i4 = fixSpaceWidthRight;
                }
                if (i6 != 0 || keyInfo.getIcCard() == 1309) {
                    arrayList3 = arrayList2;
                    r5 = true;
                } else {
                    int innerCutX = keyInfo.getInnerCutX() + 100;
                    int innerCutY = keyInfo.getInnerCutY();
                    int i9 = innerCutX != 0 ? align == 0 ? i4 - innerCutX : innerCutX + i4 : i4;
                    if (innerCutY == -1 || innerCutY == 0) {
                        innerCutY = depthByCode3;
                    }
                    if (keyInfo.getSide() == 0 || keyInfo.getSide() == 5) {
                        arrayList3 = arrayList2;
                        r5 = true;
                        arrayList3.add(new DestPoint(i9, innerCutY, true));
                        arrayList8.add(new DestPoint(i9, calculateAnotherSideDepth(keyInfo, keyAlignInfo, innerCutY), true));
                    } else {
                        r5 = true;
                        arrayList8.add(new DestPoint(i9, innerCutY, true));
                        DestPoint destPoint = new DestPoint(i9, calculateAnotherSideDepth(keyInfo, keyAlignInfo, innerCutY), true);
                        ArrayList arrayList9 = arrayList2;
                        arrayList9.add(destPoint);
                        arrayList3 = arrayList9;
                    }
                }
                if (keyInfo.getSide() == 0 || keyInfo.getSide() == 5) {
                    arrayList3.add(new DestPoint(i4, depthByCode3, (boolean) r5));
                    arrayList3.add(new DestPoint(fixSpaceWidthRight, depthByCode3, (boolean) r5));
                    arrayList8.add(new DestPoint(i4, calculateAnotherSideDepth(keyInfo, keyAlignInfo, depthByCode3), (boolean) r5));
                    arrayList8.add(new DestPoint(fixSpaceWidthRight, calculateAnotherSideDepth(keyInfo, keyAlignInfo, depthByCode3), (boolean) r5));
                } else {
                    arrayList8.add(new DestPoint(i4, depthByCode3, (boolean) r5));
                    arrayList8.add(new DestPoint(fixSpaceWidthRight, depthByCode3, (boolean) r5));
                    arrayList3.add(new DestPoint(i4, calculateAnotherSideDepth(keyInfo, keyAlignInfo, depthByCode3), (boolean) r5));
                    arrayList3.add(new DestPoint(fixSpaceWidthRight, calculateAnotherSideDepth(keyInfo, keyAlignInfo, depthByCode3), (boolean) r5));
                }
                if (i6 == arrayList4.get(i5).size() - (r5 ? 1 : 0)) {
                    int abs = align == 0 ? (int) (Math.abs(keyAlignInfo.getShoulder() - keyAlignInfo.getTip()) * MachineData.dYScale) : 0;
                    int nose = keyInfo.getNose();
                    int guide = keyInfo.getGuide();
                    if (keyInfo.getIcCard() == 1421 || keyInfo.getIcCard() == 1424 || keyInfo.getIcCard() == 1435) {
                        guide = 100;
                    }
                    if (keyInfo.getSide() == 0 || keyInfo.getSide() == 5) {
                        if (keyInfo.getIcCard() == 1421 || keyInfo.getIcCard() == 1424 || keyInfo.getIcCard() == 1435) {
                            z = true;
                            arrayList3.add(new DestPoint(abs, guide, true));
                        } else {
                            z = true;
                            arrayList3.add(new DestPoint(abs, 0, true));
                        }
                        if (nose != 0) {
                            arrayList8.add(new DestPoint(getSpaceLeft(align, abs, nose - 10), -10, z));
                        } else if (guide != 0) {
                            arrayList8.add(new DestPoint(abs, guide, z));
                        } else {
                            arrayList8.add(new DestPoint(abs, 0, z));
                            i6++;
                            arrayList7 = arrayList3;
                            spaceWidthList = list3;
                            spaceList = list2;
                            arrayList6 = arrayList;
                            depthList = list;
                        }
                    } else {
                        if (keyInfo.getIcCard() == 1421 || keyInfo.getIcCard() == 1424 || keyInfo.getIcCard() == 1435) {
                            z2 = true;
                            arrayList8.add(new DestPoint(abs, guide, true));
                        } else {
                            z2 = true;
                            arrayList8.add(new DestPoint(abs, 0, true));
                        }
                        if (nose != 0) {
                            arrayList3.add(new DestPoint(getSpaceLeft(align, abs, nose - 10), -10, z2));
                        } else if (guide != 0) {
                            arrayList3.add(new DestPoint(abs, guide, z2));
                        } else {
                            arrayList3.add(new DestPoint(abs, 0, z2));
                        }
                    }
                }
                i6++;
                arrayList7 = arrayList3;
                spaceWidthList = list3;
                spaceList = list2;
                arrayList6 = arrayList;
                depthList = list;
            }
            i5++;
            spaceList = spaceList;
        }
        ArrayList arrayList10 = arrayList6;
        arrayList10.add(arrayList7);
        arrayList10.add(arrayList8);
        return arrayList10;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0192  */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r13v15 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static List<List<DestPoint>> getDoubleInsideGrooveKeyPoint(KeyInfo r22, KeyAlignInfo r23) {
        /*
            Method dump skipped, instructions count: 551
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.KeyBlankCutStepsGenerateUtil.getDoubleInsideGrooveKeyPoint(com.cutting.machine.bean.KeyInfo, com.cutting.machine.KeyAlignInfo):java.util.List");
    }

    private static int calculateAnotherSideDepth(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, int i) {
        int width;
        int groove;
        int decodeWidth = keyAlignInfo.getDecodeWidth();
        if (decodeWidth != 0) {
            width = decodeWidth - i;
            groove = keyInfo.getGroove();
        } else {
            width = keyInfo.getWidth() - i;
            groove = keyInfo.getGroove();
        }
        return width - groove;
    }

    public static ArrayList<Index> getSortSpace(List<List<Integer>> list, final int i) {
        ArrayList<Index> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < list.size(); i2++) {
            List<Integer> list2 = list.get(i2);
            for (int i3 = 0; i3 < list2.size(); i3++) {
                arrayList.add(new Index(list2.get(i3).intValue(), i2, i3));
            }
        }
        Collections.sort(arrayList, new Comparator<Index>() { // from class: com.cutting.machine.KeyBlankCutStepsGenerateUtil.1
            @Override // java.util.Comparator
            public int compare(Index index, Index index2) {
                if (i == 0) {
                    return index.getData() - index2.getData();
                }
                return index2.getData() - index.getData();
            }
        });
        return arrayList;
    }

    /* loaded from: classes2.dex */
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
}
