package com.mega.piotr.akordownik.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mega.piotr.akordownik.R;
import com.mega.piotr.akordownik.ScrollingHelper;
import com.mega.piotr.akordownik.SongData;
import com.mega.piotr.akordownik.XmlAdapter;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;
    private TextView timeIndex;
    private ScrollingHelper helper;
    private int currentIndexScrolling=0;
    private ArrayList<View> buttons;
    private int[] times=new int[]{200,86,55,40,32, 26, 22, 19, 17, 15, 14, 13, 12, 11, 10, 19, 18, 17, 16, 15};
    private int[] pixels=new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ScrollView scroller=(ScrollView) findViewById(R.id.song_scroller);
        helper=new ScrollingHelper(scroller);
        helper.setTime(times[currentIndexScrolling]);
        helper.setPixel(pixels[currentIndexScrolling]);
        timeIndex =(TextView)findViewById(R.id.time_index);
        timeIndex.setText(currentIndexScrolling+1+"");

        ImageButton btnup=(ImageButton)findViewById(R.id.btnFaster);
        ImageButton btndown=(ImageButton)findViewById(R.id.btnSlower);
        ImageButton btnplus=(ImageButton)findViewById(R.id.btnPlus);
        ImageButton btnminus=(ImageButton)findViewById(R.id.btnMinus);
        buttons=new ArrayList<>();
        buttons.add(timeIndex);
        buttons.add(btnup);
        buttons.add(btndown);
        buttons.add(btnminus);
        buttons.add(btnplus);
        btnup.setOnClickListener(this);
        btndown.setOnClickListener(this);
        btnplus.setOnClickListener(this);
        btnminus.setOnClickListener(this);

        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
            setFullscreen(true);

        text=(TextView)findViewById(R.id.song_text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper.isInRun()) helper.stopScrolling();
                else helper.startScrolling();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String author = intent.getStringExtra("author");
        String title = intent.getStringExtra("title");
        //String fullName=author+" - "+title;
        setTitle(title);
        XmlAdapter xmlAdapter=new XmlAdapter(this);
        SongData info=xmlAdapter.getSongInfo(author,title);
        text.setText(info.text);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
           setFullscreen(true);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setFullscreen(false);
        }
    }
    public void setFullscreen(boolean full){
        if(full) {
            getSupportActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else{
            getSupportActionBar().show();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnPlus:
                float size = text.getTextSize()*1.1f;
                text.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                break;
            case R.id.btnMinus:
                float size2 = text.getTextSize()*0.9f;
                text.setTextSize(TypedValue.COMPLEX_UNIT_PX, size2);
                break;
            case R.id.btnFaster:
                faster();
                break;
            case R.id.btnSlower:
                slower();
                break;
        }
    }

    private void slower() {
        if(currentIndexScrolling>0){
            currentIndexScrolling--;
            helper.setTime(times[currentIndexScrolling]);
            helper.setPixel(pixels[currentIndexScrolling]);
            timeIndex.setText(currentIndexScrolling+1+"");
        }
    }

    private void faster() {
        if(currentIndexScrolling<times.length-1){
            currentIndexScrolling++;
            helper.setTime(times[currentIndexScrolling]);
            helper.setPixel(pixels[currentIndexScrolling]);
            timeIndex.setText(currentIndexScrolling+1+"");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_song, menu);
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
        if (id == R.id.action_hide) {
            for(int i=0;i<buttons.size();i++){
                View button=buttons.get(i);
                if(button.getVisibility()==View.GONE)
                {
                    button.setVisibility(View.VISIBLE);
                    item.getIcon().mutate().setColorFilter(ContextCompat.getColor(this,R.color.colorToolbarText), PorterDuff.Mode.SRC_ATOP);
                }
                else if(button.getVisibility()==View.VISIBLE)
                {
                    button.setVisibility(View.GONE);
                    item.getIcon().mutate().setColorFilter(ContextCompat.getColor(this,R.color.disabled), PorterDuff.Mode.SRC_ATOP);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
