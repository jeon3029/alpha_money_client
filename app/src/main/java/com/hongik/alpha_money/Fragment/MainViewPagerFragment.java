package com.hongik.alpha_money.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.Activity.MainActivity;
import com.hongik.alpha_money.R;
import com.hongik.alpha_money.ViewPager.ViewPagerCustomAdapter;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainViewPagerFragment extends Fragment {
    View rootViewBasic;
    ViewPager viewPager;
    TabPageIndicator tabPageIndicator;

    ViewPagerCustomAdapter viewPagerCustomAdapter = new ViewPagerCustomAdapter(((MainActivity)ApplicationSingleton.getInstance().GetMainActivityContext()).GetFM());
    public MainViewPagerFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_middle_viewpager_fragment, container, false);

        viewPager = (ViewPager)rootViewBasic.findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerCustomAdapter);

        tabPageIndicator = (TabPageIndicator)rootViewBasic.findViewById(R.id.id_indicator);
        tabPageIndicator.setViewPager(viewPager);
        return rootViewBasic;
    }
}
