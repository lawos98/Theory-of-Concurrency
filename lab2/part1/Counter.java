package lab2.part1;

public class Counter {
    private int number = 0;

    public void increase() {
        number+=1;
    }
    public void decrease() {
        number-=1;
    }
    public int getValue() {
        return number;
    }

}