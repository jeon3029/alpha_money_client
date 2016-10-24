package com.hongik.alpha_money.Activity;

import android.app.Activity;
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

    int option;
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


        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //ApplicationSingleton.getInstance().GetDataBase().onInsertdata(editTextDate.getText().toString(), editTextPrice.getText().toString(), editTextStore.getText().toString(), editTextCategory.getText().toString(), editTextMemo.getText().toString(), "0", "0",option);
                if(toggleButton.isChecked()){
                    option=2;//수입
                }
                else option = 1;//지출
                ApplicationSingleton.getInstance().onInsertdata(editTextDate.getText().toString(), editTextPrice.getText().toString(), editTextStore.getText().toString(), editTextCategory.getText().toString(), editTextMemo.getText().toString(), "0", "0",option);
                Toast.makeText(AddDataActivity.this, "정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();


                ApplicationSingleton.getInstance().onInsertdata("20161003","38000","맘스터치","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161005","2300","한가람","문구","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161007","10300","다이소","생활용품","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161008","36400","GIODANO","패션","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161009","5300","M&M","패션","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161009","7600","맥도날드","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161012","15600","신선설농탕","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161014","34700","Z:PC","엔터테인먼트","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161014","78700","연어상회","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161014","1300","가게A","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161015","65800","가게B","카페","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161017","14000","맛집","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161017","34500","배고픔","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161018","4700","이게뭐람","엔터테인먼트","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161018","46000","뻐꾸기","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161018","12300","자옥이","패션","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161018","200","필통","문구","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161019","6400","아메리카노큰거","카페","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161019","15600","갤6","전자제품","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161020","98700","모니터","전자제품","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161021","3400","BOHEM편의점","생활용품","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161002","35600","롯데ID편의점","생활용품","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20160903","13000","탁상시계","생활용품","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20160907","6800","포스트잇","문구","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20160927","3800","딱풀","문구","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20160930","45300","하늘보리","엔터테인먼트","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161001","156000","두부집","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161012","2600","옛집","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161013","5800","순대국집","식사","","","",1);
                ApplicationSingleton.getInstance().onInsertdata("20161003","13000","와끝이다","기타","","","",1);

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
