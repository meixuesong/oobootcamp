package oo.bootcamp.practice.unittest.parkinglot;

import oo.bootcamp.practice.parkinglot.Car;
import oo.bootcamp.practice.parkinglot.ParkingLot;
import oo.bootcamp.practice.parkinglot.SuperParkingBoy;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SuperParkingBoyTest {
    private static final String CAR_LICENSE = "SOME_CAR_LICENSE";

    @Test
    public void should_success_to_park() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Arrays.asList(parkingLot));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = superParkingBoy.park(car);

        assertEquals(car, parkingLot.pick(ticket));
    }

    @Test
    public void should_success_to_pick() throws Exception {
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Arrays.asList(new ParkingLot(1)));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = superParkingBoy.park(car);

        assertEquals(car, superParkingBoy.pick(ticket));
    }

    @Test
    public void should_park_to_first_when_two_parkinglots_both_empty() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = superParkingBoy.park(car);

        assertEquals(car, firstParkingLot.pick(ticket));
    }

    @Test
    public void should_fail_to_park_when_two_parkinglots_both_full() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(0);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = superParkingBoy.park(car);

        assertNull(firstParkingLot.pick(ticket));
    }

    public void should_fail_park_to_first_when_two_parkinglots_with_same_empty_rate() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(2) {
            {
                park(new Car("car1"));
            }
        };
        ParkingLot secondParkingLot = new ParkingLot(4) {
            {
                park(new Car("car2"));
                park(new Car("car3"));
            }
        };
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = superParkingBoy.park(car);

        assertEquals(car, firstParkingLot.pick(ticket));
    }

    @Test
    public void should_park_to_parkinglot_with_large_empty_rate() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(2) {
            {
                park(new Car("car1"));
            }
        };
        ParkingLot secondParkingLot = new ParkingLot(1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = superParkingBoy.park(car);

        assertEquals(car, secondParkingLot.pick(ticket));
    }

    @Test
    public void should_park_to_parkinglot_with_large_empty_rate_both_not_empty_or_full() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(2) {
            {
                park(new Car("car1"));
            }
        };
        ParkingLot secondParkingLot = new ParkingLot(5) {
            {
                park(new Car("car2"));
                park(new Car("car3"));
                park(new Car("car4"));
            }
        };
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = superParkingBoy.park(car);

        assertEquals(car, firstParkingLot.pick(ticket));
    }
}
