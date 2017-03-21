package dev.ongteu.bkmusic.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Created by TienGiang on 18/3/2017.
 */

public class Common {
    public static String cutterLongName(String strOrigin){
        return (strOrigin.length() < Constant.MAX_LENGTH_NAME_TITLE) ? strOrigin : strOrigin.substring(0, Math.min(strOrigin.length(), Constant.MAX_LENGTH_NAME_TITLE)) + "...";
    }

    public static int safeLongToInt(long l) {
        return (int) Math.max(Math.min(Integer.MAX_VALUE, l), Integer.MIN_VALUE);
    }

    public static String replaceSpaceToUnderscore(String strOrigin){
        return strOrigin.replaceAll(" ", "_").toLowerCase();
    }

    public static String getDirectoryOfFile(String strOrigin, String fileName){
        return strOrigin.replace(fileName, "");
    }

    /**
     * MD5 Generate
     *
     * @param strOrigin
     * @return
     */
    public static String generateImageName(String strOrigin){
        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception while encrypting to md5");
            e.printStackTrace();
        } // Encryption algorithm
        mdEnc.update(strOrigin.getBytes(), 0, strOrigin.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
        while ( md5.length() < 32 ) {
            md5 = "0"+md5;
        }
        return md5;
    }

}
