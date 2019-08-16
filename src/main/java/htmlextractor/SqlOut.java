package htmlextractor;

import java.io.*;
import java.util.*;


public class SqlOut{
    static String path = System.getProperty("user.home") + File.separator + "Generated-SQL-Scripts" ; 
    
    SqlOut(){

        File outDir = new File(path);
        if (outDir.exists()) {
            System.out.println(outDir + " already exists");
        } else if (outDir.mkdirs()) {
            System.out.println(outDir + " was created");
        } else {
            System.out.println(outDir + " was not created");
        }
        
        

    }


    private String recipeTableName = new String();
    public String getRecipeTableName() {
        return this.recipeTableName;
    }
    public void setRecipeTableName(String recipeTableName){
        this.recipeTableName = recipeTableName;
    }
    
    public String createRecipeTableString() { 
        return  "CREATE TABLE " + getRecipeTableName() +
        " (RecipeId int PRIMARY KEY NOT NULL AUTO_INCREMENT" + "," +
        "name varchar(255)" + "," +
        "img varchar(255)" + "," +
        "description varchar(900)" + "," +
        "ingredients varchar(255)" + "," +
        "preparation varchar(900)" +
        ");"
        ; 
}


    public void appendRecipe(Recipe r ) {

     String query =  "INSERT INTO " + " " + this.recipeTableName + " " +
        "(" + "name, img, description, ingredients, preparation" + ")" +
        "VALUES " + "(" +
        // r.getId()+ "," +
         "\'" + r.getName() + "\'" + "," +
        "\'" + r.getImg() + "\'" + "," +
        "\'" + r.getShortDes() + "\'" + "," +
        "\'" + r.getIngredients() + "\'" + ","+
        "\'" + r.getPreparation() +"\'" + 
         ");";

        try {
            sqlBufferedWriter(query);
            System.out.println("appended " + r.getName() + " to " + this.recipeTableName);
        } catch (Exception e) {
            e.getCause();
        }
    }

    public void createRecipeTable(String s){
        setRecipeTableName(s);
        try {
            sqlBufferedWriter(createRecipeTableString());
            System.out.println("created "+ s +" Table");
        }catch (Exception e){
            e.getCause();
        }
    }

    public static void sqlBufferedWriter(String s) throws IOException {
        String content = s;
        File sqlTable = new File(path + File.separator + "RecipeTable.sql");
        if (sqlTable.exists()){
            BufferedWriter writer = new BufferedWriter(new FileWriter(sqlTable, true));
            writer.newLine();
            writer.write(content);
            writer.close();
        } else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(sqlTable, false));
            writer.newLine();
            writer.write(content);
            writer.close();
            System.out.println(sqlTable + " made");
        }

        

    }

    
}