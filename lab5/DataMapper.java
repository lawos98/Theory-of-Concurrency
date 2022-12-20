package com.example.demo;

public class DataMapper {

    private String title="";
    private double avg=0;
    private double sd=0;
    private final long[] timeTable=new long[Config.getArraySize()];
    private int index=0;

    public DataMapper(String text) {
        this.title=text;
    }

    public void addTime(long time) {
        timeTable[index++]=time;
    }

    public void setAVG(){
        double sum=0;
        for(int i=0;i<Config.getArraySize();i++){
            sum+=timeTable[i];
        }
        this.avg=sum/Config.getArraySize();
    }

    public double getAVG(){
        return this.avg;
    }

    public void setSD(){
        double avg=getAVG();
        double sum=0;
        for(int i=0;i<Config.getArraySize();i++){
            sum+=Math.pow((timeTable[i] - avg), 2);
        }
        this.sd =Math.sqrt(sum / Config.getArraySize());
    }

    public double getSD(){
        return this.sd;
    }

    public String getTitle(){
        return this.title;
    }

    public void setAll(){
        this.setAVG();
        this.setSD();
    }

}
