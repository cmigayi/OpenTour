package com.example.cilo.opentour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 6/15/17.
 */

public class CustomGridviewUserTour extends ArrayAdapter implements View.OnClickListener{

    ArrayList<HashMap<String,String>> arrayList;
    HashMap<String,String> hashMap;
    Context context;
    LayoutInflater layoutInflater;
    ViewHolder viewHolder;

    public CustomGridviewUserTour(Context context,  ArrayList<HashMap<String, String>> arrayList) {
        super(context, R.layout.custom_gridview_user_tour, arrayList);
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
            convertView = layoutInflater.inflate(R.layout.custom_gridview_user_tour,null);
            viewHolder.urlImg = (ImageView) convertView.findViewById(R.id.img_url);
            viewHolder.commentTv = (TextView) convertView.findViewById(R.id.comment);
            viewHolder.dateTime = (TextView) convertView.findViewById(R.id.date_time);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        hashMap = getItem(position);

        Picasso.with(context).load(hashMap.get("img_url")).into(viewHolder.urlImg);
        viewHolder.commentTv.setText(hashMap.get("comment"));
        viewHolder.dateTime.setText(hashMap.get("date_time"));

        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

        }
    }

    class ViewHolder{
        ImageView urlImg;
        TextView commentTv,dateTime;
    }
}
