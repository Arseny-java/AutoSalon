public class Seller {
    private final Shop shop;
    final int sleepTime1 = 3000;
    final int sleepTime2 = 1000;

    public Seller(Shop shop) {
        this.shop = shop;
    }

    public synchronized void receiveCars() {
        try {
            System.out.println("Завод выпустил автомобиль");
            Thread.sleep(sleepTime1);
            shop.getCar().add(new Car());
            System.out.println("Машина поступила в салон");
            notify();
        } catch (InterruptedException e) {
            shop.getCar().add(new Car());
            Thread.currentThread().interrupt();
        }
        new Car();
    }

    public synchronized void sellCars() {
        try {
            while (shop.getCar().size() == 0) {
                System.out.printf("Машин нет, %s но вы держитесь\n", Thread.currentThread().getName());
                wait();
            }
            if (!shop.planIsDone()) {
                shop.soldsNumber();
                Thread.sleep(sleepTime2);
                System.out.println(Thread.currentThread().getName() + " купил автомобиль");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (shop.getCar().size() != 0) {
            shop.getCar().remove(0);
        }
    }
}
