package com.viki.other.arithmetic.bubbleSort;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/1/22 17:45
 */
public class SubClass {

    int age;

    String name;

    public SubClass(int age, String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "age:"+age + "\t name:"+ name + "\n";
    }
}
