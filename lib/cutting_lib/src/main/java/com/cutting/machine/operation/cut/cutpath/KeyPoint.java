package com.cutting.machine.operation.cut.cutpath;

/* loaded from: classes2.dex */
public class KeyPoint implements Cloneable {
    private float angle;
    private int dimplePoreRadius;
    private boolean doNotFixSpaceWidth;
    private boolean doNotSplitDepth;
    private boolean isTooth;

    /* renamed from: x */
    private int f426x;

    /* renamed from: y */
    private int f427y;

    /* renamed from: z */
    private int f428z;

    public KeyPoint() {
    }

    public KeyPoint(int i, int i2) {
        this.f426x = i;
        this.f427y = i2;
    }

    public KeyPoint(int i, int i2, int i3) {
        this.f426x = i;
        this.f427y = i2;
        this.f428z = i3;
    }

    public KeyPoint(int i, int i2, int i3, boolean z) {
        this.f426x = i;
        this.f427y = i2;
        this.f428z = i3;
        this.isTooth = z;
    }

    public int getX() {
        return this.f426x;
    }

    public void setX(int i) {
        this.f426x = i;
    }

    public int getY() {
        return this.f427y;
    }

    public void setY(int i) {
        this.f427y = i;
    }

    public int getZ() {
        return this.f428z;
    }

    public void setZ(int i) {
        this.f428z = i;
    }

    public boolean isTooth() {
        return this.isTooth;
    }

    public boolean isDoNotSplitDepth() {
        return this.doNotSplitDepth;
    }

    public boolean isDoNotFixSpaceWidth() {
        return this.doNotFixSpaceWidth;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float f) {
        this.angle = f;
    }

    public int getDimplePoreRadius() {
        return this.dimplePoreRadius;
    }

    public void setDimplePoreRadius(int i) {
        this.dimplePoreRadius = i;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public KeyPoint m603clone() throws CloneNotSupportedException {
        return (KeyPoint) super.clone();
    }

    public String toString() {
        return "KeyPoint{x=" + this.f426x + ", y=" + this.f427y + ", z=" + this.f428z + '}';
    }

    /* loaded from: classes2.dex */
    public static final class KeyPointBuilder {
        private boolean doNotFixSpaceWidth;
        private boolean doNotSplitDepth;
        private boolean isTooth;

        /* renamed from: x */
        private int f429x;

        /* renamed from: y */
        private int f430y;

        /* renamed from: z */
        private int f431z;

        private KeyPointBuilder() {
        }

        public static KeyPointBuilder aKeyPoint() {
            return new KeyPointBuilder();
        }

        public KeyPointBuilder withX(int i) {
            this.f429x = i;
            return this;
        }

        public KeyPointBuilder withY(int i) {
            this.f430y = i;
            return this;
        }

        public KeyPointBuilder withZ(int i) {
            this.f431z = i;
            return this;
        }

        public KeyPointBuilder withIsTooth(boolean z) {
            this.isTooth = z;
            return this;
        }

        public KeyPointBuilder withDoNotSplitDepth(boolean z) {
            this.doNotSplitDepth = z;
            return this;
        }

        public KeyPointBuilder withDoNotFixSpaceWidth(boolean z) {
            this.doNotFixSpaceWidth = z;
            return this;
        }

        public KeyPoint build() {
            KeyPoint keyPoint = new KeyPoint(this.f429x, this.f430y, this.f431z, this.isTooth);
            keyPoint.doNotSplitDepth = this.doNotSplitDepth;
            keyPoint.doNotFixSpaceWidth = this.doNotFixSpaceWidth;
            return keyPoint;
        }
    }
}
