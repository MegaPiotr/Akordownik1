package com.mega.piotr.akordownik;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by piotr on 12.03.2017.
 */

public class TabListViewController {
    SectionsPagerAdapter tabAdapter;
    Activity activity;
    ArrayList<String> sectionsNames= new ArrayList<>();
    ArrayList<HashSet<Pair<String,String>>> PagetData=new ArrayList<>();

    public TabListViewController(Activity context, SectionsPagerAdapter tabAdapter) {
        this.activity=context;
        this.tabAdapter=tabAdapter;
    }
    public void init() {
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) activity.findViewById(R.id.container);
        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }
    public int getPagesCount(){
        return sectionsNames.size();
    }
    public void addPage(String name) {
        sectionsNames.add(name);
        PagetData.add(new HashSet<Pair<String, String>>());
    }
    public void removePage(int id) {
        /*int size=sectionsNames.size();
        for (int i=0;i<size;i++) {
            if(sectionsNames.get(i).equals(name))
            {
                sectionsNames.remove(i);
                data.remove(i);
                return;
            }
        }*/
        //todo
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
                PagetData.get(i).add(value);
                tabAdapter.notifyDataSetChanged();
                return;
            }
        }
    }
    public void removeFromPage(int id, int position) {
        HashSet<Pair<String,String>>set=PagetData.get(id);
        Iterator iterator=set.iterator();
        Pair<String,String>rem=null;
        for(int i=0;i<position+1;i++)
            rem=(Pair<String,String>)iterator.next();
        set.remove(rem);
    }
    public HashSet<Pair<String,String>> getPageData(int id){
        return PagetData.get(id);
    }
    /*public Pair<String,String> getData(int id, int position){
        //todo
    }
    public int getItemPossition(Pair<String,String>item){
        //todo
    }*/
    public int getPagePossition(String name){
        return sectionsNames.indexOf(name);
    }
    public String getPageName(int id){
        return sectionsNames.get(id);
    }
    public ArrayList<String> getPagesNames(){
        return sectionsNames;
    }
}
