package lab3.part2;

public class Main {
    private static void printers(int n, int m){
        PrinterMonitor monitor = new PrinterMonitor(m);
        for(int i = 0; i<n; i++){
            new Thread(new Printer(monitor, i)).start();
        }
    }

    public static void main(String[] args){
        printers(15,4);
    }
}