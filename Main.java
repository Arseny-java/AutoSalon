import java.util.Random;

public class Main {
    static int dailyPlan = 10;
    final static Shop shop = new Shop(dailyPlan);
    public static Random rand = new Random();


    public static void main(String[] args) throws InterruptedException {
        ThreadGroup buyers = new ThreadGroup("Покупатели");
        ThreadGroup carGenerators = new ThreadGroup("Завод");

        new Thread(carGenerators, shop::acceptCars, "Производитель").start();

        while (!shop.planIsDone()) {

            new Thread(buyers, shop::sellCars, "Покупатель " + rand.nextInt(20)).start();
            Thread.sleep(3000);
        }
        carGenerators.interrupt();
        Thread.sleep(3000);
        System.out.println("******************************************************");
        System.out.println("Число продаж: " + shop.getSolds());
        buyers.interrupt();
    }
}


