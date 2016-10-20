package com.hongik.alpha_money.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainMiddleStatisticsFragment extends Fragment {
    View rootView;
    PieChart MonthChart;
    PieChart PaymentChart;
    HorizontalBarChart TimeChart;
    ListView WeekChart;
    LinearLayout Graph_Layout1;
    LinearLayout Graph_Layout2;
    LinearLayout Graph_Layout3;
    LinearLayout Graph_Layout4;

    public MainMiddleStatisticsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_statistics_graph, container, false);

        MonthChart = (PieChart) rootView.findViewById(R.id.month_graph);
        //WeekChart = (ListView) rootView.findViewById(R.id.week_graph);
        TimeChart = (HorizontalBarChart) rootView.findViewById(R.id.time_graph);
        PaymentChart = (PieChart) rootView.findViewById(R.id.payment_graph);
        Graph_Layout1 = (LinearLayout)rootView.findViewById(R.id.graph_layout1);
        Graph_Layout2 = (LinearLayout)rootView.findViewById(R.id.graph_layout2);
        Graph_Layout3 = (LinearLayout)rootView.findViewById(R.id.graph_layout3);
        Graph_Layout4 = (LinearLayout)rootView.findViewById(R.id.graph_layout4);
        ShowMonthGraph();

        return rootView;
    }

    public void ShowMonthGraph(){//graph1
        Graph_Layout1.setVisibility(LinearLayout.VISIBLE);
        Graph_Layout2.setVisibility(LinearLayout.GONE);
        Graph_Layout3.setVisibility(LinearLayout.GONE);
        Graph_Layout4.setVisibility(LinearLayout.GONE);

        List<PieEntry> monthentries = new ArrayList<>();

        monthentries.add(new PieEntry(18.5f, "Green"));
        monthentries.add(new PieEntry(26.7f, "Yellow"));
        monthentries.add(new PieEntry(24.0f, "Red"));
        monthentries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(monthentries, "Month Usage");

        set.setColors(new int [] {R.color.rainbow1, R.color.rainbow2, R.color.rainbow3, R.color.rainbow4, R.color.rainbow5, R.color.rainbow6, R.color.rainbow7}, ApplicationSingleton.getInstance().GetMainActivityContext());
        PieData data = new PieData(set);

        data.setHighlightEnabled(false); // highlight 삭제

        MonthChart.getDescription().setEnabled(false); // 설명삭제

        MonthChart.setData(data);
        MonthChart.invalidate(); // refresh
    }

    public void ShowWeekGraph() {//graph2
        Graph_Layout2.setVisibility(LinearLayout.VISIBLE);
        Graph_Layout1.setVisibility(LinearLayout.GONE);
        Graph_Layout3.setVisibility(LinearLayout.GONE);
        Graph_Layout4.setVisibility(LinearLayout.GONE);



    }

    public void ShowTimeGraph() {//graph3
        Graph_Layout3.setVisibility(LinearLayout.VISIBLE);
        Graph_Layout1.setVisibility(LinearLayout.GONE);
        Graph_Layout2.setVisibility(LinearLayout.GONE);
        Graph_Layout4.setVisibility(LinearLayout.GONE);

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
    }

    public void ShowPaymentGraph() {//graph4
        Graph_Layout4.setVisibility(LinearLayout.VISIBLE);
        Graph_Layout1.setVisibility(LinearLayout.GONE);
        Graph_Layout2.setVisibility(LinearLayout.GONE);
        Graph_Layout3.setVisibility(LinearLayout.GONE);

        List<PieEntry> paymententries = new ArrayList<>();

        paymententries.add(new PieEntry(18.5f, "Green"));
        paymententries.add(new PieEntry(26.7f, "Yellow"));
        paymententries.add(new PieEntry(24.0f, "Red"));
        paymententries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(paymententries, "Payment Usage");

        set.setColors(new int [] {R.color.rainbow1, R.color.rainbow2, R.color.rainbow3, R.color.rainbow4, R.color.rainbow5, R.color.rainbow6, R.color.rainbow7}, ApplicationSingleton.getInstance().GetMainActivityContext());
        PieData data = new PieData(set);

        data.setHighlightEnabled(false); // highlight 삭제

        PaymentChart.getDescription().setEnabled(false); // 설명삭제

        PaymentChart.setData(data);
        PaymentChart.invalidate(); // refresh
    }

}


