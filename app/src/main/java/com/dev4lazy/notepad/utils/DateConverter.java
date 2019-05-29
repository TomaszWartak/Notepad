package com.dev4lazy.notepad.utils;

import android.icu.text.SimpleDateFormat;

import androidx.room.TypeConverter;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public Date long2Date( Long timeStamp) {
        return new Date(timeStamp);
    }

    @TypeConverter
    public Long date2Long(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public String date2String( Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }

}