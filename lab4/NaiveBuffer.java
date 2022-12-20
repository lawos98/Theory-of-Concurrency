package com.example.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NaiveBuffer implements Buffer {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition ProducerCondition = lock.newCondition();
    private final Condition ConsumerCondition = lock.newCondition();

    private final int bufferSize;
    private int takenSlots;

    public NaiveBuffer(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public void put(int slots) {
        lock.lock();
        try {
            while (takenSlots + slots > bufferSize) {
                ProducerCondition.await();
            }
            takenSlots += slots;
            ConsumerCondition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void take(int slots) {
        lock.lock();
        try {
            while (takenSlots - slots < 0) {
                ConsumerCondition.await();
            }
            takenSlots -= slots;
            ProducerCondition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getBufferSize() {
        return this.bufferSize;
    }
}
