package com.mega.piotr.akordownik.ListViewAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;
import com.mega.piotr.akordownik.SongBookHolder;

import java.util.ArrayList;

public class ButtonListView2Adapter extends ListView2Adapter {
    protected SongBookHolder holder;
    public ButtonListView2Adapter(Context context, int resource, ArrayList<Song>data) {
        super(context,resource,data);
        holder=SongBookHolder.getInstance();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=super.getView(position,convertView,parent);
        ImageButton button=(ImageButton)convertView.findViewById(R.id.imageButton);
        button.setTag(R.string.button_tag,getItem(position));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Song song=(Song)view.getTag(R.string.button_tag);
                holder.remove(song);
                holder.notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
