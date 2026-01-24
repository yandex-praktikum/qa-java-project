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

        System.out.println("Демонстрация работы бургера:");
        System.out.println("============================");
        System.out.println("Цена бургера: " + burger.getPrice() + " руб.");
        System.out.println("\nЧек:");
        System.out.println(burger.getReceipt());

        System.out.println("\nДополнительные тесты:");
        System.out.println("=====================");

        Burger testBurger = new Burger();
        System.out.println("1. Бургер без булки:");
        System.out.println("Цена: " + testBurger.getPrice() + " руб.");
        System.out.println("Чек:\n" + testBurger.getReceipt());

        testBurger.setBuns(buns.get(1));
        System.out.println("\n2. Бургер только с булкой:");
        System.out.println("Цена: " + testBurger.getPrice() + " руб.");
        System.out.println("Чек:\n" + testBurger.getReceipt());

        testBurger.addIngredient(ingredients.get(0));
        testBurger.addIngredient(ingredients.get(3));
        System.out.println("\n3. Бургер с булкой и ингредиентами:");
        System.out.println("Цена: " + testBurger.getPrice() + " руб.");
        System.out.println("Чек:\n" + testBurger.getReceipt());
    }

}