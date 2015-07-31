package oo.bootcamp.practice.parkinglot;

public class Car {
    public boolean park(ParkingLot parkingLot){
        return parkingLot.park(this);
    }

    public boolean leave(ParkingLot parkingLot){
        return parkingLot.takeOut(this);
    }
}
