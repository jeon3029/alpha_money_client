package com.hongik.alpha_money.DataBase;

/**
 * Created by jeon3029 on 16. 9. 12..
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.hongik.alpha_money.DataStructure.struct;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String databaseName = "TH.db";
    String tableName = "expensetable";
    String tableName2 = "incometable"; // option 1 = 지출  2 = 수입
    SQLiteDatabase db;
    int count;

    public Cursor onSelectdata(String date) { // 데이터 조회
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + "where date=" + date + "", null);
        return cursor;

    }

    public ArrayList<struct> onGetalldata(int option) {//op == 1 지출 2 수입
        Cursor cursor;
        ArrayList<struct> arrayList = new ArrayList<struct>();
        SQLiteDatabase db = this.getReadableDatabase();
        if(option == 1)
            cursor = db.rawQuery("SELECT date, price, storename, category, memo, gridX, gridY, _id FROM " + tableName, null);
        else // option == 2
            cursor = db.rawQuery("SELECT date, price, storename, category, memo, gridX, gridY, _id FROM " + tableName2, null);
        count = cursor.getCount();
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false)
        {
            struct temp = new struct();
            temp.date = cursor.getString(0);
            temp.price = cursor.getString(1);
            temp.storeName = cursor.getString(2);
            temp.category = cursor.getString(3);
            temp.memo = cursor.getString(4);
            temp.gridX = cursor.getDouble(5);
            temp.gridY = cursor.getDouble(6);
            temp.ID = cursor.getInt(7);
            arrayList.add(temp);
            cursor.moveToNext();
        }

        return arrayList;
    }

    public ArrayList<struct> onGetmonthdata(String date, int option) { //op ==1 지출 2 수입
        ArrayList<struct> arrayList = new ArrayList<struct>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        if(option == 1) {
            cursor= db.rawQuery("SELECT * FROM " + tableName + "where date like "
                    + date.substring(0, 5) + "% ", null);
        }
        else {
            cursor = db.rawQuery("SELECT * FROM " + tableName2 + "where date like "
                    + date.substring(0, 5) + "% ", null);
        }
        count = cursor.getCount();
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false)
        {
            struct temp = new struct();
            temp.date = cursor.getString(0);
            temp.price = cursor.getString(1);
            temp.storeName = cursor.getString(2);
            temp.category = cursor.getString(3);
            temp.memo = cursor.getString(4);
            temp.gridX = cursor.getDouble(5);
            temp.gridY = cursor.getDouble(6);
            arrayList.add(temp);
            cursor.moveToNext();
        }

        return  arrayList;
    }

    public ArrayList<struct> onGetweekdata(String date,int option) {
        //TODO : week 나눠야함
        //방법 : 그 일의 요일을 구해서 위치 적절히 수정 ex)화요일 -1~+5 수요일 -2~+4 이런식
        //다음달로 넘어가는 경우는 따로 계산
        ArrayList<struct> arrayList = new ArrayList<struct>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        if(option == 1){
            cursor = db.rawQuery("SELECT * FROM " + tableName + "where date like "
                    + date.substring(0, 7) + "% ", null);
        }
        else{
            cursor = db.rawQuery("SELECT * FROM " + tableName2 + "where date like "
                    + date.substring(0, 7) + "% ", null);
        }
        count = cursor.getCount();
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false)
        {
            struct temp = new struct();
            temp.date = cursor.getString(0);
            temp.price = cursor.getString(1);
            temp.storeName = cursor.getString(2);
            temp.category = cursor.getString(3);
            temp.memo = cursor.getString(4);
            temp.gridX = cursor.getDouble(5);
            temp.gridY = cursor.getDouble(6);
            arrayList.add(temp);
            cursor.moveToNext();
        }
        return  arrayList;
    }

    public ArrayList<struct> onGetdaydata(String date, int option) {
        ArrayList<struct> arrayList = new ArrayList<struct>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor ;
        if(option == 1){
            cursor = db.rawQuery("SELECT * FROM " + tableName + "where date like "
                    + date.substring(0,7) + "% ", null);
        }
        else{
            cursor = db.rawQuery("SELECT * FROM " + tableName2 + "where date like "
                    + date.substring(0,7) + "% ", null);
        }
        count = cursor.getCount();
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false)
        {
            struct temp = new struct();
            temp.date = cursor.getString(0);
            temp.price = cursor.getString(1);
            temp.storeName = cursor.getString(2);
            temp.category = cursor.getString(3);
            temp.memo = cursor.getString(4);
            temp.gridX = cursor.getDouble(5);
            temp.gridY = cursor.getDouble(6);
            arrayList.add(temp);
            cursor.moveToNext();
        }

        return  arrayList;
    }


    public DBHelper(Context context) {
        super(context, databaseName, null, 1);
        //onOpen(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists " + tableName + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + "date text, "
                + "price text, "
                + "storename text, "
                + "category text, "
                + "memo text, "
                + "gridX text, "
                + "gridY text"
                + ")");

        db.execSQL("CREATE TABLE if not exists " + tableName2 + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + "date text, "
                + "price text, "
                + "storename text, "
                + "category text, "
                + "memo text, "
                + "gridX text, "
                + "gridY text"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // 어플리케이션 업그레이드시 사용될 기능
        try {
            if(db != null) {
                db.execSQL("CREATE TABLE if not exists " + tableName + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "date text, "
                        + "price text, "
                        + "storename text, "
                        + "category text, "
                        + "memo text, "
                        + "gridX text, "
                        + "gridY text"
                        + ")");
            }
            else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onInsertdata(String date, String price, String storename, String category, String memo, String gridX, String gridY,int option) {
        SQLiteDatabase db = this.getWritableDatabase();
/*
        db.execSQL("INSERT INTO " + tableName + "(date, price, storename, category, memo, gridX, gridY) VALUES " // primary키는 입력하지않아도 자동으로 증가하며 입력됨 oncreate참조
                + "(" + date
                + ", " + price
                + ", '" + storename
                + "', '" + category
                + "', '" + memo
                + "', '" + gridX
                + "', '" + gridY
                + "')");*/

        ContentValues cv = new ContentValues();

        cv.put("date", date);
        cv.put("price", price);
        cv.put("storename", storename);
        cv.put("category", category);
        cv.put("memo", memo);
        cv.put("gridX", gridX);
        cv.put("gridY", gridY);
        if(option == 1)//지출
            db.insert(tableName, null, cv);
        else {
            db.insert(tableName2, null, cv);
        }
    }

    public void onDeletedata(int option, int id){
        db = this.getWritableDatabase();
        if(option == 1)
            db.delete(tableName, "_id = ? ", new String[] {Integer.toString(id)});
        else
            db.delete(tableName2, "_id = ? ", new String[] {Integer.toString(id)});
    }

    public void onUpdate(String date, String price, String storename, String category, String memo, String gridX, String gridY,int option, int ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("date", date);
        cv.put("price", price);
        cv.put("storename", storename);
        cv.put("category", category);
        cv.put("memo", memo);
        cv.put("gridX", gridX);
        cv.put("gridY", gridY);

        if(option == 1)
            db.update(tableName, cv, "_id = ?", new String[] {Integer.toString(ID)});
        else
            db.update(tableName2, cv, "_id = ?", new String[] {Integer.toString(ID)});
    }
}