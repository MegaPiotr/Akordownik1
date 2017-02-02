package com.mega.piotr.akordownik;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ControllerTabFragment extends Fragment implements AdapterView.OnItemClickListener
{
    ListViewAdapter lvadapter;

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
        //kliknięcie w element listy
    }
}