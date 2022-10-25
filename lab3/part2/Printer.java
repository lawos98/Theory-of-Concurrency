package lab3.part2;

import java.util.Random;

public class Printer implements Runnable {
    private PrinterMonitor monitor;
    private int number;

    private int printerNumber=0;

    public Printer(PrinterMonitor monitor, int num) {
        this.monitor = monitor;
        this.number = num;
    }

    public void run() {
        try {
            this.printerNumber = monitor.reserve();
            System.out.println("Thread: " + this.number + " Printer: " + this.printerNumber + " printing");
            int time = new Random().nextInt(10)+1;
            Thread.sleep(time*100);
            System.out.println("Thread: " + this.number + " Printer: " + this.printerNumber + " release");
            monitor.release(this.printerNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
