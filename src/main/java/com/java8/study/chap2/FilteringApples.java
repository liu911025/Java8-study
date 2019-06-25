package com.java8.study.chap2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples{

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));
        List<Apple> greenApples = filterApples(inventory, (apple) -> "red".equals(apple.getColor()));
        System.out.println(greenApples);

        List<Apple> heavyApples = filterApples(inventory, Apple::isHeavyApple);
        System.out.println(heavyApples);
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                apples.add(apple);
            }
        }
        return apples;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate<Apple> p) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    @Data
    @AllArgsConstructor
    @ToString
    public static class Apple {
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
}
