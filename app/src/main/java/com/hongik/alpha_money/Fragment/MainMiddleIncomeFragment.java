package com.hongik.alpha_money.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hongik.alpha_money.ApplicationSingleton;
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
    ListCustomAdapter listCustomAdapter_1;
    ArrayList<struct> arrayList_income;
    public MainMiddleIncomeFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_income_list, container, false);
        listView_income = (ListView)rootViewBasic.findViewById(R.id.main_income_listview);
        arrayList_income = ApplicationSingleton.getInstance().GetIncomeList(0,"");

        listCustomAdapter_1 = new ListCustomAdapter(arrayList_income, ApplicationSingleton.getInstance());
        listView_income.setAdapter(listCustomAdapter_1);
        return rootViewBasic;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //TODO : same
        arrayList_income = ApplicationSingleton.getInstance().GetExpenseList(0,"");//get all data
        listCustomAdapter_1.notifyDataSetChanged();
    }

}
