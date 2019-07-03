package com.java8.study.chap3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambdas {

    public static void main(String[] args) {

        int num = 23;

        Runnable r = () -> System.out.println(num);

        Thread t = new Thread(r);
        t.start();

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));
        inventory.sort((a1, a2) -> a2.getWeight() - a1.getWeight());
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }

}

@Data
@AllArgsConstructor
@ToString
class Apple {
    private int weight = 0;
    private String color;

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

}

interface ApplePredicate<T> {
    boolean test(T t);
}
