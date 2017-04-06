package com.mega.piotr.akordownik.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.mega.piotr.akordownik.R;

public class ChordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord);

        GridView gv=(GridView)findViewById(R.id.chordGridView);
        ArrayAdapter<Integer> adapter = new MyGridAdapter(this,5);

        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    class MyGridAdapter extends ArrayAdapter<Integer>{

        private Context context;
        private Integer[] numbers;
        public MyGridAdapter(@NonNull Context context, int count) {
            super(context,0);
            this.context=context;
            numbers=new Integer[count];
            for(int i=1;i<=count;i++)
                numbers[i-1]=i;
            addAll(numbers);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            ImageView imageView;

            if (convertView == null) {
                imageView = (ImageView) inflater.inflate(R.layout.chord_item, null);
            } else {
                imageView = (ImageView) convertView;
            }
            return imageView;
        }
    }
}
