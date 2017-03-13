package com.mega.piotr.akordownik;

import android.content.Context;
import android.support.v4.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 01.12.2016.
 */

public class AppPreference
{
    private File tabs;
    private File fileDir;

    public AppPreference(Context context) {
        tabs=new File(context.getFilesDir(),"tabs");
        try {
            if (!tabs.exists()) {
                tabs.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fileDir=context.getFilesDir();
    }

    public void addItem(String tab, Pair<String,String>item) throws IOException {
        if(!_tabExist(tab))
            _addTab(tab);
        _addItem(tab,item);
    }
    public void addItems(String tab, List<Pair<String,String>> items) throws IOException {
        if(!_tabExist(tab))
            _addTab(tab);
        for (Pair<String,String>item:items) {
            _addItem(tab,item);
        }
    }
    public void removeItem(String tab, Pair<String,String>item) throws IOException {
        _removeItem(tab,item);
    }
    public void addTab(String tab) throws IOException {
        _addTab(tab);
    }
    public void removeTab(String tab) throws IOException {
        File file=new File(fileDir,tab);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line=null;
        while ((line = reader.readLine()) != null)
        {
            String[]split=line.split(" - ");
            Pair<String,String>item=new Pair<>(split[0],split[1]);
            removeItem(tab, item);
        }
        reader.close();
        _removeTab(tab);
    }
    public List<String> getTabNames() throws IOException {
        List<String> names=new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(tabs));
        String line=null;
        while ((line = reader.readLine()) != null) {
            names.add(line);
        }
        reader.close();
        return names;
    }
    public List<Pair<String,String>> getItems(String tab) throws IOException {
        List<Pair<String,String>> items=new ArrayList<Pair<String,String>>();
        File file=new File(fileDir,tab);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line=null;
        while ((line = reader.readLine()) != null) {
            String[]split=line.split(" - ");
            Pair<String,String>item=new Pair<>(split[0],split[1]);
            items.add(item);
        }
        reader.close();
        return items;
    }
    //==============================================================================================
    private void _addTab(String tab) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(tabs,true));
        writer.println(tab);
        writer.close();
    }
    private void _removeTab(String tab) throws IOException {
        File temp1=new File(fileDir,"temp");
        File temp2=new File(fileDir,tab);
        if (!temp1.exists()) {
            temp1.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(tabs));
        PrintWriter writer = new PrintWriter(new FileWriter(temp1));
        String line=null;
        while ((line = reader.readLine()) != null) {
            if(!line.equals(tab))
                writer.println(line);
        }
        reader.close();
        writer.close();
        temp2.delete();
        tabs.delete();
        temp1.renameTo(tabs);
    }
    private void _addItem(String tab, Pair<String,String>item) throws IOException {
        File file=new File(fileDir,tab);
        if (!file.exists()) {
            file.createNewFile();
        }
        PrintWriter writer = new PrintWriter(new FileWriter(file,true));
        String itemString=item.first+" - "+item.second;
        writer.println(itemString);
        writer.close();
    }
    private void _addItems(String tab,List<Pair<String,String>> items) throws IOException {
        for (Pair<String,String> item:items)
            _addItem(tab,item);
    }
    private void _removeItem(String tab, Pair<String,String> item) throws IOException {
        File file=new File(fileDir,tab);
        File temp=new File(fileDir,"temp");
        if (!temp.exists()) {
            temp.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter writer = new PrintWriter(new FileWriter(temp));
        String itemString=item.first+" - "+item.second;
        String line=null;
        while ((line = reader.readLine()) != null) {

            if(!line.equals(itemString))
                writer.println(line);
        }
        reader.close();
        writer.close();
        file.delete();
        temp.renameTo(file);
    }
    private boolean _tabExist(String tab) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(tabs));
        String line=null;
        while ((line = reader.readLine()) != null) {
            if(line.equals(tab))
            {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }
    private boolean _tabEmpty(String tab) {
        File file=new File(fileDir,tab);
        if(file.length()==0)
            return true;
        return false;
    }
}
