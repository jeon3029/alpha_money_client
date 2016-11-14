package com.hongik.alpha_money.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hongik.alpha_money.Activity.AboutDialog;
import com.hongik.alpha_money.Activity.MainActivity;
import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

/**
 * Created by jeon3029 on 16. 11. 15..
 */
public class MenuClickedFragment extends Fragment {

    View rootViewBasic;
    TextView text;
    public MenuClickedFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.menu_clicked_fragment, container, false);
        text = (TextView)rootViewBasic.findViewById(R.id.menu_about_alpha_text);
        text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showAboutDialog();
                ((MainActivity) ApplicationSingleton.getInstance().GetMainActivityContext()).topMenuBack();

            }
        });
        return rootViewBasic;
    }

    private void showAboutDialog() {
        AboutDialog about = new AboutDialog(ApplicationSingleton.getInstance().GetMainActivityContext());
        about.show();
    }
}
