package com.hongik.alpha_money;

import android.app.Application;
import android.content.Context;

import com.hongik.alpha_money.DataBase.DBHelper;

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

    public DBHelper GetDataBase(){return mydb;}

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
}
