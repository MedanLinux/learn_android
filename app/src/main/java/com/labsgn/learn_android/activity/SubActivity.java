package com.labsgn.learn_android.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.labsgn.learn_android.layout.MyFragment;
import com.labsgn.learn_android.R;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class SubActivity extends AppCompatActivity implements MaterialTabListener{

    private Toolbar toolbar;

    private MaterialTabHost materialTab;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        initComponent();
    }

    private void initComponent() {
        toolbar = (Toolbar) findViewById(R.id.subToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        materialTab = (MaterialTabHost) findViewById(R.id.subMaterialTab);
        viewPager = (ViewPager) findViewById(R.id.subViewPager);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                materialTab.setSelectedNavigationItem(position);
            }
        });

        ViewPagerAdapter adapter = (ViewPagerAdapter) viewPager.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++){
            materialTab.addTab(
                    materialTab.newTab()
                    .setIcon(adapter.getIcon(i))
                    .setTabListener(this)
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item menu selection
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{

        int icons[] = {R.drawable.ic_home_black_36dp, R.drawable.ic_account_box_black_36dp, R.drawable.ic_assignment_black_36dp};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }

        private Drawable getIcon(int position){
            Drawable returnedDrawable;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                returnedDrawable = getResources().getDrawable(icons[position],null);
            }else {
                returnedDrawable = getResources().getDrawable(icons[position]);
            }
            return returnedDrawable;
        }
    }
}
