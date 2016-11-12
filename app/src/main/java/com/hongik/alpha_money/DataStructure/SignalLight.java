package com.hongik.alpha_money.DataStructure;

import com.hongik.alpha_money.ApplicationSingleton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jeon3029 on 16. 11. 8..
 */
public class SignalLight {
    ArrayList<struct> monthList;
    public int CalcSumLight(){//0 : green 1 : yellow 2 : red
        //201611
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        monthList = ApplicationSingleton.getInstance().GetExpenseList(1,dateFormat.format(date));
        int n = monthList.size();
        int botLimit,topLimit;
        botLimit = ApplicationSingleton.getInstance().GetBotLimit();
        topLimit = ApplicationSingleton.getInstance().GetTopLimit();
        int sum=0;
        for(int i=0;i<n;i++){
            sum +=Integer.parseInt(monthList.get(i).price);
        }
        if(sum<botLimit)return 0; //green
        else if(sum>=botLimit && sum<topLimit)return 1; //yellow
        else return 2; //red
        //0 : green 1 : yellow 2 : red
    }
    public int CalcSum(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        monthList = ApplicationSingleton.getInstance().GetExpenseList(1,dateFormat.format(date));
        int n = monthList.size();
        //int botLimit,topLimit;
        //botLimit = ApplicationSingleton.getInstance().GetBotLimit();
        //topLimit = ApplicationSingleton.getInstance().GetTopLimit();
        int sum=0;
        for(int i=0;i<n;i++){
            sum +=Integer.parseInt(monthList.get(i).price);
        }
        return sum;
    }
}
