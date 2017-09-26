package com.example.cilo.opentour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.filters.SmallTest;
import android.test.ActivityUnitTestCase;
import android.view.View;

import junit.framework.TestCase;

/**
 * Created by cilo on 5/28/17.
 */
public class ActivityHomeTest extends ActivityUnitTestCase<ActivityHome> {

    private Intent startIntent;

    public ActivityHomeTest() {
        super(ActivityHome.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Context context = getInstrumentation().getContext();
        startIntent = new Intent(context,ActivityHome.class);
    }

    public void testLaunchingSubActivityFiresIntent(){
        Activity activity = startActivity(startIntent,null,ActivityOpenTour.class);
        View button = activity.findViewById(R.id.start_tour_btn);
        button.performClick();

        assertNotNull(getStartedActivityIntent());
    }
}