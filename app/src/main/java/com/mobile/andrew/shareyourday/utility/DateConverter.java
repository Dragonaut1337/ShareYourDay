package com.mobile.andrew.shareyourday.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andre on 07/03/2016.
 */
public class DateConverter {

    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public static Date convertStringToDate(String dateString){

        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String convertDateToString(Date date){
        return format.format(date);
    }
}
