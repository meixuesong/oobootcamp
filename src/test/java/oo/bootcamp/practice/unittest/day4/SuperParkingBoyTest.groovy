package oo.bootcamp.practice.unittest.day4

import oo.bootcamp.practice.day2.Car
import oo.bootcamp.practice.day2.Parkinglot
import oo.bootcamp.practice.day3.SuperParkingBoy
import oo.bootcamp.practice.day4.SuperParkingBoy
import spock.lang.Specification

/**
 * Created by Xuesong Mei on 8/16/15.
 */
class SuperParkingBoyTest extends Specification {
    def "should success when parking a car to a parkinglot which has vacancies"() {
        given:
        SuperParkingBoy boy = new SuperParkingBoy(
                new Parkinglot(1)
        );

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == boy.take(ticket)
    }

    def "should failed to parking a car when the parking lot is full"() {
        given:
        SuperParkingBoy boy = new SuperParkingBoy(
                new Parkinglot(1)
        )
        def String successLicense = "京A00001"
        def UUID successTicket = boy.park(new Car(successLicense))

        when:
        def String failLicense = "京A00002"
        Car failCar = new Car(failLicense)
        def UUID failTicket = boy.park(failCar)

        then:
        failTicket == null

        when:
        boy.take(successTicket)
        failTicket = boy.park(failCar)

        then:
        failCar == boy.take(failTicket)
    }


    def "should park the car to the first parkinglot when he has two parkinglots and both has 10 positions and first has three and second has two vacancies"() {
        given:
        Parkinglot firstParkinglot = new Parkinglot(10)
        Parkinglot secondParkinglot = new Parkinglot(10)
        def List<Parkinglot> parkinglots = [firstParkinglot, secondParkinglot]
        SuperParkingBoy boy = new SuperParkingBoy(parkinglots);
        parkingCars(firstParkinglot, 7);
        parkingCars(secondParkinglot, 8);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == firstParkinglot.take(ticket)
    }

    def "should park the car to the second parkinglot when he has two parkinglots and both has 10 positions and first has two and second has three vacancies"() {
        given:
        Parkinglot firstParkinglot = new Parkinglot(10)
        Parkinglot secondParkinglot = new Parkinglot(10)
        def List<Parkinglot> parkinglots = [firstParkinglot, secondParkinglot]
        SuperParkingBoy boy = new SuperParkingBoy(parkinglots);
        parkingCars(firstParkinglot, 8);
        parkingCars(secondParkinglot, 7);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == secondParkinglot.take(ticket)
    }

    def "should park the car to the first parkinglot when he has two parkinglots and first has 1/2 and second has 2/6 vacancies"() {
        given:
        Parkinglot firstParkinglot = new Parkinglot(2)
        Parkinglot secondParkinglot = new Parkinglot(6)
        def List<Parkinglot> parkinglots = [firstParkinglot, secondParkinglot]
        SuperParkingBoy boy = new SuperParkingBoy(parkinglots);
        parkingCars(firstParkinglot, 1);
        parkingCars(secondParkinglot, 4);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == firstParkinglot.take(ticket)
    }

    def "should park the car to the second parkinglot when he has two parkinglots and first has 2/6 and second has 1/2 vacancies"() {
        given:
        Parkinglot firstParkinglot = new Parkinglot(6)
        Parkinglot secondParkinglot = new Parkinglot(2)
        def List<Parkinglot> parkinglots = [firstParkinglot, secondParkinglot]
        SuperParkingBoy boy = new SuperParkingBoy(parkinglots);
        parkingCars(firstParkinglot, 4);
        parkingCars(secondParkinglot, 1);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == secondParkinglot.take(ticket)
    }

    def "should failed when parking a car to his two parkinglots and both of them are full"() {
        given:
        def List<Parkinglot> parkinglots = [new Parkinglot(0), new Parkinglot(0)]
        SuperParkingBoy boy = new SuperParkingBoy(parkinglots);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        null == ticket
    }

    private void parkingCars(Parkinglot parkinglot, int carNumber) {
        for (int i = 0; i < carNumber; i++) {
            parkinglot.park(new Car("Car" + i));
        }
    }
}
