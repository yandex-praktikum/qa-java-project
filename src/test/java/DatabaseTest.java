import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import praktikum.Bun;
import praktikum.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование класса Database")
class DatabaseTest {

    @Test
    @DisplayName("Проверка списка доступных булочек")
    @Description("Метод availableBuns() должен возвращать список из трёх булочек с корректными именами и ценами")
    void testAvailableBuns() {
        Database db = new Database();
        List<Bun> buns = db.availableBuns();

        assertEquals(3, buns.size(), "Должно быть три булочки в базе данных");

        // Проверяем содержимое
        assertEquals("black bun", buns.get(0).getName());
        assertEquals(100, buns.get(0).getPrice(), 0.001);

        assertEquals("white bun", buns.get(1).getName());
        assertEquals(200, buns.get(1).getPrice(), 0.001);

        assertEquals("red bun", buns.get(2).getName());
        assertEquals(300, buns.get(2).getPrice(), 0.001);
    }

    @Test
    @DisplayName("Проверка списка доступных ингредиентов")
    @Description("Метод availableIngredients() должен возвращать список из шести ингредиентов с корректными именами, типами и ценами")
    void testAvailableIngredients() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();

        assertEquals(6, ingredients.size(), "Должно быть шесть ингредиентов в базе данных");

        // Проверяем несколько элементов
        assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
        assertEquals("hot sauce", ingredients.get(0).getName());
        assertEquals(100, ingredients.get(0).getPrice(), 0.001);

        assertEquals(IngredientType.FILLING, ingredients.get(3).getType());
        assertEquals("cutlet", ingredients.get(3).getName());
        assertEquals(100, ingredients.get(3).getPrice(), 0.001);
    }
}
