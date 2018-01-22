package com.viki.other.arithmetic.bubbleSort;

import java.util.Comparator;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/1/22 17:43
 */
public class SubCompare implements Comparator<SubClass> {

    @Override
    public int compare(SubClass o1, SubClass o2) {
        return o1.age - o2.age;
    }
}
