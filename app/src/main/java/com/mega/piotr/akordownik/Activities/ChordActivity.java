package com.mega.piotr.akordownik.Activities;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mega.piotr.akordownik.Fragments.ChordFragmentDialog;
import com.mega.piotr.akordownik.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ChordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final GridView gv=(GridView)findViewById(R.id.chordGridView);
        String key=getIntent().getStringExtra(ChordBookActivity.chord);
        setTitle(key);
        ArrayAdapter<GridItem> adapter = new MyGridAdapter(this,getChordsInfo(key));

        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                FragmentManager fm = getSupportFragmentManager();
                ChordFragmentDialog editNameDialogFragment = new ChordFragmentDialog();
                editNameDialogFragment.setRes((GridItem) gv.getItemAtPosition(position));
                editNameDialogFragment.show(fm, "fragment_edit_name");
            }
        });
    }
    public ArrayList<GridItem> getChordsInfo(String key){
        TypedArray img;
        int arryidres = this.getResources().getIdentifier(key.toLowerCase()+"_res", "array", this.getPackageName());
        img = getResources().obtainTypedArray(arryidres);

        int arryidname = this.getResources().getIdentifier(key.toLowerCase()+"_names", "array", this.getPackageName());
        String[]names  = this.getResources().getStringArray(arryidname);

        ArrayList<GridItem> items=new ArrayList<>();
        int length=img.length()>names.length?names.length:img.length();
        for(int i=0;i<length;i++){
            GridItem item=new GridItem(img.getResourceId(i,0),names[i]);
            items.add(item);
        }
        return items;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public class GridItem
    {
        public GridItem(int res,String name){this.resource=res;this.name=name;}
        public int resource;
        public String name;
    }
    class MyGridAdapter extends ArrayAdapter<GridItem>{

        private Context context;
        private ArrayList<GridItem> images;
        public MyGridAdapter(@NonNull Context context, ArrayList<GridItem> images) {
            super(context,0,images);
            this.context=context;
            this.images=images;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.chord_item, null);
                ImageView imageView=(ImageView) convertView.findViewById(R.id.imageView);
                GridItem item=images.get(position);
                imageView.setImageResource(item.resource);
                TextView textView=(TextView)convertView.findViewById(R.id.description);
                textView.setText(item.name);
            }
            return convertView;
        }
    }
}
