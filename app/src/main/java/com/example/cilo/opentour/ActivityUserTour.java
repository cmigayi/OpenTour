package com.example.cilo.opentour;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 6/15/17.
 */

public class ActivityUserTour extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button startTourBtn,aboutBtn;
    Intent intent;
    HashMap<String,String> dataFromActivityHashmap;

    TextView pageTitleTv;

    TabLayout tabLayout;
    ViewPager viewPager;

    Common common;

    HashMap<String,String> serverRequestDataHashmap,serverResponseDataHashmap;
    ArrayList<HashMap<String,String>> dataFromServerArraylist,resultArraylist;
    String url;
    HandleJsonDataFromServer handleJsonDataFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tour);
        overridePendingTransition(0,0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        aboutBtn = (Button) toolbar.findViewById(R.id.about_btn);
        startTourBtn = (Button) toolbar.findViewById(R.id.start_tour_btn);
        setSupportActionBar(toolbar);

        common = new Common(this);

        startTourBtn.setText("Back home");
        startTourBtn.setBackgroundColor(Color.parseColor("#54A846"));

        intent = getIntent();
        dataFromActivityHashmap = (HashMap<String, String>) intent.getSerializableExtra("hashmap");

        pageTitleTv = (TextView) findViewById(R.id.page_title);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        pageTitleTv.setText(dataFromActivityHashmap.get("title"));

        tabLayout.addTab(tabLayout.newTab().setText("Story Line"));
        tabLayout.addTab(tabLayout.newTab().setText("Stack View"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        getUserTourData();
        Log.d("cilo51",""+dataFromServerArraylist);

        startTourBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_tour_btn:
                intent = new Intent(this,ActivityHome.class);
                startActivity(intent);
                break;
            case R.id.about_btn:
                intent = new Intent(this,OpenTourIntro.class);
                startActivity(intent);
                break;
        }
    }

    public void getUserTourData(){
        url = "/get_tours_posted_moments.php";
        serverRequestDataHashmap = new HashMap<>();
        serverRequestDataHashmap.put("user_id",""+1);
        serverRequestDataHashmap.put("tour_id",""+1);
        new ServerRequest(serverRequestDataHashmap, url, new UrlCallBack() {
            @Override
            public void done(String response) {
                if(response == null){

                }else{
                    try {
                        handleJsonDataFromServer = new HandleJsonDataFromServer(response,"tours");
                        dataFromServerArraylist = handleJsonDataFromServer.getTourMoments();
                        if(dataFromServerArraylist == null){

                        }else{
                            Log.d("cilo52",""+dataFromServerArraylist);
                            PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),dataFromServerArraylist);

                            viewPager.setAdapter(pagerAdapter);
                            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    viewPager.setCurrentItem(tab.getPosition());
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).execute();
    }
}
