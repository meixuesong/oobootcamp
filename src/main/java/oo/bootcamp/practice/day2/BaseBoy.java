package oo.bootcamp.practice.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Xuesong Mei on 8/17/15.
 */
public abstract class BaseBoy {
    private static final String INDENT_SPACE = "  ";
    private static final String REPORT = "B %d %d";
    protected List<Parkinglot> parkinglots = new ArrayList<>();

    protected BaseBoy(Parkinglot parkinglot) {
        parkinglots.add(parkinglot);
    }

    protected BaseBoy(List<Parkinglot> parkinglots) {
        this.parkinglots.addAll(parkinglots);
    }

    public UUID park(Car car) {
        Parkinglot parkinglot = chooseParkinglot();
        if (parkinglot != null) {
            return parkinglot.park(car);
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

        return null;
    }


    protected abstract Parkinglot chooseParkinglot();

    public int getParkingSize() {
        return parkinglots.stream().mapToInt(p -> p.getSize()).sum();
    }

    public int getParkingCars() {
        return parkinglots.stream().mapToInt(p -> p.getCarsCount()).sum();
    }

    public String report(String indent) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(indent + String.format(REPORT, getParkingCars(), getParkingSize()));

        for(Parkinglot parkinglot : parkinglots) {
            reportBuilder.append("\n");
            reportBuilder.append(parkinglot.report(indent + INDENT_SPACE));
        }

        return reportBuilder.toString();
    }
}
