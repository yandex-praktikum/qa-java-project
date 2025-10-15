import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTests {

    @ParameterizedTest
    @DisplayName("Проверить корректность хранения данных ингредиента")
    @CsvSource({
            "SAUCE, ketchup, 50",
            "FILLING, cutlet, 150"
    })
    public void ingredientStoresTypeNameAndPriceCorrectly(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice());
    }
}
