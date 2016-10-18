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

/**
 * A simple {@link Fragment} subclass.
 */
public class GraphPaymentFragment extends Fragment {

    PieChart PaymentChart;

    public GraphPaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.graph_payment_fragment, container, false);

        PaymentChart = (PieChart) rootView.findViewById(R.id.payment_graph);

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(entries, "Payment Usage");

        set.setColors(new int [] {R.color.rainbow1, R.color.rainbow2, R.color.rainbow3, R.color.rainbow4, R.color.rainbow5, R.color.rainbow6, R.color.rainbow7}, ApplicationSingleton.getInstance().GetMainActivityContext());
        PieData data = new PieData(set);

        data.setHighlightEnabled(false); // highlight 삭제

        PaymentChart.getDescription().setEnabled(false); // 설명삭제


        PaymentChart.setData(data);
        PaymentChart.invalidate(); // refresh

        return rootView;
    }

}
