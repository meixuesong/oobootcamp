package oo.bootcamp.practice.day5;

import oo.bootcamp.practice.day2.BaseBoy;
import oo.bootcamp.practice.day2.Car;
import oo.bootcamp.practice.day2.Parkinglot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by Xuesong Mei on 8/17/15.
 */
public class Manager {
    private static final String REPORT = "M %d %d";
    private static final String INDENT_SPACE = "  ";
    private List<Parkinglot> parkinglots = new ArrayList<>();
    private List<BaseBoy> boys = new ArrayList<>();

    public Manager(List<Parkinglot> parkinglots, List<BaseBoy> boys) {
        this.parkinglots.addAll(parkinglots);
        this.boys.addAll(boys);
    }

    public UUID park(Car car) {
        for(Parkinglot parkinglot : parkinglots) {
            if (parkinglot.hasVacancies()) {
                return parkinglot.park(car);
            }
        }

        for(BaseBoy boy : boys) {
            UUID uuid = boy.park(car);
            if (uuid != null) {
                return uuid;
            }
        }

        return null;
    }

    public Car take(UUID uuid) {
        for (Parkinglot parkinglot : parkinglots) {
            Car car = parkinglot.take(uuid);

            if (car != null) {
                return car;
            }
        }

        for(BaseBoy boy : boys) {
            Car car = boy.take(uuid);
            if (car != null) {
                return car;
            }
        }

        return null;
    }

    public String report() {
        int selfParkingSize = parkinglots == null? 0 : parkinglots.stream().mapToInt(p -> p.getSize()).sum();
        int selfParkingCars = parkinglots == null? 0 : parkinglots.stream().mapToInt(p -> p.getCarsCount()).sum();

        int boysParkingSize = boys == null? 0: boys.stream().mapToInt(b -> b.getParkingSize()).sum();
        int boysParkingCars = boys == null? 0: boys.stream().mapToInt(b -> b.getParkingCars()).sum();

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(String.format(REPORT,
                selfParkingCars + boysParkingCars,
                selfParkingSize + boysParkingSize));

        reportBuilder.append(getParkinglotsReport());
        reportBuilder.append(getBoysReport());

        return reportBuilder.toString();
    }

    private String getParkinglotsReport() {
        if (parkinglots == null) {
            return "";
        }

        StringBuilder reportBuilder = new StringBuilder();
        for(Parkinglot parkinglot : parkinglots) {
            reportBuilder.append("\n");
            reportBuilder.append(parkinglot.report(INDENT_SPACE));
        }

        return reportBuilder.toString();
    }

    private String getBoysReport() {
        if (boys == null) {
            return "";
        }

        StringBuilder reportBuilder = new StringBuilder();
        for(BaseBoy boy : boys) {
            reportBuilder.append("\n");
            reportBuilder.append(boy.report(INDENT_SPACE));
        }

        return reportBuilder.toString();
    }
}
