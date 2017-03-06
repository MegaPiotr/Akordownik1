package com.mega.piotr.akordownik;

import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 20.02.2017.
 */

public class XmlAdapter {

    private XmlPullParser myParser;
    private Activity act;

    public XmlAdapter(Activity act){
        this.act=act;
        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            myParser = xmlFactoryObject.newPullParser();
            InputStream is = act.getAssets().open("songs.xml");
            myParser.setInput(is,null);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAuthors(){
        ArrayList<String> authors = new ArrayList<>();
        try {
            int event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                if (event == XmlPullParser.START_TAG) {
                    String name = myParser.getName();
                    if (name.equals("song")){
                        String author=myParser.getAttributeValue(null, "author");
                        if(!authors.contains(author))
                            authors.add(author);
                    }
                }
                event = myParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authors;
    }
    public List<String> getTitles(String author){
        ArrayList<String> titles = new ArrayList<>();
        try {
            int event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                if (event == XmlPullParser.START_TAG) {
                    String name = myParser.getName();
                    if (name.equals("song")&&myParser.getAttributeValue(null, "author").equals(author)){
                        String title=myParser.getAttributeValue(null, "title");
                        if(!titles.contains(title))
                            titles.add(title);
                    }
                }
                event = myParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return titles;
    }

    //todo przerobić żeby zwracało dane a nie od razu z nich korzystało
    public void getDataFromXml(String author, String title) {
        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myParser = xmlFactoryObject.newPullParser();
            InputStream is = act.getAssets().open("songs.xml");
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
        while (event != XmlPullParser.END_TAG) {
            if (event == XmlPullParser.START_TAG) {
                name = myParser.getName();
                if (name.equals("text")) {
                    while ((event = myParser.next()) != XmlPullParser.TEXT){};//czekaj
                    String songText = myParser.getText();
                    TextView textLabel = (TextView) act.findViewById(R.id.song_text);
                    textLabel.setText(songText);
                    textLabel.setMovementMethod(new ScrollingMovementMethod());
                } else if (name.equals("cords")) {

                }
            }
            event=myParser.next();
        }
    }
}
