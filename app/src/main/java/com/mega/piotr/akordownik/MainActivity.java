package com.mega.piotr.akordownik;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static SectionsPagerAdapter sectionsPagerAdapter;
    public static ViewPager viewPager;
    public static TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabInit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            sectionsPagerAdapter.addPage("ogniskowy");
            updateTab();
            //sectionsPagerAdapter.addToPage("ogniskowy",new Pair<String, String>("Myslovits","Scenariusz dla moich sąsiadów"));
            //sectionsPagerAdapter.addToPage("ogniskowy",new Pair<String, String>("Myslovits","Długość dźwięku samotności"));
            //updateTab();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void tabInit() {
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        updateTab();
    }
    public static void updateTab() {
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
