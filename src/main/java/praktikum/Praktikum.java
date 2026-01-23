package praktikum;
import java.util.List;
public class Praktikum {
    public static void main(String[] args) {
        // Инициализация базы данных
        Database database = new Database();
        // Создание нового бургера
        Burger burger = new Burger();
        // количество доступных бургеров из backAnd
        List<Bun> buns = database.availableBuns();
        // Количество доступных ингридиентов из бекенда
        List<Ingredient> ingredients = database.availableIngredients();
        // создание бургера
        burger.setBuns(buns.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(4));
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(5));
        // смещение слоя ингридиентов
        burger.moveIngredient(2, 1);
        // удаление ингридиента
        burger.removeIngredient(3);

        // печать состава бургера
        System.out.println(burger.getReceipt());
    }
}