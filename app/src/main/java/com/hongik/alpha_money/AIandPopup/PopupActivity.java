package com.hongik.alpha_money.AIandPopup;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hongik.alpha_money.Activity.MainActivity;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

public class PopupActivity extends Activity {
    RelativeLayout popupOutside;
    Button left, center, right;
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

    LocationManager locManager; // 위치 정보 프로바이더
    LocationListener locationListener; // 위치 정보가 업데이트시 동작

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




        
        //TODO : GPS 작동여부 묻는 부분


        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
                    Log.i("tag", location.toString() + "location by GPS");
                    gridX = location.getLatitude();
                    gridY = location.getLongitude();
                    locManager.removeUpdates(locationListener);
                }
                else{
                    Log.i("tag", location.toString() + "location by network");
                    gridX = location.getLatitude();
                    gridY = location.getLongitude();
                    locManager.removeUpdates(locationListener);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                new AlertDialog.Builder(PopupActivity.this)
                        .setMessage("AlphaMoney\nGPS가 꺼져있습니다. 정확한 위치를 저장하시려면 위치 서비스를 켜주세요")
                        .setPositiveButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("취소", null).show();
            }
        };


        if (ActivityCompat.checkSelfPermission(ApplicationSingleton.getInstance().GetMainActivityContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, locationListener);
            //locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
        } else {
            ActivityCompat.requestPermissions((MainActivity) ApplicationSingleton.getInstance().GetMainActivityContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            if (ActivityCompat.checkSelfPermission(ApplicationSingleton.getInstance().GetMainActivityContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, locationListener);
                //locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
            }
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        locManager.removeUpdates(locationListener);

        if(gridX == 0) {
            if (ActivityCompat.checkSelfPermission(ApplicationSingleton.getInstance().GetMainActivityContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
            } else {
                ActivityCompat.requestPermissions((MainActivity) ApplicationSingleton.getInstance().GetMainActivityContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                if (ActivityCompat.checkSelfPermission(ApplicationSingleton.getInstance().GetMainActivityContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
                }
            }
        }




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
}