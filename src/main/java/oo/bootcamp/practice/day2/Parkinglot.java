package oo.bootcamp.practice.day2;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Xuesong Mei on 8/16/15.
 */
public class Parkinglot {
    private int size;
    private Map<UUID, Car> cars = new HashMap<>();

    public Parkinglot(int size) {
        this.size = size;
    }

    public UUID park(Car car) {
        if (cars.size() >= size) {
            return null;
        }

        UUID ticket = UUID.randomUUID();
        cars.put(ticket, car);

        return ticket;
    }

    public Car take(UUID uuid) {
        return cars.remove(uuid);
    }

    public boolean hasVacancies() {
        return getVacanciesCount() > 0;
    }

    public int getVacanciesCount() {
        return size - cars.size();
    }

    public BigDecimal getVacantRate() {
        if (size == 0) {
            return new BigDecimal("0");
        }

        return new BigDecimal(getVacanciesCount() * 1.0 / size).setScale(2, BigDecimal.ROUND_UP);
    }

    public int getSize() {
        return size;
    }

    public int getCarsCount() {
        return cars.size();
    }

    public String report(String indent) {
        return String.format("%sP %d %d", indent, cars.size(), size);
    }
}
