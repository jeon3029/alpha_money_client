package com.hongik.alpha_money;

import android.app.Application;
import android.content.Context;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class ApplicationSingleton extends Application {

    // Applcation 인스턴스 선언
    private static ApplicationSingleton instance;
    public static ApplicationSingleton getInstance(){
        return instance;
    }

    private Context mainActivityContext;
    public void SetMainActivityContext(Context ctx){mainActivityContext = ctx;};
    public Context GetMainActivityContext(){return mainActivityContext;}
    @Override
    public void onCreate(){
        super.onCreate();
        ApplicationSingleton.instance = this;
    }
}
