package com.mega.piotr.akordownik;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class SectionsPagerAdapter extends FragmentPagerAdapter {

    static final String KEY="KEY";
    static ArrayList<String> sectionsNames= new ArrayList<>();
    static public ArrayList<HashSet<Pair<String,String>>> PagetData=new ArrayList<>();

    SectionsPagerAdapter(FragmentManager fm){
        super(fm);
    }
    private void addPage(String name) {
        sectionsNames.add(name);
        PagetData.add(new HashSet<Pair<String, String>>());
        notifyDataSetChanged();
    }
    public void removePage(String name) {
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
                return;
            }
        }
    }
    public void removeFromPage(String name, Pair<String,String> data) {
        //todo
    }

    @Override
    public Fragment getItem(int position) {
        TabFragment fr=new TabFragment();
        Bundle bd=new Bundle();
        bd.putInt(KEY,position);
        fr.setArguments(bd);
        return fr;
    }
    @Override
    public int getCount()
    {
        return sectionsNames.size();
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        return sectionsNames.get(position);
    }
}
