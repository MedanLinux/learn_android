package com.labsgn.labsgn_learn_android;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;

import layout.MainFragment;
import layout.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item menu selection
        switch (item.getItemId()) {
            case R.id.actionSettings:
                return true;
            case R.id.navigate:
                //startActivity(new Intent(this, SubActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initComponent() {
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        MainFragment drawerFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.mainDrawer), toolbar);

        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.mainSlidingTab);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabView(R.layout.custom_tab, R.id.tabText);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent,null));
        }else {
            slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        }
        slidingTabLayout.setViewPager(viewPager);

    }

    class MainPagerAdapter extends FragmentStatePagerAdapter {
        private int[] icon = {R.drawable.ic_home_black_36dp, R.drawable.ic_assignment_black_36dp, R.drawable.ic_account_box_black_36dp};
        private String[] tabs = getResources().getStringArray(R.array.tabs);

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public CharSequence getPageTitle(int position) {
            Drawable drawable;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                drawable = getResources().getDrawable(icon[position], null);
            } else {
                drawable = getResources().getDrawable(icon[position]);
            }
            drawable.setBounds(0,0,37,37);

            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return spannableString;
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
