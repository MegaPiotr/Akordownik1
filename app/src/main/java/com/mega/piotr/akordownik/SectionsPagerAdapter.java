package com.mega.piotr.akordownik;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import java.util.ArrayList;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> sectionsNames;

    public SectionsPagerAdapter(FragmentManager fm)
    {
        super(fm);
        sectionsNames = new ArrayList<>();
    }
    public void addPage(String name)
    {
        sectionsNames.add(name);
    }
    public void removePage(String name)
    {
        sectionsNames.remove(name);
    }
    public boolean containPage(String name)
    {
        if(sectionsNames.contains(name))
            return true;
        return false;
    }
    public void addToPage(String name, Pair<String,String> data)
    {
        if(!sectionsNames.contains(name))
        {
            addPage(name);
        }
        for (int i=0;i<sectionsNames.size();i++) {
            if(sectionsNames.get(i).equals(name))
            {
                ControllerTabFragment fff=((ControllerTabFragment)getItem(i));
                ListViewAdapter ggg=fff.lvadapter;
                ggg.add(data);
            }
        }
    }
    public void removeFromPage(String name, Pair<String,String> data)
    {

    }
    @Override
    public Fragment getItem(int position)
    {
        ControllerTabFragment fragment=new ControllerTabFragment();
        return fragment;
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
