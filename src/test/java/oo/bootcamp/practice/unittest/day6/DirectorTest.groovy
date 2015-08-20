package oo.bootcamp.practice.unittest.day6

import oo.bootcamp.practice.day2.Car
import oo.bootcamp.practice.day2.ParkingBoy
import oo.bootcamp.practice.day2.Parkinglot
import oo.bootcamp.practice.day5.Manager
import spock.lang.Specification

/**
 * Created by Xuesong Mei on 8/18/15.
 */
class DirectorTest extends Specification {

    def "should generate the right parkinglot report"() {
        when:
        Parkinglot parkinglot = getParkinglot(parkinglotSize, parkingCars)

        then:
        expectReport == parkinglot.report("")

        where:
        parkinglotSize  | parkingCars   | expectReport
        0               | 0             | "P 0 0"
        1               | 0             | "P 0 1"
        1               | 1             | "P 1 1"
        2               | 1             | "P 1 2"
    }

    def "should generate the right parking boy report"() {
        when:
        ParkingBoy boy = getParkingBoy(parkinglotCount, park_1_size, park_1_cars, park_2_size, park_2_cars)

        then:
        expectReport == boy.report("")

        where:
        parkinglotCount | park_1_size   | park_1_cars   | park_2_size   | park_2_cars   | expectReport
        0               | 0             | 0             | 0             | 0             | "B 0 0"
        1               | 1             | 0             | 0             | 0             | "B 0 1\n  P 0 1"
        2               | 1             | 0             | 1             | 1             | "B 1 2\n  P 0 1\n  P 1 1"
    }

    def "should generate the right manager report when he does not manage any boy"() {
        when:
        Manager manager = getManager(parkinglotCount, park_1_size, park_1_cars, park_2_size, park_2_cars)

        then:
        expectReport == manager.report()

        where:
        parkinglotCount | park_1_size   | park_1_cars   | park_2_size   | park_2_cars   | expectReport
        0               | 0             | 0             | 0             | 0             | "M 0 0"
        1               | 1             | 0             | 0             | 0             | "M 0 1\n  P 0 1"
        2               | 1             | 0             | 1             | 1             | "M 1 2\n  P 0 1\n  P 1 1"
    }

    def "should generate the right manager report when he has parkinglots(size:1,car:1) and boys(size:1,car:1)"() {
        when:
        Manager manager = getManagerWithParkingCountAndBoyCount(parkinglotCount, boysCount)

        then:
        expectReport == manager.report()

        where:
        parkinglotCount | boysCount     | expectReport
        1               | 1             | "M 2 2\n  P 1 1\n  B 1 1\n    P 1 1"
        2               | 1             | "M 3 3\n  P 1 1\n  P 1 1\n  B 1 1\n    P 1 1"
        1               | 2             | "M 3 3\n  P 1 1\n  B 1 1\n    P 1 1\n  B 1 1\n    P 1 1"
    }

    private Parkinglot getParkinglot(int size, int parkingCars) {
        Parkinglot parkinglot = new Parkinglot(size);

        for (int i = 0; i < parkingCars; i++) {
            parkinglot.park(new Car("Car" + i));
        }

        return parkinglot;
    }

    private List<Parkinglot> getParkingLots(int parkinglotCount, int... values) {
        List<Parkinglot> parkinglots = new ArrayList<>();

        for (int i = 0; i < parkinglotCount; i++) {
            parkinglots.add(getParkinglot(values[i * 2], values[i * 2 + 1]));
        }

        return parkinglots;
    }


    private ParkingBoy getParkingBoy(int parkinglotCount, int... values) {
        ParkingBoy boy = new ParkingBoy(getParkingLots(parkinglotCount, values));

        return boy;
    }

    private Manager getManager(int parkinglotCount, int... values) {
        Manager manager = new Manager(getParkingLots(parkinglotCount, values), []);

        return manager;
    }

    private Manager getManagerWithParkingCountAndBoyCount(parkinglotCount, boysCount) {
        List<ParkingBoy> boys = new ArrayList<>();
        List<Parkinglot> parkinglots = new ArrayList<>();

        for (int i = 0; i < parkinglotCount; i++) {
            parkinglots.add(getParkinglot(1, 1));
        }

        for (int i = 0; i < boysCount; i++) {
            boys.add(getParkingBoy(1, 1, 1));
        }

        return new Manager(parkinglots, boys);
    }

}
