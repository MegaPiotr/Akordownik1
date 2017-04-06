package com.mega.piotr.akordownik.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import android.widget.Toast;

import com.mega.piotr.akordownik.AppPreference;
import com.mega.piotr.akordownik.ListViewAdapters.ButtonListView2Adapter;
import com.mega.piotr.akordownik.ListViewAdapters.ListView2Adapter;
import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;
import com.mega.piotr.akordownik.SongBookHolder;

import java.io.IOException;
import java.util.ArrayList;

public class SongBooksActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,SongBookHolder.UpdateDataListener {

    private ArrayAdapter<String> spinnerAdapter;
    private ListView2Adapter adapter;
    private AppPreference appPreference;
    private Spinner spinner;
    private ArrayList<Song>items=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_books);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listViewInit();
        spinnerInit();
        fabInit();
        SongBookHolder.getInstance().setUpdateDataListener(this);
    }
    private void fabInit() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),LibraryActivity.class);
                intent1.putExtra(LibraryActivity.check_key,true);
                ArrayList<Song> items=new ArrayList<>();
                try {
                    items=(ArrayList<Song>)appPreference.getItems(getSelectedTabName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(intent1);
            }
        });
    }
    private void spinnerInit() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> names=new ArrayList<>();
        appPreference=new AppPreference(this);
        try {
            names=(ArrayList<String>)appPreference.getTabNames();
        } catch (IOException e) {
            e.printStackTrace();
        }
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    items=(ArrayList<Song>)appPreference.getItems(spinnerAdapter.getItem(position));
                    adapter.clear();
                    adapter.addAll(items);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SongBookHolder holder=SongBookHolder.getInstance();
                holder.clear();
                holder.addAll(items);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SongBooksActivity.this,"dupson ",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void listViewInit() {
        ListView lv= (ListView) findViewById(R.id.song_books_list);
        adapter = new ButtonListView2Adapter(this,R.layout.listview_image_2_item,new ArrayList<Song>());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorToolbarText), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            removeCurrentTab();
            return true;
        }
        else if(id==R.id.action_add){
            askTitle();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    @Override
    public void update(ArrayList<Song> songs) {
        //for (Song s:songs) {
        //    if(songs.contains(s))
        //}
    }

    public String getSelectedTabName(){
        return spinner.getSelectedItem().toString();
    }
    public void removeCurrentTab(){
        String name=getSelectedTabName();
        spinnerAdapter.remove(name);
        try {
            appPreference.removeTab(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addTab(String name){
        spinnerAdapter.add(name);
        try {
            appPreference.addTab(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spinner.setSelection(spinnerAdapter.getCount()-1);
        populate();//do testów
    }
    public void removeItemFromCurrentTab(Song item){
        String name=getSelectedTabName();
        adapter.remove(item);
        try {
            appPreference.removeItem(name,item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addItemToCurrentTab(Song item){
        String name=getSelectedTabName();
        adapter.add(item);
        try {
            appPreference.addItem(name,item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void askTitle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Podaj nazwę");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                if(m_Text.isEmpty())
                    Toast.makeText(SongBooksActivity.this,"Nie można dodać śpiewnika bez nazwy :(",Toast.LENGTH_SHORT).show();
                else
                    addTab(m_Text);
            }
        });
        builder.setNegativeButton("Anuluj", null);
        builder.show();
    }

    public void populate() {
        addItemToCurrentTab(new Song("Elektryczne Gitary","Przewróciło się"));
        addItemToCurrentTab(new Song("Elektryczne Gitary","Wytrąciłaś mnie"));
        addItemToCurrentTab(new Song("Elektryczne Gitary","Co ja tutaj robię"));
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
