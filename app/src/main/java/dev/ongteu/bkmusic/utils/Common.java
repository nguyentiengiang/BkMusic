package dev.ongteu.bkmusic.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.text.DecimalFormat;

/**
 * Created by TienGiang on 18/3/2017.
 */

public class Common {
    /**
     * Cut long song name
     *
     * @param strOrigin
     * @param limit
     * @return
     */
    public static String cutterLongName(String strOrigin, int limit) {
        return (strOrigin.length() < Constant.MAX_LENGTH_NAME_TITLE) ? strOrigin : strOrigin.substring(0, Math.min(strOrigin.length(), limit)) + "...";
    }

    /**
     * Covert Long back to Integer
     *
     * @param l
     * @return
     */
    public static int safeLongToInt(long l) {
        return (int) Math.max(Math.min(Integer.MAX_VALUE, l), Integer.MIN_VALUE);
    }

    /**
     * Change space character to underscore character
     *
     * @param strOrigin
     * @return
     */
    public static String replaceSpaceToUnderscore(String strOrigin) {
        return strOrigin.replaceAll(" ", "_").toLowerCase();
    }

    /**
     * Get dir of file
     *
     * @param strOrigin
     * @param fileName
     * @return
     */
    public static String getDirectoryOfFile(String strOrigin, String fileName) {
        return strOrigin.replace(fileName, "");
    }

    /**
     * Function to convert milliseconds time to
     * Timer Format
     * Hours:Minutes:Seconds
     */
    public static String milliSecondsToTimer(double milliseconds) {
        String finalTimerString = "";
        String secondsString;

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }
        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        finalTimerString = finalTimerString + minutes + ":" + secondsString;
        // return timer string
        return finalTimerString;
    }

    /**
     * Function to get Progress percentage
     *
     * @param currentDuration
     * @param totalDuration
     */
    public static int getProgressPercentage(double currentDuration, double totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }

    /**
     * Function to change progress to timer
     *
     * @param progress      -
     * @param totalDuration returns current duration in milliseconds
     */
    public static int progressToTimer(int progress, double totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double) progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }

    /**
     * MD5 Generate
     *
     * @param strOrigin
     * @return
     */
    public static String generateImageName(String strOrigin) {
        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception while encrypting to md5");
            e.printStackTrace();
        } // Encryption algorithm
        mdEnc.update(strOrigin.getBytes(), 0, strOrigin.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
        while (md5.length() < 32) {
            md5 = "0" + md5;
        }
        return md5;
    }

}
