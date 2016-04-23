package com.labsgn.mt_design_swipe_tabs.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by rhony on 21/04/16.
 */
public class BaseFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
