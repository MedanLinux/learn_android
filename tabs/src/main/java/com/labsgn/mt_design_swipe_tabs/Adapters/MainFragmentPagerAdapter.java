package com.labsgn.mt_design_swipe_tabs.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.labsgn.mt_design_swipe_tabs.Fragments.FragmentA;
import com.labsgn.mt_design_swipe_tabs.Fragments.FragmentB;
import com.labsgn.mt_design_swipe_tabs.Fragments.FragmentC;

/**
 * Created by rhony on 21/04/16.
 */
public class MainFragmentPagerAdapter extends BaseFragmentPagerAdapter {

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0){
            fragment = new FragmentA();
        }
        if (position == 1){
            fragment = new FragmentB();
        }
        if (position == 2){
            fragment = new FragmentC();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
