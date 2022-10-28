package com.api.wishList.Utill;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/** 
 * 단방향 암호화
 * */
public class Encryption {

    /**
     * sha256 암호화
     * */
    public static String Sha256( String text ) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    /**
     * 바이트 데이터를 16진수로 변환
     * */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
