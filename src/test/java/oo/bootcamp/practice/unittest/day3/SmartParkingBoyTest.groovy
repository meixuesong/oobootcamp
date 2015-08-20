package oo.bootcamp.practice.unittest.day3

import oo.bootcamp.practice.day2.Car
import oo.bootcamp.practice.day2.Parkinglot
import oo.bootcamp.practice.day3.SmartParkingBoy
import spock.lang.Specification

/**
 * Created by Xuesong Mei on 8/16/15.
 */
class SmartSmartParkingBoyTest extends Specification {
    def "should success when parking a car to a parkinglot which has vacancies"() {
        given:
        SmartParkingBoy boy = new SmartParkingBoy(
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
        SmartParkingBoy boy = new SmartParkingBoy(
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


    def "should park the car to the first parkinglot when he has two parkinglots and first has three and second has two vacancies"() {
        given:
        Parkinglot firstParkinglot = new Parkinglot(3)
        Parkinglot secondParkinglot = new Parkinglot(2)
        def List<Parkinglot> parkinglots = [firstParkinglot, secondParkinglot]
        SmartParkingBoy boy = new SmartParkingBoy(parkinglots);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == firstParkinglot.take(ticket)
    }

    def "should park the car to the second parkinglot when he has two parkinglots and first has two and second has three vacancies"() {
        given:
        Parkinglot firstParkinglot = new Parkinglot(2)
        Parkinglot secondParkinglot = new Parkinglot(3)
        def List<Parkinglot> parkinglots = [firstParkinglot, secondParkinglot]
        SmartParkingBoy boy = new SmartParkingBoy(parkinglots);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == secondParkinglot.take(ticket)
    }

    def "should park the car to the first parkinglot when he has two parkinglots and first has 1 and second has 0 vacancies"() {
        given:
        Parkinglot firstParkinglot = new Parkinglot(1)
        Parkinglot secondParkinglot = new Parkinglot(0)
        def List<Parkinglot> parkinglots = [firstParkinglot, secondParkinglot]
        SmartParkingBoy boy = new SmartParkingBoy(parkinglots);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        car == firstParkinglot.take(ticket)
    }

    def "should failed when parking a car to his two parkinglots and both of them are full"() {
        given:
        def List<Parkinglot> parkinglots = [new Parkinglot(0), new Parkinglot(0)]
        SmartParkingBoy boy = new SmartParkingBoy(parkinglots);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = boy.park(car)

        then:
        null == ticket
    }
}
