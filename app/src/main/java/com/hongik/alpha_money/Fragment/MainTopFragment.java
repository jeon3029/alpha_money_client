package com.hongik.alpha_money.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.hongik.alpha_money.Activity.AddDataActivity;
import com.hongik.alpha_money.Activity.MainActivity;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.SignalLight;
import com.hongik.alpha_money.R;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainTopFragment extends Fragment{
    View rootViewBasic;
    ImageView addImage;
    ImageView searchImage;
    ImageView menuImage;
    ImageView lightImage;
    public MainTopFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
        SignalLight sig = new SignalLight();
        int get = sig.CalcSumLight();
        if(get == 0){//green
            lightImage.setImageResource(R.drawable.greencircle);
            Toast.makeText(ApplicationSingleton.getInstance().GetMainActivityContext(), sig.getText(0), Toast.LENGTH_LONG).show();

        }
        else if(get ==1 ){//yellow
            lightImage.setImageResource(R.drawable.yellowcircle);
            Toast.makeText(ApplicationSingleton.getInstance().GetMainActivityContext(), sig.getText(1), Toast.LENGTH_LONG).show();

        }
        else if (get == 2){//red
            lightImage.setImageResource(R.drawable.redcircle);
            Toast.makeText(ApplicationSingleton.getInstance().GetMainActivityContext(), sig.getText(2), Toast.LENGTH_LONG).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootViewBasic = inflater.inflate(R.layout.main_top_fragment, container, false);
        addImage = (ImageView)rootViewBasic.findViewById(R.id.top_plus_image);
        searchImage = (ImageView)rootViewBasic.findViewById(R.id.top_search_image);
        menuImage = (ImageView)rootViewBasic.findViewById(R.id.top_menu_image);
        lightImage = (ImageView)rootViewBasic.findViewById(R.id.top_signalLight);

        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG_IMAGE","menuClicked");
            }
        });
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG_IMAGE","searchClicked");
            }
        });
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG_IMAGE","addClicked");
                Context ctx = ApplicationSingleton.getInstance().GetMainActivityContext();
                Intent intent = new Intent(ctx, AddDataActivity.class);
                startActivity(intent);
            }
        });
        return rootViewBasic;
    }
}
