package oo.bootcamp.practice.day3;

import oo.bootcamp.practice.day2.BaseBoy;
import oo.bootcamp.practice.day2.Car;
import oo.bootcamp.practice.day2.Parkinglot;

import java.util.*;

/**
 * Created by Xuesong Mei on 8/16/15.
 */
public class SmartParkingBoy  extends BaseBoy {
    public SmartParkingBoy(Parkinglot parkinglot) {
        super(parkinglot);
    }

    public SmartParkingBoy(List<Parkinglot> parkinglots) {
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
                return o2.getVacanciesCount() - o1.getVacanciesCount();
            }
        });

        return parkinglots.get(0);
    }
}
