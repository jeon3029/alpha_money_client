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

import com.hongik.alpha_money.Activity.AddDataActivity;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainTopFragment extends Fragment{
    View rootViewBasic;
    ImageView addImage;
    ImageView searchImage;
    ImageView menuImage;

    public MainTopFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootViewBasic = inflater.inflate(R.layout.main_top_fragment, container, false);
        addImage = (ImageView)rootViewBasic.findViewById(R.id.top_plus_image);
        searchImage = (ImageView)rootViewBasic.findViewById(R.id.top_search_image);
        menuImage = (ImageView)rootViewBasic.findViewById(R.id.top_menu_image);

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
