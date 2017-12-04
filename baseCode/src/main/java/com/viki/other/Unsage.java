package com.viki.other;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/16 16:53
 */
public class Unsage {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InvocationTargetException {
        /*Field f = Unsafe.class.getDeclaredField("theUnsafe");

        f.setAccessible(true);
        Unsafe unsafe = (Unsafe)f.get(null);
        Player player = (Player) unsafe.allocateInstance(Player.class);
        System.out.println("age = " + player.getAge());
        player.setAge(100);
        System.out.println("age = " + player.getAge());*/

        Class clazz = Player.class;
        Constructor constructor = clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        Player player = (Player) constructor.newInstance();
        System.out.println(player.getAge());

    }

}

class Player {
    private int age = 12;

    public Player() {
        this.age = 50;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}