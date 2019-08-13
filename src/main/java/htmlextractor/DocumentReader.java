package htmlextractor;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;

public class DocumentReader{
    String titleSel = "h3:first-child";
    String imgSel = "";
    String ingredSel = "";
    String descripSel = "";
    String prepSel = "";

    DocumentReader(Document d){
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
            for (Element ingredient : contents.select("li")) {
                r.addIngredient(ingredient.text());
            }
            System.out.println(contents.select("li").text()); // ingredients
            System.out.println(contents.select("p:last-child").text()); // prep
            String prep = contents.select("p:last-child").text();
            r.setPreparation(prep);
            System.out.println("***** \n");
                        
        }



    }
}