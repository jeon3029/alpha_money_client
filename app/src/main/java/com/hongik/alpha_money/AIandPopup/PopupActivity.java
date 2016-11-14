package com.hongik.alpha_money.AIandPopup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.SignalLight;
import com.hongik.alpha_money.DataStructure.struct;
import com.hongik.alpha_money.R;

import org.w3c.dom.Text;

public class PopupActivity extends Activity {
    RelativeLayout popupOutside;
    Button left,center,right;

    ImageView signalLight;
    TextView priceText,storeText,mentText;

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

        initiate();


        popupOutside = (RelativeLayout)this.findViewById(R.id.popup_outside);
        popupOutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //category click X
                ApplicationSingleton.getInstance().onInsertdata(date, price, storeName, "00", "",String.valueOf(gridX) , String.valueOf(gridY),String.valueOf(payment),1);

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
                ApplicationSingleton.getInstance().onInsertdata(date, price, storeName, "10", "",String.valueOf(gridX) , String.valueOf(gridY),String.valueOf(payment),1);

                finish();
            }
        });

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //생활비
                ApplicationSingleton.getInstance().onInsertdata(date, price, storeName, "20", "",String.valueOf(gridX) , String.valueOf(gridY),String.valueOf(payment),1);

                finish();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //여가비
                ApplicationSingleton.getInstance().onInsertdata(date, price, storeName, "30", "",String.valueOf(gridX) , String.valueOf(gridY),String.valueOf(payment),1);

                finish();
            }
        });

    }

    private void initiate() {
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

        signalLight = (ImageView)findViewById(R.id.popup_signal_image);
        priceText = (TextView)findViewById(R.id.popup_price);
        storeText = (TextView)findViewById(R.id.popup_storeName);
        mentText = (TextView)findViewById(R.id.popup_ment);
        priceText.setText(price + " 원");
        storeText.setText(storeName);
        setSignalLightImage();
    }

    private void setSignalLightImage() {
        SignalLight sig = new SignalLight();
        int t = sig.CalcSum();
        t=t+Integer.parseInt(price);
        int botLimit = ApplicationSingleton.getInstance().GetBotLimit();
        int topLimit = ApplicationSingleton.getInstance().GetTopLimit();
        struct temp = new struct();
        temp.price = price;
        temp.storeName =storeName;
        temp.gridX = gridX;
        temp.gridY = gridY;
        temp.payment = payment;
        temp.date =date;
        AIMent ment = new AIMent();
        if( t < botLimit){//green
            signalLight.setImageResource(R.drawable.greencircle);
            mentText.setText(ment.getMent(temp,0));
        }
        else if(t>=botLimit && t<topLimit){//yellow
            signalLight.setImageResource(R.drawable.yellowcircle);
            mentText.setText(ment.getMent(temp,1));
        }
        else{//red
            signalLight.setImageResource(R.drawable.redcircle);
            mentText.setText(ment.getMent(temp,2));
        }
    }
}