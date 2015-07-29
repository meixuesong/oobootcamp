package oo.bootcamp.practice.unittest.day1;

import oo.bootcamp.practice.day1.Length;
import oo.bootcamp.practice.day1.UnitDismatchException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Xuesong Mei on 15/7/28.
 */
public class LengthTest {
    @Test
    public void ten_cm_should_be_greater_than_five_cm() {
        Length tenCM = new Length("cm", 10);
        Length fiveCM = new Length("cm", 5);

        assertTrue(tenCM.compareTo(fiveCM) > 0);
    }

    @Test
    public void zero_dm_should_be_less_than_five_dm() {
        Length zero = new Length("dm", 0);
        Length five = new Length("dm", 5);

        assertTrue(zero.compareTo(five) < 0);
    }

    @Test
    public void ten_Meters_should_be_equal_to_ten_Meters() {
        Length tenMeters = new Length("m", 10);
        Length tenMeters2 = new Length("m", 10);

        assertTrue(tenMeters.compareTo(tenMeters2) == 0);
    }

    @Test(expected = UnitDismatchException.class)
    public void should_return_error_compare_10_meter_to_5_cm() {
        Length tenMeters = new Length("m", 10);
        Length fiveCentimeters = new Length("cm", 5);

        assertTrue(tenMeters.compareTo(fiveCentimeters) > 0);
    }
}
