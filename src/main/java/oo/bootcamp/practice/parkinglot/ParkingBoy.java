package oo.bootcamp.practice.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private ChooseParkingLotStrategy strategy;

    public ParkingBoy(List<ParkingLot> parkingLots, ChooseParkingLotStrategy strategy) {
        this.parkingLots = parkingLots;
        this.strategy = strategy;
    }

    public Car pick(UUID ticket) {
        return this.parkingLots.stream()
                .map(parkingLot -> parkingLot.pick(ticket))
                .filter(car -> car != null)
                .findFirst()
                .orElse(null);
    }

    public UUID park(Car car) {
        ParkingLot parkingLot = strategy.apply(parkingLots);
        return parkingLot == null ? null : parkingLot.park(car);
    }

    public static ParkingBoy parkingBoy(List<ParkingLot> parkingLots) {
        ChooseParkingLotStrategy strategy = ps -> ps.stream()
                .filter(parkingLot -> parkingLot.getNumberOfFreeSpace() > 0)
                .findFirst()
                .orElse(null);
        return new ParkingBoy(parkingLots, strategy);
    }

    public static ParkingBoy smartParkingBoy(List<ParkingLot> parkingLots) {
        ChooseParkingLotStrategy strategy = ps -> ps.stream()
                .sorted(Comparator.comparing(ParkingLot::getNumberOfFreeSpace).reversed())
                .findFirst()
                .get();
        return new ParkingBoy(parkingLots, strategy);
    }

    public static ParkingBoy superParkingBoy(List<ParkingLot> parkingLots) {
        ChooseParkingLotStrategy strategy = ps -> ps.stream()
                .sorted(Comparator.comparing(ParkingLot::getEmptyRate).reversed())
                .findFirst()
                .get();
        return new ParkingBoy(parkingLots, strategy);
    }
}