package com.mega.piotr.akordownik;

import android.support.v4.util.Pair;
import android.view.View;

/**
 * Created by piotr on 15.03.2017.
 */

public interface OnDataChangeListener {
    public void onDeleteItem(String tab, Pair<String,String>data);
    public void onAddItem(String tab, Pair<String, String> data);
}
