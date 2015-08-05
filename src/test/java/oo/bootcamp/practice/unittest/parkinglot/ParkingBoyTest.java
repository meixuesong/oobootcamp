package oo.bootcamp.practice.unittest.parkinglot;

import oo.bootcamp.practice.parkinglot.Car;
import oo.bootcamp.practice.parkinglot.ParkingBoy;
import oo.bootcamp.practice.parkinglot.ParkingLot;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingBoyTest {
    @Test
     public void should_success_to_park_car_when_one_parkinglot_with_one_capacity(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        parkingBoy.park(car);

        assertTrue(parkingLot.pick(car));
    }

    @Test
    public void should_success_to_pick_car_when_parked_a_car_in_one_parkinglot_with_one_capacity(){
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));

        Car car = new Car();
        parkingBoy.park(car);

        assertTrue(parkingBoy.pick(car));
    }

    @Test
    public void should_fail_to_park_car_when_one_parkinglot_with_one_capacity_occupied(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingLot.park(new Car());

        Car car = new Car();

        assertFalse(parkingBoy.park(car));
    }

    @Test
    public void should_success_to_park_car_when_two_parkinglot_both_empty(){
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car = new Car();
        parkingBoy.park(car);

        assertTrue(parkingBoy.pick(car));
    }

    @Test
    public void should_success_to_park_car_when_two_parkinglot_one_empty_one_full(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

        ParkingLot fullParkingLot = new ParkingLot(1);
        fullParkingLot.park(new Car());
        parkingLots.add(fullParkingLot);

        ParkingLot emptyParkingLot = new ParkingLot(1);
        parkingLots.add(emptyParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Car car = new Car();
        parkingBoy.park(car);

        //then
        assertTrue(parkingBoy.pick(car));
    }

    @Test
    public void should_success_to_park_car_in_first_parkinglot_when_two_parkinglot_first_half_full_second_empty(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

        ParkingLot halfFullParkingLot = new ParkingLot(2);
        halfFullParkingLot.park(new Car());
        parkingLots.add(halfFullParkingLot);

        ParkingLot emptyParkingLot = new ParkingLot(1);
        parkingLots.add(emptyParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Car car = new Car();
        parkingBoy.park(car);

        //then
        assertTrue(halfFullParkingLot.pick(car));
    }

    @Test
    public void should_success_to_park_car_in_first_parkinglog_when_two_parkinglot_with_second_full(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

        ParkingLot firstEmptyParkingLot = new ParkingLot(1);
        parkingLots.add(firstEmptyParkingLot);

        ParkingLot secondFullParkingLot = new ParkingLot(1);
        secondFullParkingLot.park(new Car());
        parkingLots.add(secondFullParkingLot);

        ParkingLot thirdEmptyParkingLot = new ParkingLot(1);
        parkingLots.add(thirdEmptyParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Car car = new Car();
        parkingBoy.park(car);

        //then
        assertTrue(firstEmptyParkingLot.pick(car));
    }
}
