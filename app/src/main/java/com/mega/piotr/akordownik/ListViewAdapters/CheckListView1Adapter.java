package com.mega.piotr.akordownik.ListViewAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;
import com.mega.piotr.akordownik.SongBookHolder;

import java.util.ArrayList;

public class CheckListView1Adapter extends ListView1Adapter{

    protected SongBookHolder holder;
    public CheckListView1Adapter(Context context, int resource, ArrayList<Song>data) {
        super(context,resource,data);
        holder=SongBookHolder.getInstance();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=super.getView(position,convertView,parent);
        ImageButton imageButton=(ImageButton)convertView.findViewById(R.id.imageButton);
        imageButton.setTag(R.string.button_tag,getItem(position));
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Song song=(Song)view.getTag(R.string.button_tag);
                if(song.isChecked()){
                    check(false,(ImageButton)view,song);
                    holder.add(song);
                }
                else {
                    check(true,(ImageButton)view, song);
                    holder.remove(song);
                }
            }
        });
        Song song=getItem(position);
        if(holder.contain(song))
            check(true,imageButton,song);
        else
            check(false,imageButton,song);
        return convertView;
    }
    private void check(boolean b,ImageButton imageButton,Song song){
        song.setChecked(b);
        if(b) {
            imageButton.setImageResource(android.R.drawable.checkbox_on_background);
        }
        else
            imageButton.setImageResource(android.R.drawable.checkbox_off_background);
    }
}
