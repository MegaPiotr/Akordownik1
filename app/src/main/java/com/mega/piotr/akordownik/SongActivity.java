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

        Intent intent = getIntent();
        String author = intent.getStringExtra(TabFragment.AUTHOR);
        String title = intent.getStringExtra(TabFragment.TITLE);
        String fullName=author+" - "+title;
        TextView nameLabel= (TextView) findViewById(R.id.song_title);
        nameLabel.setText(fullName);
        getDataFromXml(author,title);
    }

    private void getDataFromXml(String author, String title) {
        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myParser = xmlFactoryObject.newPullParser();
            InputStream is = getAssets().open("songs.xml");
            myParser.setInput(is,null);
            findSongXml(author, title, myParser);
            getSongData(myParser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findSongXml(String author, String title, XmlPullParser myParser) throws XmlPullParserException, IOException {
        int event = myParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                String name = myParser.getName();
                if (name.equals("song") &&
                        myParser.getAttributeValue(null, "title").equals(title) &&
                        myParser.getAttributeValue(null, "author").equals(author)) {
                    return;
                }
            }
            event = myParser.next();
        }
    }

    private void getSongData(XmlPullParser myParser) throws IOException, XmlPullParserException {
        int event=myParser.next();
        String songChords="unknown";
        String name;
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                name = myParser.getName();
                if (name.equals("text")) {
                    while ((event = myParser.next()) != XmlPullParser.TEXT){};//czekaj
                    String songText = myParser.getText();
                    TextView textLabel = (TextView) findViewById(R.id.song_text);
                    textLabel.setText(songText);
                    textLabel.setMovementMethod(new ScrollingMovementMethod());
                } else if (name.equals("cords")) {

                }
            }
            event=myParser.next();
        }
    }
}
