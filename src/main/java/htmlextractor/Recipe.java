package htmlextractor;

import java.util.*;

public class Recipe{
    String name= "";
    String img = "";
    String shortDes = "";
    String ingredients = "";
    //List<String> ingredients = new ArrayList<>();
    String preparation = "";
 

    Recipe(){}

    Recipe(String s){
       this.setName(s);
    }

    Recipe(Map<String,String> m){
        this.setName(m.get("name"));
        this.setImg(m.get("img"));
        this.setShortDes(m.get("description"));
        this.setIngredients(m.get("ingredients"));
        this.setPreparation(m.get("preparation"));
    }

    //* Setters and Getters *//

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDes() {
        return this.shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return this.preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

}