package com.mega.piotr.akordownik.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;
import com.mega.piotr.akordownik.XmlAdapter;

import java.util.ArrayList;
import java.util.List;

public class TitleAuthorFragment extends ByTitleFragment {

    public TitleAuthorFragment() {
        // Required empty public constructor
    }

    @Override
    protected ArrayList<Song> getData() {
        ArrayList<Song> songs=new ArrayList<>();
        Intent intent=getActivity().getIntent();
        String author=intent.getStringExtra(getResources().getString(R.string.key_const));
        XmlAdapter xmlAdapter = new XmlAdapter(this.getActivity());
        List<String> titles=xmlAdapter.getTitles(author);
        for (String title:titles) {
            Song pair=new Song(author,title);
            songs.add(pair);
        }
        return songs;
    }
}
