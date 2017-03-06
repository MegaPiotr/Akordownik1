package com.mega.piotr.akordownik;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.author;

public class TitleListViewAdapter extends ArrayAdapter<String> implements View.OnClickListener {
    //add i remove są
    Context context;
    public TitleListViewAdapter(Context context,List<String>titles) {
        super(context, 0, titles);
        this.context=context;
        }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String data=getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.title_listview_item, parent, false);

        TextView textView1 = (TextView) convertView.findViewById(R.id.text_title);
        textView1.setText(data);

        ImageButton button= (ImageButton) convertView.findViewById(R.id.add_button);
        button.setOnClickListener(this);
        button.setTag(data);
        return convertView;
    }

    @Override
    public void onClick(View view) {
        String title=(String)view.getTag();
        TitleActivity ta=(TitleActivity)context;
        ta.addButtonClick(title);
    }
}
