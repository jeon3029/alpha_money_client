package com.hongik.alpha_money.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
    ViewPager.OnPageChangeListener listener;
    TabPageIndicator tabPageIndicator;
    FragmentManager fragmentManager = ((MainActivity)ApplicationSingleton.getInstance().GetMainActivityContext()).GetFM();
    Context ctx = ApplicationSingleton.getInstance().GetMainActivityContext();

    ViewPagerCustomAdapter viewPagerCustomAdapter = new ViewPagerCustomAdapter(fragmentManager);
    public MainViewPagerFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_middle_viewpager_fragment, container, false);

        viewPager = (ViewPager)rootViewBasic.findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerCustomAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2) {
                    fragmentManager.beginTransaction().replace(R.id.main_bottom_layout, ((MainActivity)ctx).GetEmptyFragment()).commit();
                    fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, ((MainActivity)ctx).GetStatisticsMenuFragment()).commit();
                }
                else { //it is same state on income, expense so use same command hangul anduim
                    fragmentManager.beginTransaction().replace(R.id.main_bottom_layout, ((MainActivity)ctx).GetMainBottomFragment()).commit();
                    fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, ((MainActivity)ctx).GetEmptyFragment2()).commit();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabPageIndicator = (TabPageIndicator)rootViewBasic.findViewById(R.id.id_indicator);
        tabPageIndicator.setViewPager(viewPager);

        return rootViewBasic;
    }

    /* public void SetFragment(int pos,Fragment f){
       viewPagerCustomAdapter.SetFragment(pos,f);
    }*/
}
