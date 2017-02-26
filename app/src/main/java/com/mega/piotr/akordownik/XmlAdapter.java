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

    public XmlAdapter(Activity act){
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
}
