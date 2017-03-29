package com.mega.piotr.akordownik.ListViewAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;
import com.mega.piotr.akordownik.SongBookHolder;

import java.util.ArrayList;

public class CheckListView2Adapter extends CheckListView1Adapter {

    public CheckListView2Adapter(Context context, int resource, ArrayList<Song>data) {
        super(context,resource,data);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=super.getView(position,convertView,parent);
        TextView textView2 = (TextView) convertView.findViewById(R.id.lvauthor);
        Song data=getItem(position);
        textView2.setText(data.author);
        return convertView;
    }
}
