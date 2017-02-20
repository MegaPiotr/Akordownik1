package com.mega.piotr.akordownik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LibraryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayAdapter adapter;
    public final static String AUTHOR = "AUTHOR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ListView lv= (ListView) findViewById(R.id.authors_list);
        XmlAdapter xmlAdapter=new XmlAdapter(this);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, xmlAdapter.getAuthors());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String author = (String) adapter.getItem(position);
        Intent intent = new Intent(this, TitleActivity.class);
        intent.putExtra(AUTHOR, author);
        startActivity(intent);
    }
}
