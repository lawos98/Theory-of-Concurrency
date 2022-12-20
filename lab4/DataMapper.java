package com.example.demo;

import java.util.Arrays;
public class DataMapper {
    private int[] cOccurrences;
    private long[] cTimeSum;
    private int[] pOccurrences;
    private long[] pTimeSum;
    private long[] cResults;
    private long[] pResults;

    public DataMapper(int size) {
        cOccurrences = new int[size/2 + 1];
        cTimeSum = new long[size/2 + 1];
        pOccurrences = new int[size/2 + 1];
        pTimeSum = new long[size/2 + 1];
        pResults = new long[size/2 + 1];
        cResults = new long[size/2 + 1];
        Arrays.fill(cOccurrences, 0);
        Arrays.fill(pOccurrences, 0);
        Arrays.fill(cTimeSum, 0);
        Arrays.fill(pTimeSum, 0);
        Arrays.fill(cResults, 0);
        Arrays.fill(pResults, 0);
    }

    public void addProducer(int value, long time) {
        pOccurrences[value]+=1;
        pTimeSum[value] += time;
    }

    public void addConsumer(int value, long time) {
        cOccurrences[value]+=1;
        cTimeSum[value] += time;
    }

    public long[] getResultsConsumer(){
        for(int i=0;i<cOccurrences.length;i++){
            if(cOccurrences[i]!=0)cResults[i]=cTimeSum[i]/cOccurrences[i];
        }
        return cResults;
    }

    public long[] getResultsProducer(){
        for(int i=0;i<pOccurrences.length;i++){
            if(pOccurrences[i]!=0)pResults[i]=pTimeSum[i]/pOccurrences[i];
        }
        return pResults;
    }

}
