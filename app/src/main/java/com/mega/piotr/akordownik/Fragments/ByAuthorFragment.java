package com.mega.piotr.akordownik.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mega.piotr.akordownik.Activities.LibraryActivity;
import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Activities.TitleActivity;
import com.mega.piotr.akordownik.XmlAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ByAuthorFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.author_list, container, false);
        getActivity().setTitle("Wykonawcy");
        ListView lv= (ListView) view.findViewById(R.id.universal_list);
        XmlAdapter xmlAdapter=new XmlAdapter(this.getActivity());
        List<String> authors=xmlAdapter.getAuthors();
        java.util.Collections.sort(authors, Collator.getInstance(new Locale("PL")));
        adapter = new SimpleArrayAdapter(this.getActivity(),authors);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String author = (String) adapter.getItem(position);
        Intent intent = new Intent(this.getActivity(), TitleActivity.class);
        intent.putExtra(LibraryActivity.author_key, author);
        intent.putExtra(LibraryActivity.check_key, getActivity().getIntent().getBooleanExtra(LibraryActivity.check_key,false));
        startActivity(intent);
    }
    class SimpleArrayAdapter extends ArrayAdapter<String>{

        public SimpleArrayAdapter(@NonNull Context context, @NonNull List<String> objects) {
            super(context, 0, objects);
        }
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null)
            {
                LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater ();  // <--- "The method getLayoutInflater() is undefined for the type myDynAdap"
                convertView = inflater.inflate (R.layout.listview_1_item, parent, false);
            }
            TextView textView=(TextView) convertView.findViewById(R.id.lvtitle);
            textView.setText(this.getItem(position));
            ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView2);
            imageView.setImageResource(R.drawable.singer);
            return convertView;
        }
    }
}
