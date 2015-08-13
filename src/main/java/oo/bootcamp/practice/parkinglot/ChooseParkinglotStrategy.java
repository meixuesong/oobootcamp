package oo.bootcamp.practice.parkinglot;

import java.util.List;

public interface ChooseParkingLotStrategy {
    ParkingLot apply(List<ParkingLot> parkingLots);
}