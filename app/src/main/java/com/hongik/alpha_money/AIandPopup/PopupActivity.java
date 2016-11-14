package com.hongik.alpha_money.AIandPopup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

public class PopupActivity extends Activity {
    RelativeLayout popupOutside;
    Button left,center,right;
    int option;
    String date;
    String price;
    String storeName;
    String category;
    String memo;
    String payment;
    double gridX;
    double gridY;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.popup_activity);
        intent = getIntent();
        date = intent.getStringExtra("date");
        price = intent.getStringExtra("price");
        storeName = intent.getStringExtra("storeName");
        payment = intent.getStringExtra("payment");
        //category = intent.getStringExtra("category");
        //memo = intent.getStringExtra("memo");
        gridX = intent.getDoubleExtra("gridX", 0.000000); // TODO : 0.000000으로 초기화해야함 테스트를 위해 수정했음
        gridY = intent.getDoubleExtra("gridY", 0.000000);
        //option = intent.getIntExtra("option", 0);

        popupOutside = (RelativeLayout)this.findViewById(R.id.popup_outside);
        popupOutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //category click X
                finish();
            }
        });

        left = (Button)this.findViewById(R.id.popup_button_left);
        center = (Button)this.findViewById(R.id.popup_button_center);
        right = (Button)this.findViewById(R.id.popup_button_right);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //식비
                ApplicationSingleton.getInstance().onInsertdata(date, price, storeName, "10", "",String.valueOf(gridX) , String.valueOf(gridY),String.valueOf(payment),option);

                finish();
            }
        });

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //생활비
                finish();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //여가비
                finish();
            }
        });

    }
}