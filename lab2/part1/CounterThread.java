package lab2.part1;

public class CounterThread implements Runnable{
    private Counter counter;
    boolean isIncrementing;
    BinarySemaphore semaphore;

    CounterThread(Counter counter, boolean isIncrementing, BinarySemaphore semaphore) {
        this.counter = counter;
        this.isIncrementing = isIncrementing;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        if(this.isIncrementing)
            for(int i=0; i<100000; i++) {
                semaphore.acquire();
                counter.increase();
                semaphore.release();
            }
        else
            for(int i=0; i<100000; i++) {
                semaphore.acquire();
                counter.decrease();
                semaphore.release();
            }
    }
}
