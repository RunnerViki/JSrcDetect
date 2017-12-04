package com.viki.other;

/**
 * Created by Viki on 2017/5/22.
 * Function: TODO
 */
public class AccessModifier {

    private int x = 10;

    private void t(AccessModifier accessModifier){
        System.out.println(this.x - accessModifier.x);
    }
}
