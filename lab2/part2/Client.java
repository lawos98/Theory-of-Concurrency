package lab2.part2;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Client implements Runnable {
    CountingSemaphore semaphore;
    int id;

    Client(CountingSemaphore semaphore,int id) {

        this.semaphore = semaphore;
        this.id=id;
    }

    @Override
    public void run() {
        try {
        semaphore.acquire();
        int time = new Random().nextInt(10) + 1;
        System.out.println("Client: "+id+" Acquired cart");
        sleep(time * 1000);
        System.out.println("Client: "+id+" Leaving, time: " + time);
        semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

