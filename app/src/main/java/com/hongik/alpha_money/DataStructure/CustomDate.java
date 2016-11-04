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

    public int checkWeekDay(String date) {
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

    public String checkWeekDayWithEng(String date) {
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

    public String checkWeekDayWithKor(String date) {
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


}
