
package com.hongik.alpha_money.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.CustomDate;
import com.hongik.alpha_money.DataStructure.ListCustomAdapter;
import com.hongik.alpha_money.DataStructure.struct;
import com.hongik.alpha_money.R;

import java.util.ArrayList;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainMiddleExpenseFragment extends Fragment{
    View rootViewBasic;
    ListView listView_expense;
    ListCustomAdapter listCustomAdapter_expense;
    ArrayList<struct> arrayList_expense;
    TextView TotalExpense;
    Context ctx = ApplicationSingleton.getInstance().GetMainActivityContext();
    ArrayList<struct> arrayListMonth = new ArrayList<struct>();
    ArrayList<struct> arrayListWeek = new ArrayList<struct>();
    CustomDate customDate = new CustomDate();

    public MainMiddleExpenseFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_expense_list, container, false);

        TotalExpense = (TextView)rootViewBasic.findViewById(R.id.totalprice1);

        listView_expense = (ListView)rootViewBasic.findViewById(R.id.main_expense_listview);

        listView_expense.setOnItemClickListener(ExItemClickListener);

        //TODO : 상황에 맞는 (월,주,일) 출력 해야 함.

        if(arrayListWeek.size() != 0)
            arrayListWeek.clear();

        int sum = 0, today = 0, nextday = 0;
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(2, customDate.strCurYearMonthDay);

        for(int i = 0; i < arrayList_expense.size(); i++) {
            String date = arrayList_expense.get(i).date;
            nextday = Integer.parseInt(date.substring(0, 8));
            if (today < nextday){
                struct temp = new struct();
                temp.storeName = date.substring(4,6) + "월 " + date.substring(6,8) + "일 " + customDate.checkWeekDayWithKor(date);
                temp.price = "";
                arrayListWeek.add(temp);
            }
            struct struct = arrayList_expense.get(i);
            struct.storeName = "    " + struct.storeName;
            arrayListWeek.add(struct);

            today = Integer.parseInt(date.substring(0,8));
        }

        for(int i = 0; i < arrayList_expense.size(); i++)
            if(arrayList_expense.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_expense.get(i).price); // price가 0이라 개망

        TotalExpense.setText(String.valueOf(sum));

        listCustomAdapter_expense = new ListCustomAdapter(arrayListWeek, ApplicationSingleton.getInstance());
        listView_expense.setAdapter(listCustomAdapter_expense);

        ApplicationSingleton.getInstance().SetPageState(2);

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
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(3, str);
        listCustomAdapter_expense.setItemDatas(arrayList_expense);

        for (int i = 0; i < arrayList_expense.size(); i++)
            if (arrayList_expense.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_expense.get(i).price);

        TotalExpense.setText(String.valueOf(sum));


        ApplicationSingleton.getInstance().SetPageState(1);
    }

    public void onclickWeek(String str) {
        Log.i("tag", "week clicked");
        int sum = 0, today = 0, nextday;
        if(arrayListWeek.size() != 0)
            arrayListWeek.clear();
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(2, str);

        for(int i = 0; i < arrayList_expense.size(); i++) {
            String date = arrayList_expense.get(i).date;
            nextday = Integer.parseInt(date.substring(0, 8));
            if (today < nextday){
                struct temp = new struct();
                temp.storeName = date.substring(4,6) + "월 " + date.substring(6,8) + "일 " + customDate.checkWeekDayWithKor(date);
                temp.price = "";
                arrayListWeek.add(temp);
            }
            struct struct = arrayList_expense.get(i);
            struct.storeName = "    " + struct.storeName;
            arrayListWeek.add(struct);

            today = Integer.parseInt(date.substring(0,8));
        }

        for(int i = 0; i < arrayList_expense.size(); i++)
            if(arrayList_expense.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_expense.get(i).price); // price가 0이라 개망


        listCustomAdapter_expense.setItemDatas(arrayListWeek);
        TotalExpense.setText(String.valueOf(sum));

        ApplicationSingleton.getInstance().SetPageState(2);
    }

    public void onclickMonth(String str) {
        Log.i("tag", "month clicked");
        int sum = 0;
        int dayprice[] = new int[32];
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
                struct.storeName = customDate.strCurMonth + "월 " + String.valueOf(i + 1) + "일";
                if (i < 9) {
                    struct.memo = customDate.strCurYear + customDate.strCurMonth + '0' + String.valueOf(i + 1); // memo영역을 월별 리스트에서는 날짜를 기억하는 임시저장소로 사용
                } else {
                    struct.memo = customDate.strCurYear + customDate.strCurMonth + String.valueOf(i + 1);
                }
                arrayListMonth.add(struct);
            }
        }
        listCustomAdapter_expense.setItemDatas(arrayListMonth);

        TotalExpense.setText(String.valueOf(sum));

        ApplicationSingleton.getInstance().SetPageState(3);
    }

    // TODO : 월별에서 아이템 클릭시 한번은되는데 두번이 안됨 이유는 모름 쿼리문에서 막힘 개꿀
    private AdapterView.OnItemClickListener ExItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long l_position) {
            Log.i("tag", "click item");
            if(ApplicationSingleton.getInstance().GetPageState() == 3) {
                struct struct = arrayListMonth.get(position);
                onclickToday(struct.memo);
            }
        }
    };
}
