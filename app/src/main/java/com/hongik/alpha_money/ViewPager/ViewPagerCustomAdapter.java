package com.hongik.alpha_money.ViewPager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import com.hongik.alpha_money.Fragment.MainMiddleExpenseFragment;
import com.hongik.alpha_money.Fragment.MainMiddleIncomeFragment;
import com.hongik.alpha_money.Fragment.MainMiddleStatisticsFragment;
import com.hongik.alpha_money.R;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class ViewPagerCustomAdapter extends FragmentPagerAdapter {
    Fragment[] fragments = new Fragment[3];

    public ViewPagerCustomAdapter(FragmentManager fm) {
        super(fm);
        fragments[0] = new MainMiddleExpenseFragment();
        fragments[1] = new MainMiddleIncomeFragment();
        fragments[2] = new MainMiddleStatisticsFragment();
    }
    public Fragment getItem(int arg0) {
        return fragments[arg0];
    }

    public int getCount() {
        return fragments.length;
    }

}
