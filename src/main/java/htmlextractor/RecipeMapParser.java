package htmlextractor;

import java.util.*;

public class RecipeMapParser{
    RecipeMap recipeMap = new RecipeMap();
    

    public static void makeRecipes(Map<Integer, Map<String, List<String>>> map){
        System.out.println("Making recipes from " + map.size() +"-size map!");
        for (int i: map.keySet()){
            Recipe r = new Recipe(map.get(i));
            System.out.println(r.toString());
        }
    }



}