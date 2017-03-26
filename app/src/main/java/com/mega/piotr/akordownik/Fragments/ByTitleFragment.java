package com.mega.piotr.akordownik.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mega.piotr.akordownik.Activities.LibraryActivity;
import com.mega.piotr.akordownik.ListViewAdapters.ListViewAdapter;
import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;
import com.mega.piotr.akordownik.XmlAdapter;

import java.util.ArrayList;

public class ByTitleFragment extends Fragment implements AdapterView.OnItemClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.empty_list, container, false);

        ListView lv= (ListView) view.findViewById(R.id.universal_list);
        ArrayList<Song> songs = getData();
        String author=getActivity().getIntent().getStringExtra(LibraryActivity.author_key);

        ListViewAdapter adapter=getAdapter(songs);
        adapter.getFilter().filter(author);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        return view;
    }

    protected ArrayList<Song> getData() {
        ArrayList<Song> songs;
        XmlAdapter xmlAdapter = new XmlAdapter(this.getActivity());
        songs=xmlAdapter.getAllSongs();
        return songs;
    }

    protected ListViewAdapter getAdapter(ArrayList<Song>songs){
        return new ListViewAdapter(this.getActivity(),songs);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //String title=(String) adapterView.getAdapter().getItem(i);
        //Intent intent = new Intent(this, SongActivity.class);
        //intent.putExtra(TabFragment.AUTHOR, author);
        //intent.putExtra(TabFragment.TITLE, title);
        //startActivity(intent);
    }

    /*public void addButtonClick(final String title)
    {
        //DialogFragment newFragment = MyFragmentDialog.newInstance(author,title,MainActivity.controler.getPagesNames());
        //newFragment.show(getSupportFragmentManager(),"dialog");
    }*/
}
