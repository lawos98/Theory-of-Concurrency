package lab3.part3;

import java.util.Random;

public class Client implements Runnable {
    private WaiterMonitor waiter;
    private int numberOfPair;

    public Client(WaiterMonitor waiter, int numberOfPair) {
        this.waiter = waiter;
        this.numberOfPair = numberOfPair;
    }

    public void run() {
        try {
            int time = new Random().nextInt(10)+3;
            Thread.sleep(time*100);
            waiter.orderTable(this.numberOfPair);
            System.out.println("Client: " + this.numberOfPair + "| Got table");
            time = new Random().nextInt(10)+3;
            Thread.sleep(time*100);
            System.out.println("Client: " + this.numberOfPair + "| Leave");
            waiter.freeTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
