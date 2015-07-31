package oo.bootcamp.practice.unittest.parkinglot;

import oo.bootcamp.practice.parkinglot.Car;
import oo.bootcamp.practice.parkinglot.ParkingLot;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ParkingLotTest {
    @Test
    public void should_success_to_park_when_capacity_is_one_and_no_cars_parked(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        car.park(parkingLot);

        assertTrue(car.leave(parkingLot));
    }

    @Test
    public void should_fail_to_park_when_capacity_is_one_and_one_car_parked(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        car1.park(parkingLot);

        Car car2 = new Car();
        car2.park(parkingLot);

        assertTrue(!car2.leave(parkingLot));
    }

    @Test
    public void should_fail_to_leave_when_the_car_is_not_parked(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();

        assertTrue(!car.leave(parkingLot));
    }
}
