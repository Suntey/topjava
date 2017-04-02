package ru.javawebinar.topjava;

import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");
//        System.out.println(MealsUtil.getSortedByDateAndTime(MealsUtil.MEALS));
        System.out.println(new InMemoryMealRepositoryImpl().repository.row(1));
//        System.out.println(new InMemoryMealRepositoryImpl().getAll(1));

    }
}
