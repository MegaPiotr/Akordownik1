package com.mega.piotr.akordownik.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mega.piotr.akordownik.R;

import java.util.ArrayList;

class MyFragmentDialog extends DialogFragment
{
    private String author,title;
    private ArrayList<String>names;

    public static MyFragmentDialog newInstance(String author, String songTitle, ArrayList<String>names) {
        MyFragmentDialog frag = new MyFragmentDialog();
        frag.author=author;
        frag.title=songTitle;
        frag.names=names;
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        getDialog().setTitle("Śpiewniki");
        View convertView = (View) inflater.inflate(R.layout.empty_list, null);
        /*final ListView lv = (ListView) convertView.findViewById(R.id.ratatam);
        ArrayList<String> list=new ArrayList();
        list.addAll(names);
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
                    MainActivity.controler.addToPage(selectedFromList,new Pair<String, String>(author,title));
                    dismiss();
                }
            }
        });*/
        return convertView;
    }
    private void askTitle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Podaj nazwę");

        final EditText input = new EditText(this.getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        /*builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                MainActivity.controler.addToPage(m_Text,new Pair<String, String>(author,title));
            }
        });*/
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
