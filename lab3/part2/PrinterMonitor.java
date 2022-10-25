package lab3.part2;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMonitor {
    final Lock lock = new ReentrantLock();
    final Condition watingForReserve  = lock.newCondition();
    boolean[] freePrinters;

    public PrinterMonitor(int number) {
        freePrinters = new boolean[number-1];
        Arrays.fill(freePrinters, Boolean.TRUE);
    }

    private int findFreePrinterIndex() {
        for(int i = 0; i < freePrinters.length; i++) {
            if(freePrinters[i]) return i;
        }
        return -1;
    }

    public int reserve() throws InterruptedException{
        lock.lock();
        int printerIndex;
        try {
            while ((printerIndex=findFreePrinterIndex()) == -1)
                watingForReserve.await();
            freePrinters[printerIndex] = false;
        } finally {
            lock.unlock();
        }
        return printerIndex;
    }

    public void release(int printerNumber) throws InterruptedException{
        lock.lock();
        try {
            freePrinters[printerNumber] = true;
            watingForReserve.signal();
        } finally {
            lock.unlock();
        }
    }

}
