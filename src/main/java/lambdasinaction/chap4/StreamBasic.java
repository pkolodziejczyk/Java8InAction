package lambdasinaction.chap4;

import java.util.*;
import java.util.stream.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import static lambdasinaction.chap4.Dish.menu;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
    	System.out.println("Using Java 7 for filtring then display a list with forEach Java 8");
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        System.out.println("Using Java 8 for filtring then display a list with forEach Java 8");
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
    	System.out.println("Java 7 : Filtering / remap (Old school)");
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() > 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
    	System.out.println("Java 8 : Filtering /remap Stream Java 8");
        return dishes.stream()
                .filter(d -> d.getCalories() > 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }
}
