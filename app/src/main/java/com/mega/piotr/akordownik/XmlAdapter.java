package com.mega.piotr.akordownik;

import android.app.Activity;
import android.support.v4.util.Pair;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlAdapter {

    private Activity act;

    public XmlAdapter(Activity act){
        this.act=act;
    }

    private XmlPullParser createParser() {
        XmlPullParser myParser=null;
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
        return myParser;
    }
    private int goToEvent(XmlPullParser myParser,int value) throws IOException, XmlPullParserException {
        int event=0;
        while(event!=value&&event!=XmlPullParser.END_DOCUMENT){
            event=myParser.next();
        }
        return event;
    }
    private int goToSection(XmlPullParser myParser, String title) throws IOException, XmlPullParserException {
        String name = "";
        int event=goToEvent(myParser, XmlPullParser.START_TAG);
        while (event != XmlPullParser.END_DOCUMENT){
            name = myParser.getName();
            if(name.equals(title))
                break;
            event=goToEvent(myParser, XmlPullParser.START_TAG);
        }
        return event;
    }

    public List<String> getAuthors(){
        XmlPullParser myParser=createParser();
        ArrayList<String> authors = new ArrayList<>();
        try {
            int event = myParser.getEventType();
            event=goToSection(myParser,"author");
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                String author=myParser.getAttributeValue(null,"author");
                authors.add(author);
                event=goToSection(myParser,"author");
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authors;
    }
    public List<Song> getTitles(String author){
        XmlPullParser myParser=createParser();
        int event=0;
        ArrayList<Song> titles = new ArrayList<>();
        try {
            String value="";
            while(!value.equals(author)){
                event=goToSection(myParser,"author");
                value=myParser.getAttributeValue(null,"author");
            }
            event = goToEvent(myParser, XmlPullParser.START_TAG);
            while(event!=XmlPullParser.END_DOCUMENT) {
                value = myParser.getName();
                if (value.equals("song")) {
                    Song song = new Song(author, myParser.getAttributeValue(null, "title"));
                    titles.add(song);
                }
                else if(value.equals("author"))
                    break;
                event = goToEvent(myParser, XmlPullParser.START_TAG);
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return titles;
    }
    public ArrayList<Song>getAllSongs(){
        ArrayList<Song> data = new ArrayList<>();
        List<String>authors=getAuthors();
        createParser();
        for (String author:authors) {
            data.addAll(getTitles(author));
        }
        return data;
    }
    public SongData getSongInfo(String author, String title){
        XmlPullParser myParser=createParser();
        int event=0;
        SongData data=new SongData();
        try {
            String value="";
            while(!value.equals(author)){
                event=goToSection(myParser,"author");
                value=myParser.getAttributeValue(null,"author");
            }
            value="";
            while(!value.equals(title)){
                event=goToSection(myParser,"song");
                value=myParser.getAttributeValue(null,"title");
            }
            event=goToSection(myParser,"text");
            event=goToEvent(myParser,XmlPullParser.TEXT);
            String songText = myParser.getText();
            data.text=songText;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    /*
    public void getDataFromXml(String author, String title) {
        try {
            XmlPullParser myParser=createParser();
            findSongXml(author, title, myParser);
            getSongData(myParser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findSongXml(String author, String title, XmlPullParser myParser) throws XmlPullParserException, IOException {
        myParser.getEventType();
        while(myParser.next()!=XmlPullParser.END_DOCUMENT){
            goToEvent(myParser,XmlPullParser.START_TAG);
            String name = myParser.getName();
            if (name.equals("author") && myParser.getAttributeValue(null, "author").equals(author)){
                while(myParser.next()!=XmlPullParser.END_DOCUMENT) {
                    goToEvent(myParser, XmlPullParser.START_TAG);
                    name = myParser.getName();
                    String value = myParser.getAttributeValue(null, "title");
                    if (name.equals("song") && value.equals(title)) {
                        return;
                    }
                }
            }
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
                    while ((event = myParser.next()) != XmlPullParser.TEXT){}//czekaj
                    String songText = myParser.getText();
                    TextView textLabel = (TextView) act.findViewById(R.id.song_text);
                    textLabel.setText(songText);
                    textLabel.setMovementMethod(new ScrollingMovementMethod());
                    return;
                }
            }
            event=myParser.next();
        }
    }*/
}
