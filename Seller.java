public class Seller {
    private final Shop shop;
    private final int PRODUCE_CAR_TIME = 3000;
    private final int BUY_CAR_TIME = 1000;


    public Seller(Shop shop) {
        this.shop = shop;
    }

    public synchronized void receiveCars() {

        try {
            while (!shop.planIsDone()) {
                System.out.println("Завод выпустил автомобиль");
                shop.getCar().add(new Car());
                System.out.println("Машина поступила в салон");
                Thread.sleep(PRODUCE_CAR_TIME);
                notify();
                wait();
            }
        } catch (InterruptedException ignored) {
        }
    }

    public synchronized void sellCars() {
        try {
            while (shop.getCar().size() == 0) {
                System.out.printf("Машин нет, %s но вы держитесь\n", Thread.currentThread().getName());
                wait();
            }
            if (!shop.planIsDone()) {
                shop.soldsNumber();
                Thread.sleep(BUY_CAR_TIME);
                System.out.println(Thread.currentThread().getName() + " купил автомобиль");
                notify();
            }
        } catch (InterruptedException ignored) {
        }
        if (shop.getCar().size() != 0) {
            shop.getCar().remove(0);
        }
    }
}
