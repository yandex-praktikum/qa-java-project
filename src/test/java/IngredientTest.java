import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {
    @ParameterizedTest
    @CsvSource({
            "FILLING, cutlet, 100.0",
            "SAUCE, hot sauce, 100.0",
            "FILLING, dinosaur, 200.0",
            "SAUCE, chili sauce, 300.0"
    })
    void testIngredient(String typeStr, String name, float price) {

        IngredientType type = IngredientType.valueOf(typeStr);

        Ingredient ingredient = new Ingredient(type, name, price);

        assertAll("Проверка ингредиентов",
                () -> assertEquals(type, ingredient.getType(), "Тип ингредиента не совпадает"),
                () -> assertEquals(name, ingredient.getName(), "Название ингредиента не совпадает"),
                () -> assertEquals(price, ingredient.getPrice(), "Цена ингредиента не совпадает")
        );
    }
}
