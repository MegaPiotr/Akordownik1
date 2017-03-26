package com.mega.piotr.akordownik.ListViewAdapters;
import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Song>
{
    private ArrayList<Song> songs;
    public ListViewAdapter(Context context,ArrayList<Song>songs) {
        super(context, 0,songs);this.songs=songs;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item, parent, false);

        init(convertView, position);

        return convertView;
    }

    protected void init(View convertView, final int position) {

        Song data=getItem(position);
        TextView textView1 = (TextView) convertView.findViewById(R.id.lvauthor);
        textView1.setText(data.author);

        TextView textView2 = (TextView) convertView.findViewById(R.id.lvtitle);
        textView2.setText(data.title);
    }
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Song> list = new ArrayList<>();
                if(constraint!=null) {
                    for (Song song:songs) {
                        if (song.author.equals(constraint)) {
                            list.add(song);
                        }
                    }
                }
                else
                    list.addAll(songs);
                results.count = list.size();
                results.values = list;
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((ArrayList<Song>)results.values);
                notifyDataSetChanged();
                notifyDataSetInvalidated();
            }
        };
    }
}
