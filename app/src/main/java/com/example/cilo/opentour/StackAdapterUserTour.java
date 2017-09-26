package com.example.cilo.opentour;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 5/25/17.
 */

public class StackAdapterUserTour extends BaseAdapter {

    ArrayList<HashMap<String,String>> arrayList;
    HashMap<String,String> hashMap;
    LayoutInflater inflater;
    ViewHolder holder = null;
    Context context;

    public StackAdapterUserTour(Context context, ArrayList<HashMap<String,String>> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public HashMap<String,String> getItem(int pos) {
        return arrayList.get(pos);
    }
    @Override
    public long getItemId(int pos) {
        return pos;
    }
    @Override
    public View getView(int pos, View view, ViewGroup parent) {

        if (view == null) {
            view = inflater.inflate(R.layout.stack_adapter_user_tour_item, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) view.findViewById(R.id.image);
            holder.desc = (TextView) view.findViewById(R.id.desc);
            holder.dateTime = (TextView) view.findViewById(R.id.date_time);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        hashMap = getItem(pos);
        Picasso.with(context).load(hashMap.get("img_url")).into(holder.image);
        holder.desc.setText(hashMap.get("comment"));
        holder.dateTime.setText(hashMap.get("date_time"));
        Log.d("cilo1",""+getItemId(pos));
        Log.d("cilo1",""+pos);
        return view;
    }
    public class ViewHolder {
        ImageView image;
        TextView desc;
        TextView dateTime;
    }

}
