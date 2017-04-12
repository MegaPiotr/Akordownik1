package com.mega.piotr.akordownik.ListViewAdapters;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;

import java.util.ArrayList;

public class ListView1Adapter extends ArrayAdapter<Song> implements Filterable
{
    private int resource;
    protected ArrayList<Song> songs;
    protected ArrayList<Song> filtered;
    public ListView1Adapter(Context context, int resource, ArrayList<Song>songs) {
        super(context, resource ,songs);
        this.resource=resource;
        this.songs=songs;
        this.filtered=songs;
    }
    @Override
    public Song getItem(int position) {
        return filtered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return filtered.size();
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
    @Override
    public Filter getFilter() {
        return new ValueFilter();
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList filterList = new ArrayList();
                for (int i = 0; i < songs.size(); i++) {
                    if ((songs.get(i).title.toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(songs.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = songs.size();
                results.values = songs;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered = (ArrayList) results.values;
            notifyDataSetChanged();
        }
    }
}
