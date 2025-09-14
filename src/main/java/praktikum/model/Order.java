package praktikum.model;

import java.util.List;

public class Order {
    private List<String> ingredients;


    public Order setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }





    public List<String> getIngredients() {return ingredients;}


}
