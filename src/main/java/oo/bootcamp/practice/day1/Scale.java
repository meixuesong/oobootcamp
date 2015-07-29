package oo.bootcamp.practice.day1;

import java.util.HashMap;

public class Scale {
    private static HashMap<Unit, Integer> scale = new HashMap<Unit,Integer>(){
        {
            put(Unit.CM, 1);
            put(Unit.DM, 10);
            put(Unit.M, 100);
        }
    };

    public static Integer getScale(Unit unit) {
        if(scale.containsKey(unit)){
            return scale.get(unit);
        } else {
            throw new UnitDismatchException();
        }
    }
}
