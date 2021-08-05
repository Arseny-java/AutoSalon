public class Seller {
    private final Shop shop;
    private final int PRODUCE_CAR_TIME = 3000;
    private final int BUY_CAR_TIME = 1000;


    public Seller(Shop shop) {
        this.shop = shop;
    }

    public void receiveCars(int dailyPlan) {

        try {
            for (int i = 0; i < dailyPlan; i++) {
                System.out.printf("%s выпустил новый автомобиль\n", Thread.currentThread().getName());
                Thread.sleep(PRODUCE_CAR_TIME);
                System.out.println("Автомобиль поступил в продажу");
                synchronized (this) {
                    shop.getCar().add(new Car());
                    notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            }
        } catch (InterruptedException ignored) {
        }
        if (shop.getCar().size() != 0) {
            shop.getCar().remove(0);
        }
    }
}
