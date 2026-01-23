package utils;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;
import java.util.List;
import java.util.Random;
public class Utils {
    // Генерация случайной булочки из Database
    public static Bun getRandomBun() {
        Database database = new Database();
        Random random = new Random();
        List<Bun> buns = database.availableBuns();
        return buns.get(random.nextInt(buns.size()));
    }

    // Генерация случайного ингредиента из Database
    public static Ingredient getRandomIngredient() {
        Database database = new Database();
        Random random = new Random();
        List<Ingredient> ingredients = database.availableIngredients();
        return ingredients.get(random.nextInt(ingredients.size()));
    }
}