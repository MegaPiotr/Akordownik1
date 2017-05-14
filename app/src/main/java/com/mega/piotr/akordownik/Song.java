package com.mega.piotr.akordownik;

import android.support.annotation.NonNull;
import android.widget.Checkable;

import java.text.Collator;
import java.util.Locale;

public class Song implements Checkable, Comparable<Song>{

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

    @Override
    public int compareTo(@NonNull Song o) {
        Collator c = Collator.getInstance(new Locale("pl"));
        c.setStrength(Collator.PRIMARY);
        int porownaneNazwiska = c.compare(title,o.title);
        if(porownaneNazwiska == 0) {
            return c.compare(author,o.author);
        }
        return porownaneNazwiska;
    }
}
