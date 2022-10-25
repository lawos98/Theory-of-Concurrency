package lab3.part3;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaiterMonitor {
    final private Lock lock = new ReentrantLock();

    final private Condition waitForPair  = lock.newCondition();
    final private Condition waitForTable = lock.newCondition();

    private final Queue<Integer> queueToTable = new LinkedList<>();
    private final Set<Integer> waitingClientsForPair = new HashSet<>();

    private int slots=0;
    public void orderTable(int PairNumber) throws InterruptedException {
        lock.lock();
        try {
            if (!waitingClientsForPair.contains(PairNumber)) {
                waitingClientsForPair.add(PairNumber);
                while (waitingClientsForPair.contains(PairNumber)) {
                    waitForPair.await();
                }
            }
            else {
                queueToTable.add(PairNumber);
                while (queueToTable.element() != PairNumber) {
                    waitForTable.await();
                }
                waitingClientsForPair.remove(PairNumber);
                slots=2;
                waitForPair.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void freeTable() throws Exception {
        lock.lock();
        try {
            slots-=1;
            if(slots==0) {
                int x = queueToTable.element();
                queueToTable.remove(x);
                waitForTable.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
