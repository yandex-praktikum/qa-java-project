import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.*;
import java.util.List;

 class DatabaseTest {
    private Database db;
    @BeforeEach
    void setUp() {
        db = new Database();
    }
    @Test
    void availableBunsShouldReturnThreeBuns() {
        List<Bun> buns = db.availableBuns();
        assertEquals(3, buns.size());
        // проверяем первый black bun
        assertEquals("black bun", buns.get(0).getName());
        assertEquals(100f, buns.get(0).getPrice());
    }
    @Test
    void availableIngredientsShouldReturnSixIngredients() {
        List<Ingredient> ingredients = db.availableIngredients();
        assertEquals(6, ingredients.size());
        // 3 соуса + 3 начинки
        long sauces = ingredients.stream()
                .map(Ingredient::getType)
                .filter(type -> type == IngredientType.SAUCE)
                .count();
        assertEquals(3, sauces);
    }

}
