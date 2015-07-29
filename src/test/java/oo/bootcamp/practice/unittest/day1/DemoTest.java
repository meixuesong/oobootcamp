package oo.bootcamp.practice.unittest.day1;

import oo.bootcamp.practice.day1.Demo;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Xuesong Mei on 15/7/28.
 */
public class DemoTest {
    @Test
    public void test() {
        Demo demo = new Demo();
        assertTrue(demo.isTrue());
    }
}
