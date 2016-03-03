package com.labsgn.learn_android.activity;

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
import android.view.Menu;
import android.view.MenuItem;

import com.labsgn.learn_android.R;
import com.labsgn.learn_android.layout.FragmentBoxOffice;
import com.labsgn.learn_android.layout.FragmentSearch;
import com.labsgn.learn_android.layout.FragmentUpcoming;
import com.labsgn.learn_android.layout.MainFragment;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener{

    private Toolbar toolbar;
    private ViewPager viewPager;
    //Todo 6. Menggunakan materialTab :)
    private MaterialTabHost materialTab;

    //Todo 4. Membuat index untuk fragment
    private static final int
            MOVIES_SEARCH   = 0,
            MOVIES_HITS     = 1,
            MOVIES_UPCOMING = 2;


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
        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        materialTab = (MaterialTabHost) findViewById(R.id.mainMaterialTab);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        MainFragment drawerFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.mainDrawer), toolbar);

        //Todo 7. Sedikit modifikasi untuk viewPager & adapter
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position){
                materialTab.setSelectedNavigationItem(position);
            }
        });

        MainPagerAdapter adapter = (MainPagerAdapter) viewPager.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++){
            materialTab.addTab(
                    materialTab.newTab()
                            .setIcon(adapter.getIcon(i))
                            .setTabListener(this)
            );
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

    //Gunakan extends FragmentStatePagerAdapter agar onSavedInstance dipanggil
    class MainPagerAdapter extends FragmentStatePagerAdapter {
        private int[] icons = {R.drawable.ic_home_black_36dp, R.drawable.ic_assignment_black_36dp, R.drawable.ic_account_box_black_36dp};
        private String[] tabs = getResources().getStringArray(R.array.tabs);

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public CharSequence getPageTitle(int position) {
            return tabs[position];
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

        @Override
        public Fragment getItem(int position) {
            //Todo 5. Menampilkan fragment berdasarkan index posisi
            Fragment fragment = null;

            switch (position){
                case MOVIES_SEARCH:
                    fragment = FragmentSearch.newInstance("","");
                    break;

                case MOVIES_HITS:
                    fragment = FragmentBoxOffice.newInstance("","");
                    break;

                case MOVIES_UPCOMING:
                    fragment = FragmentUpcoming.newInstance("","");
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
