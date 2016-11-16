package com.hongik.alpha_money.AIandPopup;

import android.util.Log;

import com.hongik.alpha_money.DataStructure.CustomDate;
import com.hongik.alpha_money.DataStructure.struct;

import java.util.Random;

/**
 * Created by jeon3029 on 16. 11. 15..
 */
public class AIMent {
    struct info = new struct();
    CustomDate customDate = new CustomDate();


    public String getMent(struct st,int t) {
        info = st;
        int time, weekday, money;
        boolean weekend = false;
        Random random = new Random();
        //나누어야 할 타입 : 시간, 카테고리

        if (st.date.length() >= 10) {
            time = Integer.parseInt(st.date.substring(8, 10));
        } else
            time = -1;

        money = Integer.parseInt(st.price);


        weekday = customDate.CheckWeekDay(st.date);
        if (weekday == 0 || weekday > 4)
            weekend = true;


        if (money >= 80000) { // 80000원 이상 사용
            switch (random.nextInt(4)) {
                case 0:
                    return "우리 주인님 부자인걸?";
                case 1:
                    return "뭔데 뭔데 어디에 이렇게 많이쓴거야?";
                case 2:
                    return "좀 많이 썼지만 기분만 좋다면 굿!";
                case 3:
                    return "오늘의~ 부자!";
            }
        } else if (money == 2500 || money == 3000 || money == 3500) {
            return "밥버거야? 내꺼는?";
        } else if (0 <= time && time < 6) { // 새벽
            Log.i("tag", "새벽");
            if (t == 0) {//초록불
                switch (random.nextInt(4)) {
                    case 0:
                        return "이 어플리케이션 개발자가 옵치할 시간 Patabear#3528";
                    case 1:
                        return "뭐야 뭔데 오늘 불태우는거야?";
                    case 2:
                        return "ZZZzz...";
                    case 3:
                        return "넌 아직 여유가 있다 돌격해!";
                }

            } else if (t == 1) {//노란불
                switch (random.nextInt(4)) {
                    case 0:
                        return "이 어플리케이션 개발자가 옵치할 시간 Patabear#3528";
                    case 1:
                        return "마무리는 역시 피시방이지";
                    case 2:
                        return "오늘 막쓰면 위험해질수가 있어..야바이..";
                    case 3:
                        return "(부스스..) 뭐야 아직 해 안떴잖아..";
                }

            } else if (t == 2) {//빨간불
                switch (random.nextInt(4)) {
                    case 0:
                        return "오늘 야식.. 내일 거지..";
                    case 1:
                        return "사실 넌 이미 거지...";
                    case 2:
                        return "(힐끗) 절레절레";
                    case 3:
                        return "아니야 그만놀고 들어가 다음달에 놀아";
                }

            }
        } else if (6 <= time && time < 12) { // 아침
            Log.i("tag", "아침");
            if (t == 0) {//초록불
                switch (random.nextInt(6)) {
                    case 0:
                        return "아침부터 부지런해~";
                    case 1:
                        return "우리 자기 아침은 먹었니? (심쿵)";
                    case 2:
                        return "ZZZzz...";
                    case 3:
                        return "넌 아직 여유가 있다 돌격해!";
                    case 4:
                        return "수업가기 정말 너어어어무 싫다.";
                    case 5:
                        return "도르마무! 어디에 쓴건지 알러왔다!";
                }
            } else if (t == 1) {//노란불
                switch (random.nextInt(6)) {
                    case 0:
                        return "아침부터 부지런해~";
                    case 1:
                        return "우리 자기 아침은 먹었니? (심쿵)";
                    case 2:
                        return "ZZZzz...";
                    case 3:
                        return "노란불이야 이새는 노란부리란마리야!";
                    case 4:
                        return "수업가기 정말 너어어어무 싫다.";
                    case 5:
                        return "도르마무! 어디에 쓴건지 알러왔다!";
                }
            } else if (t == 2) {//빨간불
                switch (random.nextInt(6)) {
                    case 0:
                        return "아침부터 부지런해~ 근데 아껴써~";
                    case 1:
                        return "오늘 점심은 밥버거로..";
                    case 2:
                        return "ZZZzz...얼마 남았더라";
                    case 3:
                        return "너의 소비는 빨간불에도 멈추지않아 BOY~♪";
                    case 4:
                        return "돈없다고 이상한데서 빌리면 안된다 ㅡㅡ";
                    case 5:
                        return "아껴쓴다고 하던 나는 어디있지? 존나 어디있냔말야!";
                }
            }
        } else if (12 <= time && time < 18) { // 낮
            Log.i("tag", "낮");
            if (t == 0) {//초록불
                if (weekend == false) {
                    switch (random.nextInt(8)) {
                        case 0:
                            return "점심먹고 이따가 저녁먹어야지~";
                        case 1:
                            return "수업째고 옵치하는 개발자 Patabear#3528";
                        case 2:
                            return "귀여운 가계부 왔쪄염 뿌우";
                        case 3:
                            return "넌 아직 여유가 있다 돌격해!";
                        case 4:
                            return "오늘 얼마나 존거야 옆에 침묻었다";
                        case 5:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                        case 6:
                            return "도르마무! 다이어트를 하러왔다.";
                        case 7:
                            return "????? PROFIT!!!";
                    }
                } else if (weekend == true) {
                    switch (random.nextInt(7)) {
                        case 0:
                            return "점심먹고 이따가 저녁먹어야지~";
                        case 1:
                            return "주말이라고 옵치하는 개발자 Patabear#3528";
                        case 2:
                            return "귀여운 가계부 왔쪄염 뿌우";
                        case 3:
                            return "넌 아직 여유가 있다 돌격해!";
                        case 4:
                            return "설마 지금 일어난거 아니지..?";
                        case 5:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                        case 6:
                            return "오늘 저녁에 뭐하고 놀지 정했어?";
                        case 7:
                            return "도르마무! 다이어트를 하러왔다.";

                    }
                }
            } else if (t == 1) {//노란불
                if (weekend == false) {
                    switch (random.nextInt(8)) {
                        case 0:
                            return "점심먹고 이따가 저녁먹어야지~";
                        case 1:
                            return "수업째고 옵치하는 개발자 Patabear#3528";
                        case 2:
                            return "귀여운 가계부 왔쪄염 뿌우";
                        case 3:
                            return "노란불 깜빡깜빡";
                        case 4:
                            return "오늘 얼마나 존거야 옆에 침묻었다";
                        case 5:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                        case 6:
                            return "도르마무! 다이어트를 하러왔다.";
                        case 7:
                            return "????? PROFIT!!!";
                    }
                } else if (weekend == true) {
                    switch (random.nextInt(8)) {
                        case 0:
                            return "점심먹고 이따가 저녁먹어야지~";
                        case 1:
                            return "주말이라고 옵치하는 개발자 Patabear#3528";
                        case 2:
                            return "귀여운 가계부 왔쪄염 뿌우";
                        case 3:
                            return "오늘 저녁에 달리면 통장이 위험할지도..";
                        case 4:
                            return "설마 지금 일어난거 아니지..?";
                        case 5:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                        case 6:
                            return "오늘 저녁에 뭐하고 놀지 정했어?";
                        case 7:
                            return "도르마무! 다이어트를 하러왔다.";

                    }
                }

            } else if (t == 2) {//빨간불
                if (weekend == false) {
                    switch (random.nextInt(11)) {
                        case 0:
                            return "오늘 저녁은 밥버거야..";
                        case 1:
                            return "헐벗은 너의 통장 야바이..";
                        case 2:
                            return "너의 소비는 빨간불에도 멈추지않아 BOY~♪";
                        case 3:
                            return "돈없다고 이상한데서 빌리면 안된다 ㅡㅡ";
                        case 4:
                            return "아껴쓴다고 하던 나는 어디있지? 존나 어디있냔말야!";
                        case 5:
                            return "소비했어 안했어? 밥도 먹지 마!\n 티비도 보지마!";
                        case 6:
                            return "사실 넌 이미 거지...";
                        case 7:
                            return "(힐끗) 절레절레";
                        case 8:
                            return "아니야 그만놀고 들어가 다음달에 놀아";
                        case 9:
                            return "오늘 놀수가 없어 (털썩)";
                        case 10:
                            return "도르마무! 오늘부터 절약하러 왔다!";
                    }
                } else if (weekend == true) {
                    switch (random.nextInt(11)) {
                        case 0:
                            return "오늘 저녁은 밥버거야..";
                        case 1:
                            return "헐벗은 너의 통장 야바이..";
                        case 2:
                            return "너의 소비는 빨간불에도 멈추지않아 BOY~♪";
                        case 3:
                            return "돈없다고 이상한데서 빌리면 안된다 ㅡㅡ";
                        case 4:
                            return "아껴쓴다고 하던 나는 어디있지? 존나 어디있냔말야!";
                        case 5:
                            return "소비했어 안했어? 밥도 먹지 마!\n 티비도 보지마!";
                        case 6:
                            return "사실 넌 이미 거지...";
                        case 7:
                            return "(힐끗) 절레절레";
                        case 8:
                            return "아니야 그만놀고 들어가 다음달에 놀아";
                        case 9:
                            return "오늘 놀수가 없어 (털썩)";
                        case 10:
                            return "도르마무! 오늘부터 절약하러왔다!";
                    }
                }

            }


        } else if (18 <= time && time < 24) { // 저녁
            Log.i("tag", "저녁");
            if (t == 0) {//초록불
                if (weekend == false) {
                    switch (random.nextInt(6)) {
                        case 0:
                            return "뭐야 뭔데 오늘 불태우는거야?";
                        case 1:
                            return "넌 아직 여유가 있다 돌격해!";
                        case 2:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                        case 3:
                            return "저녁먹고 이따가 야식 콜? 난 치킨";
                        case 4:
                            return "귀여운 가계부 왔쪄염 뿌우";
                        case 5:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                    }
                } else if (weekend == true) {
                    switch (random.nextInt(6)) {
                        case 0:
                            return "뭐야 뭔데 오늘 불태우는거야?";
                        case 1:
                            return "넌 아직 여유가 있다 돌격해!";
                        case 2:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                        case 3:
                            return "저녁먹고 이따가 야식 콜? 난 치킨";
                        case 4:
                            return "귀여운 가계부 왔쪄염 뿌우";
                        case 5:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                    }

                }
            } else if (t == 1) {//노란불
                if (weekend == false) {
                    switch (random.nextInt(7)) {
                        case 0:
                            return "저녁먹고 이따가 야식먹어야지~";
                        case 1:
                            return "수업째고 옵치하는 개발자 Patabear#3528";
                        case 2:
                            return "귀여운 가계부 왔쪄염 뿌우";
                        case 3:
                            return "노란불 깜빡깜빡";
                        case 4:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                        case 5:
                            return "도르마무! 다이어트를 하러왔다.";
                        case 6:
                            return "????? PROFIT!!!";
                    }
                } else if (weekend == true) {
                    switch (random.nextInt(7)) {
                        case 0:
                            return "저녁먹고 이따가 야식먹어야지~";
                        case 1:
                            return "주말이라고 옵치하는 개발자 Patabear#3528";
                        case 2:
                            return "귀여운 가계부 왔쪄염 뿌우";
                        case 3:
                            return "오늘 밤에 달리면 통장이 위험할지도..";
                        case 4:
                            return "도르마무! 어디에 쓴건지 알러왔다!";
                        case 5:
                            return "오늘 밤에 뭐하고 놀지 정했어?";
                        case 6:
                            return "도르마무! 다이어트를 하러왔다.";

                    }
                }

            } else if (t == 2) {//빨간불
                if (weekend == false) {
                    switch (random.nextInt(10)) {
                        case 0:
                            return "헐벗은 너의 통장 야바이..";
                        case 1:
                            return "너의 소비는 빨간불에도 멈추지않아 BOY~♪";
                        case 2:
                            return "돈없다고 이상한데서 빌리면 안된다 ㅡㅡ";
                        case 3:
                            return "아껴쓴다고 하던 나는 어디있지? 존나 어디있냔말야!";
                        case 4:
                            return "소비했어 안했어? 밥도 먹지 마!\n 티비도 보지마!";
                        case 5:
                            return "사실 넌 이미 거지...";
                        case 6:
                            return "(힐끗) 절레절레";
                        case 7:
                            return "아니야 그만놀고 들어가 다음달에 놀아";
                        case 8:
                            return "오늘 놀수가 없어 (털썩)";
                        case 9:
                            return "도르마무! 오늘부터 절약하러 왔다!";
                    }
                } else if (weekend == true) {
                    switch (random.nextInt(10)) {
                        case 0:
                            return "헐벗은 너의 통장 야바이..";
                        case 1:
                            return "너의 소비는 빨간불에도 멈추지않아 BOY~♪";
                        case 2:
                            return "돈없다고 이상한데서 빌리면 안된다 ㅡㅡ";
                        case 3:
                            return "아껴쓴다고 하던 나는 어디있지? 존나 어디있냔말야!";
                        case 4:
                            return "소비했어 안했어? 밥도 먹지 마!\n 티비도 보지마!";
                        case 5:
                            return "사실 넌 이미 거지...";
                        case 6:
                            return "(힐끗) 절레절레";
                        case 7:
                            return "아니야 그만놀고 들어가 다음달에 놀아";
                        case 8:
                            return "오늘 놀수가 없어 (털썩)";
                        case 9:
                            return "도르마무! 오늘부터 절약하러왔다!";
                    }
                }
            }
        }
        return "시간정보를 못받았어..!";
    }
}
