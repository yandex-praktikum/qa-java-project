package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    @ParameterizedTest
    @CsvSource({
            "SAUCE, hot sauce, 100.0",
            "FILLING, cutlet, 250.5"
    })
    void getTypeReturnsExpectedType(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest
    @CsvSource({
            "SAUCE, sour cream, 200.0",
            "FILLING, dinosaur, 300.0"
    })
    void getNameReturnsExpectedName(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @CsvSource({
            "SAUCE, chili sauce, 0.0",
            "FILLING, sausage, 300.75"
    })
    void getPriceReturnsExpectedPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }
}