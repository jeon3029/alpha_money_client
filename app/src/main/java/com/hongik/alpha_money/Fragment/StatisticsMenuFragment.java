package com.hongik.alpha_money.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

import org.w3c.dom.Text;

/*
 * Created by jeon3029 on 16. 9. 13..
 */
public class StatisticsMenuFragment extends Fragment{

    View rootViewBasic;
    Context ctx;
    LinearLayout menuLayout;
    Button menuBtn;
    ImageView updownImg;
    RelativeLayout relativeLayout;
    TextView menu1;
    TextView menu2;
    TextView menu3;
    TextView menu4;
    boolean updownState;

    public StatisticsMenuFragment(){
        updownState = false; //false = down true = up
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_statistics_menu,container,false);
        ctx = ApplicationSingleton.getInstance().getApplicationContext();
        //initiate
        menuBtn = (Button)rootViewBasic.findViewById(R.id.menubtn);
        updownImg = (ImageView)rootViewBasic.findViewById(R.id.menu_up_image);
        menuLayout = (LinearLayout)rootViewBasic.findViewById(R.id.menu_linear_layout);
        menu1 = (TextView)rootViewBasic.findViewById(R.id.menu1);
        menu2 = (TextView)rootViewBasic.findViewById(R.id.menu2);
        menu3 = (TextView)rootViewBasic.findViewById(R.id.menu3);
        menu4 = (TextView)rootViewBasic.findViewById(R.id.menu4);
        relativeLayout = (RelativeLayout)rootViewBasic.findViewById(R.id.menu_bottom_relative);

        updownImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("TAGMENU","Click img");
                if(updownState == false){//다운 되어 있는 상태
                    updownState = true;
                    menuLayout.setVisibility(LinearLayout.VISIBLE);
                    updownImg.setImageResource(R.mipmap.menubar_down);
                    int paddingPixel = 0;
                    float density = ctx.getResources().getDisplayMetrics().density;
                    int paddingDp = (int)(paddingPixel * density);
                    relativeLayout.setPaddingRelative(0,paddingDp,0,0);//red line, but it's ok
                }
                else if(updownState == true){//업 되어 있는 상태
                    updownState = false;
                    menuLayout.setVisibility(LinearLayout.GONE);
                    updownImg.setImageResource(R.mipmap.menubar_up);
                    int paddingPixel = 200;
                    float density = ctx.getResources().getDisplayMetrics().density;
                    int paddingDp = (int)(paddingPixel * density);
                    relativeLayout.setPaddingRelative(0,paddingDp,0,0);//red line, but it's ok
                }
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("TAGMENU","Click menu button");
                if(updownState == false){//다운 되어 있는 상태
                    updownState = true;
                    menuLayout.setVisibility(LinearLayout.VISIBLE);
                    updownImg.setImageResource(R.mipmap.menubar_down);
                    int paddingPixel = 0;
                    float density = ctx.getResources().getDisplayMetrics().density;
                    int paddingDp = (int)(paddingPixel * density);
                    relativeLayout.setPaddingRelative(0,paddingDp,0,0);//red line, but it's ok
                }
                else if(updownState == true){//업 되어 있는 상태
                    updownState = false;
                    menuLayout.setVisibility(LinearLayout.GONE);
                    updownImg.setImageResource(R.mipmap.menubar_up);
                    int paddingPixel = 200;
                    float density = ctx.getResources().getDisplayMetrics().density;
                    int paddingDp = (int)(paddingPixel * density);
                    relativeLayout.setPaddingRelative(0,paddingDp,0,0);//red line, but it's ok
                }
            }
        });




        return rootViewBasic;
    }
}
