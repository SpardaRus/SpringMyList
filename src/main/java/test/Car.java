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
        if (price > o.price) return -1;
        else if (price == o.price) return 0;
        else return 1;
    }

    @Override
    public String toString() {
        return "Car: "+price;
    }
}
