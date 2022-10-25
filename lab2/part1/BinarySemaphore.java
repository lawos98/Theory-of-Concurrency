package lab2.part1;

public class BinarySemaphore {
    private boolean released = true;

    public synchronized void acquire() {
        while(!released) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        released = false;
    }

    public synchronized void release() {
        released = true;
        notifyAll();
    }

}