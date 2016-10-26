package com.hongik.alpha_money;

import android.app.Application;
import android.content.Context;

import com.hongik.alpha_money.DataBase.DBHelper;
import com.hongik.alpha_money.DataStructure.struct;
import com.hongik.alpha_money.Fragment.MainMiddleStatisticsFragment;

import java.util.ArrayList;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class ApplicationSingleton extends Application {

    // Applcation 인스턴스 선언
    private static ApplicationSingleton instance;
    public static ApplicationSingleton getInstance(){
        return instance;
    }

    DBHelper mydb;

    ArrayList<struct> arrayList_expense;
    ArrayList<struct> arrayList_income;

    public ArrayList<struct> GetExpenseList(int option,String key){//0 = all 1=month 2=week 3=day
        if(option == 0){
            arrayList_expense = mydb.onGetalldata(1);//1=expense
        }
        else if(option == 1){
            //TODO:: 월일 나누면서 추가로 작업

            return mydb.onGetmonthdata(key, 1);
        }
        else if(option == 2){

        }
        else if(option == 3){

        }
        return arrayList_expense;
    }
    public ArrayList<struct> GetIncomeList(int option, String key){//0 = all 1=month 2=week 3=day
        if(option == 0){
            arrayList_income = mydb.onGetalldata(2);//2=income
        }
        else if(option == 1){
            //TODO:: 월일 나누면서 추가로 작업
        }
        else if(option == 2){

        }
        else if(option == 3){

        }
        return arrayList_income;
    }
    public DBHelper GetDataBase(){return mydb;}
    public void onInsertdata(String date, String price, String storename, String category, String memo, String gridX, String gridY,int option) {
        mydb.onInsertdata(date,price,storename,category,memo,gridX,gridY,option);
        return;
    }

    private Context mainActivityContext;
    public void SetMainActivityContext(Context ctx){
        mainActivityContext = ctx;
        mydb = new DBHelper(ctx);
    }
    public Context GetMainActivityContext(){return mainActivityContext;}
    @Override
    public void onCreate(){
        super.onCreate();
        ApplicationSingleton.instance = this;
    }
    MainMiddleStatisticsFragment StatisticsFragment;
    public void SetStatisticsFragment(MainMiddleStatisticsFragment fm){
        StatisticsFragment =fm;
    }
    public MainMiddleStatisticsFragment GetStatisticsFragment(){
        return StatisticsFragment;
    }
}
