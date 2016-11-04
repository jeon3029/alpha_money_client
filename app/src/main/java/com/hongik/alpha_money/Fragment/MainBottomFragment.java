package com.hongik.alpha_money.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.CustomDate;
import com.hongik.alpha_money.R;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainBottomFragment extends Fragment {
    View rootViewBasic;
    Button buttonToday, buttonWeek, buttonMonth;
    CustomDate customDate = new CustomDate();
    MainMiddleExpenseFragment mainMiddleExpenseFragment = ApplicationSingleton.getInstance().GetExpenseFragment();
    MainMiddleIncomeFragment mainMiddleIncomeFragment = ApplicationSingleton.getInstance().GetIncomeFragment();

    public MainBottomFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootViewBasic = inflater.inflate(R.layout.main_bottom_menu_fragment, container, false);

        buttonToday = (Button)rootViewBasic.findViewById(R.id.buttonToday);
        buttonWeek = (Button)rootViewBasic.findViewById(R.id.buttonWeek);
        buttonMonth = (Button)rootViewBasic.findViewById(R.id.buttonMonth);

        buttonToday.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ApplicationSingleton.getInstance().GetPageState() < 4)
                    mainMiddleExpenseFragment.onclickToday(customDate.strCurYearMonthDay);
                else
                    mainMiddleIncomeFragment.onclickToday(customDate.strCurYearMonthDay);
            }
        });

        buttonWeek.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ApplicationSingleton.getInstance().GetPageState() < 4)
                    mainMiddleExpenseFragment.onclickWeek(customDate.strCurYearMonthDay);
                else
                    mainMiddleIncomeFragment.onclickWeek(customDate.strCurYearMonthDay);
            }
        });

        buttonMonth.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ApplicationSingleton.getInstance().GetPageState() < 4)
                    mainMiddleExpenseFragment.onclickMonth(customDate.strCurYearMonthDay);
                else
                    mainMiddleIncomeFragment.onclickMonth(customDate.strCurYearMonthDay);

            }
        });

        return rootViewBasic;
    }
}
