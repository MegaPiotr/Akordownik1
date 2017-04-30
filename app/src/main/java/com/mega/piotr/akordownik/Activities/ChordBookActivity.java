package com.mega.piotr.akordownik.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.mega.piotr.akordownik.R;

public class ChordBookActivity extends AppCompatActivity {

    GridView gridView;
    public static final String chord="chord";
    static final String[] numbers = new String[] {
            "A","B","H","C","C#","D","D#","E","F","F#","G","G#"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chord_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Akordy");

        gridView = (GridView) findViewById(R.id.gridView1);

        ArrayAdapter<String> adapter = new MyGridAdapter(this,numbers);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String item=(String)parent.getItemAtPosition(position);
                Intent intent=new Intent(ChordBookActivity.this, ChordActivity.class);
                intent.putExtra(chord,item);
                startActivity(intent);
            }
        });
        PublisherAdView mAdView = (PublisherAdView) findViewById(R.id.ad_view);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    class MyGridAdapter extends ArrayAdapter<String>{

        private Context context;
        private String[] strings;
        public MyGridAdapter(@NonNull Context context, String[] names) {
            super(context, 0, names);
            this.context=context;
            strings=names;
        }
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.grid_folder_item, null);
                TextView textView = (TextView) convertView.findViewById(R.id.grid_item_label);
                textView.setText(strings[position]);
            }
            return convertView;
        }
    }
}
