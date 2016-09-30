
package com.hongik.alpha_money.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hongik.alpha_money.Activity.MainActivity;
import com.hongik.alpha_money.ApplicationSingleton;
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
    ListCustomAdapter listCustomAdapter_2;
    ArrayList<struct> arrayList_expense;
    public MainMiddleExpenseFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_expense_list, container, false);
        listView_expense = (ListView)rootViewBasic.findViewById(R.id.main_expense_listview);

        //TODO : 상황에 맞는 (월,주,일) 출력 해야 함.
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(0,"");//get all data
        listCustomAdapter_2 = new ListCustomAdapter(arrayList_expense, ApplicationSingleton.getInstance());
        listView_expense.setAdapter(listCustomAdapter_2);

        return rootViewBasic;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //TODO : same
        arrayList_expense = ApplicationSingleton.getInstance().GetExpenseList(0,"");//get all data
        listCustomAdapter_2.notifyDataSetChanged();
    }

}
