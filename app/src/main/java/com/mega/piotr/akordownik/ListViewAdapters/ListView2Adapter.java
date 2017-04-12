package com.mega.piotr.akordownik.ListViewAdapters;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;

import java.util.ArrayList;

public class ListView2Adapter extends ListView1Adapter
{
    public ListView2Adapter(Context context, int resource,ArrayList<Song>songs) {
        super(context,resource,songs);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=super.getView(position,convertView,parent);
        Song data=getItem(position);
        TextView textView1 = (TextView) convertView.findViewById(R.id.lvauthor);
        textView1.setText(data.author);
        ImageView lineColorCode = (ImageView)convertView.findViewById(R.id.imageView2);
        return convertView;
    }
}
