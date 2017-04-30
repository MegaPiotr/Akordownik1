package com.mega.piotr.akordownik;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.ListViewCompat;
import android.widget.ListView;
import android.widget.ScrollView;


public class ScrollingHelper{

    private final Handler timerHandler=new Handler();
    private ScrollingRunable timerRunnable;
    private boolean inRun;

    public ScrollingHelper(ListView listView) {

        timerRunnable = new ScrollingRunable(listView);
        inRun=false;
    }
    public void startScrolling(){
        timerHandler.postDelayed(timerRunnable, 0);
        inRun=true;
    }
    public void stopScrolling(){
        if(inRun){
        timerHandler.removeCallbacks(timerRunnable);
        inRun=false;
        }
    }
    public boolean isInRun(){return inRun;}
    public boolean setTime(int time) {
        timerRunnable.setTime(time);
        if(time<1&&inRun)
            return false;
        return true;
    }
    public void setPixel(int pixel){
        timerRunnable.setPixel(pixel);
    }

    private class ScrollingRunable implements Runnable{

        private ListView listView;
        //private ScrollView scrollView;
        private int time=500;
        private int pixel=0;

        ScrollingRunable(ListView listView){
            //this.scrollView=scrollView;
            this.listView=listView;
            time=0;
        }

        public void setTime(int time) {
            this.time = time;
        }
        public void setPixel(int pixel){
            this.pixel=pixel;
        }

        @Override
        public void run() {
            ListViewCompat.scrollListBy(listView, pixel);
            //scrollView.smoothScrollBy(0,pixel);         // 5 is how many pixels you want it to scroll vertically by
            timerHandler.postDelayed(this, time);     // 10 is how many milliseconds you want this thread to run
        }
    }
}
