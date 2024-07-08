package com.kkkcut.e20j.ui.fragment.engraving;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;

import androidx.core.view.ViewCompat;

import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.speed.Speed;
import com.kkkcut.e20j.utils.BitmapUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EngravePathGen {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v6 */
    public static List<StepBean> bitmapToPath(Bitmap bitmap, EngraveParam engraveParam) {
        int i;
        int i2;
        int i3;
        ArrayList arrayList = new ArrayList();
        Bitmap binaryBitmap = binaryBitmap(bitmap);
        BitmapUtil.saveBitmap(binaryBitmap, new File(Environment.getExternalStorageDirectory(), "test.bmp").getPath());
        int width = binaryBitmap.getWidth();
        int height = binaryBitmap.getHeight();
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = width - 1;
            if (i5 >= i6) {
                StepBean stepBean = (StepBean) arrayList.get(i4);
                arrayList.add(0, new StepBean("", 0, stepBean.getX(), stepBean.getY(), stepBean.getZ() - 100, "", "", false));
                arrayList.add(1, new StepBean(0, stepBean.getX(), stepBean.getY(), stepBean.getZ() - 100, "", "C:5,X;C:5,Y;C:5,Z;SUP:1,8000", false));
                StepBean stepBean2 = (StepBean) arrayList.get(arrayList.size() - 1);
                arrayList.add(new StepBean(0, stepBean2.getX(), stepBean2.getY(), stepBean2.getZ(), "", "C:5,X;C:5,Y;C:5,Z;SUP:0,0", false));
                arrayList.add(new StepBean(998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
                arrayList.add(new StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
                return arrayList;
            }
            int i7 = 0;
            boolean z = i4 != 0;
            while (true) {
                int i8 = height - 1;
                if (i7 < i8) {
                    if (binaryBitmap.getPixel(i5, i7) == -1) {
                        addCutPoint(arrayList, i5, i7, engraveParam, z);
                        addCutPoint(arrayList, i5, i7, engraveParam, true);
                        int i9 = ViewCompat.MEASURED_STATE_MASK;
                        binaryBitmap.setPixel(i5, i7, ViewCompat.MEASURED_STATE_MASK);
                        int i10 = i5;
                        int i11 = i7;
                        while (true) {
                            if (i10 > 0) {
                                i = i10 - 1;
                                if (binaryBitmap.getPixel(i, i11) == -1) {
                                    addCutPoint(arrayList, i, i11, engraveParam, true);
                                    binaryBitmap.setPixel(i, i11, i9);
                                    i10 = i;
                                }
                            }
                            if (i10 > 0 && i11 < i8) {
                                i = i10 - 1;
                                i2 = i11 + 1;
                                if (binaryBitmap.getPixel(i, i2) == -1) {
                                    addCutPoint(arrayList, i, i2, engraveParam, true);
                                    i9 = ViewCompat.MEASURED_STATE_MASK;
                                    binaryBitmap.setPixel(i, i2, ViewCompat.MEASURED_STATE_MASK);
                                    i11 = i2;
                                    i10 = i;
                                } else {
                                    i9 = ViewCompat.MEASURED_STATE_MASK;
                                }
                            }
                            if (i11 < i8) {
                                i3 = i11 + 1;
                                if (binaryBitmap.getPixel(i10, i3) == -1) {
                                    addCutPoint(arrayList, i10, i3, engraveParam, true);
                                    binaryBitmap.setPixel(i10, i3, i9);
                                    i11 = i3;
                                }
                            }
                            if (i10 < i6 && i11 < i8) {
                                i = i10 + 1;
                                i2 = i11 + 1;
                                if (binaryBitmap.getPixel(i, i2) == -1) {
                                    addCutPoint(arrayList, i, i2, engraveParam, true);
                                    i9 = ViewCompat.MEASURED_STATE_MASK;
                                    binaryBitmap.setPixel(i, i2, ViewCompat.MEASURED_STATE_MASK);
                                    i11 = i2;
                                    i10 = i;
                                }
                            }
                            i9 = ViewCompat.MEASURED_STATE_MASK;
                            if (i10 < i6) {
                                i = i10 + 1;
                                if (binaryBitmap.getPixel(i, i11) == -1) {
                                    addCutPoint(arrayList, i, i11, engraveParam, true);
                                    binaryBitmap.setPixel(i, i11, ViewCompat.MEASURED_STATE_MASK);
                                    i10 = i;
                                }
                            }
                            if (i10 < i6 && i11 > 0) {
                                i = i10 + 1;
                                i2 = i11 - 1;
                                if (binaryBitmap.getPixel(i, i2) == -1) {
                                    addCutPoint(arrayList, i, i2, engraveParam, true);
                                    i9 = ViewCompat.MEASURED_STATE_MASK;
                                    binaryBitmap.setPixel(i, i2, ViewCompat.MEASURED_STATE_MASK);
                                    i11 = i2;
                                    i10 = i;
                                }
                            }
                            i9 = ViewCompat.MEASURED_STATE_MASK;
                            if (i11 > 0) {
                                i3 = i11 - 1;
                                if (binaryBitmap.getPixel(i10, i3) == -1) {
                                    addCutPoint(arrayList, i10, i3, engraveParam, true);
                                    binaryBitmap.setPixel(i10, i3, ViewCompat.MEASURED_STATE_MASK);
                                    i11 = i3;
                                }
                            }
                            if (i10 <= 0 || i11 <= 0) {
                                break;
                            }
                            i = i10 - 1;
                            i2 = i11 - 1;
                            if (binaryBitmap.getPixel(i, i2) != -1) {
                                break;
                            }
                            addCutPoint(arrayList, i, i2, engraveParam, true);
                            i9 = ViewCompat.MEASURED_STATE_MASK;
                            binaryBitmap.setPixel(i, i2, ViewCompat.MEASURED_STATE_MASK);
                            i11 = i2;
                            i10 = i;
                        }
                        z = false;
                        addCutPoint(arrayList, i10, i11, engraveParam, false);
                    }
                    i7++;
                    z = z;
                }
                break;
            }
            i5++;
            i4 = z ? 1 : 0;
        }
    }

    public static List<StepBean> detectKeyPosition(EngraveParam engraveParam) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StepBean("移动到夹具上方", 0, (int) (engraveParam.getClampOriginX() - (450.0f / MachineData.dYScale)), (int) (engraveParam.getClampOriginY() - (360.0f / MachineData.dYScale)), 0, "8000,8000,3000", "C:5,X;C:5,Y;C:5,Z", false));
        arrayList.add(new StepBean("探测夹具表面", 1, 0, 0, 3500, "4000,4000,3000", "S:1,Z;"));
        arrayList.add(new StepBean("离开夹具表面", 0, 0, 0, -50, "8000,8000,3000", "U:Z;"));
        arrayList.add(new StepBean("探测右夹块位置", 1, -2500, 0, 0, "4000,4000,3000", "U:X;"));
        arrayList.add(new StepBean("离开右夹块", 0, 50, 0, 0, "8000,8000,3000", "S:4,X;C:3,R,X-4"));
        arrayList.add(new StepBean("Z轴复位", 998, 0, 0, 0, "8000,8000,3000", ""));
        arrayList.add(new StepBean("XY轴复位", 999, 0, 0, 0, "8000,8000,3000", ""));
        return arrayList;
    }

    public static List<StepBean> detectCutterHigh(Bitmap bitmap, EngraveParam engraveParam) {
        ArrayList arrayList = new ArrayList();
        int width = (bitmap.getWidth() / 2) * 10;
        int height = (bitmap.getHeight() / 2) * 10;
        int clampOriginX = engraveParam.getClampOriginX();
        int clampOriginY = engraveParam.getClampOriginY();
        float f = clampOriginX;
        float dcX = engraveParam.getDcX();
        float f2 = clampOriginY;
        float dcY = engraveParam.getDcY();
        arrayList.add(new StepBean("移动到夹具上方", 0, (int) ((f - (300.0f / MachineData.dXScale)) + dcX), (int) ((f2 - (450.0f / MachineData.dYScale)) + dcY), 0, "8000,8000,3000", "C:5,X;C:5,Y;", false));
        arrayList.add(new StepBean("探测夹具上表面", 1, 0, 0, 3500, "4000,4000,3000", "U:Z"));
        arrayList.add(new StepBean("离开夹具上表面", 0, 0, 0, -3500, "8000,8000,3000", "S:2,Z;C:3,Z,Z-2"));
        arrayList.add(new StepBean("移动到切割区域中点", 0, (int) ((f - ((width + engraveParam.getPictureOriginX()) / MachineData.dXScale)) + dcX), (int) (f2 + ((height + engraveParam.getPictureOriginY()) / MachineData.dYScale) + dcY), 0, "8000,8000,3000", "C:5,X;C:5,Y;", false));
        arrayList.add(new StepBean("探测钥匙表面", 1, 0, 0, 180, "4000,4000,3000", "SL:Z,Z;"));
        arrayList.add(new StepBean("离开钥匙表面", 0, 0, 0, -100, "8000,8000,3000", "S:5,Z;C:3,K,Z-5"));
        return arrayList;
    }

    private static Bitmap binaryBitmap(Bitmap bitmap) {
        bitmap.setPremultiplied(false);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                int pixel = bitmap.getPixel(i, i2);
                bitmap.setPixel(i, i2, ((((Color.red(pixel) * 30) + (Color.green(pixel) * 59)) + (Color.blue(pixel) * 11)) + 50) / 100 > 220 ? ViewCompat.MEASURED_STATE_MASK : -1);
            }
        }
        return bitmap;
    }

    private static void addCutPoint(List<StepBean> list, int i, int i2, EngraveParam engraveParam, boolean z) {
        StepBean stepBean;
        int clampOriginX = engraveParam.getClampOriginX();
        int clampOriginY = engraveParam.getClampOriginY();
        int dcX = engraveParam.getDcX();
        int dcY = engraveParam.getDcY();
        int pictureOriginX = (int) ((clampOriginX - (((i * 10) + engraveParam.getPictureOriginX()) / MachineData.dXScale)) + dcX);
        int pictureOriginY = (int) (clampOriginY + (((i2 * 10) + engraveParam.getPictureOriginY()) / MachineData.dYScale) + dcY);
        int keyFaceZ = engraveParam.getKeyFaceZ();
        if (z) {
            stepBean = new StepBean("切割", 0, pictureOriginX, pictureOriginY, keyFaceZ + engraveParam.getCutDepth(), Speed.get_Speed_Engrave(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false);
        } else {
            stepBean = new StepBean("空走", 0, pictureOriginX, pictureOriginY, (int) (keyFaceZ - (20.0f / MachineData.dZScale)), Speed.get_Speed_Engrave(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false);
        }
        list.add(stepBean);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class EngraveParam {
        int clampOriginX;
        int clampOriginY;
        int cutDepth;
        int cutSpeed;
        int dcX;
        int dcY;
        int keyFaceZ;
        int pictureOriginX;
        int pictureOriginY;

        public EngraveParam(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.clampOriginX = i;
            this.clampOriginY = i2;
            this.pictureOriginX = i3;
            this.pictureOriginY = i4;
            this.dcX = i5;
            this.dcY = i6;
            this.keyFaceZ = i7;
        }

        public EngraveParam(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.clampOriginX = i;
            this.clampOriginY = i2;
            this.pictureOriginX = i3;
            this.pictureOriginY = i4;
            this.dcX = i5;
            this.dcY = i6;
            this.keyFaceZ = i7;
            this.cutDepth = i8;
        }

        public EngraveParam(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.clampOriginX = i;
            this.clampOriginY = i2;
            this.pictureOriginX = i3;
            this.pictureOriginY = i4;
            this.dcX = i5;
            this.dcY = i6;
            this.keyFaceZ = i7;
            this.cutDepth = i8;
            this.cutSpeed = i9;
        }

        public int getClampOriginX() {
            return this.clampOriginX;
        }

        public int getClampOriginY() {
            return this.clampOriginY;
        }

        public int getPictureOriginX() {
            return this.pictureOriginX;
        }

        public int getPictureOriginY() {
            return this.pictureOriginY;
        }

        public int getDcX() {
            return this.dcX;
        }

        public int getDcY() {
            return this.dcY;
        }

        public int getKeyFaceZ() {
            return this.keyFaceZ;
        }

        public int getCutDepth() {
            return this.cutDepth;
        }

        public int getCutSpeed() {
            return this.cutSpeed;
        }
    }
}
