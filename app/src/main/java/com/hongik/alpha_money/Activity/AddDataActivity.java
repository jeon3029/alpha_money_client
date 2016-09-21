package com.hongik.alpha_money.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.R;

public class AddDataActivity extends Activity {
    TextView editTextDate;
    TextView editTextPrice;
    TextView editTextStore;
    TextView editTextCategory;
    TextView editTextMemo;
    ToggleButton toggleButton;
    Button saveButton;
    Button closeButton;
    TextView totalEI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_data);

        editTextDate = (TextView) findViewById(R.id.editTextDate);
        editTextPrice = (TextView) findViewById(R.id.editTextPrice);
        editTextStore = (TextView) findViewById(R.id.editTextStore);
        editTextCategory = (TextView) findViewById(R.id.editTextCategory);
        editTextMemo = (TextView) findViewById(R.id.editTextMemo);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        saveButton = (Button)findViewById(R.id.saveButton);
        closeButton = (Button)findViewById(R.id.closeButton);

        final int option;
        if(toggleButton.isChecked()){
            option=2;//수입
        }
        else option = 1;//지출
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ApplicationSingleton.getInstance().GetDataBase().onInsertdata(editTextDate.getText().toString(), editTextPrice.getText().toString(), editTextStore.getText().toString(), editTextCategory.getText().toString(), editTextMemo.getText().toString(), "0", "0",option);
                Toast.makeText(AddDataActivity.this, "정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(AddDataActivity.this, "닫기", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
