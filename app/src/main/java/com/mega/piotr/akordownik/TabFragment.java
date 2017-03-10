package com.mega.piotr.akordownik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class TabFragment extends Fragment implements AdapterView.OnItemClickListener
{
    public final static String AUTHOR = "AUTHOR";
    public final static String TITLE = "TITLE";
    ListViewAdapter lvadapter;

    @Override
    public void onResume(){
        super.onResume();
        lvadapter.clear();
        lvadapter.addAll(SectionsPagerAdapter.PagetData.get(getArguments().getInt(SectionsPagerAdapter.KEY)));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.button_list);
        lvadapter = new ListViewAdapter(this.getActivity());
        listView.setAdapter(lvadapter);
        listView.setOnItemClickListener(this);
        return rootView;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Pair<String,String> pair=lvadapter.getItem(position);
        Intent intent = new Intent(this.getActivity(), SongActivity.class);
        intent.putExtra(AUTHOR, pair.first);
        intent.putExtra(TITLE, pair.second);
        startActivity(intent);
    }
}