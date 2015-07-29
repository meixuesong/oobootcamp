package oo.bootcamp.practice.day1;

/**
 * Created by Xuesong Mei on 15/7/28.
 */
public class Length implements Comparable<Length> {
    private String unit;
    private int length;

    public Length(String unit, int length) {
        this.unit = unit;
        this.length = length;
    }

    @Override
    public int compareTo(Length length) {
        if (!this.unit.equals(length.unit)) {
            throw new UnitDismatchException();
        }
        return ((Integer) this.length).compareTo(length.length);
    }
}
