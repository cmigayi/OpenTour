package com.example.cilo.opentour;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by cilo on 6/13/17.
 */

public class ActivityUserProfile extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button startTourBtn,aboutBtn;
    ImageButton profileBtn;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        overridePendingTransition(0,0);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");

        aboutBtn = (Button) toolbar.findViewById(R.id.about_btn);
        startTourBtn = (Button) toolbar.findViewById(R.id.start_tour_btn);
        profileBtn = (ImageButton) toolbar.findViewById(R.id.profile_btn);
        setSupportActionBar(toolbar);

        startTourBtn.setText("Back home");
        startTourBtn.setBackgroundColor(Color.parseColor("#54A846"));

        startTourBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.about_btn:
                intent = new Intent(this,OpenTourIntro.class);
                startActivity(intent);
                break;

            case R.id.start_tour_btn:
                intent = new Intent(this,ActivityHome.class);
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
}
