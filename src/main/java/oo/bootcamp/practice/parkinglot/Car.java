package oo.bootcamp.practice.parkinglot;

public class Car {
    private String license;

    public Car(String license) {
        this.license = license;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return !(license != null ? !license.equals(car.license) : car.license != null);
    }

    @Override
    public int hashCode() {
        return license != null ? license.hashCode() : 0;
    }
}
