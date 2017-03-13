package com.mega.piotr.akordownik;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import java.io.IOException;
import java.util.List;

import static com.mega.piotr.akordownik.TabFragment.AUTHOR;

public class MainActivity extends AppCompatActivity {

    static TabListViewController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        controller=new TabListViewController(this,sectionsPagerAdapter);
        controller.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_library) {
            Intent intent = new Intent(this, LibraryActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_delete) {
            controller.removePage(controller.getSelectedPage());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

