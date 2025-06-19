import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTests {
    Ingredient ingredient;

    private static final float EXPECTED_PRICE = 10.5f;
    private static final String EXPECTED_NAME = "Говяжий метеорит";

    @Test
    public void getPricePositiveTest() {
        ingredient = new Ingredient(null, "", EXPECTED_PRICE);
        float actualPrice = ingredient.getPrice();
        assertEquals(EXPECTED_PRICE, actualPrice,
                String.format("Ingredient's price was expected to be: %f, but actually was: %f", EXPECTED_PRICE, actualPrice));
    }

    @Test
    public void getNamePositiveTest() {
        ingredient = new Ingredient(null, EXPECTED_NAME, 0);
        String actualName = ingredient.getName();
        assertEquals(EXPECTED_NAME, actualName,
                String.format("Ingredient's name was expected to be: %s, but actually was: %s", EXPECTED_NAME, actualName));
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    public void getNamePositiveTest(IngredientType expectedIngredientType) {
        ingredient = new Ingredient(expectedIngredientType, "", 0);
        IngredientType actualType = ingredient.getType();
        assertEquals(expectedIngredientType, actualType,
                String.format("Ingredient's type was expected to be: %s, but actually was: %s", expectedIngredientType.toString(), actualType.toString()));
    }
}
