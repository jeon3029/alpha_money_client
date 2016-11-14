package com.hongik.alpha_money.DataStructure;
import com.hongik.alpha_money.ApplicationSingleton;

import java.lang.reflect.Array;
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
    public int CalcSum(){//returns total amount of spend this month
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
    public String getText(int t){
        String ret = new String();
        ArrayList<String> totString = new ArrayList<String>();
        if(t ==0){
            totString.add("아직 정상이네~!");
            totString.add("매우 양호!");
            totString.add("우왕 초록불이당");
            totString.add("이달의 건전한 소비자는 바로 당신!");
            int i = (int)(Math.random()*totString.size());
            ret = totString.get(i);
            return ret;
        }
        else if(t==1){
            totString.add("아직 까지는 통~장~!");
            totString.add("아직까지는 괜찮아..");
            totString.add("좀만 더 힘내자");
            int i = (int)(Math.random()*totString.size());
            ret = totString.get(i);
            return ret;
        }
        else if(t==2){
            totString.add("빨간불이네용ㅋㅋ솔찍히 집에 그냥 있어라");
            totString.add("빨간불.. 이참에 다이어트..");
            totString.add("빨간불이야..이번달은 글렀어...");
            totString.add("텅~장~~");
            int i = (int)(Math.random()*totString.size());
            ret = totString.get(i);
            return ret;
        }
        else{
            ret = "우왕~~~";
            return ret;
        }
    }
}
