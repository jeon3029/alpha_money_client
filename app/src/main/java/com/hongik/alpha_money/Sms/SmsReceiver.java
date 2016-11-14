package com.hongik.alpha_money.Sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.hongik.alpha_money.AIandPopup.PopupActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Patabear on 2016-10-13.
 */
public class SmsReceiver extends BroadcastReceiver{

    public SmsReceiver(){
        Log.i("TAG", "knjbvbuvctydcytvkjnk");
    }

    public static final String SMS_RECV = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(final Context context, Intent intent) {

        String strMessage = "";
        int string = 0;
        if(intent.getAction().equals(SMS_RECV)){
            Log.i("TAG", "문자수신");

            Bundle bundle = intent.getExtras();
            Object messages[] = (Object[])bundle.get("pdus");
            SmsMessage smsMessage[] = new SmsMessage[messages.length];

            // PDU 포맷으로 되어 있는 메시지를 복원합니다.
            if(Build.VERSION.SDK_INT > 22){
                for(int i = 0; i < messages.length; i++) {
                    String format = bundle.getString("format");
                    smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i], format);

                    strMessage += smsMessage[i].getMessageBody();
                }
            }
            else{
                for(int i = 0; i < messages.length; i++) {
                    smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);

                    strMessage += smsMessage[i].getMessageBody();
                }
            }

            Log.i("TAG", strMessage); // message 의 string 전체

            Date curDate = new Date(smsMessage[0].getTimestampMillis());
            Log.i("TAG", curDate.toString());
            Log.i("TAG", String.valueOf(curDate.getMonth()));
            Log.i("TAG", String.valueOf(curDate.getDate()));

            try {
                FileOutputStream fos = context.openFileOutput("text.txt", Context.MODE_APPEND);
                PrintWriter out = new PrintWriter(fos);
                out.println(strMessage);
                Log.i("TAG", "try out!");
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Log.i("TAG", String.valueOf(context.getFileStreamPath("text")));
            Log.i("TAG", String.valueOf(context.getFileStreamPath("a.out")));


            String[] command = {"", "", String.valueOf(context.getFileStreamPath("a.out"))};
            try {
                Runtime.getRuntime().exec("a.out < text");
                Log.i("TAG", "try!");
            } catch (IOException e) {
                e.printStackTrace();
            }

/*
// SMS 발신 번호 확인
            String origNumber = smsMessage[0].getOriginatingAddress();

// SMS 메시지 확인
            String message = smsMessage[0].getMessageBody().toString();
            Log.i("TAG", "발신자 : "+origNumber+", 내용 : " + message);

*/

        }


        // 팝업실행 코드부분
        Intent intentPop = new Intent(context, PopupActivity.class);
        intentPop.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
        context.startActivity(intentPop);


    }
}
