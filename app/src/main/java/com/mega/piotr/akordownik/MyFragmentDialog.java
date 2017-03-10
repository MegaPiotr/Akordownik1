package com.mega.piotr.akordownik;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by piotr on 10.03.2017.
 */

class MyFragmentDialog extends DialogFragment
{
    private String author,title;

    public static MyFragmentDialog newInstance(String author, String songTitle) {
        MyFragmentDialog frag = new MyFragmentDialog();
        frag.author=author;
        frag.title=songTitle;
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        getDialog().setTitle("Śpiewniki");
        View convertView = (View) inflater.inflate(R.layout.custom, null);
        final ListView lv = (ListView) convertView.findViewById(R.id.ratatam);
        ArrayList<String> list=new ArrayList();
        list.addAll(MainActivity.sectionsPagerAdapter.sectionsNames);
        list.add("Nowy");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==lv.getCount()-1) {
                    dismiss();
                    askTitle();
                }
                else
                {
                String selectedFromList = (String)(lv.getItemAtPosition(position));
                MainActivity.sectionsPagerAdapter.addToPage(selectedFromList,new Pair<String, String>(author,title));
                dismiss();
                }
            }
        });
        return convertView;
    }
    private void askTitle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Podaj nazwę");

        final EditText input = new EditText(this.getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                MainActivity.sectionsPagerAdapter.addToPage(m_Text,new Pair<String, String>(author,title));
            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
