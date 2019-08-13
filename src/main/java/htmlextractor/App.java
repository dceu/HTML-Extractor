package htmlextractor;

import java.util.*;
import java.io.File;
public class App 
{

    static String testUrl = "https://iba-world.com/iba-cocktails/";
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //System.out.println("enter the url to extract data from:");
        System.out.println("In testing mode");
        
        try(Scanner scan = new Scanner(System.in)){
            String s = "";
            //s = scan.nextLine();

            s = testUrl; //toggle testmode

            File txtFile = HtmlDownloader.toText(s);
            // String txt = HtmlReader.readToString(txtFile);
            Map<Integer, Map<String,List<String>>> recipeMap = HtmlReader.crawlAndMap(txtFile);
            //if (recipeMap!= null) RecipeMapParser.makeRecipes(recipeMap);
    
        }catch (Exception e){
            System.out.println(e);
        }

        

    }
}
