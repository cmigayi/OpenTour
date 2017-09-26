package com.example.cilo.opentour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.StackView;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener{
    Button signinBtn,signupBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(0,0);

        signinBtn = (Button) findViewById(R.id.signin_btn);
        signupBtn = (Button) findViewById(R.id.signup_btn);

        signinBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.signin_btn:
                intent = new Intent(this, ActivityHome.class);
                startActivity(intent);
                break;
            case R.id.signup_btn:
                intent = new Intent(this, ActivityUserSignup.class);
                startActivity(intent);
                break;
        }
    }
}
