package com.zsb.dynamicproxy;

public class test {
    public static void main(String[] args) {
        MyInterface test = (MyInterface)ProxyFactory.invoke();
        System.out.println(test.say("te"));
        System.out.println(test.change(78));
    }
}
