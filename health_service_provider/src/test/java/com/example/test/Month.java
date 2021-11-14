package com.example.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class Month {

    public static void main(String[] args) {
        //date1();
        date2();

    }

    private static void date1() {
        String date="2021-11";
        String[] dates = date.split("-");
        for (String s : dates) {
            System.out.println(s);
        }
        int year=Integer.parseInt(dates[0]);
        int month=Integer.parseInt(dates[1]);
        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2021);
//        cal.set(Calendar.MONTH, 2 - 1);//Java月份才0开始算
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);//Java月份才0开始算
        int dateOfMonth = cal.getActualMaximum(Calendar.DATE);

        String begin = date + "-1";//2021-11-1
        String end = date + "-" + dateOfMonth;//2021-11-30

        System.out.println(dateOfMonth);
        System.out.println(begin);
        System.out.println(end);
    }

    public static void date2() {
        String month = "2021-05";
        String[] dates = month.split("-");
        System.out.println(dates.length);
        if (dates.length > 0) {
            for (String date : dates) {
                System.out.println(date);
            }
            int year1 = Integer.parseInt(dates[0]);
            int month1 = Integer.parseInt(dates[1]);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year1);
            cal.set(Calendar.MONTH, month1 - 1);//Java月份才0开始算
            int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
            String date = month + "-" + dateOfMonth;//2021-05-31
            System.out.println(date);
        }
    }
}
