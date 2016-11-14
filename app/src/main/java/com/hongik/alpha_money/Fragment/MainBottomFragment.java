package com.hongik.alpha_money.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hongik.alpha_money.Activity.MainActivity;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.CustomDate;
import com.hongik.alpha_money.R;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainBottomFragment extends Fragment {
    View rootViewBasic;
    Button buttonToday, buttonWeek, buttonMonth;
    CustomDate customDate;
    Context ctx = ApplicationSingleton.getInstance().GetMainActivityContext();

    public MainBottomFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootViewBasic = inflater.inflate(R.layout.main_bottom_menu_fragment, container, false);

        customDate = new CustomDate();

        buttonToday = (Button)rootViewBasic.findViewById(R.id.buttonToday);
        buttonWeek = (Button)rootViewBasic.findViewById(R.id.buttonWeek);
        buttonMonth = (Button)rootViewBasic.findViewById(R.id.buttonMonth);

        buttonToday.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) { // TODO : 통계갖다왔을때 버튼 씹히는현상 고치기
                buttonToday.setBackgroundColor(0xffaf8944);
                buttonWeek.setBackgroundColor(0xff9abae6);
                buttonMonth.setBackgroundColor(0xff9abae6);
                buttonToday.invalidate();
                buttonWeek.invalidate();
                buttonMonth.invalidate();

                Log.i("tag", String.valueOf(((MainActivity)ctx).GetPageState()));
                if(((MainActivity)ctx).GetPageState() < 4) {
                    ApplicationSingleton.getInstance().GetExpenseFragment().onclickToday(customDate.strCurYearMonthDay);
                }
                else {
                    ApplicationSingleton.getInstance().GetIncomeFragment().onclickToday(customDate.strCurYearMonthDay);
                }
            }
        });

        buttonWeek.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonToday.setBackgroundColor(0xff9abae6);
                buttonWeek.setBackgroundColor(0xffaf8944);
                buttonMonth.setBackgroundColor(0xff9abae6);
                buttonToday.invalidate();
                buttonWeek.invalidate();
                buttonMonth.invalidate();

                Log.i("tag", String.valueOf(((MainActivity)ctx).GetPageState()));
                if(((MainActivity)ctx).GetPageState() < 4)
                    ApplicationSingleton.getInstance().GetExpenseFragment().onclickWeek(customDate.strCurYearMonthDay);
                else
                    ApplicationSingleton.getInstance().GetIncomeFragment().onclickWeek(customDate.strCurYearMonthDay);
            }
        });

        buttonMonth.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonToday.setBackgroundColor(0xff9abae6);
                buttonWeek.setBackgroundColor(0xff9abae6);
                buttonMonth.setBackgroundColor(0xffaf8944);
                buttonToday.invalidate();
                buttonWeek.invalidate();
                buttonMonth.invalidate();

                Log.i("tag", String.valueOf(((MainActivity)ctx).GetPageState()));
                if(((MainActivity)ctx).GetPageState() < 4) {
                    ApplicationSingleton.getInstance().GetExpenseFragment().onclickMonth(customDate.strCurYearMonthDay);
                    Log.i("tag", "click month Ex");
                }
                else {
                    ApplicationSingleton.getInstance().GetIncomeFragment().onclickMonth(customDate.strCurYearMonthDay);
                    Log.i("tag", "click month In");
                }
            }
        });

        return rootViewBasic;
    }
}
