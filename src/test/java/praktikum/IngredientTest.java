package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    private static final IngredientType TYPE = IngredientType.FILLING;
    private static final String NAME = "cutlet";
    private static final float PRICE = 100f;

    @Test
    void constructor_shouldSetType() {
        Ingredient ingredient = new Ingredient(TYPE, NAME, PRICE);

        assertEquals(TYPE, ingredient.getType());
    }

    @Test
    void constructor_shouldSetName() {
        Ingredient ingredient = new Ingredient(TYPE, NAME, PRICE);

        assertEquals(NAME, ingredient.getName());
    }

    @Test
    void constructor_shouldSetPrice() {
        Ingredient ingredient = new Ingredient(TYPE, NAME, PRICE);

        assertEquals(PRICE, ingredient.getPrice());
    }
}