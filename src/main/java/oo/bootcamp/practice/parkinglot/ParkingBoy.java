package oo.bootcamp.practice.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots.addAll(parkingLots);
    }

    public UUID park(Car car){
        UUID ticket = null;
        for(ParkingLot parkingLot : this.parkingLots) {
            ticket = parkingLot.park(car);
            if(ticket!=null){
                break;
            }
        }
        return ticket;
    }

    public Car pick(UUID ticket){
        Car car = null;
        for(ParkingLot parkingLot : this.parkingLots) {
            car = parkingLot.pick(ticket);
            if(car!=null){
                break;
            }
        }
        return car;
    }
}
