package com.example.cilo.opentour;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 5/26/17.
 */

public class ActivityHome  extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button openTourBtn,aboutBtn;
    ImageButton profileBtn;
    FloatingActionButton postPicBtn;
    GridView gridView;
    TextView noData;
    Intent intent;

    HashMap<String,String> serverRequestDataHashmap,serverResponseDataHashmap;
    ArrayList<HashMap<String,String>> dataFromServerArraylist;
    String url;
    HandleJsonDataFromServer handleJsonDataFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        overridePendingTransition(0,0);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");

        aboutBtn = (Button) toolbar.findViewById(R.id.about_btn);
        openTourBtn = (Button) toolbar.findViewById(R.id.start_tour_btn);
        profileBtn = (ImageButton) toolbar.findViewById(R.id.profile_btn);
        setSupportActionBar(toolbar);

        postPicBtn = (FloatingActionButton) findViewById(R.id.fab_post_pics);
        gridView = (GridView) findViewById(R.id.tours_grid_view);
        noData = (TextView) findViewById(R.id.no_data);

        getTours();

        openTourBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);
        postPicBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.about_btn:
                intent = new Intent(this,OpenTourIntro.class);
                startActivity(intent);
                break;

            case R.id.start_tour_btn:
                intent = new Intent(this,ActivityOpenTour.class);
                startActivity(intent);
                break;

            case R.id.fab_post_pics:
                intent = new Intent(this, ActivityPostPics.class);
                startActivity(intent);
                break;
            case R.id.profile_btn:
                intent = new Intent(this, ActivityUserProfile.class);
                startActivity(intent);
                break;
        }
    }

    public void getTours(){
        url = "/get_tours.php";
        serverRequestDataHashmap = new HashMap<>();
        serverRequestDataHashmap.put("user_id",""+1);

        new ServerRequest(serverRequestDataHashmap, url, new UrlCallBack() {
            @Override
            public void done(String response) {
                if(response == null){

                }else{
                    try {
                        handleJsonDataFromServer = new HandleJsonDataFromServer(response,"tours");
                        dataFromServerArraylist = handleJsonDataFromServer.getTours();

                        if(dataFromServerArraylist == null){

                        }else{
                            Log.d("cilo1",""+dataFromServerArraylist);
                            noData.setVisibility(View.GONE);
                            gridView.setVisibility(View.VISIBLE);

                            CustomGridviewTour customGridviewTour =
                                    new CustomGridviewTour(getBaseContext(), dataFromServerArraylist);

                            gridView.setAdapter(customGridviewTour);
                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    serverResponseDataHashmap = dataFromServerArraylist.get(position);

                                    intent = new Intent(getBaseContext(),ActivityUserTour.class);
                                    intent.putExtra("hashmap",serverResponseDataHashmap);
                                    startActivity(intent);
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
