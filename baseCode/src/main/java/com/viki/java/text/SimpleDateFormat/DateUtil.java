package com.viki.java.text.SimpleDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/6 11:09
 */

public class DateUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) throws ParseException {
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return sdf.parse(strDate);
    }
}