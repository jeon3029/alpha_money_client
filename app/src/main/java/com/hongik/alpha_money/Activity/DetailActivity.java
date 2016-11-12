package com.hongik.alpha_money.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.hongik.alpha_money.ApplicationSingleton;
import com.hongik.alpha_money.DataStructure.Category;
import com.hongik.alpha_money.R;

public class DetailActivity extends Activity implements OnClickListener{
    Intent intent;
    int ID, option;
    String date;
    String price;
    String storeName;
    String category;
    String memo;
    double gridX;
    double gridY;

    Category cate = new Category();

    TextView detailDate;
    TextView detailPrice;
    TextView detailStore;
    TextView detailCategory;
    TextView detailMemo;
    private Button buttonConfirm, buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initiate();
        setText();

    }

    private void setText() {
        if(date.length()!=0)detailDate.setText(date);
        if(price.length()!=0)detailPrice.setText(price);
        if(storeName.length()!=0)detailStore.setText(storeName);
        if(category.length()!=0)detailCategory.setText(cate.GetCategoryName(category));
        if(memo.length()!=0)detailMemo.setText(memo);
    }

    private void initiate() {
        intent = getIntent();
        ID = intent.getIntExtra("ID", 0);
        date = intent.getStringExtra("date");
        price = intent.getStringExtra("price");
        storeName = intent.getStringExtra("storeName");
        category = intent.getStringExtra("category");
        memo = intent.getStringExtra("memo");
        gridX = intent.getDoubleExtra("gridX", 0.000000);
        gridY = intent.getDoubleExtra("gridY", 0.000000);
        option = intent.getIntExtra("option", 0);//1 expense 2 income

        detailDate = (TextView) findViewById(R.id.detail_editTextDate);
        detailPrice = (TextView) findViewById(R.id.detail_editTextPrice);
        detailStore = (TextView) findViewById(R.id.detail_editTextStore);
        detailCategory = (TextView) findViewById(R.id.detail_editTextCategory);
        detailMemo = (TextView) findViewById(R.id.detail_editTextMemo);

        buttonConfirm = (Button) findViewById(R.id.detail_confirmButton);
        buttonDelete = (Button) findViewById(R.id.detail_deleteButton);

        buttonConfirm.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        intent = new Intent();
        switch (v.getId()) {
            case R.id.detail_confirmButton:
                date = detailDate.getText().toString();
                price = detailPrice.getText().toString();
                storeName = detailStore.getText().toString();
                /*(int i;
                for(i=0;;i++){

                }*/
                //storeName.replaceAll("\\s+","");//removing spaces
                storeName.trim();//removing all leading spaces
                category = detailCategory.getText().toString();
                memo = detailMemo.getText().toString();
                intent.putExtra("From", 1);
                intent.putExtra("Del", 0);
                intent.putExtra("ID", ID);
                intent.putExtra("date", date);
                intent.putExtra("price", price);
                intent.putExtra("storeName", storeName.trim());
                intent.putExtra("category", cate.GetCategoryNumber(category)); // 거꾸로
                intent.putExtra("memo", memo);
                intent.putExtra("gridX", gridX);
                intent.putExtra("gridY", gridY);
                intent.putExtra("option", option);
                setResult(RESULT_OK, intent);
                this.finish();
                break;
            case R.id.detail_deleteButton:
                intent.putExtra("From", 1);
                intent.putExtra("Del", 1);
                intent.putExtra("ID", ID);
                intent.putExtra("option", option);
                setResult(RESULT_OK, intent);
                this.finish();
                break;
            default:
                break;
        }

        int stateForRefresh = ((MainActivity) ApplicationSingleton.getInstance().GetMainActivityContext()).GetPageState();

        if(stateForRefresh < 4) {//expense page
            ApplicationSingleton.getInstance().GetExpenseFragment().onclickRefresh();
        }
        else if(3 < stateForRefresh && stateForRefresh < 7) {//income page
            ApplicationSingleton.getInstance().GetIncomeFragment().onclickRefresh();
        }

    }
}
