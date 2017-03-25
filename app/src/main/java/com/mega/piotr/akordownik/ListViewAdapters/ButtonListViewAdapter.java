package com.mega.piotr.akordownik.ListViewAdapters;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mega.piotr.akordownik.Activities.SongBooksActivity;
import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;

import static android.R.drawable.ic_menu_edit;

public class ButtonListViewAdapter extends ListViewAdapter {
    protected AppCompatActivity activity;
    public ButtonListViewAdapter(AppCompatActivity activity) {
        super(activity.getApplicationContext());
        this.activity=activity;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item_image, parent, false);

        init(convertView, position);
        initButton(position, convertView);

        return convertView;
    }

    protected void initButton(int position, View convertView) {
        ImageButton button=(ImageButton)convertView.findViewById(R.id.imageButton);
        button.setTag(R.string.key_const,getItem(position));
        //button.setImageResource();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Song song=(Song)((ImageButton)view).getTag(R.string.key_const);
                ((SongBooksActivity)activity).removeItemFromCurrentTab(song);
            }
        });
    }
}
