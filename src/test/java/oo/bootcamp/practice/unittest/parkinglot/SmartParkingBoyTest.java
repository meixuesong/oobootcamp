package oo.bootcamp.practice.unittest.parkinglot;

import oo.bootcamp.practice.parkinglot.Car;
import oo.bootcamp.practice.parkinglot.ParkingLot;
import oo.bootcamp.practice.parkinglot.SmartParkingBoy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SmartParkingBoyTest {
    private static final String CAR_LICENSE = "SOME_CAR_LICENSE";
    private static final String ANOTHER_CAR_LICENSE = "ANOTHER_CAR_LICENSE";

    @Test
     public void should_success_to_park_car_when_one_parkinglot_with_one_capacity(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLots.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        assertTrue(car.equals(parkingLot.pick(ticket)));
    }

    @Test
    public void should_success_to_pick_car_when_parked_a_car_in_one_parkinglot_with_one_capacity(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLots.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        assertTrue(car.equals(smartParkingBoy.pick(ticket)));
    }

    @Test
    public void should_fail_to_park_car_when_one_parkinglot_with_one_capacity_occupied(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLots.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        parkingLot.park(new Car(ANOTHER_CAR_LICENSE));

        Car car = new Car(CAR_LICENSE);

        assertNull(smartParkingBoy.park(car));
    }

    @Test
    public void should_success_to_park_car_when_two_parkinglot_both_empty(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        assertTrue(car.equals(smartParkingBoy.pick(ticket)));
    }

    @Test
    public void should_success_to_park_at_second_when_two_parkinglot_first_has_one_space_second_has_two(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot oneSpaceParkinglot = new ParkingLot(1);
        parkingLots.add(oneSpaceParkinglot);

        ParkingLot twoSpaceParkingLot = new ParkingLot(2);
        parkingLots.add(twoSpaceParkingLot);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //when
        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        //then
        assertTrue(car.equals(twoSpaceParkingLot.pick(ticket)));
    }

    @Test
    public void should_success_to_park_car_in_first_parkinglot_when_two_parkinglot_both_one_space(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot oneSpaceParkinglot = new ParkingLot(1);
        parkingLots.add(oneSpaceParkinglot);

        ParkingLot anotherOneSpaceParkinglot = new ParkingLot(1);
        parkingLots.add(anotherOneSpaceParkinglot);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //when
        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        //then
        assertTrue(car.equals(oneSpaceParkinglot.pick(ticket)));
    }

    @Test
    public void should_fail_to_park_car_when_two_parkinglot_both_full(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(0));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //when
        Car car = new Car(CAR_LICENSE);

        //then
        assertNull(smartParkingBoy.park(car));
    }
}
