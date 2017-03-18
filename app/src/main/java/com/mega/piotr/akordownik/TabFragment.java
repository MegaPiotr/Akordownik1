package com.mega.piotr.akordownik;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class TabFragment extends Fragment implements AdapterView.OnItemClickListener
{
    public final static String AUTHOR = "AUTHOR";
    public final static String TITLE = "TITLE";
    private static HashMap<String,FragmentData> FragmentsData;
    String name;

    public static void setData(HashMap<String,FragmentData> data){
        FragmentsData=data;
    }
    /*public void setOnDataChangeListener(OnDataChangeListener listener){
        listeners.add(listener);
    }*/
    /*@Override
    public void onResume(){
        super.onResume();
        //lvadapter.clear();
        //lvadapter.addAll(MainActivity.controller.getPageData(name));
        //lvadapter.notifyDataSetChanged();
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment, container, false);
        setHasOptionsMenu(true);

        name=getArguments().getString(SectionsPagerAdapter.KEY);

        ListViewAdapter lvadapter = FragmentsData.get(name).getAdapter();
        ListView listView = (ListView) rootView.findViewById(R.id.button_list);
        listView.setAdapter(lvadapter);
        listView.setOnItemClickListener(this);
        registerForContextMenu(listView);

        return rootView;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Pair<String,String> pair=FragmentsData.get(name).getItem(position);
        Intent intent = new Intent(this.getActivity(), SongActivity.class);
        intent.putExtra(AUTHOR, pair.first);
        intent.putExtra(TITLE, pair.second);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete: // <-- your custom menu item id here
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Pair<String,String>pair=MainActivity.controler.getItemFromPage(name,info.position);
                for (OnDataChangeListener li:FragmentsData.get(name).getListeners()) {
                    li.onDeleteItem(name,pair);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}