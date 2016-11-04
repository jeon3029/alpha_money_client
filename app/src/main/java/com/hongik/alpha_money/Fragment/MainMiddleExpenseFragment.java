
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hongik.alpha_money.Activity.DetailActivity;
import com.hongik.alpha_money.Activity.MainActivity;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.ListCustomAdapter;
import com.hongik.alpha_money.DataStructure.struct;
import com.hongik.alpha_money.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainMiddleExpenseFragment extends Fragment{
    View rootViewBasic;
    View tempView;
    ListView listView_expense;
    ListCustomAdapter listCustomAdapter_expense;
    ArrayList<struct> arrayList_expense;
    Button buttonToday, buttonWeek, buttonMonth;
    Button.OnClickListener todayListener, weekListener, monthListener;
    TextView TotalExpense;
    Context ctx = ApplicationSingleton.getInstance().GetMainActivityContext();
    int state; // 현재 내가 보고있는 리스트의 종류 1 = today  2 = week  3 = month
    ArrayList<struct> arrayListMonth = new ArrayList<struct>();

    long now = System.currentTimeMillis();
    Date date = new Date(now); // 현재시간을 받고

    // 시간 포맷 지정
    SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
    SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");

    // 지정된 포맷으로 String 타입 리턴
    String strCurYear = CurYearFormat.format(date);
    String strCurMonth = CurMonthFormat.format(date);
    String strCurDay = CurDayFormat.format(date);

    String strCurYearMonth = strCurYear + strCurMonth + strCurDay; // yyyymm 형식의 string


    Intent intent;// 보낼때 사용 보내는 형식은 ID, date, ~ ,gridY 를 putintent 하여 전송
    // 받을때 사용 받는 형식은 From (어느 액티비티에서 왔는지), Del, ID (삭제여부와 삭제할 아이디)  + intent에 있던것들  추후 추가 가능


    public MainMiddleExpenseFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_expense_list, container, false);
        rootViewBasic.setTag("expense");

        tempView = ((MainActivity)ctx).getMainBottomFragment().rootViewBasic;
        buttonToday = (Button)tempView.findViewById(R.id.buttonToday);
        buttonWeek = (Button)tempView.findViewById(R.id.buttonWeek);
        buttonMonth = (Button)tempView.findViewById(R.id.buttonMonth);

        TotalExpense = (TextView)rootViewBasic.findViewById(R.id.totalprice1);

        buttonToday.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickToday(strCurYearMonth);
            }
        });

        buttonWeek.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickWeek(strCurYearMonth);
            }
        });

        buttonMonth.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickMonth(strCurYearMonth);
            }
        });

        listView_expense = (ListView)rootViewBasic.findViewById(R.id.main_expense_listview);

        //listView_expense.setOnItemClickListener(mItemClickListener);

        //TODO : 상황에 맞는 (월,주,일) 출력 해야 함.
        int sum = 0;
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(2, strCurYearMonth);
        listCustomAdapter_expense = new ListCustomAdapter(arrayList_expense, ApplicationSingleton.getInstance());

        for(int i = 0; i < arrayList_expense.size(); i++)
            if(arrayList_expense.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_expense.get(i).price); // price가 0이라 개망

        TotalExpense.setText(String.valueOf(sum));
        listView_expense.setAdapter(listCustomAdapter_expense);

        state = 2;
        //list custom Adapter click listener by tj aa 2014 11 03
        listView_expense.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(state <3) {
                    struct temp = arrayList_expense.get(position);

                    intent = new Intent(ApplicationSingleton.getInstance().GetMainActivityContext(), DetailActivity.class);

                    intent.putExtra("ID", temp.ID);
                    intent.putExtra("date", temp.date);
                    intent.putExtra("price", temp.price);
                    intent.putExtra("storeName", temp.storeName);
                    intent.putExtra("category", temp.category);
                    intent.putExtra("memo", temp.memo);
                    intent.putExtra("gridX", temp.gridX);
                    intent.putExtra("gridY", temp.gridY);
                    intent.putExtra("option", 1); //1 = expense 2=income
                    startActivityForResult(intent, 1);
                }
                else if(state ==3){
                    struct struct = arrayListMonth.get(position);
                    onclickToday(struct.memo);
                }
                else{

                }
            }
        });
        return rootViewBasic;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //TODO : same
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(0,"");//get all data
        listCustomAdapter_expense.notifyDataSetChanged();
    }


    public void onclickToday(String str) {
        Log.i("tag", "today clicked");
        int sum = 0;
        arrayList_expense.clear();
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(3, str);
        listCustomAdapter_expense.setItemDatas(arrayList_expense);

        for (int i = 0; i < arrayList_expense.size(); i++)
            if (arrayList_expense.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_expense.get(i).price);

        TotalExpense.setText(String.valueOf(sum));

        state = 1; // 현재 보고있는 리스트 종류를 어댑터에 알려주기위한 용도
    }

    public void onclickWeek(String str) {
        Log.i("tag", "week clicked");
        int sum = 0;
        arrayList_expense.clear();
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(2, str);
        listCustomAdapter_expense.setItemDatas(arrayList_expense);

        for(int i = 0; i < arrayList_expense.size(); i++)
            if(arrayList_expense.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_expense.get(i).price); // price가 0이라 개망

        TotalExpense.setText(String.valueOf(sum));

        state = 2; // 현재 보고있는 리스트 종류를 어댑터에 알려주기위한 용도
    }

    public void onclickMonth(String str) {
        Log.i("tag", "month clicked");
        int sum = 0;
        int dayprice[] = new int[32];
        arrayList_expense.clear();
        arrayListMonth.clear();
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(1, str);

        for(int i = 0; i < arrayList_expense.size(); i++) {
            if (arrayList_expense.get(i).price.length() != 0) {
                sum += Integer.parseInt(arrayList_expense.get(i).price);
                dayprice[Integer.parseInt(arrayList_expense.get(i).date.substring(6,8))] +=  Integer.parseInt(arrayList_expense.get(i).price);
            }
        }
        for(int i = 0; i < 31; i++) { // TODO : 월별 출력에 문제가 있음
            if( dayprice[i+1] != 0) {
                struct struct = new struct();
                struct.price = String.valueOf(dayprice[i + 1]);
                struct.storeName = strCurMonth + "월 " + String.valueOf(i + 1) + "일";
                if (i < 9) {
                    struct.memo = strCurYear + strCurMonth + '0' + String.valueOf(i + 1); // memo영역을 월별 리스트에서는 날짜를 기억하는 임시저장소로 사용
                } else {
                    struct.memo = strCurYear + strCurMonth + String.valueOf(i + 1);
                }
                arrayListMonth.add(struct);
            }
        }
        listCustomAdapter_expense.setItemDatas(arrayListMonth);

        TotalExpense.setText(String.valueOf(sum));

        state = 3; // 현재 보고있는 리스트 종류를 어댑터에 알려주기위한 용도
    }
/*
    // TODO : 월별에서 아이템 클릭시 한번은되는데 두번이 안됨 이유는 모름 쿼리문에서 막힘 개꿀
    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long l_position) {
            Log.i("tag", "click item");
            if(state == 3) {

            }
        }
    };*/

}
