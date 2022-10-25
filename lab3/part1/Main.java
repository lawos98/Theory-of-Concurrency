package lab3.part1;


public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Consumer consumer1 = new Consumer(buffer);
        Thread thread1 = new Thread(consumer1);
        Producer producer1 = new Producer(buffer);
        Thread thread2 = new Thread(producer1);
        Consumer consumer2 = new Consumer(buffer);
        Thread thread3 = new Thread(consumer2);
        Producer producer2 = new Producer(buffer);
        Thread thread4 = new Thread(producer2);
        Consumer consumer3 = new Consumer(buffer);
        Thread thread5 = new Thread(consumer3);
        Producer producer3 = new Producer(buffer);
        Thread thread6 = new Thread(producer3);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}