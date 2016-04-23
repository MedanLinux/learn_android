package com.labsgn.mt_design_swipe_tabs.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.labsgn.mt_design_swipe_tabs.Adapters.MainFragmentPagerAdapter;
import com.labsgn.mt_design_swipe_tabs.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager mainViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewPager = (ViewPager) findViewById(R.id.mainPager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mainViewPager.setAdapter(new MainFragmentPagerAdapter(fragmentManager));
    }
}