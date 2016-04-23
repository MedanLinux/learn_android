package com.labsgn.mt_design_swipe_tabs.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labsgn.mt_design_swipe_tabs.R;

/**
 * Created by rhony on 21/04/16.
 */
public class FragmentA extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_a,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
