package htmlextractor.dead;



import java.io.*;
import java.util.*;



public class HtmlReader{

HtmlReader(){};





// static public String readToString(File f){ // take txt file and convert to String
//     StringBuilder contentBuilder = new StringBuilder();

//     try(BufferedReader in = new BufferedReader(new FileReader(f))){
//         String str = new String();
//         while(in.readLine()!= null){
//             //System.out.println("Building content from file");
//             str = in.readLine();
//             contentBuilder.append(str +"\n");
//            // System.out.println("appending..." + str);
//         }
//         return contentBuilder.toString();
       

//     } catch (IOException e){
//         System.out.println(e);
//         return contentBuilder.toString();
//     }
    
//     //return contentBuilder.toString();
// }


public static Map<Integer, Map<String,List<String>>> crawlAndMap(File f){     // crawl over string and map recipes

    Map<Integer, Map<String, List<String>>> recipeMap =
    new HashMap<Integer, Map<String,List<String>>>(); //TODO: refactor to a RecipeMap object

    // try(BufferedReader testIn = new BufferedReader(new FileReader(f))){  // TODO: export to test
    //     int testLineCount= 0;
    //     while(testIn.readLine()!=null){
    //         String line = testIn.readLine();
    //         testLineCount ++;
    //     }
    //     System.out.println("read " + testLineCount + " lines");
    // }catch (Exception e){
    //     System.out.println(e.getMessage());
    // }
    long lineCounter = 0;
    int mode = 0 ;     // mode of nested switch that modulates after specific step has been completed
    try(BufferedReader in = new BufferedReader(new FileReader(f))){
            System.out.println("cralwing over txtfile");
            int recipeMapIndex = 0;
           

            while(in.readLine()!= null) {
                
                String line = in.readLine();
                lineCounter++;   
               // System.out.println("line"+ lineCounter+ ": " + line);
      
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
                        if (line.contains("<img>")){    // find <a image url />
                            int open = line.indexOf("<img>") + 1;    //
                            int close = line.lastIndexOf("/>");     // extract contents from <p> tags to img field
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
            
            }               // end while loop
            int recipeCount = recipeMapIndex + 1;
            System.out.println("mapped: " +lineCounter + " lines to " + recipeCount + " recipes!");
            return recipeMap;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("parsing error at line:" + lineCounter);
            System.out.println(e.getLocalizedMessage());
            System.out.println("Switch in mode: "+ mode);
        }
    return recipeMap;
    
    } 

}



    
