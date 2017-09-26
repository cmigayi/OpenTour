package com.example.cilo.opentour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by cilo on 6/19/17.
 */

public class ActivityUserSignup extends AppCompatActivity implements View.OnClickListener {
    Button signinBtn,signupBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
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
                intent = new Intent(this, ActivityMain.class);
                startActivity(intent);
                break;
            case R.id.signup_btn:
                break;
        }
    }
}
