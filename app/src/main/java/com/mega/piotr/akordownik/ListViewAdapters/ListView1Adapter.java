package com.mega.piotr.akordownik.ListViewAdapters;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;

import java.util.ArrayList;

public class ListView1Adapter extends ArrayAdapter<Song>
{
    private int resource;
    protected ArrayList<Song> songs;
    public ListView1Adapter(Context context, int resource, ArrayList<Song>songs) {
        super(context, resource ,songs);
        this.resource=resource;
        this.songs=songs;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //convertView=super.getView(position,convertView,parent);
        if (convertView == null)
        {
            LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater ();  // <--- "The method getLayoutInflater() is undefined for the type myDynAdap"
            convertView = inflater.inflate (resource, parent, false);
        }
        Song data=getItem(position);
        TextView textView2 = (TextView) convertView.findViewById(R.id.lvtitle);
        textView2.setText(data.title);
        return convertView;
    }
}
