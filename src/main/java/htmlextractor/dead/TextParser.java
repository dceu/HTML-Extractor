package htmlextractor.dead;

import java.util.*;

public class TextParser{

    public void parseTxt(String line){
    int mode = 0;
    Map<Integer, Map<String, List<String>>> recipeMap = new HashMap<Integer, Map<String,List<String>>>();
    int recipeMapIndex = 0;
    switch (mode){
        case 0: 
            if (line.contains("<h3>")){                         // once found <h3>:
                System.out.println("found a recipe title!");
                int open = line.indexOf("<h3>" ) + 4;                // find index of </h3>
                int close = line.lastIndexOf("</h3>" ) -4;          // extract name of recipe
                System.out.println("Sub string endpoints: " + open +","+ close);
                System.out.println("Found recipe for:" + line.substring(open , close));                  
                List<String> recipeNames = new ArrayList<>();
            
                recipeNames.add(line.substring(open + 4, close));
                Map<String, List<String>> nameMap = new HashMap<String, List<String>>();   
                
                nameMap.put("name", recipeNames);                // map it to name field
                recipeMap.put(recipeMapIndex, nameMap);         // index it in master Map
                mode = 1;                                       // modulate switch 
                break;
            }
        case 1: 
            if (line.contains("<p>")){    // find <a image url />
                int open = line.indexOf("\"") + 1;    //
                int close = line.lastIndexOf("\"");     // extract contents from <p> tags to img field
                List<String> recipeImg = new ArrayList<>();
                recipeImg.add(line.substring(open, close));
                Map<String,List<String>> imgMap = new HashMap<String, List<String>>();
                imgMap.put("img", recipeImg);
                recipeMap.put(recipeMapIndex, imgMap);
                mode = 2;
                break;
            }
        case 2:
            if (line.contains("<p>")){
                int open = line.indexOf("<p>") + 3; 
                int close = line.lastIndexOf("</p>");
                List<String> recipeDes = new ArrayList<>();
                recipeDes.add(line.substring(open,close));
                Map<String,List<String>> descriptionMap = new HashMap<>();
                descriptionMap.put("description", recipeDes);
                recipeMap.put(recipeMapIndex, descriptionMap);
                mode = 3;
                break;
            }
        case 3:
            if (line.contains("</ul")){                 // find <ul> , // until </ul>:
                mode = 4;
                break;
            }
            if (line.contains("<li>")){                         // add every <li> as an ingredient
                int open = line.indexOf("<li>") + 4;
                int close = line.lastIndexOf("</li>");
                List<String> ingredient = new ArrayList<>();
                ingredient.add(line.substring(open,close));
                Map<String,List<String>> ingredientMap = new HashMap<>();
                ingredientMap.put("ingredients", ingredient);
                recipeMap.put(recipeMapIndex, ingredientMap);
                break; 
            }
        case 4:
            if (line.contains("<p>")){              // find <p> preparation
                int open = line.indexOf("<p>") + 3;
                int close = line.lastIndexOf("</p>");
                List<String> preparation = new ArrayList<>();
                preparation.add(line.substring(open, close));
                Map<String,List<String>> preparationMap = new HashMap<>();
                preparationMap.put("preparation", preparation);             // extract everything from <p> to preparation field
                recipeMap.put(recipeMapIndex, preparationMap);
                recipeMapIndex++;
                mode = 0;                   // find next index of <h3>
                break;

            }
    }               // end switch block
}

}