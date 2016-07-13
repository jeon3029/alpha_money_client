package com.hongik.alpha_money;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hongik.alpha_money.Fragment.MainBottomFragment;
import com.hongik.alpha_money.Fragment.MainViewPagerFragment;
import com.hongik.alpha_money.Fragment.MainTopFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    MainTopFragment mainTopFragment;
    MainViewPagerFragment mainViewPagerFragment;
    MainBottomFragment mainBottomFragment;
    FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiate();
        if (savedInstanceState == null) {
            //프래그먼트 5가지 세팅
            fragmentManager.beginTransaction().add(R.id.main_topmenu_layout, mainTopFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_middle_layout, mainViewPagerFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_bottom_layout, mainBottomFragment).commit();
        }
    }
    public FragmentManager GetFM(){return fm;}
    private void initiate() {
        ApplicationSingleton.getInstance().SetMainActivityContext(this);
        mainBottomFragment = new MainBottomFragment();
        mainViewPagerFragment = new MainViewPagerFragment();
        mainTopFragment =  new MainTopFragment();
    }
    public void ShowMainExpenseList(){
        fragmentManager.beginTransaction().replace(R.id.main_topmenu_layout, mainTopFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.main_middle_layout, mainViewPagerFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.main_bottom_layout, mainBottomFragment).commit();
    }
}
