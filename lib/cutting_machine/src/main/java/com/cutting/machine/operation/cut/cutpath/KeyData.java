package com.liying.core.operation.cut.cutpath;

import android.text.TextUtils;
import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.KeyInfo;
import com.liying.core.bean.KeyType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class KeyData {
    public static final int ANGLE_KEY = 7;
    public static final int DIMPLE_KEY = 6;
    public static final int DOUBLE_INSIDE_GROOVE_KEY = 2;
    public static final int DOUBLE_OUTSIDE_GROOVE_KEY = 4;
    public static final int DOUBLE_SIDE_KEY = 0;
    public static final int SIDE_TOOTH_KEY = 9;
    public static final int SINGLE_INSIDE_GROOVE_KEY = 5;
    public static final int SINGLE_OUTSIDE_GROOVE_KEY = 3;
    public static final int SINGLE_SIDE_KEY = 1;
    public static final int TUBULAR_KEY = 8;
    private KeyAlign align;
    private String cutSharpenType;
    private KeyInfo keyInfo;
    private KeyType keyType;
    private List<List<Integer>> realDepthList;
    private List<List<Integer>> spaceList;
    private List<List<Integer>> spaceWidthList;

    public KeyData(KeyInfo keyInfo) {
        this(keyInfo, null);
    }

    public KeyData(KeyInfo keyInfo, String str) {
        this.keyInfo = keyInfo;
        this.keyType = type(keyInfo);
        this.align = keyInfo.getAlign() == 0 ? KeyAlign.SHOULDERS_ALIGN : KeyAlign.FRONTEND_ALIGN;
        this.spaceList = initSpaceList(keyInfo.getSpaceStr());
        this.spaceWidthList = changeStr2ListInt(keyInfo.getSpaceWidthStr());
        this.realDepthList = keyInfo.toothCode2RealDepthList(str);
        if (this.keyType == KeyType.DOUBLE_SIDE_KEY && this.spaceList.size() == 1) {
            fillKeyData(this.spaceList);
            fillKeyData(this.spaceWidthList);
            fillKeyData(this.realDepthList);
        }
        this.cutSharpenType = keyInfo.getCutSharpenType();
    }

    public KeyType type(KeyInfo keyInfo) {
        return keyInfo.type(keyInfo.getType());
    }

    public KeyInfo getKeyInfo() {
        return this.keyInfo;
    }

    public List<List<Integer>> initSpaceList(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(";");
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            String[] split2 = str2.split(",");
            ArrayList arrayList2 = new ArrayList();
            for (String str3 : split2) {
                int parseInt = Integer.parseInt(str3.trim());
                if (getKeyType() != KeyType.TUBULAR_KEY && getAlign() == KeyAlign.FRONTEND_ALIGN) {
                    parseInt = -parseInt;
                }
                arrayList2.add(Integer.valueOf(parseInt));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private void fillKeyData(List<List<Integer>> list) {
        List<Integer> list2 = list.get(0);
        ArrayList arrayList = new ArrayList(list2.size());
        Collections.addAll(arrayList, new Integer[list2.size()]);
        Collections.copy(arrayList, list2);
        list.add(arrayList);
    }

    public List<List<Integer>> changeStr2ListInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(";");
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            String[] split2 = str2.split(",");
            ArrayList arrayList2 = new ArrayList();
            for (String str3 : split2) {
                arrayList2.add(Integer.valueOf(Integer.parseInt(str3.trim())));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public List<List<Integer>> getSpaceList() {
        return this.spaceList;
    }

    public List<List<Integer>> getSpaceWidthList() {
        return this.spaceWidthList;
    }

    public List<List<Integer>> getRealDepthList() {
        return this.realDepthList;
    }

    public KeyType getKeyType() {
        return this.keyType;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    public KeyAlign getAlign() {
        return this.align;
    }

    public int getWidth() {
        return getKeyInfo().getWidth();
    }

    public int getCutDepth() {
        return getKeyInfo().getCutDepth();
    }

    public String getCutSharpenType() {
        return this.cutSharpenType;
    }

    public int getThick() {
        return this.keyInfo.getThick();
    }

    public String getExCut() {
        return this.keyInfo.getExCut();
    }

    public String getLstPosition() {
        return this.keyInfo.getLastPosition();
    }

    public int getNose() {
        return this.keyInfo.getNose();
    }

    public int getGuide() {
        return this.keyInfo.getGuide();
    }

    public int getKeyId() {
        return this.keyInfo.getIcCard();
    }

    public int getSide() {
        return this.keyInfo.getSide();
    }

    public int getGroove() {
        return this.keyInfo.getGroove();
    }

    public int getInnerCutX() {
        return this.keyInfo.getInnerCutX();
    }

    public int getInnerCutY() {
        return this.keyInfo.getInnerCutY();
    }

    public int getRemainingDepth() {
        return this.keyInfo.getRemainingDepth();
    }

    public String getExtTopCut() {
        return this.keyInfo.getExtTopCut();
    }

    public int[] getRowPositionArr() {
        int[] iArr = new int[0];
        String row_pos = this.keyInfo.getRow_pos();
        if (TextUtils.isEmpty(row_pos)) {
            return iArr;
        }
        String[] split = row_pos.split(";");
        int[] iArr2 = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            iArr2[i] = Integer.parseInt(split[i]);
        }
        return iArr2;
    }

    public boolean PeugeoKey() {
        return getKeyType() == KeyType.DOUBLE_INSIDE_GROOVE_KEY && getGroove() > 0 && !TextUtils.isEmpty(getKeyInfo().getSpacenew()) && !TextUtils.isEmpty(getKeyInfo().getSpacewidthnew());
    }
}
