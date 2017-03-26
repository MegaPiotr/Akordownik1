package com.mega.piotr.akordownik.ListViewAdapters;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.mega.piotr.akordownik.Activities.SongBooksActivity;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;

public class CheckedListViewAdapter extends ButtonListViewAdapter {

    public CheckedListViewAdapter(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    protected void initButton(int position, View convertView) {
        /*ImageButton button=(ImageButton)convertView.findViewById(R.id.imageButton);
        CheckSong song=(CheckSong)getItem(position);
        button.setTag(R.string.key_const,song);
        setImage(button,song);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckSong song =(CheckSong)view.getTag(R.string.key_const);
                boolean checked=song.toggle();
                if(checked)
                {
                    //todo add or remove from list
                }
                else{
                    //todo add or remove from list
                }
                setImage((ImageButton)view,song);
            }
        });*/
    }
    private void setImage(ImageButton button){
        /*if(song.checked)
            button.setImageResource(android.R.drawable.checkbox_on_background);
        else
            button.setImageResource(android.R.drawable.checkbox_off_background);*/
    }
}
