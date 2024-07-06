package com.kkkcut.e20j.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.SyncFailedException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class Object2FileUitl {
    private static String Algorithm = "DESede";
    private static String PASSWORD = "2A5DFG6QEWt9m6k2";

    public static boolean Object2File(Object obj, String str) {
        FileOutputStream fileOutputStream = null;
        SecretKeySpec secretKeySpec = new SecretKeySpec(PASSWORD.getBytes(), Algorithm);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(Algorithm);
            cipher.init(1, secretKeySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (NoSuchPaddingException e3) {
            e3.printStackTrace();
        }
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(cipherOutputStream);
                try {
                    objectOutputStream.writeObject(obj);
                    objectOutputStream.flush();
                    cipherOutputStream.flush();
                    fileOutputStream.flush();
                    fileOutputStream.getFD().sync();
                    objectOutputStream.close();
                    cipherOutputStream.close();
                    fileOutputStream.close();
                    return true;
                } catch (SyncFailedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
            }
        } finally {
        }
    }

    public static Object file2Object(String str) {
        ObjectInputStream objectInputStream;
        Exception e = null;
        Object readObject = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(PASSWORD.getBytes(), Algorithm);
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(2, secretKeySpec);
            objectInputStream = new ObjectInputStream(new CipherInputStream(new BufferedInputStream(new FileInputStream(str)), cipher));
            try {
                readObject = objectInputStream.readObject();
                objectInputStream.close();
                return readObject;
            } catch (Exception ex) {
                e = ex;
                e.printStackTrace();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Exception e3) {
            e = e3;
            objectInputStream = null;
        }
        return readObject;
    }
}
