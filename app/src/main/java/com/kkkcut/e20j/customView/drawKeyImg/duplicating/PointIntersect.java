package com.kkkcut.e20j.customView.drawKeyImg.duplicating;

/* loaded from: classes.dex */
public class PointIntersect {

    /* loaded from: classes.dex */
    public static class Point {
        private double x;
        private double y;

        public Point(double d, double d2) {
            this.x = d;
            this.y = d2;
        }

        public double getX() {
            return this.x;
        }

        public void setX(double d) {
            this.x = d;
        }

        public double getY() {
            return this.y;
        }

        public void setY(double d) {
            this.y = d;
        }

        public String toString() {
            return "Point{x=" + this.x + ", y=" + this.y + '}';
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
