package ru.javawebinar.topjava;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    private static int id = 0;
    private static synchronized int generateID(){
        return ++id;
    }
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");
        System.out.println(generateID());
        System.out.println(generateID());
        System.out.println(generateID());
    }
}
