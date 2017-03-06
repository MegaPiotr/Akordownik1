package com.mega.piotr.akordownik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
    }
    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String author = intent.getStringExtra(TabFragment.AUTHOR);
        String title = intent.getStringExtra(TabFragment.TITLE);
        String fullName=author+" - "+title;
        setTitle(fullName);
        XmlAdapter xmlAdapter=new XmlAdapter(this);
        xmlAdapter.getDataFromXml(author,title);
    }
}
