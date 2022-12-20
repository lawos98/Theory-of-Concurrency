package com.example.demo;

import java.util.Random;

public class Consumer implements Runnable {
    private final Buffer naiveBuffer;

    private final DataMapper mapper;

    private final int maxSize;


    public Consumer(Buffer naiveBuffer, DataMapper mapper){
        this.naiveBuffer = naiveBuffer;
        this.maxSize = naiveBuffer.getBufferSize() / 2;
        this.mapper=mapper;
    }

    public void run() {
        for (int i = 0; i< 10000; i++) {
            int randomValue = new Random().nextInt(maxSize);
            long time = System.nanoTime();
            naiveBuffer.take(randomValue);
            time = System.nanoTime() - time;
            mapper.addConsumer(randomValue,time);
        }
    }
}
