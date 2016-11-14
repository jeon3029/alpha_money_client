package com.hongik.alpha_money.Sms;

import android.util.Log;

import com.hongik.alpha_money.DataStructure.struct;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jeon3029 on 16. 11. 13..
 */
public class Tokenizer {
    /*
    [Web발신]
[체크승인]
58,250원
NH농협카드(8*7*)
이*정 님
10/09 03:42
G마켓

[체크.승인]
4,200원
우리카드(0371)전*준님
10/08 22:17
지급가능액336,396원
스위트스페이
     */
    public struct parsingString(String str){
        struct temp = new struct();
        int type;
        temp.price = "1";
        //first type check
        type = typecheck(str);
        if(type == 0){//농협
            Pattern p =Pattern.compile("(.+)\n(.+)\n(.+)\n(.+)\n(.+)\n(.+)\n(.+)");
            //Pattern p = Pattern.compile("")
            Matcher m =p.matcher(str);
            /*Pattern pp = Pattern.compile("(.+)\t(\\d+)\t(male|female)");
            Matcher mm = pp.matcher("jakeWarten 25  male");
            if(mm.matches()){
                Log.i("PATTERN",mm.group(0));
                Log.i("PATTERN",mm.group(1));
                Log.i("PATTERN",mm.group(2));
            }*/

            if(m.matches()) {
                Log.i("PATTERN", m.group(0));//웹발신 (졸프때 보내는 문자에는 웹발신 없음...?)
                Log.i("PATTERN", m.group(1));//체크승인
                Log.i("PATTERN", m.group(2));//금액
                Log.i("PATTERN", m.group(3));//카드
                Log.i("PATTERN", m.group(4));//사용자 이름
                Log.i("PATTERN", m.group(5));//날짜
                Log.i("PATTERN", m.group(6));//내역

                String price = m.group(2);
                price = price.replaceAll(",","");
                price = price.replace("원","");
                temp.price = price;
                temp.payment = m.group(3);
                temp.storeName = m.group(6);
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
                Date date = new Date();
                temp.date = dateFormat.format(date);
            }
            else{
                //Log.i("PATTERN",m.)
            }
        }
        else if(type == 1){//우리은행

        }
        return temp;
    }

    private int typecheck(String str) {
        if(str.contains("NH농협카드")){
            return 0;
        }
        else if(str.contains("우리카드")){
            return 1;
        }
        return 0;
    }
}
