package com.example.cilo.opentour;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 4/20/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    FragmentManager fm;
    Bundle bundle;

    ArrayList<HashMap<String,String>> dataFromServerArraylist;

    FragmentUserTourStackView fragmentUserTourStackView;
    FragmentUserTourGridView fragmentUserTourGridView;

    public PagerAdapter(FragmentManager fm, int numOfTabs, ArrayList<HashMap<String,String>> dataFromServerArraylist) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.dataFromServerArraylist = dataFromServerArraylist;
        bundle = new Bundle();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                fragmentUserTourGridView = new FragmentUserTourGridView();
                bundle.putSerializable("arraylist",dataFromServerArraylist);
                fragmentUserTourGridView.setArguments(bundle);
                return fragmentUserTourGridView;
            case 1:
                fragmentUserTourStackView = new FragmentUserTourStackView();
                bundle.putSerializable("arraylist",dataFromServerArraylist);
                fragmentUserTourStackView.setArguments(bundle);
                return fragmentUserTourStackView;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
