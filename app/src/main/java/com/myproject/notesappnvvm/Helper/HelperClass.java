package com.myproject.notesappnvvm.Helper;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HelperClass {

    public static Date StringToDate(String date) {
        try {
            return new SimpleDateFormat("MMM dd yyyy").parse(date);
        } catch (Exception e) {
            Log.e("Exception", "StringToDate: " + e);
        }
        return null;
    }

    public static String DateToString(Date date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
            return dateFormat.format(date);
        } catch (Exception e) {
            Log.e("Exception", "DateToString" + e);
        }
        return null;
    }

    public static String DaysDifference(Date currentDate, Date dueDate) {
        try {

            long date1 = currentDate.getTime();
            long date2 = dueDate.getTime();
            long difference = Math.abs(date1 - date2);
            long differenceDate = difference / (24 * 60 * 60 * 1000);
            return Long.toString(differenceDate);
        } catch (
                Exception exception) {
            Log.e("Error", "instance initializer: " + exception);
        }
        return null;
    }


    public static void initDatePicker(Context context, DatePickerDialog datePickerDialog, Button datePickerButton) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                datePickerButton.setText(getTodayDate());
                datePickerButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(context, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 10000);
    }

    public static String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        month = month + 1;
        return makeDateString(day, month, year);
    }

    public static String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    public static String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        return "DEC";
    }
}
