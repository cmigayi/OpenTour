package com.example.cilo.opentour;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;


public class ActivityOpenTour extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button startTourBtn,aboutBtn,startBtn;
    EditText tourTitleET,tourDestinationET;
    String tourTitleStr,tourDestinationStr;
    Intent intent;

    HashMap<String,String> serverRequestDataHashmap,serverResponseDataHashmap;
    String url;
    HandleJsonDataFromServer handleJsonDataFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_tour);
        overridePendingTransition(0, 0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        startTourBtn = (Button) toolbar.findViewById(R.id.start_tour_btn);
        aboutBtn = (Button) findViewById(R.id.about_btn);
        setSupportActionBar(toolbar);

        startBtn = (Button) findViewById(R.id.start_btn);
        tourTitleET = (EditText) findViewById(R.id.tour_title);
        tourDestinationET = (EditText) findViewById(R.id.tour_destination);

        startTourBtn.setText("Back home");
        startTourBtn.setBackgroundColor(Color.parseColor("#54A846"));

        startTourBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
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
            case R.id.start_btn:
                startTour();
                break;
        }
    }

    public void startTour(){
        boolean titleState = false, destinationState = false;

        tourTitleStr = tourTitleET.getText().toString();
        tourDestinationStr = tourDestinationET.getText().toString();

        if(tourTitleStr.length() == 0){
            tourTitleET.setError("Title must be field!");
        }else{
            titleState = true;
        }

        if(tourDestinationStr.length() == 0){
            tourDestinationET.setError("Destination must be field");
        }else{
            destinationState = true;
        }

        if(titleState == true && destinationState == true){

            url = "/start_tour.php";
            serverRequestDataHashmap = new HashMap<>();
            serverRequestDataHashmap.put("user_id",""+1);
            serverRequestDataHashmap.put("title",tourTitleET.getText().toString());
            serverRequestDataHashmap.put("destination",tourDestinationET.getText().toString());

            new ServerRequest(serverRequestDataHashmap, url, new UrlCallBack() {
                @Override
                public void done(String response) {
                    if(response == null){

                    }else{
                        boolean state = false;

                        try {
                            handleJsonDataFromServer = new HandleJsonDataFromServer(response,"tours");
                            if(handleJsonDataFromServer == null){

                            }else {
                                state = handleJsonDataFromServer.startTour();

                                if (state == true) {
                                    intent = new Intent(getBaseContext(), ActivityHome.class);
                                    startActivity(intent);
                                } else {
                                    Log.d("cilo1", "Tour was not started!");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).execute();
        }
    }

}
