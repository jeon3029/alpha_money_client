package com.hongik.alpha_money.DataStructure;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Patabear on 2016-11-04.
 */
public class CustomDate {
    long now = System.currentTimeMillis();
    Date date = new Date(now); // 현재시간을 받고
    int t[] = {0,3,2,5,0,3,5,1,4,6,2,4};
    int notLeap[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 평년
    int leapYear[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 윤년

    // 시간 포맷 지정
    public SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
    public SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
    public SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");

    // 지정된 포맷으로 String 타입 리턴
    public String strCurYear = CurYearFormat.format(date);
    public String strCurMonth = CurMonthFormat.format(date);
    public String strCurDay = CurDayFormat.format(date);

    public String strCurYearMonth = strCurYear + strCurMonth;
    public String strCurYearMonthDay = strCurYear + strCurMonth + strCurDay;

    public int CheckWeekDay(String date) {
        int y = Integer.parseInt(date.substring(0, 4));
        int m = Integer.parseInt(date.substring(4, 6));
        int d = Integer.parseInt(date.substring(6, 8));
        int checkday;

        if (m < 3) { // 요일 확인
            y--;
        }
        checkday = (y + (y / 4) - (y / 100) + (y / 400) + t[m - 1] + d) % 7; // 0 = SUN ~ 6 = SAT

        return checkday;
    }

    public String CheckWeekDayWithEng(String date) {
        int y = Integer.parseInt(date.substring(0, 4));
        int m = Integer.parseInt(date.substring(4, 6));
        int d = Integer.parseInt(date.substring(6, 8));
        int checkday;

        if (m < 3) { // 요일 확인
            y--;
        }
        checkday = (y + (y / 4) - (y / 100) + (y / 400) + t[m - 1] + d) % 7; // 0 = SUN ~ 6 = SAT

        return GetWeekNameWithEng(checkday);
    }

    public String CheckWeekDayWithKor(String date) {
        int y = Integer.parseInt(date.substring(0, 4));
        int m = Integer.parseInt(date.substring(4, 6));
        int d = Integer.parseInt(date.substring(6, 8));
        int checkday;

        if (m < 3) { // 요일 확인
            y--;
        }
        checkday = (y + (y / 4) - (y / 100) + (y / 400) + t[m - 1] + d) % 7; // 0 = SUN ~ 6 = SAT

        return GetWeekNameWithKor(checkday);
    }

    public String GetBeforeDay(String date) {
        int Year, Month, Day;
        if(date.length() != 0) {
            Year = Integer.parseInt(date.substring(0, 4));
            Month = Integer.parseInt(date.substring(4,6));
            Day = Integer.parseInt(date.substring(6,8));
        }
        else {
            return date;
        }
        if(Day > 1) {
                return String.valueOf(Year*10000 + Month*100 + Day-1);
        }
        else {
            if(Month > 1){
                Month--;
                if(LeapYear(Year) == 1){ // 윤년
                    Day = leapYear[Month];
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
                else {
                    Day = notLeap[Month];
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
            }
            else{
                Year--;
                Month = 12;
                if(LeapYear(Year) == 1){ // 윤년
                    Day = leapYear[Month];
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
                else {
                    Day = notLeap[Month];
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
            }
        }
    }

    public String GetBeforeWeek(String date) {
        int Year, Month, Day;
        if(date.length() != 0) {
            Year = Integer.parseInt(date.substring(0, 4));
            Month = Integer.parseInt(date.substring(4,6));
            Day = Integer.parseInt(date.substring(6,8));
        }
        else {
            return date;
        }
        if(Day > 7) {
            return String.valueOf(Year*10000 + Month*100 + Day-7);
        }
        else {
            if(Month > 1){
                Month--;
                if(LeapYear(Year) == 1){ // 윤년
                    Day = leapYear[Month] + Day - 7;
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
                else {
                    Day = notLeap[Month] + Day - 7;
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
            }
            else{
                Year--;
                Month = 12;
                if(LeapYear(Year) == 1){ // 윤년
                    Day = leapYear[Month] + Day - 7;
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
                else {
                    Day = notLeap[Month] + Day - 7;
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
            }
        }


    }

    public String GetBeforeMonth(String date) {
        int Year, Month;
        if(date.length() != 0) {
            Year = Integer.parseInt(date.substring(0,4));
            Month = Integer.parseInt(date.substring(4,6));
        }
        else {
            return date;
        }
        if(Month > 1) {
            return String.valueOf(Year*100 + (Month-1));
        }
        else {
            Year--;
            Month = 12;
            return String.valueOf(Year*100 + Month);
        }

    }

    public String GetNextDay(String date) {
        int Year, Month, Day;
        if(date.length() != 0) {
            Year = Integer.parseInt(date.substring(0, 4));
            Month = Integer.parseInt(date.substring(4,6));
            Day = Integer.parseInt(date.substring(6,8));
        }
        else {
            return date;
        }

        if(LeapYear(Year) == 1){
            if(leapYear[Month] == Day){
                Month++;
                Day = 1;
                if(Month > 12){
                    Month = 1;
                    Year++;
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
                else {
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
            }
            else{
                return String.valueOf(Year*10000 + Month*100 + Day+1);
            }
        }

        else{
            if(notLeap[Month] == Day) {
                Month++;
                Day = 1;
                if(Month > 12){
                    Month = 1;
                    Year++;
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
                else {
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
            }
            else {
                return String.valueOf(Year*10000 + Month*100 + Day+1);
            }
        }
    }

    public String GetNextWeek(String date) {
        int Year, Month, Day;
        if(date.length() != 0) {
            Year = Integer.parseInt(date.substring(0, 4));
            Month = Integer.parseInt(date.substring(4,6));
            Day = Integer.parseInt(date.substring(6,8));
        }
        else {
            return date;
        }

        if(LeapYear(Year) == 1){
            if(leapYear[Month]-7 < Day){
                Day = Day + 7 - leapYear[Month];
                Month++;
                if(Month > 12){
                    Month = 1;
                    Year++;
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
                else {
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
            }
            else{
                return String.valueOf(Year*10000 + Month*100 + Day+7);
            }
        }

        else{
            if(notLeap[Month]-7 < Day) {
                Day = Day + 7 - notLeap[Month];
                Month++;
                if(Month > 12){
                    Month = 1;
                    Year++;
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
                else {
                    return String.valueOf(Year*10000 + Month*100 + Day);
                }
            }
            else {
                return String.valueOf(Year*10000 + Month*100 + Day+7);
            }
        }
    }

    public String GetNextMonth(String date) {
        int Year, Month;
        if(date.length() != 0) {
            Year = Integer.parseInt(date.substring(0, 4));
            Month = Integer.parseInt(date.substring(4,6));
        }
        else {
            return date;
        }

        if(Month < 12) {
            return String.valueOf(Year*100 + (Month+1));
        }
        else {
            Month = 1;
            Year++;
            return String.valueOf(Year*100 + Month);
        }
    }

    private String GetWeekNameWithEng(int week){
        if(week == 0)
            return "SUN";
        else if(week == 1)
            return "MON";
        else if(week == 2)
            return "TUE";
        else if(week == 3)
            return "WED";
        else if(week == 4)
            return "THU";
        else if(week == 5)
            return "FRI";
        else
            return "SAT";
    }

    private String GetWeekNameWithKor(int week){
        if(week == 0)
            return "(일)";
        else if(week == 1)
            return "(월)";
        else if(week == 2)
            return "(화)";
        else if(week == 3)
            return "(수)";
        else if(week == 4)
            return "(목)";
        else if(week == 5)
            return "(금)";
        else
            return "(토)";
    }

    private int LeapYear(int y) { // 1이면 윤년
        int leap = 0;

        if(y % 4 > 0)
            leap = 0;
        else {
            if(y % 400 == 0)
                leap = 1;
            else if(y % 100 == 0)
                leap = 0;
            else
                leap = 1;
        }

        return leap;
    }



}
