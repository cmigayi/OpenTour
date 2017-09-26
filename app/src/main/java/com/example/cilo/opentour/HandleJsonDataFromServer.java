package com.example.cilo.opentour;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 4/26/17.
 */

public class HandleJsonDataFromServer {
    JSONObject jsonObject;
    JSONArray dataFromServerJsonArray;
    HashMap<String,String> dataFromServerHashMap;
    ArrayList<HashMap<String,String>> dataFromServerArraylist;
    String response,jsonStringName,jsonStringData = null;
    boolean state;

    public HandleJsonDataFromServer(String response, String jsonStringName) throws JSONException {
        this.response = response;
        this.jsonStringName = jsonStringName;
        dataFromServerArraylist = new ArrayList<HashMap<String, String>>();
        jsonStringData = null;
        jsonObject = new JSONObject(response);
    }

    public  boolean startTour(){
        try{
            dataFromServerJsonArray = getJsonArray();
            jsonStringData = checkState(dataFromServerJsonArray);

            if(jsonStringData != null){
                state = true;
            }else{
                state = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return state;
    }

    public  ArrayList<HashMap<String,String>> getTours(){

        try{
            dataFromServerJsonArray = getJsonArray();
            jsonStringData = checkState(dataFromServerJsonArray);

            if(jsonStringData != null){
                JSONObject secondDataItem = dataFromServerJsonArray.getJSONObject(1);
                dataFromServerJsonArray = secondDataItem.getJSONArray(jsonStringName);

                for(int i=0; i< dataFromServerJsonArray.length(); i++){
                    JSONObject dataItem = dataFromServerJsonArray.getJSONObject(i);
                    String tourId = dataItem.getString("tour_id");
                    String userId = dataItem.getString("user_id");
                    String title = dataItem.getString("title");
                    String destination = dataItem.getString("destination");
                    String status = dataItem.getString("status");
                    String dateTime = dataItem.getString("dateTime");

                    dataFromServerHashMap = new HashMap<String, String>();
                    dataFromServerHashMap.put("tour_id", tourId);
                    dataFromServerHashMap.put("user_id", userId);
                    dataFromServerHashMap.put("title", title);
                    dataFromServerHashMap.put("destination", destination);
                    dataFromServerHashMap.put("status", status);
                    dataFromServerHashMap.put("dateTime", dateTime);

                    dataFromServerArraylist.add(dataFromServerHashMap);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return dataFromServerArraylist;
    }

    public  boolean postToursMoments(){
        try{
            dataFromServerJsonArray = getJsonArray();
            jsonStringData = checkState(dataFromServerJsonArray);

            if(jsonStringData != null){
                state = true;
            }else{
                state = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return state;
    }

    public  ArrayList<HashMap<String,String>> getTourMoments(){

        try{
            dataFromServerJsonArray = getJsonArray();
            jsonStringData = checkState(dataFromServerJsonArray);

            if(jsonStringData != null){
                JSONObject secondDataItem = dataFromServerJsonArray.getJSONObject(1);
                dataFromServerJsonArray = secondDataItem.getJSONArray(jsonStringName);

                for(int i=0; i< dataFromServerJsonArray.length(); i++){
                    JSONObject dataItem = dataFromServerJsonArray.getJSONObject(i);
                    String tourId = dataItem.getString("tour_id");
                    String imgUrl = dataItem.getString("img_url");
                    String comment = dataItem.getString("comment");
                    String dateTime = dataItem.getString("date_time");

                    dataFromServerHashMap = new HashMap<String, String>();
                    dataFromServerHashMap.put("tour_id", tourId);
                    dataFromServerHashMap.put("img_url", imgUrl);
                    dataFromServerHashMap.put("comment", comment);
                    dataFromServerHashMap.put("date_time", dateTime);

                    dataFromServerArraylist.add(dataFromServerHashMap);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return dataFromServerArraylist;
    }

    public String checkState(JSONArray jsonArray) throws JSONException {
        JSONObject dataItem = jsonArray.getJSONObject(0);
        int val = dataItem.getInt("state");

        if(val == 1){
            JSONObject dataItem2 = jsonArray.getJSONObject(1);
            jsonStringData = dataItem2.getString(jsonStringName);
            return jsonStringData;
        }else{
            return null;
        }
    }

    public JSONArray getJsonArray() throws JSONException {

        if(jsonObject.length() == 0){
            dataFromServerArraylist = null;
        }

        dataFromServerJsonArray = jsonObject.getJSONArray("content");

        return dataFromServerJsonArray;
    }
}
