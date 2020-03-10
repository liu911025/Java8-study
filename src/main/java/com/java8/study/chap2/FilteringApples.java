package com.java8.study.chap2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteringApples{

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));

        /**
         * 传递类(策略)
         */
        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println("greenApples: " + greenApples);

        /**
         * 匿名类
         */
        List<Apple> blueApples = filterApples(inventory, (apple) -> {
                System.out.println("蓝苹果");
                return "blue".equalsIgnoreCase(apple.getColor());
        });
        System.out.println(blueApples);

        /**
         * lambda
         */
        List<Apple> redApples = filterApples(inventory, (Apple apple) -> { return "red".equals(apple.getColor());});
        System.out.println("redApples: " + redApples);

        List<Apple> heavyApples = filterApples(inventory, Apple::isHeavyApple);
        System.out.println("heavyApples: " + heavyApples);

        List<Apple> greenApples2 = inventory.stream().filter((Apple a) -> "green".equals(a.getColor())).collect(Collectors.toList());
        System.out.println("greenApples2: " + greenApples2);
        System.out.println("-------------------------------------------------");
        // 分组
        Map<String, List<Apple>> map = inventory.stream().collect(Collectors.groupingBy(Apple::getColor));

        System.out.println(map);

        List<String> map1 = map(inventory, (apple) -> apple.getColor());

    }

    /**
     * 挑选绿色苹果
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equalsIgnoreCase(apple.getColor())) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 根据颜色挑选苹果
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    public static List<Apple> filterApples(List<Apple> inventory, String color,
                                           int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ( (flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight) ){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 根据重量挑选苹果
     * @param inventory
     * @param weight
     * @return
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 根据条件挑选
     * @param inventory
     * @param p
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate<Apple> p) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 方法抽象泛型
     * @param list
     * @param p
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
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
/**
 * 选择标准(谓语)
 * @param <T>
 */
interface ApplePredicate<T> {
    boolean test(T t);
}

/**
 * 重量
 */
class AppleHeavyWeightPredicate implements ApplePredicate<Apple> {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

/**
 * 绿色
 */
class AppleGreenColorPredicate implements ApplePredicate<Apple>{
    public boolean test(Apple apple){
        return "green".equals(apple.getColor());
    }
}
