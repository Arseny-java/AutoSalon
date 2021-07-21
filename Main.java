
public class Main {
    static int dailyPlan = 10;
    final static Shop shop = new Shop(dailyPlan);

    public static void newBuyers(int numberB, ThreadGroup thread, Runnable task) {
        for (int i = 0; i <= numberB; i++) {
            shop.increaseTraffic();
            new Thread(thread, task, "Посетитель " + shop.getBuyers()).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup buyers = new ThreadGroup("Покупатели");
        ThreadGroup carGenerators = new ThreadGroup("Завод");

        while (shop.planIsDone()) {
            Car.generateNewCars(1, carGenerators, shop::acceptCars);
            newBuyers(1, buyers, shop::sellCars);
            Thread.sleep(3000);
        }

        carGenerators.interrupt();
        Thread.sleep(3000);
        System.out.println();
        System.out.println("Число продаж: " + shop.getSolds());
        buyers.interrupt();

    }


}


