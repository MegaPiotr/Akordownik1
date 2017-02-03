package com.mega.piotr.akordownik;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import java.util.ArrayList;
import java.util.List;

class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> sectionsNames;
    private ArrayList<List<Pair<String,String>>> data=new ArrayList<>();

    SectionsPagerAdapter(FragmentManager fm)
    {
        super(fm);
        sectionsNames = new ArrayList<>();
    }
    private void addPage(String name)
    {
        sectionsNames.add(name);
        data.add(new ArrayList<Pair<String, String>>());
    }
    public void removePage(String name)
    {
        int size=sectionsNames.size();
        for (int i=0;i<size;i++) {
            if(sectionsNames.get(i).equals(name))
            {
                sectionsNames.remove(i);
                data.remove(i);
                return;
            }
        }
    }
    public boolean containPage(String name)
    {
        return sectionsNames.contains(name);
    }
    void addToPage(String name, Pair<String,String> value)
    {
        if(!sectionsNames.contains(name))
            addPage(name);
        for (int i=0;i<sectionsNames.size();i++) {
            if(sectionsNames.get(i).equals(name))
            {
                data.get(i).add(value);
                return;
            }
        }
    }
    public void removeFromPage(String name, Pair<String,String> data)
    {
        //todo
    }
    @Override
    public Fragment getItem(int position)
    {
        ControllerTabFragment fragment=new ControllerTabFragment();
        fragment.addAll(data.get(position));
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
