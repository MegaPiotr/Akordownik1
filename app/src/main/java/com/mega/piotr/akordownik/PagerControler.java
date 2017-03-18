package com.mega.piotr.akordownik;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by piotr on 18.03.2017.
 */

public class PagerControler implements OnDataChangeListener{
    private SectionsPagerAdapter adapter;
    private AppCompatActivity activity;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppPreference appPreference;
    private ArrayList<String> sectionsNames= new ArrayList<>();
    private HashMap<String,FragmentData> PagerData =new HashMap<>();

    public PagerControler(AppCompatActivity activity){
        adapter=new SectionsPagerAdapter(this,activity.getSupportFragmentManager());
        this.activity=activity;
        appPreference=new AppPreference(activity);
        try {
            ArrayList<String> tabNames=(ArrayList<String>) appPreference.getTabNames();
            for (String tab:tabNames) {
                _addPage(tab);
                _addToPageAll(tab,(ArrayList)appPreference.getItems(tab));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        TabFragment.setData(PagerData);
        init(activity);
    }
    public void addPage(String name) {
        _addPage(name);
        try {
            appPreference.addTab(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removePage(String name) {
        /*try {
            appPreference.removeTab(name);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        int position=sectionsNames.indexOf(name);
        PagerData.remove(name);
        sectionsNames.remove(name);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment f : fragments) {
            transaction.remove(f);
        }
        transaction.commit();
        viewPager.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    public void addToPage(String name, Pair<String,String> value) {
        if(!sectionsNames.contains(name))
            addPage(name);
        PagerData.get(name).addItem(value);
        try {
            appPreference.addItem(name,value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeFromPage(String name, Pair<String,String> value) {
        PagerData.get(name).removeItem(value);
        try {
            appPreference.removeItem(name,value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPagesCount(){
        return sectionsNames.size();
    }
    public int getSelectedPage() {
        return tabLayout.getSelectedTabPosition();
    }

    public ArrayList<String> getPagesNames(){
        return sectionsNames;
    }
    public String getPageName(int id){
        return sectionsNames.get(id);
    }

    private void _addPage(String name) {
        sectionsNames.add(name);
        FragmentData.Builder builder=new FragmentData.Builder()
                .setData(new ArrayList<Pair<String, String>>())
                .setAdapter(new ListViewAdapter(activity))
                .addOnDataChangeListener(this);
        FragmentData fd=builder.build();
        PagerData.put(name,fd);
        adapter.notifyDataSetChanged();
    }
    private void _addToPageAll(String name, ArrayList<Pair<String,String>> values) {
        if(!sectionsNames.contains(name))
            _addPage(name);
        PagerData.get(name).addItems(values);
    }

    public Pair<String,String>getItemFromPage(String name,int position) {
        return PagerData.get(name).getItem(position);
    }
    private void init(Activity activity) {
        tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        viewPager = (ViewPager) activity.findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDeleteItem(String tab, Pair<String, String> data) {
        removeFromPage(tab,data);
    }
    @Override
    public void onAddItem(String tab, Pair<String, String> data) {
        addToPage(tab,data);
    }
}
