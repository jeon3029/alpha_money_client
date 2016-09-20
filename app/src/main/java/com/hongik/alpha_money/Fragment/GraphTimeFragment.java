package com.hongik.alpha_money.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongik.alpha_money.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraphTimeFragment extends Fragment {


    public GraphTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.graph_time_fragment, container, false);
    }

}
