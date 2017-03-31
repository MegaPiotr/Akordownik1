package com.mega.piotr.akordownik.Activities;

import android.content.Context;
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

import com.mega.piotr.akordownik.R;

public class ChordBookActivity extends AppCompatActivity {

    GridView gridView;

    static final String[] numbers = new String[] {
            "A", "a" ,"B","b","C","c","C#","c#","D","d","D#","d#","E","e","F","f","F#","f#","G","g","G#","g#"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chord_book);

        gridView = (GridView) findViewById(R.id.gridView1);

        ArrayAdapter<String> adapter = new MyGridAdapter(this,numbers);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

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

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View gridView;

            if (convertView == null) {

                gridView = new View(context);

                gridView = inflater.inflate(R.layout.grid_folder_item, null);

                TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
                textView.setText(strings[position]);

            } else {
                gridView = (View) convertView;
            }

            return gridView;
        }
    }
}
