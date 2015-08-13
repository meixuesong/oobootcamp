package oo.bootcamp.practice.unittest.parkinglot;

import oo.bootcamp.practice.parkinglot.Car;
import oo.bootcamp.practice.parkinglot.ParkingBoy;
import oo.bootcamp.practice.parkinglot.ParkingLot;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ParkingBoyTest {
    private static final String CAR_LICENSE = "SOME_CAR_LICENSE";
    private static final String ANOTHER_CAR_LICENSE = "ANOTHER_CAR_LICENSE";

    @Test
    public void should_success_to_park_car_when_one_parkinglot_with_one_capacity() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = ParkingBoy.parkingBoy(parkingLots);

        Car car = new Car(CAR_LICENSE);
        UUID ticket = parkingBoy.park(car);

        assertTrue(car.equals(parkingLot.pick(ticket)));
    }

    @Test
    public void should_success_to_pick_car_when_parked_a_car_in_one_parkinglot_with_one_capacity() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = ParkingBoy.parkingBoy(parkingLots);

        Car car = new Car(CAR_LICENSE);
        UUID ticket = parkingBoy.park(car);

        assertTrue(car.equals(parkingBoy.pick(ticket)));
    }

    @Test
    public void should_fail_to_park_car_when_one_parkinglot_with_one_capacity_occupied() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = ParkingBoy.parkingBoy(parkingLots);
        parkingLot.park(new Car(ANOTHER_CAR_LICENSE));

        Car car = new Car(CAR_LICENSE);

        assertNull(parkingBoy.park(car));
    }

    @Test
    public void should_success_to_park_car_when_two_parkinglot_both_empty() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        ParkingBoy parkingBoy = ParkingBoy.parkingBoy(parkingLots);

        Car car = new Car(CAR_LICENSE);
        UUID ticket = parkingBoy.park(car);

        assertTrue(car.equals(parkingBoy.pick(ticket)));
    }

    @Test
    public void should_success_to_park_car_when_two_parkinglot_one_empty_one_full() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot fullParkingLot = new ParkingLot(1);
        fullParkingLot.park(new Car(ANOTHER_CAR_LICENSE));
        parkingLots.add(fullParkingLot);

        ParkingLot emptyParkingLot = new ParkingLot(1);
        parkingLots.add(emptyParkingLot);

        ParkingBoy parkingBoy = ParkingBoy.parkingBoy(parkingLots);

        //when
        Car car = new Car(CAR_LICENSE);
        UUID ticket = parkingBoy.park(car);

        //then
        assertTrue(car.equals(parkingBoy.pick(ticket)));
    }

    @Test
    public void should_success_to_park_car_in_first_parkinglot_when_two_parkinglot_first_half_full_second_empty() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot halfFullParkingLot = new ParkingLot(2);
        halfFullParkingLot.park(new Car(ANOTHER_CAR_LICENSE));
        parkingLots.add(halfFullParkingLot);

        ParkingLot emptyParkingLot = new ParkingLot(1);
        parkingLots.add(emptyParkingLot);

        ParkingBoy parkingBoy = ParkingBoy.parkingBoy(parkingLots);

        //when
        Car car = new Car(CAR_LICENSE);
        UUID ticket = parkingBoy.park(car);

        //then
        assertTrue(car.equals(halfFullParkingLot.pick(ticket)));
    }

    @Test
    public void should_success_to_park_car_in_first_parkinglog_when_two_parkinglot_with_second_full() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot firstEmptyParkingLot = new ParkingLot(1);
        parkingLots.add(firstEmptyParkingLot);

        ParkingLot secondFullParkingLot = new ParkingLot(1);
        secondFullParkingLot.park(new Car(ANOTHER_CAR_LICENSE));
        parkingLots.add(secondFullParkingLot);

        ParkingLot thirdEmptyParkingLot = new ParkingLot(1);
        parkingLots.add(thirdEmptyParkingLot);

        ParkingBoy parkingBoy = ParkingBoy.parkingBoy(parkingLots);

        //when
        Car car = new Car(CAR_LICENSE);
        UUID ticket = parkingBoy.park(car);

        //then
        assertTrue(car.equals(firstEmptyParkingLot.pick(ticket)));
    }
}
