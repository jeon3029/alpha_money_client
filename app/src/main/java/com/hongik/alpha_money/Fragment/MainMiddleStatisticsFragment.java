package com.hongik.alpha_money.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongik.alpha_money.R;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainMiddleStatisticsFragment extends Fragment {
    View rootViewBasic;
    public MainMiddleStatisticsFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_statistics_graph, container, false);
        return rootViewBasic;
    }
}
