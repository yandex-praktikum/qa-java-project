package praktikum;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс с методами по работе с базой данных.
 */
public class Database {

    private final List<Bun> buns = new ArrayList<>();
    private final List<Ingredient> ingredients = new ArrayList<>();

    public Database() {
        buns.add(new Bun("Флюоресцентная булка R2-D3", 988));
        buns.add(new Bun("Краторная булка N-200i", 1255));

        ingredients.add(new Ingredient(IngredientType.SAUCE, "Соус фирменный Space Sauce", 80));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "Соус Spicy-X", 90));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "Соус традиционный галактический", 15));

        ingredients.add(new Ingredient(IngredientType.FILLING, "Мясо бессмертных моллюсков Protostomia", 1337));
        ingredients.add(new Ingredient(IngredientType.FILLING, "Плоды Фалленианского дерева", 874));
        ingredients.add(new Ingredient(IngredientType.FILLING, "Хрустящие минеральные кольца", 300));
    }

    public List<Bun> availableBuns() {
        return buns;
    }

    public List<Ingredient> availableIngredients() {
        return ingredients;
    }

}