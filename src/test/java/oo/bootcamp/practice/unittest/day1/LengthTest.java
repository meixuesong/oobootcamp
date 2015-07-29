package oo.bootcamp.practice.unittest.day1;

import oo.bootcamp.practice.day1.Length;
import oo.bootcamp.practice.day1.Unit;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Xuesong Mei on 15/7/28.
 */
public class LengthTest {
    @Test
    public void ten_cm_should_be_greater_than_five_cm() {
        Length tenCM = new Length(Unit.CM, 10);
        Length fiveCM = new Length(Unit.CM, 5);

        assertTrue(tenCM.compareTo(fiveCM) > 0);
    }

    @Test
    public void zero_cm_should_be_less_than_five_cm() {
        Length zero = new Length(Unit.CM, 0);
        Length five = new Length(Unit.CM, 5);

        assertTrue(zero.compareTo(five) < 0);
    }

    @Test
    public void ten_cm_should_be_equal_to_ten_cm() {
        Length tenMeters = new Length(Unit.CM, 10);
        Length tenMeters2 = new Length(Unit.CM, 10);

        assertTrue(tenMeters.compareTo(tenMeters2) == 0);
    }

    @Test
    public void ten_cm_should_be_less_than_five_dm() {
        Length tenCM = new Length(Unit.CM, 10);
        Length fiveDM= new Length(Unit.DM, 5);

        assertTrue(tenCM.compareTo(fiveDM) < 0);
    }

    @Test
    public void ten_dm_should_be_equal_to_one_meter() {
        Length tenDM = new Length(Unit.DM, 10);
        Length oneM= new Length(Unit.M, 1);

        assertTrue(tenDM.compareTo(oneM) == 0);
    }

    @Test
    public void twenty_cm_should_be_greater_than_one_dm() {
        Length twentyCM = new Length(Unit.CM, 20);
        Length oneDM= new Length(Unit.DM, 1);

        assertTrue(twentyCM.compareTo(oneDM) > 0);
    }
}
