package com.mega.piotr.akordownik.ListViewAdapters;
import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;

public class ListViewAdapter extends ArrayAdapter<Song>
{
    public ListViewAdapter(Context context) {
        super(context, 0);
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
}
