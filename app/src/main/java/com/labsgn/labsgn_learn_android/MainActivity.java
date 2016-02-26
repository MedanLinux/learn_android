package com.labsgn.labsgn_learn_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
                startActivity(new Intent(this, SubActivity.class));
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
        drawerFragment.setUp((DrawerLayout)findViewById(R.id.mainDrawer), toolbar);

        //Todo 6. Inisialisasi view
        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.mainSlidingTab);

        //Todo 14
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        slidingTabLayout.setViewPager(viewPager);

    }

    //Todo 7. Menambahkan Pager adapter untuk digunakan di slidingTabLayout
    class MainPagerAdapter extends FragmentPagerAdapter{

        //Todo 12. Inisialisasi string array tab
        private String[] tabs;

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        //Todo 13
        public CharSequence getPageTitle(int position){
            return tabs[position];
        }

        @Override
        public Fragment getItem(int position) {
            //Todo 10.
            return myMainFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    //Todo 8. Membuat class singleton custom fragment
    public  static class myMainFragment extends Fragment{

        private TextView textView;

        public static myMainFragment  getInstance(int position){
            myMainFragment myFragment = new myMainFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment.setArguments(args);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.main_fragment, container, false);
            textView = (TextView) layout.findViewById(R.id.position);
            Bundle bundle = getArguments();
            if (bundle != null){
                textView.setText("Selected page : "+bundle.getInt("position"));
            }

            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }
}
