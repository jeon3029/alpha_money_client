package com.hongik.alpha_money.ViewPager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongik.alpha_money.Fragment.MainMiddleExpenseFragment;
import com.hongik.alpha_money.Fragment.MainMiddleIncomeFragment;
import com.hongik.alpha_money.Fragment.MainMiddleStatisticsFragment;
import com.hongik.alpha_money.R;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class ViewPagerCustomAdapter extends FragmentPagerAdapter {
    private static final String[] CONTENT = new String[]{"지출","수입","통계"};
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(position == 0){

        }
        else if(position == 1){

        }
        else if(position == 2){

        }
        return super.instantiateItem(container, position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position % CONTENT.length];
    }

    public int getCount() {
        return fragments.length;
    }

}
