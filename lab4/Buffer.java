package com.example.demo;

public interface Buffer {

    public void put(int slots);

    public void take(int slots);

    public int getBufferSize();
}

