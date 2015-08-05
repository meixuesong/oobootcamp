package oo.bootcamp.practice.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots.addAll(parkingLots);
    }

    public boolean park(Car car){
        for(ParkingLot parkingLot : this.parkingLots) {
            if(parkingLot.park(car)){
                return true;
            }
        }
        return false;
    }

    public boolean pick(Car car){
        for(ParkingLot parkingLot : this.parkingLots) {
            if(parkingLot.pick(car)){
                return true;
            }
        }
        return false;
    }
}
