import org.junit.Before;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.List;
import java.util.Random;

public class BaseIUTest {
    private List<Bun> allBuns;
    private List<Ingredient> allIngredients;
    protected Bun selectedBun;
    protected Ingredient selectedIngredient;

    @Before
public void getBunsAndIngredient() {
        Database database = new Database();
//    получаем доступные булочки и ингредиенты
        allBuns = database.availableBuns();
        allIngredients = database.availableIngredients();

//    выбираем случайную булочку и случайный ингредиент
        Random random = new Random();
        selectedBun = allBuns.get(random.nextInt(allBuns.size()));
        selectedIngredient = allIngredients.get(random.nextInt(allIngredients.size()));
    }

//     Геттеры для списков булочек и ингредиентов
    protected List<Bun> getAllBuns() {
        return allBuns;
    }

    protected List<Ingredient> getAllIngredients() {
        return allIngredients;
    }
}
