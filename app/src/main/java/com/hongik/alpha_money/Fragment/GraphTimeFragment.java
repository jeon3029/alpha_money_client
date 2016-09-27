package com.hongik.alpha_money.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraphTimeFragment extends Fragment {

    HorizontalBarChart TimeChart;

    public GraphTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.graph_time_fragment, container, false);
        // Inflate the layout for this fragment
        TimeChart = (HorizontalBarChart) rootView.findViewById(R.id.timegraph);

        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0, 0, "t"));
        entries.add(new BarEntry(1, new float[] {4, 4, 2, 6, 4}, "test1"));
        entries.add(new BarEntry(2, new float[] {2, 7, 10, 1}, "Yellow"));
        entries.add(new BarEntry(3, new float[] {5, 2, 5, 4, 4}, "Red"));
        entries.add(new BarEntry(4, new float[] {3, 3, 1, 2, 5, 2, 4}, "Blue"));
        entries.add(new BarEntry(5, 0, "t2"));

        BarDataSet set = new BarDataSet(entries, "Time Usage");
        set.setColors(new int [] {R.color.rainbow1, R.color.rainbow2, R.color.rainbow3, R.color.rainbow4, R.color.rainbow5, R.color.rainbow6, R.color.rainbow7}, ApplicationSingleton.getInstance().GetMainActivityContext());

        BarData data = new BarData(set);
        data.setHighlightEnabled(false); // highlight 삭제
        data.setDrawValues(false); // data 위에 써지는 값 삭제

        TimeChart.setScaleEnabled(false); // zoom 삭제

        
        // X,Y선, 축이름 삭제
        TimeChart.getXAxis().setDrawGridLines(false);
        TimeChart.getXAxis().setDrawAxisLine(false);
        TimeChart.getXAxis().setDrawLabels(false);
        TimeChart.getXAxis().setDrawLimitLinesBehindData(false);
        TimeChart.getAxisLeft().setDrawAxisLine(false);
        TimeChart.getAxisLeft().setDrawGridLines(false);
        TimeChart.getAxisLeft().setDrawLabels(false);
        TimeChart.getAxisRight().setDrawGridLines(false);
        TimeChart.getAxisRight().setDrawAxisLine(false);
        TimeChart.getAxisRight().setDrawLabels(false);

        TimeChart.getLegend().setEnabled(false); // 축척삭제
        TimeChart.getDescription().setEnabled(false); // 설명삭제

        TimeChart.setData(data);
        TimeChart.invalidate(); // refresh

        return rootView;
    }
}
