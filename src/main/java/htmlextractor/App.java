package htmlextractor;

import java.util.*;
import java.io.File;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class App 
{

    static String[] testUrls = {"https://iba-world.com/iba-cocktails/",
    "https://iba-world.com/new-era-drinks/",
    "https://iba-world.com/contemporary-classics/"};
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //System.out.println("enter the url to extract data from:");
        System.out.println("In testing mode");
        for (String testUrl : testUrls) {
            
        
        try (Scanner scan = new Scanner(System.in)){
            String s = "";
            //s = scan.nextLine();
            s = testUrl;
            System.out.println("Testing with Jsoup");
            Document doc = HtmlDownloader.toDocument(s);
            DocumentReader reader = new DocumentReader(doc);

        }catch (Exception e){
            e.printStackTrace();
        }
        }
        
        // try(Scanner scan = new Scanner(System.in)){ // old method
        //     String s = "";
        //     //s = scan.nextLine();

        //     s = testUrl; //toggle testmode

        //     File txtFile = HtmlDownloader.toText(s);
        //     // String txt = HtmlReader.readToString(txtFile);
        //     Map<Integer, Map<String,List<String>>> recipeMap = HtmlReader.crawlAndMap(txtFile);
        //     //if (recipeMap!= null) RecipeMapParser.makeRecipes(recipeMap);
    
        // }catch (Exception e){
        //     System.out.println(e);
        // }

        

    }
}
