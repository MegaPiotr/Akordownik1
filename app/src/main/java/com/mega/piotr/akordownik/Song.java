package com.mega.piotr.akordownik;

import android.widget.Checkable;

public class Song implements Checkable{

    public String author;
    public String title;
    private boolean check=false;

    public Song() {}
    public Song(String author, String title) {this.author=author;this.title=title;}

    @Override
    public void setChecked(boolean b) {
        check=b;
    }

    @Override
    public boolean isChecked() {
        return check;
    }

    @Override
    public void toggle() {
        check=!check;
    }
}
