
package com.hongik.alpha_money.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongik.alpha_money.R;

/**
 * Created by jeon3029 on 16. 7. 13..
 */
public class MainMiddleExpenseFragment extends Fragment{
    View rootViewBasic;
    public MainMiddleExpenseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootViewBasic = inflater.inflate(R.layout.main_expense_list, container, false);
        return rootViewBasic;
    }
}
