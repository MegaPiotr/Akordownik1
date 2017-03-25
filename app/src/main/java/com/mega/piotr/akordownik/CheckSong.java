package com.mega.piotr.akordownik;

public class CheckSong extends Song {
    public CheckSong() {}
    public CheckSong(String author, String title) {this.author=author;this.title=title;}
    public CheckSong(Song song, boolean checked) {this.author=song.author;this.title=song.title;this.checked=checked;}
    public boolean checked;
    public boolean toggle(){
        checked=!checked;
        return checked;
    }
}
