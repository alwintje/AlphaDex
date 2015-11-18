package com.alwin.alphadex;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Alwin on 17-11-2015.
 */

public class DetailsFragment extends Fragment {



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.d("fragment", "started");

        String item = (String) getActivity().getIntent().getExtras().get("item");

//

        setHasOptionsMenu(true);
        this.getView().setFocusableInTouchMode(true);
        this.getView().requestFocus();
        this.getView().setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    LinearLayout gridList = (LinearLayout) getActivity().findViewById(R.id.grid_list);
                    gridList.setVisibility(View.INVISIBLE);

                    FrameLayout relativeDetail = (FrameLayout) getActivity().findViewById(R.id.details);
                    relativeDetail.setVisibility(View.GONE);

                    setHasOptionsMenu(false);

                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });
    }
    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.details_fragment, container, false);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here

//        getActivity().getMenuInflater().inflate(R.menu.details, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    //    @Override
//    public void onBackStackChanged() {
//
//        int backStackEntryCount = getFragmentManager().getBackStackEntryCount();
//
//        if(backStackEntryCount > 0){
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }else{
//            getActionBar().setDisplayHomeAsUpEnabled(false);
//        }
//    }
}
