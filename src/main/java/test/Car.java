package test;

/**
 * Test Object Car whith method "compareTo"
 */
public class Car implements Comparable<Car> {
    private int price;
    public Car(int p){
        price=p;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int compareTo(Car o) {
        return price > o.price ? 1 : price == o.price ? 0 : -1;
    }

    @Override
    public String toString() {
        return "Car: "+price;
    }
}
