import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.*;

public class IngredientTest {

    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient(SAUCE, "Кетчуп", 50.2f);
    }

    @Test
    void getPriceShouldReturnPrice() {
        assertEquals(50.2f, ingredient.getPrice());
    }

    @Test
    void getNameShouldReturnName() {
        assertEquals("Кетчуп", ingredient.getName());
    }

    @Test
    void getTypeShouldReturnIngredientType() {
        assertEquals(SAUCE, ingredient.getType());
    }
}
