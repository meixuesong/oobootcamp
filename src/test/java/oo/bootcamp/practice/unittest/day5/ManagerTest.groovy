package oo.bootcamp.practice.unittest.day5

import oo.bootcamp.practice.day2.BaseBoy
import oo.bootcamp.practice.day2.Car
import oo.bootcamp.practice.day2.ParkingBoy
import oo.bootcamp.practice.day2.Parkinglot
import oo.bootcamp.practice.day3.SmartParkingBoy
import oo.bootcamp.practice.day4.SuperParkingBoy
import oo.bootcamp.practice.day5.Manager
import spock.lang.Specification

/**
 * Created by Xuesong Mei on 8/16/15.
 */
class ManagerTest extends Specification {
    def "should success when parking a car to a parkinglot which has vacancies"() {
        given:
        Manager manager = new Manager([new Parkinglot(1)], [])

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        car == manager.take(ticket)
    }

    def "should failed to parking a car when the parking lot is full"() {
        given:
        Manager manager = new Manager([new Parkinglot(1)], [])
        def String successLicense = "京A00001"
        def UUID successTicket = manager.park(new Car(successLicense))

        when:
        def String failLicense = "京A00002"
        Car failCar = new Car(failLicense)
        def UUID failTicket = manager.park(failCar)

        then:
        failTicket == null

        when:
        manager.take(successTicket)
        failTicket = manager.park(failCar)

        then:
        failCar == manager.take(failTicket)
    }


    def "should success when parking a car to his two parkinglots and both of them has vacancies"() {
        given:
        Manager manager = new Manager([new Parkinglot(1), new Parkinglot(1)], []);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        car == manager.take(ticket)
    }

    def "should success when parking a car to his two parkinglots and one of them has vacancies"() {
        given:
        Manager manager = new Manager([new Parkinglot(0), new Parkinglot(1)], []);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        car == manager.take(ticket)
    }

    def "should failed when parking a car to his two parkinglots and both of them are full"() {
        given:
        Manager manager = new Manager([new Parkinglot(0), new Parkinglot(0)], []);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        null == ticket
    }
    
    //begin manage
    
    def "should success to park a car when he manage a ParkingBoy who has a parking lot(1 vacancy)"() {
        given:
        ParkingBoy boy = new ParkingBoy(new Parkinglot(1));
        Manager manager = new Manager([], [boy]);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        car == manager.take(ticket)
    }

    def "should success to park a car when he manage a SmartParkingBoy who has a parking lot(1 vacancy)"() {
        given:
        SmartParkingBoy boy = new SmartParkingBoy(new Parkinglot(1));
        Manager manager = new Manager([], [boy]);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        car == boy.take(ticket)
    }

    def "should success to park a car when he manage a SuperParkingBoy who has a parking lot(1 vacancies)"() {
        given:
        SuperParkingBoy boy = new SuperParkingBoy(new Parkinglot(1));
        Manager manager = new Manager([], [boy]);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        car == manager.take(ticket)
    }

    def "should success to park a car when he manage a ParkingBoy and a SmartParkingBoy both has a parking lot(1 vacancies)"() {
        given:
        ParkingBoy parkingBoy = new ParkingBoy(new Parkinglot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new Parkinglot(1));
        Manager manager = new Manager([], [parkingBoy, smartParkingBoy]);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        car == manager.take(ticket)
    }

    def "should success to park a car when he manage a ParkingBoy and a SmartParkingBoy both has a parking lot but smart's is full"() {
        given:
        ParkingBoy parkingBoy = new ParkingBoy(new Parkinglot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new Parkinglot(1));
        smartParkingBoy.park(new Car("test"))
        Manager manager = new Manager([], [parkingBoy, smartParkingBoy]);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        car == manager.take(ticket)
    }

    def "should failed to park a car when he manage a ParkingBoy who has a parking lot(is full)"() {
        given:
        ParkingBoy parkingBoy = new ParkingBoy(new Parkinglot(1));
        parkingBoy.park(new Car("a car"))
        Manager manager = new Manager([], [parkingBoy]);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        ticket == null
    }

    def "should failed to park a car when he manage a ParkingBoy and a SmartParkingBoy both has a parking lot(both are full)"() {
        given:
        ParkingBoy parkingBoy = new ParkingBoy(new Parkinglot(1));
        parkingBoy.park(new Car("a car"))
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new Parkinglot(1));
        smartParkingBoy.park(new Car("another car"))
        Manager manager = new Manager([], [parkingBoy, smartParkingBoy]);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        ticket == null
    }

    def "should success to park a car when he has a parkinglot(has vacancies) and manage a ParkingBoy(has a parking lot which is full)"() {
        given:
        ParkingBoy parkingBoy = new ParkingBoy(new Parkinglot(1));
        parkingBoy.park(new Car("a car"))
        Manager manager = new Manager([new Parkinglot(1)], [parkingBoy]);

        when:
        Car car = new Car("京A00001")
        def UUID ticket = manager.park(car)

        then:
        car == manager.take(ticket);
    }
    
}
