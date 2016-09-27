package com.hongik.alpha_money.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.ListCustomAdapter;
import com.hongik.alpha_money.DataStructure.struct;
import com.hongik.alpha_money.Fragment.EmptyFragment;
import com.hongik.alpha_money.Fragment.MainBottomFragment;
import com.hongik.alpha_money.Fragment.MainViewPagerFragment;
import com.hongik.alpha_money.Fragment.MainTopFragment;
import com.hongik.alpha_money.Fragment.StatisticsMenuFragment;
import com.hongik.alpha_money.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    MainTopFragment mainTopFragment;
    MainViewPagerFragment mainViewPagerFragment;
    EmptyFragment emptyFragment;
    EmptyFragment emptyFragment2;
    MainBottomFragment mainBottomFragment;
    StatisticsMenuFragment statisticsMenuFragment;

    FragmentManager fm = getSupportFragmentManager();

    ArrayList<struct> arrayList_income;
    ArrayList<struct> arrayList_expense;

    ListCustomAdapter listCustomAdapter_1;
    ListCustomAdapter listCustomAdapter_2;
    ListView listView_income;
    ListView listView_expense;
    TextView priceView;
    ImageView signalLightView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApplicationSingleton.getInstance().SetMainActivityContext(this);//initiate 안에 있으면 에러남(initiate를 다른거 하면서 돌리는듯
        initiate();

        arrayList_expense = ApplicationSingleton.getInstance().GetDataBase().onGetalldata(1);
        arrayList_income = ApplicationSingleton.getInstance().GetDataBase().onGetalldata(2);

        if (savedInstanceState == null) {
            //프래그먼트 3가지 세팅

            fragmentManager.beginTransaction().add(R.id.main_topmenu_layout, mainTopFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_middle_layout, mainViewPagerFragment).commit();
            fragmentManager.beginTransaction().add(R.id.main_bottom_layout, mainBottomFragment).commit();

            //통계에서 메뉴바 레이아웃으로 사용 될 것임
            fragmentManager.beginTransaction().add(R.id.statistics_bottom_layout,emptyFragment).commit();
            //처음엔 아무것도 안보이게 세팅
        }
    }
    public FragmentManager GetFM(){return fm;}
    private void initiate() {
        mainBottomFragment = new MainBottomFragment();
        mainViewPagerFragment = new MainViewPagerFragment();
        mainTopFragment =  new MainTopFragment();
        emptyFragment = new EmptyFragment();
        emptyFragment2 = new EmptyFragment();
        statisticsMenuFragment = new StatisticsMenuFragment();


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

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        arrayList_expense = ApplicationSingleton.getInstance().GetDataBase().onGetalldata(1);
        arrayList_income = ApplicationSingleton.getInstance().GetDataBase().onGetalldata(2);
    }

    public void ChangeStatisticsMenuState(int option){//0 = empty 1 = visible
        if(option == 0) {
            fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, emptyFragment2).commit();
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.statistics_bottom_layout, statisticsMenuFragment).commit();
        }
    }
    //그래프 작업 시작
}
