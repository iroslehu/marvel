package com.albo.marvel.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {
    public static String hash(String publicApiKey, String privateApiKey) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            String timeStamp =  String.valueOf(System.currentTimeMillis());
            String toHash =   timeStamp + privateApiKey + publicApiKey;
            String hash = new BigInteger(1, md.digest(toHash.getBytes())).toString(16);

            String result = "ts=" + timeStamp + "&apikey=" + publicApiKey + "&hash=" + hash;
            return result;
        } catch (NoSuchAlgorithmException e) {
        }

        return null;
    }
}
