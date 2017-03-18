package com.mega.piotr.akordownik;

import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by piotr on 16.03.2017.
 */

public class FragmentData {
    private AppCompatActivity activity;
    private ListViewAdapter adapter;
    private ArrayList<Pair<String,String>> PagerData=new ArrayList<>();
    private ArrayList<OnDataChangeListener> listeners=new ArrayList<>();

    public static class Builder{
        private FragmentData data=new FragmentData();
        public Builder setAdapter(ListViewAdapter adapter){
            data.setAdapter(adapter);
            return this;
        }
        public Builder setData(ArrayList<Pair<String,String>> dataset){
            data.setData(dataset);
            return this;
        }
        public Builder addOnDataChangeListener(OnDataChangeListener listener){
            data.addOnDataChangeListener(listener);
            return this;
        }
        public FragmentData build(){
            return data;
        }
    }

    private FragmentData(){}
    public void setAdapter(ListViewAdapter adapter){
        this.adapter=adapter;
    }
    public ListViewAdapter getAdapter(){
        return adapter;
    }
    public void setData(ArrayList<Pair<String,String>> data){
        PagerData=data;
    }
    public ArrayList<Pair<String,String>> getData(){
        return PagerData;
    }
    public boolean addItem(Pair<String,String>item){
        if(PagerData.contains(item))
            return false;
        else {
            PagerData.add(item);
            adapter.add(item);
            adapter.notifyDataSetChanged();
        }
        return true;
    }
    public void addItems(ArrayList<Pair<String,String>>items){
        for (Pair<String ,String>p:items) {
            addItem(p);
        }
    }
    public boolean removeItem(Pair<String,String>item){
        if(PagerData.contains(item)){
            PagerData.remove(item);
            adapter.remove(item);
            adapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }
    public Pair<String,String>getItem(int id){
        return PagerData.get(id);
    }
    public void addOnDataChangeListener(OnDataChangeListener listener){
        if(!listeners.contains(listener))
            listeners.add(listener);
    }
    public ArrayList<OnDataChangeListener>getListeners(){
        return listeners;
    }
}
