package com.hongik.alpha_money.DataStructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hongik.alpha_money.R;

import java.util.ArrayList;

/**
 * Created by jeon3029 on 16. 9. 20..
 */
public class ListCustomAdapter extends BaseAdapter {
    private ArrayList<struct> itemDatas = null;
    private LayoutInflater layoutInflater = null;

    public ListCustomAdapter(ArrayList<struct> itemDatas, Context ctx){
        this.itemDatas = itemDatas;
        layoutInflater = (LayoutInflater)ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
    }
    public void setItemDatas(ArrayList<struct> itemDatas){
        this.itemDatas = itemDatas;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount(){
        return itemDatas !=null ? itemDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {

        return (itemDatas !=null && (position>=0 &&position<itemDatas.size())?itemDatas.get(position):null);
    }

    @Override
    public long getItemId(int position) {
        return (itemDatas!=null &&(position>=0 && position<itemDatas.size())?position:0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.list_item,parent,false);
            //convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView history = (TextView)convertView.findViewById(R.id.history);
        TextView price = (TextView)convertView.findViewById(R.id.price);
        struct itemData_temp = itemDatas.get(position);
        history.setText(itemData_temp.storeName);
        price.setText(itemData_temp.price);
        return convertView;
    }

}
