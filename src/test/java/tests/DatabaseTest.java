package tests;

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

public class DatabaseTest {

    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    @Test
    @DisplayName("availableBuns возвращает список булочек")
    void shouldReturnBunsList() {
        List<Bun> buns = database.availableBuns();
        assertNotNull(buns);
        assertEquals(3, buns.size());

        Bun blackBun = buns.get(0);
        assertEquals("black bun", blackBun.getName());
        assertEquals(100.0f, blackBun.getPrice());
    }

    @Test
    @DisplayName("availableIngredients возвращает список ингредиентов")
    void shouldReturnIngredientsList() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertNotNull(ingredients);
        assertEquals(6, ingredients.size());

        Ingredient hotSauce = ingredients.get(0);
        assertEquals(IngredientType.SAUCE, hotSauce.getType());
        assertEquals("hot sauce", hotSauce.getName());
        assertEquals(100.0f, hotSauce.getPrice());

        Ingredient cutlet = ingredients.get(3);
        assertEquals(IngredientType.FILLING, cutlet.getType());
        assertEquals("cutlet", cutlet.getName());
        assertEquals(100.0f, cutlet.getPrice());
    }
}
