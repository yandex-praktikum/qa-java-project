package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    Ingredient ingredient;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f);
    }

    @Test
    void getPriceTest() {
        assertEquals(100.0f, ingredient.getPrice());
    }

    @Test
    void getNameTest() {
        assertEquals("hot sauce", ingredient.getName());
    }

    @Test
    void getTypeTest() {
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}