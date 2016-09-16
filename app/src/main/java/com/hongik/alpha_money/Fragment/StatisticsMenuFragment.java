package com.hongik.alpha_money.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

/*
 * Created by jeon3029 on 16. 9. 13..
 */
public class StatisticsMenuFragment extends Fragment{

    View rootViewBasic;
    Context ctx;
    LinearLayout menuLayout;
    Button menuBtn;
    RelativeLayout relativeLayout;
    TextView menu1;
    TextView menu2;
    TextView menu3;
    TextView menu4;
    boolean updownState;

    public StatisticsMenuFragment(){
        updownState = false; //false = down true = up
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_statistics_menu,container,false);
        ctx = ApplicationSingleton.getInstance().getApplicationContext();

        return rootViewBasic;
    }
}
