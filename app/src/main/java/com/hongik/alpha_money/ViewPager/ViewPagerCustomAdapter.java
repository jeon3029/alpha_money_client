package com.hongik.alpha_money.ViewPager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.hongik.alpha_money.Activity.MainActivity;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.Fragment.MainMiddleExpenseFragment;
import com.hongik.alpha_money.Fragment.MainMiddleIncomeFragment;
import com.hongik.alpha_money.Fragment.MainMiddleStatisticsFragment;

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

    public void SetFragment(int pos,Fragment f){
        fragments[pos]=f;
    }

    public Fragment getItem(int arg0) {
        return fragments[arg0];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //TODO : statistics 에 왔을 때 아래의 오늘 이번주 이번달 -> 메뉴 로 변화시켜야 함
        //Have a PROBLEM!! (NOT SOLVED)
        Context ctx = ApplicationSingleton.getInstance().GetMainActivityContext();
        if(position == 0){
            Log.i("TAGPAGE","0");
            ((MainActivity)ctx).ShowExpenseFragment();
        }
        else if(position == 1){
            Log.i("TAGPAGE","1");
            ((MainActivity)ctx).ShowIncomeFragment();
        }
        else if(position == 2){

            Log.i("TAGPAGE","2");
            ((MainActivity)ctx).ShowStatisticsFragment();
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
