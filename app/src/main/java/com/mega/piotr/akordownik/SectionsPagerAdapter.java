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

    SectionsPagerAdapter(FragmentManager fm){
        super(fm);
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
        return MainActivity.controller.getPagesCount();
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        return MainActivity.controller.getPageName(position);
    }
}
