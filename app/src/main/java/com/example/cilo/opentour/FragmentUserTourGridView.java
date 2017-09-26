package com.example.cilo.opentour;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 6/15/17.
 */

public class FragmentUserTourGridView extends Fragment{
    GridView userTourGridview;

    ArrayList<HashMap<String,String>> dataFromActivityArrayList;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_tour_gridview,null);
        userTourGridview = (GridView) view.findViewById(R.id.user_tour_grid_view);

        bundle = getArguments();

        if(bundle == null){

        }else {
            dataFromActivityArrayList = (ArrayList<HashMap<String, String>>) bundle.getSerializable("arraylist");
            Log.d("cilo5",""+dataFromActivityArrayList);
            if(dataFromActivityArrayList == null){

            }else {
                CustomGridviewUserTour customGridviewUserTour =
                        new CustomGridviewUserTour(getContext(), dataFromActivityArrayList);
                userTourGridview.setAdapter(customGridviewUserTour);
            }
        }

        return view;
    }
}
