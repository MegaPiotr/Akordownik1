package com.mega.piotr.akordownik;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import static android.R.attr.author;

public class TitleActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        ListView lv= (ListView) findViewById(R.id.song_list);
        Intent intent = getIntent();
        author = intent.getStringExtra(LibraryActivity.AUTHOR);
        setTitle(author);
        XmlAdapter xmlAdapter=new XmlAdapter(this);
        TitleListViewAdapter adapter = new TitleListViewAdapter(this,xmlAdapter.getTitles(author));
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String title=(String) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent(this, SongActivity.class);
        intent.putExtra(TabFragment.AUTHOR, author);
        intent.putExtra(TabFragment.TITLE, title);
        startActivity(intent);
    }

    public void addButtonClick(final String title)
    {
        DialogFragment newFragment = MyFragmentDialog.newInstance(author,title,MainActivity.controler.getPagesNames());
        newFragment.show(getSupportFragmentManager(),"dialog");
    }
}
