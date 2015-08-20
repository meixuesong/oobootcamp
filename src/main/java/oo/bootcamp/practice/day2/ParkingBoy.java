package oo.bootcamp.practice.day2;

import java.util.*;

/**
 * Created by Xuesong Mei on 8/16/15.
 */
public class ParkingBoy extends BaseBoy {
    public ParkingBoy(Parkinglot parkinglot) {
        super(parkinglot);
    }

    public ParkingBoy(List<Parkinglot> parkinglots) {
        super(parkinglots);
    }

    protected Parkinglot chooseParkinglot() {
        for(Parkinglot parkinglot : parkinglots) {
            if (parkinglot.hasVacancies()) {
                return parkinglot;
            }
        }

        return null;
    }
}
