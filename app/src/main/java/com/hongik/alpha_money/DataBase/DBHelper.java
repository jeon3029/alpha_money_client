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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    // ex.2016102119125 yyyy/mm/dd/hh/mm/day

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
            cursor= db.rawQuery("SELECT date, price, storename, category, memo, gridX, gridY, _id FROM " + tableName + " where date like '"
                    + date.substring(0, 6) + "%'", null);
        }
        else {
            cursor = db.rawQuery("SELECT date, price, storename, category, memo, gridX, gridY, _id FROM " + tableName2 + " where date like '"
                    + date.substring(0, 6) + "%'", null);
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
        // 받은 요일이 포함된 월~일 주를 모두 반환
        ArrayList<struct> arrayList = new ArrayList<struct>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        Date date1;
        int y, m, d, checkday;
        int t[] = {0,3,2,5,0,3,5,1,4,6,2,4};
        int notleap[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 평년
        int leapYear[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 윤년
        int leap = 0; // 1 = 윤년 2 = 평년
        int weekday[] = {0,0,0,0,0,0,0}; // 주별날짜를 얻기위한 월일 int 형
        int tempy, tempm, tempd;
        String tempstring;

        long now = System.currentTimeMillis();
        date1 = new Date(now); // 현재시간을 받고

        // 시간 포맷 지정

        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
        // 지정된 포맷으로 String 타입 리턴
        String strCurYear = CurYearFormat.format(date1);
        String strCurMonth = CurMonthFormat.format(date1);
        String strCurDay = CurDayFormat.format(date1);

        y = Integer.parseInt(strCurYear);
        tempy = Integer.parseInt(strCurYear);
        m = Integer.parseInt(strCurMonth);
        tempm = Integer.parseInt(strCurMonth);
        d = Integer.parseInt(strCurDay);
        tempd = Integer.parseInt(strCurDay);

        //윤년 체크
        if(y % 4 > 0)
            leap = 0;
        else {
            if(y % 400 == 0)
                leap = 1;
            else if(y % 100 == 0)
                leap = 0;
            else
                leap = 1;
        }

        if (m < 3) { // 요일 확인
            y--;
        }
        checkday = (y + (y / 4) - (y / 100) + (y / 400) + t[m - 1] + d) % 7; // 0 = SUN ~ 6 = SAT

        y = tempy;
        m = tempm;
        d = tempd;

        if(leap == 1)//윤년일때 일주일의 날짜 7개를 찾아냄
        {
            for(int i = checkday;i < 7; i++, d++)
            {
                if(d > leapYear[m-1]) {
                    d = 1;
                    m++;
                    if(m > 12)
                    {
                        m = 1;
                        y++;
                    }
                }
                weekday[i] = y*10000 + m*100 + d;
            }

            y = tempy;
            m = tempm;
            d = tempd;

            for(int i = checkday; i >= 0; i--, d--)
            {
                if(d < 1) {
                    d = leapYear[m - 2];
                    m--;
                    if(m < 1){
                        m = 12;
                        y--;
                    }
                }
                weekday[i] = y*10000 + m*100 + d;
            }
        }
        else // 평년일대
        {
            for(int i = checkday;i < 7; i++, d++)
            {
                if(d > notleap[m-1]) {
                    d = 1;
                    m++;
                }
                weekday[i] = y*10000 + m*100 +d;
            }

            y = tempy;
            m = tempm;
            d = tempd;

            for(int i = checkday; i >= 0; i--, d--)
            {
                if(d < 1) {
                    d = notleap[m - 2];
                    m--;
                    if(m < 1){
                        m = 12;
                        y--;
                    }
                }
                weekday[i] = y*10000 + m*100 + d;
            }
        }

        if(option == 1) {
            for (int i = 0; i < 7; i++) {
                struct temp;
                tempstring = String.valueOf(weekday[i]);
                cursor = db.rawQuery("SELECT date, price, storename, category, memo, gridX, gridY, _id FROM " + tableName + " where date like '"
                        + tempstring + "%'", null);
                count = cursor.getCount();
                cursor.moveToFirst();

                while(cursor.isAfterLast() == false)
                {
                    temp = new struct();
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
            }
        }
        else{
            for (int i = 0; i < 7; i++) {
                struct temp;
                tempstring = String.valueOf(weekday[i]);
                cursor = db.rawQuery("SELECT date, price, storename, category, memo, gridX, gridY, _id FROM " + tableName2 + " where date like '"
                        + tempstring + "%'", null);
                count = cursor.getCount();
                cursor.moveToFirst();

                while (cursor.isAfterLast() == false) {
                    temp = new struct();
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
            }
        }

        return  arrayList;
    }

    public ArrayList<struct> onGetdaydata(String date, int option) {
        ArrayList<struct> arrayList = new ArrayList<struct>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;
        if(option == 1){
            cursor = db.rawQuery("SELECT date, price, storename, category, memo, gridX, gridY, _id FROM " + tableName + " where date like '"
                    + date.substring(0,8) + "%'", null);
        }
        else{
            cursor = db.rawQuery("SELECT date, price, storename, category, memo, gridX, gridY, _id FROM " + tableName2 + " where date like '"
                    + date.substring(0,8) + "%'", null);
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