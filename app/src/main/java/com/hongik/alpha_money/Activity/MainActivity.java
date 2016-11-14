package com.hongik.alpha_money.Activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hongik.alpha_money.AIandPopup.PopupActivity;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataBase.DBHelper;
import com.hongik.alpha_money.DataStructure.CustomDate;
import com.hongik.alpha_money.DataStructure.ListCustomAdapter;
import com.hongik.alpha_money.DataStructure.struct;
import com.hongik.alpha_money.Fragment.EmptyFragment;
import com.hongik.alpha_money.Fragment.MainBottomFragment;
import com.hongik.alpha_money.Fragment.MainMiddleStatisticsFragment;
import com.hongik.alpha_money.Fragment.MainTopFragment;
import com.hongik.alpha_money.Fragment.MainViewPagerFragment;
import com.hongik.alpha_money.Fragment.StatisticsMenuFragment;
import com.hongik.alpha_money.R;
import com.hongik.alpha_money.Sms.SmsReceiver;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    //Fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    MainTopFragment mainTopFragment;
    MainViewPagerFragment mainViewPagerFragment;
    EmptyFragment emptyFragment;
    EmptyFragment emptyFragment2;
    EmptyFragment emptyFragment3;
    MainBottomFragment mainBottomFragment;
    StatisticsMenuFragment statisticsMenuFragment;
    MainMiddleStatisticsFragment mainMiddleStatisticsFragment;
    PopupActivity popupActivity;
    CustomDate customDate = new CustomDate();

    BroadcastReceiver smsReceiver;

    ArrayList<struct> arrayList_income;
    ArrayList<struct> arrayList_expense;
    Bundle instance;
    ListCustomAdapter listCustomAdapter_1;
    ListCustomAdapter listCustomAdapter_2;
    ListView listView_income;
    ListView listView_expense;
    TextView priceView;
    ImageView signalLightView;

    int pageState = 2;/*1 : expense, today
                        2 : expense, week
                        3 : expense, month
                        4 : income, today
                        5 : income, week
                        6 : income, month
                        7 : statistics, menu1(month)
                        8 : statistics, menu2(week)
                        9 : statistics, menu3(time)
                        10 : statistics, menu4(payment)
                        */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApplicationSingleton.getInstance().SetMainActivityContext(this);//initiate 안에 있으면 에러남(initiate를 다른거 하면서 돌리는듯

        //clear all caches in application
        //((ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE)).clearApplicationUserData();

        initiate();
        //arrayList_expense = ApplicationSingleton.getInstance().GetDataBase().onGetalldata(1);
        //arrayList_income = ApplicationSingleton.getInstance().GetDataBase().onGetalldata(2);
        if (savedInstanceState == null) {
            //프래그먼트 3가지 세팅
            fragmentManager.beginTransaction().add(R.id.main_topmenu_layout, mainTopFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_middle_layout, mainViewPagerFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_bottom_layout, mainBottomFragment).commit();

            //통계에서 메뉴바 레이아웃으로 사용 될 것임
            fragmentManager.beginTransaction().add(R.id.statistics_bottom_layout,emptyFragment).commit();

            pageState = 0;
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
    }

    private void initiate() {
        mainBottomFragment = new MainBottomFragment();
        mainViewPagerFragment = new MainViewPagerFragment();
        mainTopFragment =  new MainTopFragment();
        emptyFragment = new EmptyFragment();
        emptyFragment2 = new EmptyFragment();
        emptyFragment3= new EmptyFragment();
        statisticsMenuFragment = new StatisticsMenuFragment();
        popupActivity = new PopupActivity();
        //mainMiddleStatisticsFragment = new MainMiddleStatisticsFragment();

        ApplicationSingleton.getInstance().SetPopupActivity(popupActivity);
        mainMiddleStatisticsFragment = ApplicationSingleton.getInstance().GetStatisticsFragment();
    }

    public void ShowExpenseFragment() {

    }
    public void ShowIncomeFragment() {

    }
    public void ShowStatisticsFragment() {

    }





    public void ShowExpenseIncomeFragment(){
        fragmentManager.beginTransaction().replace(R.id.main_bottom_layout, mainBottomFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, emptyFragment2).commit();
    }
    public void ShowStatisticsMenuFragment(){
        fragmentManager.beginTransaction().replace(R.id.main_bottom_layout, emptyFragment3).commit();
        fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, statisticsMenuFragment).commit();
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        arrayList_expense = ApplicationSingleton.getInstance().GetDataBase().onGetalldata(1);
        arrayList_income = ApplicationSingleton.getInstance().GetDataBase().onGetalldata(2);
    }
    //그래프 작업 시작
    public void ChangeMenu1Graph(){
        //mainViewPagerFragment.SetFragment(2,graphMonthFragment);
        //fragmentManager.beginTransaction().replace(R.id.main_middle_layout, graphMonthFragment).commit();
        mainMiddleStatisticsFragment.ShowMonthGraph(customDate.strCurYearMonth);
    }
    public void ChangeMenu2Graph(){
        //fragmentManager.beginTransaction().replace(R.id.main_middle_layout, graphWeekFragment).commit();
        mainMiddleStatisticsFragment.ShowWeekGraph(customDate.strCurYearMonth);
    }
    public void ChangeMenu3Graph(){
        //fragmentManager.beginTransaction().replace(R.id.main_middle_layout, graphTimeFragment).commit();
        mainMiddleStatisticsFragment.ShowTimeGraph();
    }
    public void ChangeMenu4Graph(){
        //fragmentManager.beginTransaction().replace(R.id.main_middle_layout, graphPaymentFragment).commit();
        mainMiddleStatisticsFragment.ShowPaymentGraph(customDate.strCurYearMonth);
    }

    public FragmentManager GetFM(){
            return fragmentManager;
    }
   // public MainMiddleStatisticsFragment GetMainMiddleStatisticsFragment() { return mainMiddleStatisticsFragment; }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        unregisterReceiver(smsReceiver);
    }


    @Override
    protected void onActivityResult(int RequestCode, int ResultCode, Intent data) {
        switch (ResultCode) {
            case RESULT_OK:
                struct temp = new struct();
                int From, Del, ID, option;
                From = data.getIntExtra("From", 0);
                Del = data.getIntExtra("Del", 0);
                ID = data.getIntExtra("ID", 0);
                option = data.getIntExtra("option", 0);
                DBHelper mydb = ApplicationSingleton.getInstance().GetDataBase();

                if (From == 1) {
                    if(Del == 1) {
                        mydb.onDeletedata(option, ID);
                    }
                    else {
                        temp.ID = data.getIntExtra("ID", 0);
                        temp.date = data.getStringExtra("date");
                        temp.price = data.getStringExtra("price");
                        temp.storeName = data.getStringExtra("storeName");
                        temp.category = data.getStringExtra("category");
                        temp.memo = data.getStringExtra("memo");
                        temp.gridX = data.getDoubleExtra("gridX", 0.000000);
                        temp.gridY = data.getDoubleExtra("gridY", 0.000000);
                        temp.payment = data.getStringExtra("payment");
                        mydb.onUpdate(temp.date, temp.price, temp.storeName, temp.category, temp.memo
                                , Double.toString(temp.gridX), Double.toString(temp.gridY),temp.payment, option, temp.ID);
                    }
                }
                break;
            default:
                super.onActivityResult(RequestCode, ResultCode, data);
        }
    }
    public MainBottomFragment getMainBottomFragment() {
        return mainBottomFragment;
    }
    public void SetPageState (int state) { pageState = state; }
    public int GetPageState () { return pageState; }
}
