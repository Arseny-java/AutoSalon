import java.util.ArrayList;
import java.util.List;

public class Shop {
    int countCars = 10;
    int sales;
    protected int traffic = 0;
    protected int sold = 0;
    protected int dailySalesPlan = 10;
    Seller customer = new Seller(this);
    List<Car> cars = new ArrayList<>(countCars);

    public Shop(int dailyPlan) {
        sales = dailyPlan;
    }

    public void sellCars() {
        System.out.printf("%s зашел в магазин\n", Thread.currentThread().getName());
        customer.sellCars();
    }

    public void acceptCars() {
        customer.receiveCars();
    }

    List<Car> getCar() {
        return cars;
    }

    public int getBuyers() {
        return traffic;
    }

    public void increaseTraffic() {
        traffic++;
    }

    public boolean planIsDone() {
        return sold < dailySalesPlan;
    }

    public int getSolds() {
        return sold;
    }

    protected void soldsNumber() {
        sold++;
    }
}
