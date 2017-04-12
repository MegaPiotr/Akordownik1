package com.mega.piotr.akordownik;

public class ScrollingCalculator {
    private int tmin=1;
    private int tmax=500;
    private int pmin=1;
    private int pmax=1;
    private int div=30;
    private ScrollingHelper helper;
    private ScrollingData pre=new ScrollingData();

    public ScrollingCalculator(){
        pre=new ScrollingData(pmin,tmax);
    }
    public void setupWithScrollingHelper(ScrollingHelper helper){
        this.helper=helper;
    }
    public void scrollUp(){
        ScrollingData data=nextUp();
        helper.setTime(data.t);
        helper.setPixel(data.p);
        if(!helper.isInRun())
            helper.startScrolling();
    }
    public void scrollDown(){
        ScrollingData data=nextDown();
        if(data.t>tmax)
            helper.stopScrolling();
        else {
        helper.setTime(data.t);
        helper.setPixel(data.p);
        }
    }
    private ScrollingData calculateUp(ScrollingData pre){
        ScrollingData data=new ScrollingData();
        for(data.p=pre.p;pre.p<=pmax;data.p++){
            data.t=data.p*pre.t*tmin*div/(pre.p*tmin*div+pmax*pre.t-pmin*tmin*pre.t/tmax);
            if(data.t>0)break;
        }
        this.pre=data;
        return data;
    }
    private ScrollingData nextUp(){
        return calculateUp(pre);
    }
    private ScrollingData calculateDown(ScrollingData pre){
        ScrollingData data=new ScrollingData();
        for(data.p=pre.p;pre.p<=pmax;data.p++){
            data.t=data.p/(pre.p/pre.t+(pmax/tmin-pmin/tmax)/div);
            if(data.t>0)break;
        }
        this.pre=data;
        return data;
    }
    private ScrollingData nextDown(){
        return calculateDown(pre);
    }
    public class ScrollingData{
        ScrollingData(){this.p=0;this.t=500;}
        public ScrollingData(int p, int t){this.p=p;this.t=t;}
        int p;
        int t;
    }
}
