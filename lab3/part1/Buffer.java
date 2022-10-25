package lab3.part1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    private String bufferMessage = "";

    public void put(String message)
    {
        lock.lock();
        try {
            while (!bufferMessage.equals("")) notFull.await();
            bufferMessage=message;
            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public synchronized String take()
    {
        lock.lock();
        try {
            while (bufferMessage.equals(""))notEmpty.await();
            String message = bufferMessage;
            bufferMessage = "";
            notFull.signal();
            return message;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
