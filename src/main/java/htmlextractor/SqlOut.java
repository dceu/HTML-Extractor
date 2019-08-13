package htmlextractor;

import java.util.*;


public class SqlOut{
    SqlOut(){}

    private String tableName = "";
    private String createTable = "CREATE TABLE" + tableName +
        "(RecipeId int PRIMARY KEY" + "," +
        "img varchar(255)" + "," +
        "description varchar(255)" + "," +
        "ingredients varchar(255)" + "," +
        "preparation" +
        ")"
        ; 

    

    public void setTableName(String s){
        this.tableName = s;
    }

    public String getTableName(){
        return this.tableName;
    }
}