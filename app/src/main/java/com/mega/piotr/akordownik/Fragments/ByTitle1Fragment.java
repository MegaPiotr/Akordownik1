package com.mega.piotr.akordownik.Fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.mega.piotr.akordownik.Activities.LibraryActivity;
import com.mega.piotr.akordownik.Activities.SongActivity;
import com.mega.piotr.akordownik.ListViewAdapters.CheckListView1Adapter;
import com.mega.piotr.akordownik.ListViewAdapters.ListView1Adapter;
import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;
import com.mega.piotr.akordownik.XmlAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class ByTitle1Fragment extends Fragment implements AdapterView.OnItemClickListener {

    private String author;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.song_list, container, false);

        ListView lv= (ListView) view.findViewById(R.id.universal_list);
        author=getActivity().getIntent().getStringExtra(LibraryActivity.author_key);
        ArrayList<Song> songs = getData();
        if(author!=null)
            getActivity().setTitle(author);
        else
            getActivity().setTitle("Piosenki");

        final ArrayAdapter<Song> adapter=getAdapter(songs);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        SearchView searchView=(SearchView)view.findViewById(R.id.search);
        EditText searchEdit=(EditText)(searchView.findViewById(searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null)));
        searchEdit.setTextColor(ContextCompat.getColor(this.getContext(),R.color.colorPrimary));
        searchEdit.setHintTextColor(ContextCompat.getColor(this.getContext(),R.color.colorPrimary));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return view;
    }

    protected ArrayList<Song> getData() {
        XmlAdapter xmlAdapter=new XmlAdapter(this.getActivity());
        ArrayList<Song> list= (ArrayList<Song>) xmlAdapter.getTitles(author);
        java.util.Collections.sort(list);
        return list;
    }

    protected ArrayAdapter<Song> getAdapter(ArrayList<Song>songs){
        boolean check=getActivity().getIntent().getBooleanExtra(LibraryActivity.check_key,false);
        if(check)
            return new CheckListView1Adapter(this.getActivity(),R.layout.listview_image_1_item,songs);
        else
            return new ListView1Adapter(this.getActivity(),R.layout.listview_1_item,songs);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Song song=(Song) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent(this.getActivity(), SongActivity.class);
        intent.putExtra("author", song.author);
        intent.putExtra("title", song.title);
        startActivity(intent);
        //todo
    }

    /*public void addButtonClick(final String title)
    {
        //DialogFragment newFragment = MyFragmentDialog.newInstance(author,title,MainActivity.controler.getPagesNames());
        //newFragment.show(getSupportFragmentManager(),"dialog");
    }*/
}
