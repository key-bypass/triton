package com.kkkcut.e20j;

import java.io.File;

/* loaded from: classes.dex */
public class Constant {
    public static final String CODE_DATABASE_PATH;
    public static final String CODE_DB_ZIP_PATH;
    public static final String CONFIG_PATH = MyApplication.getInstance().getFilesDir() + "/configuration.sec";
    public static final String CONFIG_UPDATA = "config_version.json";
    public static final String DATABASE_PATH;
    public static final String DATABASE_ZIP_PATH;
    public static final String FILE_PATH;
    public static final String JSON_CODE_DB_UPDATE = "code_db_version.json";
    public static final String JSON_MAIN_DB_UPDATE = "db_version.json";
    public static final boolean OFFLINE = false;
    public static final String ZIP_NAME_CODE_DB = "code.zip";
    public static final String ZIP_NAME_MAIN_DB = "database.zip";
    public static final float dXScale = 0.25f;
    public static final float dYScale = 0.25f;
    public static final float dZScale = 0.25f;

    static {
        String absolutePath = MyApplication.getInstance().getFilesDir().getAbsolutePath();
        FILE_PATH = absolutePath;
        DATABASE_PATH = "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/";
        CODE_DATABASE_PATH = "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/code/";
        DATABASE_ZIP_PATH = new File(absolutePath, ZIP_NAME_MAIN_DB).getPath();
        CODE_DB_ZIP_PATH = new File(absolutePath, ZIP_NAME_CODE_DB).getPath();
    }
}
