package com.kkkcut.e20j.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/* loaded from: classes.dex */
public class SecurityUtil {
    public static void EncryptFile(File file, String str) throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file.getPath()));
        POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem(bufferedInputStream);
        Biff8EncryptionKey.setCurrentUserPassword(str);
        HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(pOIFSFileSystem, true);
        FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
        hSSFWorkbook.writeProtectWorkbook(Biff8EncryptionKey.getCurrentUserPassword(), "");
        hSSFWorkbook.write(fileOutputStream);
        bufferedInputStream.close();
        fileOutputStream.close();
    }
}
