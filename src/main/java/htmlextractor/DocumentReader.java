package htmlextractor;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;
import java.util.*;

public class DocumentReader{
    String titleSel = "h3:first-child";
    String imgSel = "";
    String ingredSel = "";
    String descripSel = "";
    String prepSel = "";
    SqlOut out = new SqlOut();

    DocumentReader(Document d){
        String main = d.select("h1").first().text();
        String main2 = main.replaceAll(" ","_").toLowerCase();
        out.createRecipeTable(main2);

        Map<Integer, Recipe> recipeMap = new HashMap<Integer,Recipe>();
        int mapIndex = 0;
        Element body = d.body();
        body.selectFirst("footer").remove();
        Elements titles = body.select(titleSel);
        for (Element title : titles) {
            System.out.println("\n"+title.text());
            Recipe r = new Recipe(title.text());
            Elements contents = title.nextElementSiblings(); //make Recipes from Elements
            if(contents.select("p>a").first()!=null){
                String imgString = contents.select("p > a").first().attributes().html(); // img link
                r.setImg(imgString);
            }else {
                System.out.println("");
            }
            r.setShortDes(contents.select("p + p").text()); // description
            Elements ingredients = contents.select("li");
            for (Element ingredient : ingredients) {
                r.addIngredient(ingredient.text());
            }
            System.out.println(ingredients.text()); // ingredients
            System.out.println(contents.select("p:last-child").text()); // prep
            String prep = contents.select("p:last-child").text();
            r.setPreparation(prep);
            recipeMap.put(mapIndex, r);
            out.appendRecipe(r);
            mapIndex ++;

            System.out.println("***** \n");

                        
        }



    }
}