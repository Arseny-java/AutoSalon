import java.util.ArrayList;
import java.util.List;

public class Shop {
    int sales;
    protected int sold;
    Seller customer = new Seller(this);
    List<Car> cars = new ArrayList<>();

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

    public boolean planIsDone() {
        return sales <= sold;
    }

    public int getSolds() {
        return sold;
    }

    protected void soldsNumber() {
        sold++;
    }
}
