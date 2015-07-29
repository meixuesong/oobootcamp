package oo.bootcamp.practice.day1;

/**
 * Created by Xuesong Mei on 15/7/28.
 */
public class Length implements Comparable<Length> {
    private Unit unit;
    private int length;

    public Length(Unit unit, int length) {
        this.unit = unit;
        this.length = length;
    }

    public Integer getLength(){
        return this.length * Scale.getScale(this.unit);
    }

    @Override
    public int compareTo(Length length) {
        if (this.unit.equals(length.unit)) {
            return ((Integer) this.length).compareTo(length.length);
        } else {
            return this.getLength().compareTo(length.getLength());
        }
    }
}
