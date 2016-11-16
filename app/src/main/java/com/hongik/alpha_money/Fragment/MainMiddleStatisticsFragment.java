package com.hongik.alpha_money.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.CustomDate;
import com.hongik.alpha_money.DataStructure.struct;
import com.hongik.alpha_money.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    RelativeLayout Graph_Layout1;
    ScrollView Graph_Layout2;
    LinearLayout Graph_Layout3;
    RelativeLayout Graph_Layout4;
    ArrayList<struct> arrayList;
    CustomDate customDate = new CustomDate();

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

    TextView date_monthGraph;
    TextView date_paymentGraph;
    TextView date_weekGraph;
    String str_date_MonthGraph;
    String str_date_PaymentGraph;
    String str_date_WeekGraph;
    ImageView date_left_month, date_right_month, date_left_payment, date_right_payment, date_left_week, date_right_week;


    ApplicationSingleton instance = ApplicationSingleton.getInstance();

    public MainMiddleStatisticsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_statistics_graph, container, false);

        date_monthGraph = (TextView)rootView.findViewById(R.id.date_monthGraph);
        date_paymentGraph = (TextView)rootView.findViewById(R.id.date_paymentGraph);
        date_weekGraph = (TextView)rootView.findViewById(R.id.date_weekGraph);
        date_monthGraph.setText(customDate.strCurYearMonth.substring(0,4) + "." + customDate.strCurYearMonth.substring(4,6));
        date_paymentGraph.setText(customDate.strCurYearMonth.substring(0,4) + "." + customDate.strCurYearMonth.substring(4,6));
        date_weekGraph.setText(customDate.strCurYearMonth.substring(0,4) + "." + customDate.strCurYearMonth.substring(4,6));
        str_date_MonthGraph = customDate.strCurYearMonth;
        str_date_PaymentGraph = customDate.strCurYearMonth;
        str_date_WeekGraph = customDate.strCurYearMonth;
        date_left_month = (ImageView)rootView.findViewById(R.id.date_left_monthGraph);
        date_right_month = (ImageView)rootView.findViewById(R.id.date_right_monthGraph);
        date_left_payment = (ImageView)rootView.findViewById(R.id.date_left_paymentGraph);
        date_right_payment = (ImageView)rootView.findViewById(R.id.date_right_paymentGraph);
        date_left_week = (ImageView)rootView.findViewById(R.id.date_left_weekGraph);
        date_right_week = (ImageView)rootView.findViewById(R.id.date_right_weekGraph);

        date_left_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag", "left click");
                str_date_MonthGraph = customDate.GetBeforeMonth(str_date_MonthGraph);
                date_monthGraph.setText(str_date_MonthGraph.substring(0,4) + "." + str_date_MonthGraph.substring(4,6));
                ShowMonthGraph(str_date_MonthGraph);
            }
        });

        date_right_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag", "right click");
                str_date_MonthGraph = customDate.GetNextMonth(str_date_MonthGraph);
                date_monthGraph.setText(str_date_MonthGraph.substring(0,4) + "." + str_date_MonthGraph.substring(4,6));
                ShowMonthGraph(str_date_MonthGraph);
            }
        });

        date_left_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag", "left click");
                str_date_PaymentGraph = customDate.GetBeforeMonth(str_date_PaymentGraph);
                date_paymentGraph.setText(str_date_PaymentGraph.substring(0,4) + "." + str_date_PaymentGraph.substring(4,6));
                ShowPaymentGraph(str_date_PaymentGraph);
            }
        });

        date_right_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag", "left click");
                str_date_PaymentGraph = customDate.GetNextMonth(str_date_PaymentGraph);
                date_paymentGraph.setText(str_date_PaymentGraph.substring(0,4) + "." + str_date_PaymentGraph.substring(4,6));
                ShowPaymentGraph(str_date_PaymentGraph);
            }
        });

        date_left_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_date_WeekGraph = customDate.GetBeforeMonth(str_date_WeekGraph);
                date_weekGraph.setText(str_date_WeekGraph.substring(0,4) + "." + str_date_WeekGraph.substring(4,6));
                ShowWeekGraph(str_date_WeekGraph);
            }
        });

        date_right_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_date_WeekGraph = customDate.GetNextMonth(str_date_WeekGraph);
                date_weekGraph.setText(str_date_WeekGraph.substring(0,4) + "." + str_date_WeekGraph.substring(4,6));
                ShowWeekGraph(str_date_WeekGraph);
            }
        });

        MonthChart = (PieChart) rootView.findViewById(R.id.month_graph);
        //WeekChart = (ListView) rootView.findViewById(R.id.week_graph);
        TimeChart = (HorizontalBarChart) rootView.findViewById(R.id.time_graph);
        PaymentChart = (PieChart) rootView.findViewById(R.id.payment_graph);
        Graph_Layout1 = (RelativeLayout)rootView.findViewById(R.id.graph_layout1);
        Graph_Layout2 = (ScrollView)rootView.findViewById(R.id.graph_layout2);
        Graph_Layout3 = (LinearLayout)rootView.findViewById(R.id.graph_layout3);
        Graph_Layout4 = (RelativeLayout)rootView.findViewById(R.id.graph_layout4);
        ShowMonthGraph(customDate.strCurYearMonth);

        return rootView;
    }

    public void ShowMonthGraph(String str){//graph1
        Graph_Layout1.setVisibility(LinearLayout.VISIBLE);
        Graph_Layout2.setVisibility(LinearLayout.GONE);
        Graph_Layout3.setVisibility(LinearLayout.GONE);
        Graph_Layout4.setVisibility(LinearLayout.GONE);

        List<PieEntry> monthentries = new ArrayList<>();
        ArrayList<struct> arrayList;

        int Cate = 4, i;
        int NotCate = 0, food = 0, living = 0, leisure = 0, sum = 0;

        arrayList = ApplicationSingleton.getInstance().GetExpenseList(1, str);

        for(i = 0; i < arrayList.size(); i++)
        {
            if(arrayList.get(i).category.length() != 0)
                Cate = Integer.parseInt(arrayList.get(i).category.substring(0,1));
            if(Cate == 0){
                NotCate += Integer.parseInt(arrayList.get(i).price);
            }
            else if(Cate == 1) {
                food += Integer.parseInt(arrayList.get(i).price);
            }
            else if(Cate == 2){
                living += Integer.parseInt(arrayList.get(i).price);
            }
            else if(Cate == 3) {
                leisure += Integer.parseInt(arrayList.get(i).price);
            }

            sum += Integer.parseInt(arrayList.get(i).price);
        }

        if(NotCate != 0)
            monthentries.add(new PieEntry(NotCate, "미분류"));
        if(food != 0)
            monthentries.add(new PieEntry(food, "식비"));
        if(living != 0)
            monthentries.add(new PieEntry(living, "생활비"));
        if(leisure != 0)
            monthentries.add(new PieEntry(leisure, "여가"));

        PieDataSet set = new PieDataSet(monthentries, " ");

        set.setColors(new int [] {R.color.rainbow1, R.color.rainbow2, R.color.rainbow3, R.color.rainbow4, R.color.rainbow5, R.color.rainbow6, R.color.rainbow7, R.color.rainbow8, R.color.rainbow9, R.color.rainbow10, R.color.rainbow11, R.color.rainbow12, R.color.rainbow13, R.color.rainbow14, R.color.rainbow15, R.color.rainbow16, R.color.rainbow17, R.color.rainbow18, R.color.rainbow19, R.color.rainbow20, R.color.rainbow21, R.color.rainbow22, R.color.rainbow23, R.color.rainbow24, R.color.rainbow25, R.color.rainbow26, R.color.rainbow27, R.color.rainbow28, R.color.rainbow29, R.color.rainbow30, R.color.rainbow31}, ApplicationSingleton.getInstance().GetMainActivityContext());
        set.setValueTextSize(22.0f);
        PieData data = new PieData(set);

        data.setHighlightEnabled(false); // highlight 삭제

        MonthChart.setEntryLabelTextSize(22.0f);
        MonthChart.setEntryLabelColor(Color.BLACK);
        MonthChart.getDescription().setEnabled(false); // 설명삭제
        MonthChart.getLegend().setFormSize(20.0f);
        MonthChart.getLegend().setTextSize(20.0f);
        MonthChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        MonthChart.setCenterText(String.valueOf(NumberFormat.getIntegerInstance().format(sum)) + "원");
        MonthChart.setCenterTextSize(28.0f);

        MonthChart.setRotationEnabled(false);

        MonthChart.setData(data);
        MonthChart.invalidate(); // refresh
    }


    public void ShowWeekGraph(String str) {//graph2
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

        int checkday;

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
        arrayList = instance.GetExpenseList(1, str); // 해당 월의 DB 부름

        for(Iterator<struct> iterator = arrayList.iterator(); iterator.hasNext();) { // 가져온 데이터를 돌면서
            struct = iterator.next();

            checkday = customDate.CheckWeekDay(struct.date);

            switch (checkday) { // TODO : @EMERGENCY@ 값을 더하는 과정에 오류가 있음. SAT가 0임
                case 0:
                    weeks[0].SetValue(weeks[0].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 1:
                    weeks[1].SetValue(weeks[1].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 2:
                    weeks[2].SetValue(weeks[2].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 3:
                    weeks[3].SetValue(weeks[3].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 4:
                    weeks[4].SetValue(weeks[4].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 5:
                    weeks[5].SetValue(weeks[5].GetValue() + Integer.parseInt(struct.price));
                    break;
                case 6:
                    weeks[6].SetValue(weeks[6].GetValue() + Integer.parseInt(struct.price));
                    break;
            }
        }
            Arrays.sort(weeks);

            Log.i("TAG",weeks[6].GetValue() + "  " +  weeks[6].GetWeek());

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



    public void ShowTimeGraph() {//graph3

        Toast.makeText(instance.GetMainActivityContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();


        /*Graph_Layout3.setVisibility(LinearLayout.VISIBLE);
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
        set.setColors(new int [] {R.color.rainbow1, R.color.rainbow2, R.color.rainbow3, R.color.rainbow4, R.color.rainbow4, R.color.rainbow5, R.color.rainbow6}, ApplicationSingleton.getInstance().GetMainActivityContext());

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
        */
    }

    public void ShowPaymentGraph(String str) {//graph4
        Graph_Layout4.setVisibility(LinearLayout.VISIBLE);
        Graph_Layout1.setVisibility(LinearLayout.GONE);
        Graph_Layout2.setVisibility(LinearLayout.GONE);
        Graph_Layout3.setVisibility(LinearLayout.GONE);

        List<PieEntry> paymententries = new ArrayList<>();
        ArrayList<struct> arrayList;

        String payment[] = new String[30];
        int paymentsum[] = new int[30];
        boolean find = false;
        int count = 0, sum = 0, i, j;

        arrayList = ApplicationSingleton.getInstance().GetExpenseList(1, str);

        for(i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).payment != null && arrayList.get(i).payment.length() != 0) {
                    for (j = 0; j < count; j++) {
                        if (payment[j].equals(arrayList.get(i).payment)) {
                            paymentsum[j] += Integer.parseInt(arrayList.get(i).price);
                            sum += Integer.parseInt(arrayList.get(i).price);
                            find = true;
                            break;
                        }
                    }
                    if (find == false) {
                        payment[count] = arrayList.get(i).payment;
                        paymentsum[count] += Integer.parseInt(arrayList.get(i).price);
                        sum += Integer.parseInt(arrayList.get(i).price);
                        count++;
                    }
                }
            find = false;
        }

        for(i = 0; i < count; i++) {
            paymententries.add(new PieEntry(paymentsum[i], payment[i]));
        }

        PieDataSet set = new PieDataSet(paymententries, " ");



        set.setColors(new int [] {R.color.rainbow1, R.color.rainbow2, R.color.rainbow3, R.color.rainbow4, R.color.rainbow5, R.color.rainbow6, R.color.rainbow7, R.color.rainbow8, R.color.rainbow9, R.color.rainbow10, R.color.rainbow11, R.color.rainbow12, R.color.rainbow13, R.color.rainbow14, R.color.rainbow15, R.color.rainbow16, R.color.rainbow17, R.color.rainbow18, R.color.rainbow19, R.color.rainbow20, R.color.rainbow21, R.color.rainbow22, R.color.rainbow23, R.color.rainbow24, R.color.rainbow25, R.color.rainbow26, R.color.rainbow27, R.color.rainbow28, R.color.rainbow29, R.color.rainbow30, R.color.rainbow31}, ApplicationSingleton.getInstance().GetMainActivityContext());
        set.setValueTextSize(22.0f);
        PieData data = new PieData(set);

        data.setHighlightEnabled(false); // highlight 삭제

        PaymentChart.setEntryLabelTextSize(22.0f);
        PaymentChart.setEntryLabelColor(Color.BLACK);
        PaymentChart.getDescription().setEnabled(false); // 설명삭제
        PaymentChart.getLegend().setFormSize(20.0f);
        PaymentChart.getLegend().setTextSize(20.0f);
        PaymentChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        PaymentChart.setCenterText(String.valueOf(NumberFormat.getIntegerInstance().format(sum)) + "원");
        PaymentChart.setCenterTextSize(28.0f);

        PaymentChart.setRotationEnabled(false);

        PaymentChart.setData(data);
        PaymentChart.invalidate(); // refresh

    }

}


