package lab2.part1;

public class Main {
    private static void startCounter() {
        Counter counter = new Counter();
        BinarySemaphore semaphore = new BinarySemaphore();
        Thread thread1 = new Thread(new CounterThread(counter,true, semaphore));
        Thread thread2 = new Thread(new CounterThread(counter, false, semaphore));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter.getValue());
    }
    public static void main(String[] args) throws InterruptedException {
        startCounter();
    }
}