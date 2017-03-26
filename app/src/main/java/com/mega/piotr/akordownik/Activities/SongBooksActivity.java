package com.mega.piotr.akordownik.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.Pair;
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
import com.mega.piotr.akordownik.ListViewAdapters.ButtonListViewAdapter;
import com.mega.piotr.akordownik.ListViewAdapters.ListViewAdapter;
import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.Song;

import java.io.IOException;
import java.util.ArrayList;

public class SongBooksActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayAdapter<String> dataAdapter;
    private ListViewAdapter adapter;
    private AppPreference appPreference;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_books);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listViewInit();

        spinnerInit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),LibraryActivity.class);
                //Bundle bundle=new Bundle();
                //bundle.putString(getResources().getString(R.string.key_const),getSelectedTabName());
                //intent1.putExtra(getResources().getString(R.string.key_const),bundle);
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
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Song>items;
                try {
                    items=(ArrayList<Song>)appPreference.getItems(dataAdapter.getItem(position));
                    adapter.clear();
                    adapter.addAll(items);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        adapter = new ButtonListViewAdapter(this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public String getSelectedTabName(){
        return spinner.getSelectedItem().toString();
    }
    public void removeCurrentTab(){
        String name=getSelectedTabName();
        dataAdapter.remove(name);
        try {
            appPreference.removeTab(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addTab(String name){
        dataAdapter.add(name);
        try {
            appPreference.addTab(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        spinner.setSelection(dataAdapter.getCount()-1);
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
}
