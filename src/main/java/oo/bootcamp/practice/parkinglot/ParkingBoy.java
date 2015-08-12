package oo.bootcamp.practice.parkinglot;

import java.util.List;
import java.util.UUID;

public class ParkingBoy extends  ParkingBoyBase {

    public ParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
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

}
