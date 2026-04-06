package praktikum;

import java.util.List;

public class Praktikum {

    public static void main(String[] args) {

        Database database = new Database();


        Burger burger = new Burger();


        List<Bun> buns = database.availableBuns();


        List<Ingredient> ingredients = database.availableIngredients();


        burger.setBuns(buns.get(0));

        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(4));
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(5));


        burger.moveIngredient(2, 1);


        burger.removeIngredient(3);


        System.out.println(burger.getReceipt());
    }

}