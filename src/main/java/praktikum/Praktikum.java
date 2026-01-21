package praktikum;

import java.util.List;

public class Praktikum {

    public static void main(String[] args) {
        // Инициализия базы данных
        Database database = new Database();

        // Создание нового бургера
        Burger burger = new Burger();

        // Подсчет списка доступных булок из базы данных
        List<Bun> buns = database.availableBuns();

        // Подсчет списка доступных ингредиентов из базы данных
        List<Ingredient> ingredients = database.availableIngredients();

        // Сборка заказа бургера
        burger.setBuns(buns.get(0));

        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(4));
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(5));

        // Перемещение слоя с ингредиентом
        burger.moveIngredient(2, 1);

        // Удаление ингредиента
        burger.removeIngredient(3);

        // Распечатка рецепта бургера
        System.out.println(burger.getReceipt());
    }

}