package com.mega.piotr.akordownik.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mega.piotr.akordownik.Activities.LibraryActivity;
import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Activities.TitleActivity;
import com.mega.piotr.akordownik.XmlAdapter;

public class ByAuthorFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_library, container, false);
        getActivity().setTitle("Wykonawcy");
        ListView lv= (ListView) view.findViewById(R.id.authors_list);
        XmlAdapter xmlAdapter=new XmlAdapter(this.getActivity());
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, xmlAdapter.getAuthors());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String author = (String) adapter.getItem(position);
        Intent intent = new Intent(this.getActivity(), TitleActivity.class);
        intent.putExtra(LibraryActivity.author_key, author);
        startActivity(intent);
    }
}
