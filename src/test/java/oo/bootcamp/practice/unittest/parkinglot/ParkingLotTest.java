package oo.bootcamp.practice.unittest.parkinglot;

import oo.bootcamp.practice.parkinglot.Car;
import oo.bootcamp.practice.parkinglot.ParkingLot;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {
    private static final String CAR_LICENSE = "SOME_CAR_LICENSE";
    private static final String ANOTHER_CAR_LICENSE = "ANOTHER_CAR_LICENSE";

    @Test
    public void should_success_to_park_when_capacity_is_one_and_no_cars_parked(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car(CAR_LICENSE);
        UUID ticket = parkingLot.park(car);

        assertTrue(car.equals(parkingLot.pick(ticket)));
    }

    @Test
    public void should_fail_to_park_when_capacity_is_one_and_one_car_parked(){
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car(ANOTHER_CAR_LICENSE));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = parkingLot.park(car);

        assertNull(parkingLot.pick(ticket));
    }

    @Test
    public void should_fail_to_leave_when_the_car_is_not_parked(){
        ParkingLot parkingLot = new ParkingLot(1);

        assertNull(parkingLot.pick(UUID.randomUUID()));
    }
}
