package com.example.cilo.opentour;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 5/29/17.
 */

public class Tour {
    int tour_id;
    String tour_title,tour_destination,tour_date,status;
    HashMap<String,String> hashMap;

    public Tour() {
    }

    public Tour(ArrayList<HashMap<String,String>> arrayList) {
        HashMap<String,String> hashMap = arrayList.get(0);
        this.tour_id = Integer.parseInt(hashMap.get("tour_id"));
        this.tour_title = hashMap.get("title");
        this.tour_destination = hashMap.get("destination");
        this.tour_date = hashMap.get("dateTime");
        this.status = hashMap.get("status");
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public String getTour_title() {
        return tour_title;
    }

    public void setTour_title(String tour_title) {
        this.tour_title = tour_title;
    }

    public String getTour_destination() {
        return tour_destination;
    }

    public void setTour_comment(String tour_destination) {
        this.tour_destination = tour_destination;
    }

    public String getTour_date() {
        return tour_date;
    }

    public void setTour_date(String tour_date) {
        this.tour_date = tour_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
