package com.mega.piotr.akordownik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button library = (Button) findViewById(R.id.library);
        Button songBooks = (Button) findViewById(R.id.song_books);
        Button tuner = (Button) findViewById(R.id.tuner);
        Button metronom = (Button) findViewById(R.id.metronom);
        Button about = (Button) findViewById(R.id.about);
        library.setOnClickListener(this);
        songBooks.setOnClickListener(this);
        tuner.setOnClickListener(this);
        metronom.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.library:
                Intent intent1 = new Intent(this, LibraryActivity.class);
                startActivity(intent1);
                break;
            case R.id.song_books:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
