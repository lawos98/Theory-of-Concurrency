package lab2.part2;

public class CountingSemaphore {
    private int permits;

    public CountingSemaphore(int permits) {
        this.permits = permits;
    }

    private void increasePermit() {
        permits+=1;
    }
    private void decreasePermit() {
        if(permits == 0) throw new RuntimeException("Semaphore was 0");
        permits-=1;
    }


    public synchronized void acquire() {
        while(permits==0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.decreasePermit();
    }

    public synchronized void release() {
        increasePermit();
        notifyAll();
    }
}