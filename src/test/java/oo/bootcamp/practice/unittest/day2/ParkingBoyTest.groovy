package oo.bootcamp.practice.unittest.day2

import oo.bootcamp.practice.day2.Car
import oo.bootcamp.practice.day2.ParkingBoy
import oo.bootcamp.practice.day2.Parkinglot

import spock.lang.Specification

/**
 * Created by Xuesong Mei on 8/16/15.
 */
class ParkingBoyTest extends Specification {
    def "should success when parking a car to a parkinglot which has vacancies"() {
        given:
        ParkingBoy boy = new ParkingBoy(
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
        ParkingBoy boy = new ParkingBoy(
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


    def "should success when parking a car to his two parkinglots and both of them has vacancies"() {
        given:
        def List<Parkinglot> parkinglots = [new Parkinglot(1), new Parkinglot(1)]
        ParkingBoy boy = new ParkingBoy(parkinglots);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == boy.take(ticket)
    }

    def "should success when parking a car to his two parkinglots and one of them has vacancies"() {
        given:
        def List<Parkinglot> parkinglots = [new Parkinglot(0), new Parkinglot(1)]
        ParkingBoy boy = new ParkingBoy(parkinglots);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == boy.take(ticket)
    }

    def "should failed when parking a car to his two parkinglots and both of them are full"() {
        given:
        def List<Parkinglot> parkinglots = [new Parkinglot(0), new Parkinglot(0)]
        ParkingBoy boy = new ParkingBoy(parkinglots);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        null == ticket
    }
}
