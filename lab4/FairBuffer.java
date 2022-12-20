package com.example.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FairBuffer implements Buffer{

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition firstProducerCondition = lock.newCondition();
    private final Condition restProducersCondition = lock.newCondition();
    private final Condition firstConsumerCondition = lock.newCondition();
    private final Condition restConsumersCondition = lock.newCondition();

    private final int bufferSize;
    private int takenSlots;

    public FairBuffer(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public void put(int slots) {
        lock.lock();
        try {
            while (lock.hasWaiters(firstProducerCondition)) {
                restProducersCondition.await();
            }
            while (takenSlots + slots > bufferSize) {
                firstProducerCondition.await();
            }
            takenSlots += slots;
            restProducersCondition.signal();
            firstConsumerCondition.signal();
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
            while (lock.hasWaiters(firstConsumerCondition)) {
                restConsumersCondition.await();
            }
            while (takenSlots - slots < 0) {
                firstConsumerCondition.await();
            }
            takenSlots -= slots;
            restConsumersCondition.signal();
            firstProducerCondition.signal();
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
