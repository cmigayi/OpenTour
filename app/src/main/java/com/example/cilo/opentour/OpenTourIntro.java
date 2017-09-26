package com.example.cilo.opentour;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by cmigayi on 16-Dec-15.
 */
public class OpenTourIntro extends AppIntro {
    @Override
    public void init(@Nullable Bundle bundle) {
        addSlide(SampleSlide.newInstance(R.layout.fragment_open_tour_demo_one));
        addSlide(SampleSlide.newInstance(R.layout.fragment_open_tour_demo_two));

        setBarColor(Color.parseColor("#ee0000"));
        setSeparatorColor(Color.parseColor("#2196F3"));
    }

    @Override
    public void onSkipPressed() {
        finish();
    }

    @Override
    public void onDonePressed() {
        finish();
    }
}
