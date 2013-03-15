package com.gulf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AddSHA1 {

    public static String sha1(String input) {
        MessageDigest mDigest;
        StringBuffer sb = new StringBuffer();
        try {
            mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String SHA1(String inStr) {
        MessageDigest md = null;
        String outStr = null;
        try {
            md = MessageDigest.getInstance("MD5");     // 选择SHA-1，也可以选择MD5
            byte[] digest = md.digest(inStr.getBytes());       // 返回的是byet[]，要转化为String存储比较方便
            outStr = bytetoString(digest);
        }
        catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return outStr;
    }

    public static String bytetoString(byte[] digest) {
        String str = "";
        String tempStr = "";

        for (int i = 1; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            }
            else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();
    }
}
