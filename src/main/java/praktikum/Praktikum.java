package praktikum;

import java.util.List;

public class Praktikum {

    public static void main(String[] args) {
        // Инициализируем базу данных
        Database database = new Database();

        // Создадим новый бургер
        Burger burger = new Burger();

        // Считаем список доступных булок из базы данных
        List<Bun> buns = database.availableBuns();

        // Считаем список доступных ингредиентов из базы данных
        List<Ingredient> ingredients = database.availableIngredients();

        // Соберём бургер
        burger.setBuns(buns.get(0));

        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(4));
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(5));

        // Переместим слой с ингредиентом
        burger.moveIngredient(2, 1);

        // Удалим ингредиент
        burger.removeIngredient(3);

        // Распечатаем рецепт бургера
        System.out.println(burger.getReceipt());
    }

}