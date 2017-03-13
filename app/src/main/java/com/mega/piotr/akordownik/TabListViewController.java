package com.mega.piotr.akordownik;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by piotr on 12.03.2017.
 */

public class TabListViewController {
    SectionsPagerAdapter tabAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    Activity activity;
    ArrayList<String> sectionsNames= new ArrayList<>();
    ArrayList<HashSet<Pair<String,String>>> PagerData =new ArrayList<>();
    public AppPreference appPreference;

    public TabListViewController(Activity context, SectionsPagerAdapter tabAdapter) {
        this.activity=context;
        this.tabAdapter=tabAdapter;
        appPreference=new AppPreference(activity);
        try {
            ArrayList<String> tabNames=(ArrayList<String>) appPreference.getTabNames();
            for (String tab:tabNames) {
                _addPage(tab);
                _addToPageAll(tab,appPreference.getItems(tab));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void init() {
        tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        viewPager = (ViewPager) activity.findViewById(R.id.container);
        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }
    public int getPagesCount(){
        return sectionsNames.size();
    }
    public void addPage(String name) {
        _addPage(name);
        try {
            appPreference.addTab(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void _addPage(String name) {
        sectionsNames.add(name);
        PagerData.add(new HashSet<Pair<String, String>>());
    }
    public void removePage(int id) {
        try {
            appPreference.removeTab(getPageName(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PagerData.remove(id);
        sectionsNames.remove(id);
        notifyMe();
    }
    public boolean containPage(String name)
    {
        return sectionsNames.contains(name);
    }
    public void addToPage(String name, Pair<String,String> value) {
        if(!sectionsNames.contains(name))
            addPage(name);
        for (int i=0;i<sectionsNames.size();i++) {
            if(sectionsNames.get(i).equals(name))
            {
                PagerData.get(i).add(value);
                tabAdapter.notifyDataSetChanged();
                try {
                    appPreference.addItem(name,value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
    private void _addToPageAll(String name, List<Pair<String,String>> values) {
        if(!sectionsNames.contains(name))
            _addPage(name);
        for (int i=0;i<sectionsNames.size();i++) {
            if(sectionsNames.get(i).equals(name))
            {
                PagerData.get(i).addAll(values);
                tabAdapter.notifyDataSetChanged();
                return;
            }
        }
    }
    public void removeFromPage(int id, int position) {
        HashSet<Pair<String,String>>set= PagerData.get(id);
        Iterator iterator=set.iterator();
        Pair<String,String>rem=null;
        for(int i=0;i<position+1;i++)
            rem=(Pair<String,String>)iterator.next();
        set.remove(rem);
        try {
            appPreference.removeItem(getPageName(id),rem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HashSet<Pair<String,String>> getPageData(int id){
        return PagerData.get(id);
    }
    public int getPagePossition(String name){
        return sectionsNames.indexOf(name);
    }
    public String getPageName(int id){
        return sectionsNames.get(id);
    }
    public ArrayList<String> getPagesNames(){
        return sectionsNames;
    }
    public int getSelectedPage()
    {
        return tabLayout.getSelectedTabPosition();
    }
    private void notifyMe()
    {
        tabAdapter.notifyDataSetChanged();
        viewPager.invalidate();
    }
}
