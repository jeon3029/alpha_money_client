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
public class MainMiddleIncomeFragment extends Fragment {
    View rootViewBasic;
    ListView listView_income;
    ListCustomAdapter listCustomAdapter_income;
    ArrayList<struct> arrayList_income;
    TextView TotalIncome;
    Context ctx = ApplicationSingleton.getInstance().GetMainActivityContext();
    ArrayList<struct> arrayListMonth = new ArrayList<struct>();
    ArrayList<struct> arrayListWeek = new ArrayList<struct>();
    CustomDate customDate = new CustomDate();

    public MainMiddleIncomeFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_income_list, container, false);

        TotalIncome = (TextView)rootViewBasic.findViewById(R.id.totalprice2);

        listView_income = (ListView)rootViewBasic.findViewById(R.id.main_income_listview);

        listView_income.setOnItemClickListener(InItemClickListener);

        if(arrayListWeek.size() != 0)
            arrayListWeek.clear();

        int sum = 0, today = 0, nextday = 0;
        arrayList_income = ApplicationSingleton.getInstance().GetIncomeList(2, customDate.strCurYearMonthDay);

        for(int i = 0; i < arrayList_income.size(); i++) {
            String date = arrayList_income.get(i).date;
            nextday = Integer.parseInt(date.substring(0, 8));
            if (today < nextday){
                struct temp = new struct();
                temp.storeName = date.substring(4,6) + "월 " + date.substring(6,8) + "일 " + customDate.checkWeekDayWithKor(date);
                temp.price = "";
                arrayListWeek.add(temp);
            }
            struct struct = arrayList_income.get(i);
            struct.storeName = "    " + struct.storeName;
            arrayListWeek.add(struct);

            today = Integer.parseInt(date.substring(0, 8));
        }

        for(int i = 0; i < arrayList_income.size(); i++)
            if(arrayList_income.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_income.get(i).price); // price가 0이라 개망

        TotalIncome.setText(String.valueOf(sum));
        ApplicationSingleton.getInstance().SetPageState(5);

        listCustomAdapter_income = new ListCustomAdapter(arrayListWeek, ApplicationSingleton.getInstance());
        listView_income.setAdapter(listCustomAdapter_income);

        return rootViewBasic;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //TODO : same
        arrayList_income = ApplicationSingleton.getInstance().GetExpenseList(0,"");//get all data
        listCustomAdapter_income.notifyDataSetChanged();
    }

    public void onclickToday(String str) {
        Log.i("tag", "today clicked In");
        int sum = 0;
        arrayList_income = ApplicationSingleton.getInstance().GetExpenseList(3, str);
        listCustomAdapter_income.setItemDatas(arrayList_income);

        for (int i = 0; i < arrayList_income.size(); i++)
            if (arrayList_income.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_income.get(i).price);

        TotalIncome.setText(String.valueOf(sum));

        ApplicationSingleton.getInstance().SetPageState(4);
    }

    public void onclickWeek(String str) {
        Log.i("tag", "week clicked In");
        int sum = 0, today = 0, nextday;
        if(arrayListWeek.size() != 0)
            arrayListWeek.clear();
        arrayList_income = ApplicationSingleton.getInstance().GetExpenseList(2, str);

        for(int i = 0; i < arrayList_income.size(); i++) {
            String date = arrayList_income.get(i).date;
            nextday = Integer.parseInt(date.substring(0, 8));
            if (today < nextday){
                struct temp = new struct();
                temp.storeName = date.substring(4,6) + "월 " + date.substring(6,8) + "일 " + customDate.checkWeekDayWithKor(date);
                temp.price = "";
                arrayListWeek.add(temp);
            }
            struct struct = arrayList_income.get(i);
            struct.storeName = "    " + struct.storeName;
            arrayListWeek.add(struct);

            today = Integer.parseInt(date.substring(0,8));
        }

        for(int i = 0; i < arrayList_income.size(); i++)
            if(arrayList_income.get(i).price.length() != 0)
                sum += Integer.parseInt(arrayList_income.get(i).price); // price가 0이라 개망


        listCustomAdapter_income.setItemDatas(arrayListWeek);
        TotalIncome.setText(String.valueOf(sum));

        ApplicationSingleton.getInstance().SetPageState(5);
    }

    public void onclickMonth(String str) {
        Log.i("tag", "month clicked In");
        int sum = 0;
        int dayprice[] = new int[32];
        arrayListMonth.clear();
        arrayList_income = ApplicationSingleton.getInstance().GetExpenseList(1, str);

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
                struct.storeName = customDate.strCurMonth + "월 " + String.valueOf(i + 1) + "일";
                if (i < 9) {
                    struct.memo = customDate.strCurYear + customDate.strCurMonth + '0' + String.valueOf(i + 1); // memo영역을 월별 리스트에서는 날짜를 기억하는 임시저장소로 사용
                } else {
                    struct.memo = customDate.strCurYear + customDate.strCurMonth + String.valueOf(i + 1);
                }
                arrayListMonth.add(struct);
            }
        }
        listCustomAdapter_income.setItemDatas(arrayListMonth);

        TotalIncome.setText(String.valueOf(sum));

        ApplicationSingleton.getInstance().SetPageState(6);
    }

    // TODO : 월별에서 아이템 클릭시 한번은되는데 두번이 안됨 이유는 모름 쿼리문에서 막힘 개꿀
    private AdapterView.OnItemClickListener InItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long l_position) {
            Log.i("tag", "click item Income");
            if(ApplicationSingleton.getInstance().GetPageState() == 6) {
                struct struct = arrayListMonth.get(position);
                onclickToday(struct.memo);
            }
        }
    };
}
