package com.mega.piotr.akordownik;
import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

class SectionsPagerAdapter extends FragmentStatePagerAdapter
{
    public static final String KEY = "key";
    private PagerControler controler;

    SectionsPagerAdapter(PagerControler controler,FragmentManager fm){
        super(fm);
        this.controler=controler;
    }

    @Override
    public Fragment getItem(int position) {
        TabFragment fr=new TabFragment();
        Bundle bd=new Bundle();
        bd.putString(KEY, controler.getPageName(position));
        fr.setArguments(bd);
        return fr;
    }
    @Override
    public int getCount() {
        return controler.getPagesCount();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return controler.getPageName(position);
    }
    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }
}
