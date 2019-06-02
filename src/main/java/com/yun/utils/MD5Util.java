package com.yun.utils;

/**
 * @version : V1.0
 * @ClassName: MD5Util
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/6/2 18:21
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class MD5Util {
    public static String getStringMD5(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return new String(encode(digest.digest(str.getBytes())));
        } catch (Exception e) {
            throw new RuntimeException("md5 digest fail:", e);
        }
    }

    public static String getFileMD5(File file) {
        FileInputStream in = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            if (!file.isFile()) {
                throw new RuntimeException("md5 digest fail: file not exists!");
            }
            byte buffer[] = new byte[1024];
            int len;
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            return new String(encode(digest.digest()));
        } catch (Exception e) {
            throw new RuntimeException("md5 digest fail:", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static char[] encode(byte[] bytes) {
        final int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];

        int j = 0;
        for (int i = 0; i < nBytes; i++) {
            // Char for top 4 bits
            result[j++] = HEX[(0xF0 & bytes[i]) >>> 4];
            // Bottom 4
            result[j++] = HEX[(0x0F & bytes[i])];
        }

        return result;
    }

}