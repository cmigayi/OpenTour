package com.example.cilo.opentour;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.StackView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 6/15/17.
 */

public class FragmentUserTourStackView extends Fragment {
    StackView stackView;

    ArrayList<HashMap<String,String>> dataFromActivityArrayList;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_tour_stackview, null);
        stackView = (StackView) view.findViewById(R.id.stack_view);

        bundle = getArguments();

        if (bundle == null) {

        } else {
            dataFromActivityArrayList = (ArrayList<HashMap<String, String>>) bundle.getSerializable("arraylist");
            Log.d("cilo5", "" + dataFromActivityArrayList);
            if (dataFromActivityArrayList == null) {

            } else {

                StackAdapterUserTour adapter = new StackAdapterUserTour(getContext(), dataFromActivityArrayList);
                stackView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
        return view;
    }
}
