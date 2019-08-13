package htmlextractor;

import java.util.*;

public class Recipe{
    String name= "";
    String img = "";
    String shortDes = "";
    List<String> ingredients;
    //List<String> ingredients = new ArrayList<>();
    String preparation = "";

    List<String> altNames;
    List<String> altImgs;
    List<String> altDes;
    List<String> altPrep;
 

    Recipe(){}

    Recipe(String s){
       this.setName(s);
    }

    Recipe(Map<String, List<String>> m){
        List<String> nameList = m. get("name");
        List<String> imgList = m.get("img");
        List<String> ingredList = m.get("ingredients");
        List<String> description = m.get("description");
        List<String> prep = m.get("preparation");
        this.setName(nameList.get(0));
            if(nameList.size()>1){
                setAltNames(nameList.subList(1, nameList.size()-1));
            }
        this.setImg(imgList.get(0));
            if(imgList.size()>1){
                setAltImgs(imgList.subList(1, imgList.size()-1));
            }
        this.setShortDes(description.get(0));
            if(description.size()>1){
                setAltDes(description.subList(1, description.size()-1));
            }
        this.setIngredients(ingredList);
        this.setPreparation(prep.get(0));
            if (prep.size()>1){
                setAltPrep(prep.subList(1, prep.size()-1));
            }
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

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String s){
        this.ingredients.add(s);
    }

    public String getPreparation() {
        return this.preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    
    public List<String> getAltNames() {
        return this.altNames;
    }

    public void setAltNames(List<String> altNames) {
        this.altNames = altNames;
    }

    public List<String> getAltImgs() {
        return this.altImgs;
    }

    public void setAltImgs(List<String> altImgs) {
        this.altImgs = altImgs;
    }

    public List<String> getAltDes() {
        return this.altDes;
    }

    public void setAltDes(List<String> altDes) {
        this.altDes = altDes;
    }

    public List<String> getAltPrep() {
        return this.altPrep;
    }

    public void setAltPrep(List<String> altPrep) {
        this.altPrep = altPrep;
    }


    @Override
    public String toString() {
        return "*****\n" +
            " name='" + name + "\n" +
            ", img='" + img + "\n" +
            ", shortDes='" + shortDes + "\n" +
            ", ingredients='" + ingredients + "\n" +
            ", preparation='" + preparation + "\n" +
            ", altNames='" + altNames + "\n" +
            ", altImgs='" + altImgs + "\n" +
            ", altDes='" + altDes + "\n" +
            ", altPrep='" + altPrep + "\n" +
            "*****\n";
    }


}