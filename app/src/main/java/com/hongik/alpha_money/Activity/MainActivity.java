package com.hongik.alpha_money.Activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.Fragment.EmptyFragment;
import com.hongik.alpha_money.Fragment.GraphMonthFragment;
import com.hongik.alpha_money.Fragment.GraphPaymentFragment;
import com.hongik.alpha_money.Fragment.GraphTimeFragment;
import com.hongik.alpha_money.Fragment.GraphWeekFragment;
import com.hongik.alpha_money.Fragment.MainBottomFragment;
import com.hongik.alpha_money.Fragment.MainTopFragment;
import com.hongik.alpha_money.Fragment.MainViewPagerFragment;
import com.hongik.alpha_money.Fragment.StatisticsMenuFragment;
import com.hongik.alpha_money.R;
import com.hongik.alpha_money.Sms.SmsReceiver;

public class MainActivity extends AppCompatActivity {

    //Fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    MainTopFragment mainTopFragment;
    MainViewPagerFragment mainViewPagerFragment;
    EmptyFragment emptyFragment;
    EmptyFragment emptyFragment2;
    MainBottomFragment mainBottomFragment;
    StatisticsMenuFragment statisticsMenuFragment;

    GraphMonthFragment graphMonthFragment;
    GraphTimeFragment graphTimeFragment;
    GraphWeekFragment graphWeekFragment;
    GraphPaymentFragment graphPaymentFragment;
    BroadcastReceiver smsReceiver;

    FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiate();
        if (savedInstanceState == null) {
            //프래그먼트 3가지 세팅
            fragmentManager.beginTransaction().add(R.id.main_topmenu_layout, mainTopFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_middle_layout, mainViewPagerFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_bottom_layout, mainBottomFragment).commit();

            //통계에서 메뉴바 레이아웃으로 사용 될 것임
            fragmentManager.beginTransaction().add(R.id.statistics_bottom_layout,emptyFragment).commit();
            //처음엔 아무것도 안보이게 세팅
        }

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            // 권한 없음
            int i = 0;
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS}, i);
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provide.Telephony.SMS_RECEIVED");
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver, intentFilter);
    }
    public FragmentManager GetFM(){return fm;}
    private void initiate() {
        ApplicationSingleton.getInstance().SetMainActivityContext(this);
        mainBottomFragment = new MainBottomFragment();
        mainViewPagerFragment = new MainViewPagerFragment();
        mainTopFragment =  new MainTopFragment();
        emptyFragment = new EmptyFragment();
        emptyFragment2 = new EmptyFragment();
        statisticsMenuFragment = new StatisticsMenuFragment();

        graphMonthFragment = new GraphMonthFragment();
        graphPaymentFragment = new GraphPaymentFragment();
        graphTimeFragment = new GraphTimeFragment();
        graphWeekFragment = new GraphWeekFragment();
    }
    public void ShowExpenseFragment(){
        fragmentManager.beginTransaction().replace(R.id.main_topmenu_layout, mainTopFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.main_middle_layout, mainViewPagerFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.main_bottom_layout, mainBottomFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, emptyFragment2).commit();

    }
    public void ShowIncomeFragment(){
        fragmentManager.beginTransaction().replace(R.id.main_topmenu_layout, mainTopFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.main_middle_layout, mainViewPagerFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.main_bottom_layout, mainBottomFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, emptyFragment2).commit();
    }
    public void ShowStatisticsFragment(){
        fragmentManager.beginTransaction().replace(R.id.main_topmenu_layout, mainTopFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.main_middle_layout, mainViewPagerFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.main_bottom_layout, emptyFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, statisticsMenuFragment).commit();
    }
    public void ChangeStatisticsMenuState(int option){//0 = empty 1 = visible
        if(option == 0) {
            fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, emptyFragment2).commit();
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, statisticsMenuFragment).commit();
        }
    }
    public void ChangeMenu1Graph(){
        //mainViewPagerFragment.SetFragment(2,graphMonthFragment);
        fragmentManager.beginTransaction().replace(R.id.main_middle_layout, graphMonthFragment).commit();
    }
    public void ChangeMenu2Graph(){
        //mainViewPagerFragment.SetFragment(2,graphWeekFragment);
        fragmentManager.beginTransaction().replace(R.id.main_middle_layout, graphWeekFragment).commit();
    }
    public void ChangeMenu3Graph(){
        //mainViewPagerFragment.SetFragment(2,graphTimeFragment);
        fragmentManager.beginTransaction().replace(R.id.main_middle_layout, graphTimeFragment).commit();
    }
    public void ChangeMenu4Graph(){
        //mainViewPagerFragment.SetFragment(2,graphPaymentFragment);
        fragmentManager.beginTransaction().replace(R.id.main_middle_layout, graphPaymentFragment).commit();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        unregisterReceiver(smsReceiver);
    }
    /*public class Broadcast extends BroadcastReceiver{

    }*/

}
