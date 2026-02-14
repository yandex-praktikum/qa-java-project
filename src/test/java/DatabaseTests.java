import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseTests {

    private Database database;

    @BeforeEach
    @DisplayName("Создание объекта класса Database")
    void setUp() {
        database = new Database();
    }

    @Test
    @DisplayName("Метод availableBuns() должен возвращать не-null список")
    void availableBunsShouldReturnNonNullList() {
        List<Bun> buns = database.availableBuns();
        assertNotNull(buns, "Список булок не должен быть null");
    }

    @Test
    @DisplayName("Метод availableBuns() должен возвращать булки с правильными названиями и ценами")
    void availableBunsShouldReturnBunsWithCorrectNamesAndPrices() {
        List<Bun> buns = database.availableBuns();

        Bun blackBun = buns.get(0);
        assertEquals("black bun", blackBun.getName());
        assertEquals(100, blackBun.getPrice());

        Bun whiteBun = buns.get(1);
        assertEquals("white bun", whiteBun.getName());
        assertEquals(200, whiteBun.getPrice());

        Bun redBun = buns.get(2);
        assertEquals("red bun", redBun.getName());
        assertEquals(300, redBun.getPrice());
    }

    @Test
    @DisplayName("Метод availableIngredients() должен возвращать не-null список")
    void availableIngredientsShouldReturnNonNullList() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertNotNull(ingredients, "Список ингредиентов не должен быть null");
    }

    @Test
    @DisplayName("Метод availableIngredients() должен возвращать ингредиенты с правильными типами, названиями и ценами")
    void availableIngredientsShouldHaveCorrectProperties() {
        List<Ingredient> ingredients = database.availableIngredients();

        assertIngredient(ingredients.get(0), IngredientType.SAUCE, "hot sauce", 100);
        assertIngredient(ingredients.get(1), IngredientType.SAUCE, "sour cream", 200);
        assertIngredient(ingredients.get(2), IngredientType.SAUCE, "chili sauce", 300);

        assertIngredient(ingredients.get(3), IngredientType.FILLING, "cutlet", 100);
        assertIngredient(ingredients.get(4), IngredientType.FILLING, "dinosaur", 200);
        assertIngredient(ingredients.get(5), IngredientType.FILLING, "sausage", 300);
    }

    private void assertIngredient(Ingredient ingredient, IngredientType expectedType, String expectedName, float expectedPrice) {
        assertEquals(expectedType, ingredient.getType());
        assertEquals(expectedName, ingredient.getName());
        assertEquals(expectedPrice, ingredient.getPrice());
    }
}

