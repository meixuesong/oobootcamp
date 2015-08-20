package oo.bootcamp.practice.day4;

import oo.bootcamp.practice.day2.BaseBoy;
import oo.bootcamp.practice.day2.Car;
import oo.bootcamp.practice.day2.Parkinglot;

import java.util.*;

/**
 * Created by Xuesong Mei on 8/16/15.
 */
public class SuperParkingBoy  extends BaseBoy {
    public SuperParkingBoy(Parkinglot parkinglot) {
        super(parkinglot);
    }

    public SuperParkingBoy(List<Parkinglot> parkinglots) {
        super(parkinglots);
    }

    @Override
    protected Parkinglot chooseParkinglot() {
        if (parkinglots.size() == 0) {
            return null;
        }

        Collections.sort(parkinglots, new Comparator<Parkinglot>() {
            @Override
            public int compare(Parkinglot o1, Parkinglot o2) {
                return o2.getVacantRate().compareTo(o1.getVacantRate());
            }
        });

        return parkinglots.get(0);
    }
}
