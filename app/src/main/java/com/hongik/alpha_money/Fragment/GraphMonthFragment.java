package com.hongik.alpha_money.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

import java.util.ArrayList;
import java.util.List;

public class GraphMonthFragment extends Fragment {

    PieChart MonthChart;

    public GraphMonthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.graph_month_fragment, container, false);
        // Inflate the layout for this fragment
        MonthChart = (PieChart) rootView.findViewById(R.id.month_graph);

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(entries, "Month Usage");

        set.setColors(new int [] {R.color.rainbow1, R.color.rainbow2, R.color.rainbow3, R.color.rainbow4, R.color.rainbow5, R.color.rainbow6, R.color.rainbow7}, ApplicationSingleton.getInstance().GetMainActivityContext());
        PieData data = new PieData(set);

        data.setHighlightEnabled(false); // highlight 삭제

        MonthChart.getDescription().setEnabled(false); // 설명삭제


        MonthChart.setData(data);
        MonthChart.invalidate(); // refresh

        return rootView;
    }

}
