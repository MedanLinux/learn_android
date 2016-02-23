package com.labsgn.labsgn_learn_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import layout.MainFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

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
    }
}
