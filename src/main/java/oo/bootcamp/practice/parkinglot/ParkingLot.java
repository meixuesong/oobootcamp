package oo.bootcamp.practice.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private List<Car> parkedCars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        parkedCars = new ArrayList<Car>();
    }

    private boolean isFull() {
        return this.capacity == parkedCars.size();
    }

    private boolean hasParked(Car car) {
        return this.parkedCars.contains(car);
    }

    public boolean park(Car car) {
        if (this.isFull()) {
            return false;
        } else {
            this.parkedCars.add(car);
            return true;
        }
    }

    public boolean pick(Car car) {
        if (this.hasParked(car)) {
            this.parkedCars.remove(car);
            return true;
        } else {
            return false;
        }
    }
}
