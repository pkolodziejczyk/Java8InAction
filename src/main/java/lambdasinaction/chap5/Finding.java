package lambdasinaction.chap5;
import lambdasinaction.chap4.*;

import java.util.stream.*;
import java.util.*;

import static lambdasinaction.chap4.Dish.menu;

public class Finding{

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }
    
    private static boolean isVegetarianFriendlyMenu(){
    	System.out.println("Checking if at least one of the stream match a Predicate");
        return menu.stream().anyMatch(Dish::isVegetarian);
    }
    
    private static boolean isHealthyMenu(){
    	System.out.println("Checking if all of the stream match a Predicate");
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }
    
    private static boolean isHealthyMenu2(){
    	System.out.println("Checking if none of the stream match a Predicate");
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }
    
    private static Optional<Dish> findVegetarianDish(){
    	System.out.println("Using findAny to return an element of the stream (no matter witch one)");
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
    
}
