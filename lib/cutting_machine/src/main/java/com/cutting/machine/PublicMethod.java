package com.liying.core;

import com.liying.core.speed.SpeedXYZ;

/* loaded from: classes2.dex */
class PublicMethod {
    PublicMethod() {
    }

    public static String GetXYZSpeedProportion(SpeedXYZ speedXYZ) {
        return speedXYZ.get_SpeedX() + "," + speedXYZ.get_SpeedY() + "," + speedXYZ.get_SpeedZ();
    }

    public static SpeedXYZ GetXYZSpeedProportion(SpeedXYZ speedXYZ, int i) {
        switch (i) {
            case 1:
                return new SpeedXYZ((int) (speedXYZ.get_SpeedX() * 0.8d), (int) (speedXYZ.get_SpeedY() * 0.8d), (int) (speedXYZ.get_SpeedZ() * 0.8d));
            case 2:
                return new SpeedXYZ((int) (speedXYZ.get_SpeedX() * 0.9d), (int) (speedXYZ.get_SpeedY() * 0.9d), (int) (speedXYZ.get_SpeedZ() * 0.9d));
            case 3:
                return new SpeedXYZ(speedXYZ.get_SpeedX(), speedXYZ.get_SpeedY(), speedXYZ.get_SpeedZ());
            case 4:
                return new SpeedXYZ((int) (speedXYZ.get_SpeedX() * 1.1d), (int) (speedXYZ.get_SpeedY() * 1.1d), (int) (speedXYZ.get_SpeedZ() * 1.1d));
            case 5:
                return new SpeedXYZ((int) (speedXYZ.get_SpeedX() * 1.2d), (int) (speedXYZ.get_SpeedY() * 1.2d), (int) (speedXYZ.get_SpeedZ() * 1.2d));
            case 6:
                return new SpeedXYZ((int) (speedXYZ.get_SpeedX() * 1.3d), (int) (speedXYZ.get_SpeedY() * 1.3d), (int) (speedXYZ.get_SpeedZ() * 1.3d));
            case 7:
                return new SpeedXYZ((int) (speedXYZ.get_SpeedX() * 1.4d), (int) (speedXYZ.get_SpeedY() * 1.4d), (int) (speedXYZ.get_SpeedZ() * 1.4d));
            case 8:
                return new SpeedXYZ((int) (speedXYZ.get_SpeedX() * 1.5d), (int) (speedXYZ.get_SpeedY() * 1.5d), (int) (speedXYZ.get_SpeedZ() * 1.5d));
            default:
                return new SpeedXYZ(speedXYZ.get_SpeedX(), speedXYZ.get_SpeedY(), speedXYZ.get_SpeedZ());
        }
    }
}
