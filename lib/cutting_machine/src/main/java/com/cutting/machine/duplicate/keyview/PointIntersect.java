package com.liying.core.duplicate.keyview;

/* loaded from: classes2.dex */
public class PointIntersect {

    /* loaded from: classes2.dex */
    public static class Point {

        /* renamed from: x */
        private double f419x;

        /* renamed from: y */
        private double f420y;

        public Point(double d, double d2) {
            this.f419x = d;
            this.f420y = d2;
        }

        public double getX() {
            return this.f419x;
        }

        public void setX(double d) {
            this.f419x = d;
        }

        public double getY() {
            return this.f420y;
        }

        public void setY(double d) {
            this.f420y = d;
        }

        public String toString() {
            return "Point{x=" + this.f419x + ", y=" + this.f420y + '}';
        }
    }

    public static Point getIntersectPoint(Point point, Point point2, Point point3, Point point4) {
        double y = point.getY() - point2.getY();
        double x = point2.getX() - point.getX();
        double x2 = (point.getX() * y) + (point.getY() * x);
        double y2 = point3.getY() - point4.getY();
        double x3 = point4.getX() - point3.getX();
        double x4 = (point3.getX() * y2) + (point3.getY() * x3);
        double d = (y * x3) - (y2 * x);
        if (Math.abs(d) < 1.0E-5d) {
            return null;
        }
        return new Point(((x3 / d) * x2) + (((x * (-1.0d)) / d) * x4), (((y2 * (-1.0d)) / d) * x2) + ((y / d) * x4));
    }
}
