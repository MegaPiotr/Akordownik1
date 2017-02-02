package com.mega.piotr.akordownik;
import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<Pair<String,String>>
{
    //add i remove są

    public ListViewAdapter(Context context) {
        super(context, 0);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Pair<String,String> data=getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item, parent, false);

        TextView textView1 = (TextView) convertView.findViewById(R.id.lvauthor);
        textView1.setText(data.first);

        TextView textView2 = (TextView) convertView.findViewById(R.id.lvtitle);
        textView2.setText(data.second);
        return convertView;
    }
}
