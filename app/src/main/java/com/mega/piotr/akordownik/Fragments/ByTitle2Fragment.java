package com.mega.piotr.akordownik.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mega.piotr.akordownik.Activities.LibraryActivity;
import com.mega.piotr.akordownik.Activities.SongActivity;
import com.mega.piotr.akordownik.ListViewAdapters.CheckListView2Adapter;
import com.mega.piotr.akordownik.ListViewAdapters.ListView2Adapter;
import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;
import com.mega.piotr.akordownik.XmlAdapter;

import java.util.ArrayList;

public class ByTitle2Fragment extends ByTitle1Fragment {

    @Override
    protected ArrayAdapter<Song> getAdapter(ArrayList<Song>songs){
        boolean check=getActivity().getIntent().getBooleanExtra(LibraryActivity.check_key,false);
        if(check)
            return new CheckListView2Adapter(this.getActivity(),R.layout.listview_image_2_item,songs);
        else
            return new ListView2Adapter(this.getActivity(),R.layout.listview_2_item,songs);
    }
    @Override
    protected ArrayList<Song> getData() {
        XmlAdapter xmlAdapter=new XmlAdapter(this.getActivity());
        return xmlAdapter.getAllSongs();
    }
}
