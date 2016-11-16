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
import com.hongik.alpha_money.DataStructure.Category;
import com.hongik.alpha_money.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    Category category = new Category();

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

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        editTextDate.setText(dateFormat.format(date));
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //ApplicationSingleton.getInstance().GetDataBase().onInsertdata(editTextDate.getText().toString(), editTextPrice.getText().toString(), editTextStore.getText().toString(), editTextCategory.getText().toString(), editTextMemo.getText().toString(), "0", "0",option);
                if(toggleButton.isChecked()){
                    option=2;//수입
                }
                else option = 1;//지출

                if(editTextDate.getText().toString().equals("111")) {
                    ApplicationSingleton.getInstance().onInsertdata("20161103", "38000", "맘스터치", "10", "", "37.550341", "126.925179", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161105", "2300", "한가람", "20", "", "38.556275", "126.446555", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161107", "10300", "다이소", "20", "", "37.551341", "126.925179", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161108", "36400", "GIODANO", "35", "", "37.580341", "126.925179", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161109", "5300", "M&M", "35", "", "37.550310", "126.925129", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161109", "7600", "맥도날드", "10", "", "37.557341", "126.925179", "KB(5*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161112", "15600", "신선설농탕", "11", "", "37.850341", "126.925179", "KB(5*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161114", "34700", "Z:PC", "10", "", "37.550391", "126.925379", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161114", "78700", "연어상회", "10", "", "37.580341", "126.922179", "KB(5*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161114", "1300", "가게A", "10", "", "37.550841", "126.925179", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161115", "65800", "가게B", "12", "", "37.558341", "126.926179", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161117", "14000", "맛집", "10", "", "37.550641", "126.925279", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161117", "34500", "배고픔", "11", "", "37.556341", "126.926179", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161118", "4700", "이게뭐람", "30", "", "37.560341", "126.928179", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161118", "46000", "뻐꾸기", "10", "", "37.560341", "126.925979", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161118", "12300", "자옥이", "20", "", "37.550341", "126.925479", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161118", "200", "필통", "21", "", "35.550341", "126.925159", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161119", "6400", "아메리카노큰거", "12", "", "39.550341", "126.915179", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161119", "15600", "갤6", "22", "", "37.550321", "126.925159", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161120", "98700", "모니터", "22", "", "37.550241", "126.925079", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161121", "3400", "BOHEM편의점", "21", "", "37.350341", "126.905179", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161102", "35600", "롯데ID편의점", "21", "", "37.450341", "126.920179", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161003", "13000", "탁상시계", "21", "", "37.520341", "126.925079", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161007", "6800", "포스트잇", "21", "", "37.560341", "126.925079", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160927", "3800", "딱풀", "22", "", "37.550341", "126.920179", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160930", "45300", "하늘보리", "32", "", "37.520341", "126.925079", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161001", "156000", "두부집", "10", "", "37.510341", "126.925379", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161012", "2600", "옛집", "10", "", "37.550141", "126.925139", "현금", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161013", "5800", "순대국집", "12", "", "37.550241", "126.925379", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20161003", "13000", "세븐일레븐", "00", "", "37.551341", "126.935179", "NH(8*7*)", 1);

                    ApplicationSingleton.getInstance().onInsertdata("20160904", "3700", "미니스톱", "10", "", "37.850341", "126.935179", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160804", "78700", "연어상회", "10", "", "37.350341", "126.925079", "KB(5*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160728", "13000", "맘스터치", "10", "", "37.350341", "126.925079", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160630", "44000", "오버워치", "30", "", "37.450341", "126.925579", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160522", "30000", "윤씨밀방", "10", "", "37.554341", "126.925199", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160513", "34500", "에버랜드", "31", "", "37.554341", "126.925479", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160501", "7500", "리소헤어", "34", "", "37.550441", "126.925159", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160416", "88000", "클럽A", "30", "", "37.550141", "126.925279", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160330", "19000", "게임현질", "30", "", "37.551341", "126.922179", "신한(4*8*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160211", "200000", "A랜드", "33", "", "37.550241", "126.920179", "NH(8*7*)", 1);
                    ApplicationSingleton.getInstance().onInsertdata("20160101", "18000", "피자나라치킨공주", "10", "", "37.550541", "126.923179", "현금", 1);
                }
                else if(editTextDate.getText().toString().equals("222")){
                    ApplicationSingleton.getInstance().onInsertdata("20161103", "3080000", "월급", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20161105", "2300", "이자", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20161107", "10300", "투자", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160819", "64000", "펀드A", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160720", "50000001", "월급", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160620", "9870000", "임차료", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160521", "340000", "월급", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160506", "35600", "펀드B", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160503", "200000", "주식A", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160430", "680000", "월급", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160330", "3800000", "월급", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160315", "450300", "아버지", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160301", "156000", "어머니", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160312", "2600", "이자", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160113", "5800000", "월급", "20", "", "", "", "", 2);
                    ApplicationSingleton.getInstance().onInsertdata("20160203", "25000", "용돈", "20", "", "", "", "", 2);
                }
                else{
                    ApplicationSingleton.getInstance().onInsertdata(editTextDate.getText().toString(), editTextPrice.getText().toString(), editTextStore.getText().toString(), category.GetCategoryNumber(editTextCategory.getText().toString()), editTextMemo.getText().toString(), "0", "0","NH(8*7*)",option);
                    Toast.makeText(AddDataActivity.this, "정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                }

                int stateForRefresh = ((MainActivity)ApplicationSingleton.getInstance().GetMainActivityContext()).GetPageState();

                if(stateForRefresh < 4) {
                    ApplicationSingleton.getInstance().GetExpenseFragment().onclickRefresh();
                }
                else if(3 < stateForRefresh && stateForRefresh < 7) {
                    ApplicationSingleton.getInstance().GetIncomeFragment().onclickRefresh();
                }

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
