package com.code.kai.leetcode.dojo;


/**
 * @author Mohan Sharma
 */
public class Practice {

    public static void main(String[] args) {
        Parent p = new Parent();
        Parent c = new Child();
        System.out.println(p.greet(1));
        //System.out.println(c.greet());
    }
}

 class Parent {
    public static String greet(long a) {
        return "This is the parent speaking";
    }
}
class Child extends Parent {
    public static String greet() {
        return "This is the child speaking";
    }
}