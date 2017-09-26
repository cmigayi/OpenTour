package com.example.cilo.opentour;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 5/29/17.
 */

public class CustomGridviewTour extends ArrayAdapter implements View.OnClickListener{
    ArrayList<HashMap<String,String>> arrayList;
    HashMap<String,String> hashMap;
    Context context;
    LayoutInflater layoutInflater;
    ViewHolder viewHolder;

    public CustomGridviewTour(Context context,  ArrayList<HashMap<String, String>> arrayList) {
        super(context, R.layout.custom_gridview_tour_item, arrayList);
        this.arrayList = arrayList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public HashMap<String, String> getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.custom_gridview_tour_item,null);
            viewHolder.postedImg = (ImageView) convertView.findViewById(R.id.posted_img);
            viewHolder.userImg = (ImageView) convertView.findViewById(R.id.user_img);
            viewHolder.destinationTv = (TextView) convertView.findViewById(R.id.destination);
            viewHolder.dateTimeTv = (TextView) convertView.findViewById(R.id.date_time);
            viewHolder.statusTv = (TextView) convertView.findViewById(R.id.status);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        hashMap = getItem(position);

        if(hashMap.get("status").equals("open")){

        }else{
            viewHolder.statusTv.setVisibility(View.GONE);
        }

        viewHolder.destinationTv.setText(hashMap.get("destination"));
        viewHolder.dateTimeTv.setText(hashMap.get("dateTime"));

        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

        }
    }

    class ViewHolder{
        ImageView postedImg,userImg;
        TextView destinationTv,dateTimeTv,statusTv;
    }
}
