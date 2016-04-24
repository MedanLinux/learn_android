package com.labsgn.mt_design_swipe_tabs.Activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.labsgn.mt_design_swipe_tabs.Adapters.MainFragmentPagerAdapter;
import com.labsgn.mt_design_swipe_tabs.Animations.ZoomOutTranformer;
import com.labsgn.mt_design_swipe_tabs.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager mainViewPager = (ViewPager) findViewById(R.id.mainPager);

        assert mainViewPager != null;
        mainViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));

        //Todo: silahkan ubah jenis animasi yang mau di pakai disini
        mainViewPager.setPageTransformer(true, new ZoomOutTranformer());
    }
}