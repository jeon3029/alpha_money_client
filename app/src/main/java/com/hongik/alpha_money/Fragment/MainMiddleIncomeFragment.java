package com.hongik.alpha_money.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hongik.alpha_money.Activity.DetailActivity;
import com.hongik.alpha_money.Activity.MainActivity;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.CustomDate;
import com.hongik.alpha_money.DataStructure.ListCustomAdapter;
import com.hongik.alpha_money.DataStructure.struct;
import com.hongik.alpha_money.R;

import java.util.ArrayList;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainMiddleIncomeFragment extends Fragment {
    View rootViewBasic;
    ListView listView_income;
    ListCustomAdapter listCustomAdapter_income;
    ArrayList<struct> arrayList_income;
    TextView LayoutDate;
    TextView TotalIncome;
    Context ctx = ApplicationSingleton.getInstance().GetMainActivityContext();
    ArrayList<struct> arrayListMonth = new ArrayList<struct>();
    ArrayList<struct> arrayListWeek = new ArrayList<struct>();
    CustomDate customDate = new CustomDate();
    int stateInFrag; // 내부적으로 현재 표시중인 리스트의 종류를 표현  프래그먼트 시작과함께 2번  버튼클릭시 해당 스테이트로 바뀜
    // 1 = 오늘  2 = 주별  3 = 월별
    String tempDateForRefresh;
    String layoutDate;
    ImageView dateLeft, dateRight;

    Intent intent;// 보낼때 사용 보내는 형식은 ID, date, ~ ,gridY 를 putintent 하여 전송
    // 받을때 사용 받는 형식은 From (어느 액티비티에서 왔는지), Del, ID (삭제여부와 삭제할 아이디)  + intent에 있던것들  추후 추가 가능

    public MainMiddleIncomeFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_income_list, container, false);

        dateLeft = (ImageView)rootViewBasic.findViewById(R.id.date_left_income);
        dateRight = (ImageView)rootViewBasic.findViewById(R.id.date_right_income);

        LayoutDate = (TextView)rootViewBasic.findViewById(R.id.date_income);
        TotalIncome = (TextView)rootViewBasic.findViewById(R.id.totalprice2);

        listView_income = (ListView)rootViewBasic.findViewById(R.id.main_income_listview);

        stateInFrag = 2;

        if(arrayListWeek.size() != 0)
            arrayListWeek.clear();

        int sum = 0, today = 0, nextday = 0;
        arrayList_income = ApplicationSingleton.getInstance().GetIncomeList(2, customDate.strCurYearMonthDay);

        for(int i = 0; i < arrayList_income.size(); i++) {
            String date = arrayList_income.get(i).date;
            nextday = Integer.parseInt(date.substring(0, 8));
            if (today < nextday){
                struct temp = new struct();
                temp.storeName = date.substring(4,6) + "월 " + date.substring(6,8) + "일 " + customDate.CheckWeekDayWithKor(date);
                temp.price = "";
                temp.invalid = true;
                arrayListWeek.add(temp);
            }
            struct struct = arrayList_income.get(i);
            struct.storeName = "     " + struct.storeName;
            arrayListWeek.add(struct);

            today = Integer.parseInt(date.substring(0, 8));
        }

        for(int i = 0; i < arrayList_income.size(); i++)
            if(arrayList_income.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_income.get(i).price); // price가 0이라 개망

        LayoutDate.setText(customDate.strCurYear + "." + customDate.strCurMonth + "." + customDate.strCurDay);
        TotalIncome.setText(String.valueOf(sum));
        layoutDate = customDate.strCurYearMonthDay;

        listCustomAdapter_income = new ListCustomAdapter(arrayListWeek, ApplicationSingleton.getInstance());
        listView_income.setAdapter(listCustomAdapter_income);

        tempDateForRefresh = customDate.strCurYearMonthDay;

        //list custom Adapter click listener by tj aa 2014 11 03
        listView_income.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(stateInFrag < 3) {
                    struct temp;
                    if(stateInFrag == 1)
                        temp = arrayList_income.get(position);
                    else
                        temp = arrayListWeek.get(position);

                    if(temp.invalid != true) {
                        intent = new Intent(ApplicationSingleton.getInstance().GetMainActivityContext(), DetailActivity.class);

                        intent.putExtra("ID", temp.ID);
                        intent.putExtra("date", temp.date);
                        intent.putExtra("price", temp.price);
                        Log.i("Trim","i1: " + temp.storeName);
                        temp.storeName.trim();//removing all leading spaces
                        Log.i("Trim","i2: " + temp.storeName.trim());
                        intent.putExtra("storeName", temp.storeName.trim());
                        intent.putExtra("category", temp.category);
                        intent.putExtra("memo", temp.memo);
                        intent.putExtra("gridX", temp.gridX);
                        intent.putExtra("gridY", temp.gridY);
                        intent.putExtra("option", 2); //1 = expense 2=income
                        startActivityForResult(intent, 2);
                    }
                }
                else if(stateInFrag == 3){
                    struct struct = arrayListMonth.get(position);
                    onclickToday(struct.memo);
                }
                else{

                }
            }
        });

        dateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateInFrag == 1){
                    onclickToday(customDate.GetBeforeDay(layoutDate));
                }
                else if(stateInFrag == 2){
                    onclickWeek(customDate.GetBeforeWeek(layoutDate));
                }
                else if(stateInFrag == 3){
                    onclickMonth(customDate.GetBeforeMonth(layoutDate));
                }
            }
        });

        dateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateInFrag == 1){
                    onclickToday(customDate.GetNextDay(layoutDate));
                }
                else if(stateInFrag == 2){
                    onclickWeek(customDate.GetNextWeek(layoutDate));
                }
                else if(stateInFrag == 3){
                    onclickMonth(customDate.GetNextMonth(layoutDate));
                }
            }
        });

        return rootViewBasic;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //TODO : same
        arrayList_income = ApplicationSingleton.getInstance().GetIncomeList(0,"");//get all data
        listCustomAdapter_income.notifyDataSetChanged();
    }

    public void onclickToday(String str) {
        Log.i("tag", "today clicked In");
        str = str.substring(0,8);
        tempDateForRefresh = str;
        int sum = 0;
        arrayList_income = ApplicationSingleton.getInstance().GetIncomeList(3, str);
        listCustomAdapter_income.setItemDatas(arrayList_income);

        for (int i = 0; i < arrayList_income.size(); i++)
            if (arrayList_income.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_income.get(i).price);

        LayoutDate.setText(str.substring(0,4) + "." + str.substring(4,6) + "." + str.substring(6,8));
        TotalIncome.setText(String.valueOf(sum));

        layoutDate = str;

        ((MainActivity)ctx).SetPageState(4);
        stateInFrag = 1;
    }

    public void onclickWeek(String str) {
        Log.i("tag", "week clicked In");
        str = str.substring(0,8);
        tempDateForRefresh = str;
        int sum = 0, today = 0, nextday;
        if(arrayListWeek.size() != 0)
            arrayListWeek.clear();
        arrayList_income = ApplicationSingleton.getInstance().GetIncomeList(2, str);

        for(int i = 0; i < arrayList_income.size(); i++) {
            String date = arrayList_income.get(i).date;
            nextday = Integer.parseInt(date.substring(0, 8));
            if (today < nextday){
                struct temp = new struct();
                temp.storeName = date.substring(4,6) + "월 " + date.substring(6,8) + "일 " + customDate.CheckWeekDayWithKor(date);
                temp.price = "";
                temp.invalid = true;
                arrayListWeek.add(temp);
            }
            struct struct = arrayList_income.get(i);
            struct.storeName = "     " + struct.storeName;
            arrayListWeek.add(struct);

            today = Integer.parseInt(date.substring(0,8));
        }

        for(int i = 0; i < arrayList_income.size(); i++)
            if(arrayList_income.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_income.get(i).price); // price가 0이라 개망


        listCustomAdapter_income.setItemDatas(arrayListWeek);

        LayoutDate.setText(str.substring(0,4) + "." + str.substring(4,6) + "." + str.substring(6,8));
        TotalIncome.setText(String.valueOf(sum));

        layoutDate = str;

        ((MainActivity)ctx).SetPageState(5);

        stateInFrag = 2;
    }

    public void onclickMonth(String str) {
        Log.i("tag", "month clicked In");
        str = str.substring(0,6);
        tempDateForRefresh = str;
        int sum = 0;
        int dayprice[] = new int[32];
        arrayListMonth.clear();
        arrayList_income = ApplicationSingleton.getInstance().GetIncomeList(1, str);

        for(int i = 0; i < arrayList_income.size(); i++) {
            if (arrayList_income.get(i).price.length() != 0) {
                sum += Integer.parseInt(arrayList_income.get(i).price);
                dayprice[Integer.parseInt(arrayList_income.get(i).date.substring(6,8))] +=  Integer.parseInt(arrayList_income.get(i).price);
            }
        }
        for(int i = 0; i < 31; i++) { // TODO : 월별 출력에 문제가 있음
            if( dayprice[i+1] != 0) {
                struct struct = new struct();
                struct.price = String.valueOf(dayprice[i + 1]);
                struct.storeName = str.substring(4,6) + "월 " + String.valueOf(i + 1) + "일";
                if (i < 9) {
                    struct.memo = str.substring(0,4) + str.substring(4,6) + '0' + String.valueOf(i + 1); // memo영역을 월별 리스트에서는 날짜를 기억하는 임시저장소로 사용
                } else {
                    struct.memo = str.substring(0,4) + str.substring(4,6) + String.valueOf(i + 1);
                }
                arrayListMonth.add(struct);
            }
        }
        listCustomAdapter_income.setItemDatas(arrayListMonth);

        LayoutDate.setText(str.substring(0,4) + "." + str.substring(4,6));
        TotalIncome.setText(String.valueOf(sum));

        layoutDate = str;

        ((MainActivity)ctx).SetPageState(6);
        stateInFrag = 3;
    }

    public void onclickRefresh() {
        if(stateInFrag == 1)
            onclickToday(tempDateForRefresh);
        else if(stateInFrag == 2)
            onclickWeek(tempDateForRefresh);
        else if(stateInFrag == 3)
            onclickMonth(tempDateForRefresh);
    }
}
