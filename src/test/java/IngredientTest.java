package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    @ParameterizedTest
    @CsvSource({
            "SAUCE, hot sauce, 100",
            "FILLING, cutlet, 200",
            "SAUCE, cheese sauce, 50"
    })
    void ingredientShouldStoreCorrectData(IngredientType type, String name, float price) {

        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals((int) price, (int) ingredient.getPrice());
    }
}