public class Car {
    public static void generateNewCars(int numberOfCars, ThreadGroup group, Runnable task) {
        for (int i = 0; i < numberOfCars; i++) {
            new Thread(group, task, "Производитель машины").start();
        }
    }

}
