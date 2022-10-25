package lab2.part2;

public class Main { private static void presentShop() {
        Shop shop = new Shop(11,3);
        shop.runShop();
    }

    public static void main(String[] args) throws InterruptedException {
        Main.presentShop();
    }
}