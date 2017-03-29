package com.mega.piotr.akordownik;

import java.util.ArrayList;


//singleton
public class SongBookHolder {
    private ArrayList<Song>songs=new ArrayList<>();
    private ArrayList<UpdateDataListener> listeners=new ArrayList<>();
    private SongBookHolder(){}
    public void setUpdateDataListener(UpdateDataListener listener){
        listeners.add(listener);
    }
    public void clear(){
        songs.clear();
    }
    public void add(Song song){
        songs.add(song);
    }
    public void addAll(ArrayList<Song>list){
        songs.addAll(list);
    }
    public boolean remove(Song song){
        if(songs.contains(song)) {
            songs.remove(song);
            return true;
        }
        else return false;
    }
    public ArrayList<Song> get() {return songs;}
    public boolean contain(Song song){
        return songs.contains(song);
    }
    public void notifyDataSetChanged(){
        for (UpdateDataListener li:listeners) {
            li.update(songs);
        }
    }
    private static final SongBookHolder holder = new SongBookHolder();
    public static SongBookHolder getInstance() {return holder;}
    public interface UpdateDataListener{
        public void update(ArrayList<Song>songs);
    }
}
