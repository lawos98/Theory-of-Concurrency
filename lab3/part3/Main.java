package lab3.part3;

public class Main {

    private static void waiter(int n) {
        WaiterMonitor waiter = new WaiterMonitor();
        for(int i=0; i<n; i++) {
            new Thread(new Client(waiter, i)).start();
            new Thread(new Client(waiter, i)).start();
        }
    }

    public static void main(String[] args){
        waiter(7);
    }
}