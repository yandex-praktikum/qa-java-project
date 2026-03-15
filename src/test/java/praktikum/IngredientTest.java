package praktikum;

import constants.TestMessages;
import data.ParamSource;
import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    private Ingredient ingredient;
    private String name;
    private float price;

    @BeforeEach
    void setUp() {
        TestData data = new TestData();
        name = data.getName();
        price = data.getPrice();
        ingredient = new Ingredient(IngredientType.SAUCE, name, price);
    }

    @Test
    void getPriceReturnsPrice() {
        assertEquals(price, ingredient.getPrice(), TestMessages.ERROR);
    }

    @Test
    void getNameReturnsName() {
        assertEquals(name, ingredient.getName(), TestMessages.ERROR);
    }

    @ParameterizedTest
    @MethodSource("data.ParamSource#ingredientTypes")
    void getTypeReturnsType(IngredientType type, Ingredient ing) {
        assertEquals(type, ing.getType(), TestMessages.ERROR);
    }
}