package com.hongik.alpha_money.DataStructure;

/**
 * Created by jeon3029 on 16. 9. 12..
 */


public class struct {
    public int ID;
    public String date; // ex.2016102119125 yyyy/mm/dd/hh/mm/day
    public String price;
    public String storeName;
    public String category;
    public String memo;
    public double gridX;
    public double gridY;
    public boolean invalid;


//TODO: 나중에 쓰게 되면 추가
    public int week;
    public void SetWeek(int n){
        week = n;
    }// 0 sun 1 mon 2 tue 3 wed 4 thu 5 fri 6 sat
    public int GetWeek(){return week;}
}

