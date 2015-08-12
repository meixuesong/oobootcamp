package oo.bootcamp.practice.unittest.parkinglot;

import oo.bootcamp.practice.parkinglot.Car;
import oo.bootcamp.practice.parkinglot.ParkingLot;
import oo.bootcamp.practice.parkinglot.SmartParkingBoy;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SmartParkingBoyTest {
    private static final String CAR_LICENSE = "SOME_CAR_LICENSE";
    private static final String ANOTHER_CAR_LICENSE = "ANOTHER_CAR_LICENSE";

    @Test
     public void should_success_to_park_car_when_one_parkinglot_with_one_capacity(){
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        assertEquals(car, parkingLot.pick(ticket));
    }

    @Test
    public void should_success_to_pick_car_when_parked_a_car_in_one_parkinglot_with_one_capacity(){
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(1)));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        assertEquals(car, smartParkingBoy.pick(ticket));
    }

    @Test
    public void should_fail_to_park_car_when_one_parkinglot_with_one_capacity_occupied(){
        ParkingLot parkingLot = new ParkingLot(1){
            {
                park(new Car(ANOTHER_CAR_LICENSE));
            }
        };
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot));

        Car car = new Car(CAR_LICENSE);

        assertNull(smartParkingBoy.park(car));
    }

    @Test
    public void should_success_to_park_car_when_two_parkinglot_both_empty(){
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(1), new ParkingLot(1)));

        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        assertEquals(car, smartParkingBoy.pick(ticket));
    }

    @Test
    public void should_success_to_park_at_second_when_two_parkinglot_first_has_one_space_second_has_two(){
        //given
        ParkingLot oneSpaceParkinglot = new ParkingLot(1);
        ParkingLot twoSpaceParkingLot = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(oneSpaceParkinglot,twoSpaceParkingLot));

        //when
        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        //then
        assertEquals(car, twoSpaceParkingLot.pick(ticket));
    }

    @Test
    public void should_success_to_park_car_in_first_parkinglot_when_two_parkinglot_both_one_space(){
        //given
        ParkingLot oneSpaceParkinglot = new ParkingLot(1);
        ParkingLot anotherOneSpaceParkinglot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(oneSpaceParkinglot,anotherOneSpaceParkinglot));

        //when
        Car car = new Car(CAR_LICENSE);
        UUID ticket = smartParkingBoy.park(car);

        //then
        assertEquals(car,oneSpaceParkinglot.pick(ticket));
    }

    @Test
    public void should_fail_to_park_car_when_two_parkinglot_both_full(){
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(0), new ParkingLot(0)));

        //when
        Car car = new Car(CAR_LICENSE);

        //then
        assertNull(smartParkingBoy.park(car));
    }
}
