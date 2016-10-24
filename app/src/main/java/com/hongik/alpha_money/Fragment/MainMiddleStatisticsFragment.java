package com.hongik.alpha_money.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.struct;
import com.hongik.alpha_money.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
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
    ArrayList<struct> arrayList;
    Date date;
    int t[] = {0,3,2,5,0,3,5,1,4,6,2,4};

    TextView week1day;
    TextView week2day;
    TextView week3day;
    TextView week4day;
    TextView week5day;
    TextView week6day;
    TextView week7day;
    TextView week1price;
    TextView week2price;
    TextView week3price;
    TextView week4price;
    TextView week5price;
    TextView week6price;
    TextView week7price;


    ApplicationSingleton instance = ApplicationSingleton.getInstance();

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

        week1day = (TextView)rootView.findViewById(R.id.week_graph_1_day);
        week2day = (TextView)rootView.findViewById(R.id.week_graph_2_day);
        week3day = (TextView)rootView.findViewById(R.id.week_graph_3_day);
        week4day = (TextView)rootView.findViewById(R.id.week_graph_4_day);
        week5day = (TextView)rootView.findViewById(R.id.week_graph_5_day);
        week6day = (TextView)rootView.findViewById(R.id.week_graph_6_day);
        week7day = (TextView)rootView.findViewById(R.id.week_graph_7_day);
        week1price = (TextView)rootView.findViewById(R.id.week_graph_1_price);
        week2price = (TextView)rootView.findViewById(R.id.week_graph_2_price);
        week3price = (TextView)rootView.findViewById(R.id.week_graph_3_price);
        week4price = (TextView)rootView.findViewById(R.id.week_graph_4_price);
        week5price = (TextView)rootView.findViewById(R.id.week_graph_5_price);
        week6price = (TextView)rootView.findViewById(R.id.week_graph_6_price);
        week7price = (TextView)rootView.findViewById(R.id.week_graph_7_price);

        int y, m, d, checkday;

        class Week implements Comparable{
            int value;//sum of week value
            int week;//sun,mon,.... = 0,1,2...
            public Week(int n){
                value = 0;
                week = n;
            }
            public void SetValue(int n){value=n;}
            public int GetValue(){return value;}
            public String GetWeek(){
                if(week == 0)
                    return "SUN";
                else if(week == 1)
                    return "MON";
                else if(week == 2)
                    return "TUE";
                else if(week == 3)
                    return "WED";
                else if(week == 4)
                    return "THU";
                else if(week == 5)
                    return "FRI";
                else
                    return "SAT";
                }
            @Override
            public int compareTo(Object obj) {
                Week Other = (Week)obj;
                if(value > Other.value){
                    return -1;
                }
                else return 1;
            }
        };
        Week[] weeks = new Week[7];
        weeks[0] = new Week(0);
        weeks[1] = new Week(1);
        weeks[2] = new Week(2);
        weeks[3] = new Week(3);
        weeks[4] = new Week(4);
        weeks[5] = new Week(5);
        weeks[6] = new Week(6);

        struct struct;
        long now = System.currentTimeMillis();
        date = new Date(now); // 현재시간을 받고

        // 시간 포맷 지정

        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
        // 지정된 포맷으로 String 타입 리턴
        String strCurYear = CurYearFormat.format(date);
        String strCurMonth = CurMonthFormat.format(date);
        String strCurDay;
        String strCurYearMonth = strCurYear + strCurMonth; // yyyymm 형식의 string

        arrayList = instance.GetExpenseList(1, strCurYearMonth); // 해당 월의 DB 부름

        for(Iterator<struct> iterator = arrayList.iterator(); iterator.hasNext();) { // 가져온 데이터를 돌면서
            struct = iterator.next();

            strCurYear = struct.date.substring(0, 3);
            strCurMonth = struct.date.substring(4, 5);
            strCurDay = struct.date.substring(6, 7);

            Log.i("TAG", strCurYear + " " + strCurMonth + " " + strCurDay);

            //요일 계산
            y = Integer.parseInt(strCurYear);
            m = Integer.parseInt(strCurMonth);
            d = Integer.parseInt(strCurDay);

            if (m < 3) {
                y--;
            }
            checkday = (y + (y / 4) - (y / 100) + (y / 400) + t[m - 1] + d) % 7; // 0 = SUN ~ 6 = SAT

            switch (checkday){ // TODO : @EMERGENCY@ 값을 더하는 과정에 오류가 있음. SAT가 0임
                case 0:
                    weeks[0].SetValue( weeks[0].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 1:
                    weeks[1].SetValue( weeks[1].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 2:
                    weeks[2].SetValue( weeks[2].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 3:
                    weeks[3].SetValue( weeks[3].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 4:
                    weeks[4].SetValue( weeks[4].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 5:
                    weeks[5].SetValue( weeks[5].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 6:
                    weeks[6].SetValue( weeks[6].GetValue() + Integer.parseInt(struct.price));
                    break;
            }
            Arrays.sort(weeks);

            weeks[0].GetWeek();
            Log.i("TAG",weeks[0].GetValue() + "  " +  weeks[0].GetWeek());

            week1day.setText(weeks[0].GetWeek());
            week2day.setText(weeks[1].GetWeek());
            week3day.setText(weeks[2].GetWeek());
            week4day.setText(weeks[3].GetWeek());
            week5day.setText(weeks[4].GetWeek());
            week6day.setText(weeks[5].GetWeek());
            week7day.setText(weeks[6].GetWeek());

            week1price.setText(String.valueOf(weeks[0].GetValue()));
            week2price.setText(String.valueOf(weeks[1].GetValue()));
            week3price.setText(String.valueOf(weeks[2].GetValue()));
            week4price.setText(String.valueOf(weeks[3].GetValue()));
            week5price.setText(String.valueOf(weeks[4].GetValue()));
            week6price.setText(String.valueOf(weeks[5].GetValue()));
            week7price.setText(String.valueOf(weeks[6].GetValue()));

        }

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


