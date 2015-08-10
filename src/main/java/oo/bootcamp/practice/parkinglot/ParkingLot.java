package oo.bootcamp.practice.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private int capacity;
    private Map<UUID, Car> parkedCars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        parkedCars = new HashMap<>();
    }

    private boolean isFull() {
        return this.capacity == parkedCars.size();
    }

    private boolean hasParked(UUID ticket) {
        return this.parkedCars.containsKey(ticket);
    }

    public int getNumberOfFreeSpace(){
        return capacity - parkedCars.size();
    }

    public UUID park(Car car) {
        if(!this.isFull()) {
            UUID ticket = UUID.randomUUID();
            this.parkedCars.put(ticket, car);
            return ticket;
        }
        return null;
    }

    public Car pick(UUID ticket) {
        if (this.hasParked(ticket)) {
            Car car = this.parkedCars.get(ticket);
            this.parkedCars.remove(ticket);
            return car;
        }
        return null;
    }
}
