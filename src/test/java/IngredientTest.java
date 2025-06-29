import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class IngredientTest {
    @ParameterizedTest
    @CsvSource({
            "FILLING, Lettuce, 10.0",
            "SAUCE, Ketchup, 5.5",
            "FILLING, Tomato, 12.0"
    })
    void testCreateIngredient(String typeStr, String name, double price) {
        IngredientType type = IngredientType.valueOf(typeStr);
        Ingredient ingredient = new Ingredient(type, name, (float)price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals((float)price, ingredient.getPrice());
    }

    @Test
    void testEqualsAndHashCode() {
        Ingredient ingredient1 = new Ingredient(IngredientType.FILLING, "Lettuce", 5.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Lettuce", 5.0f);
        Ingredient ingredient3 = new Ingredient(IngredientType.SAUCE, "Ketchup", 10.5f);

        // Проверка, что поля совпадают
        assertEquals(ingredient1.getName(), ingredient2.getName());
        assertEquals(ingredient1.getType(), ingredient2.getType());
        assertEquals(ingredient1.getPrice(), ingredient2.getPrice());

        // Проверка, что поля у разных объектов отличаются
        assertNotEquals(ingredient1.getName(), ingredient3.getName());
        assertNotEquals(ingredient1.getType(), ingredient3.getType());
        assertNotEquals(ingredient1.getPrice(), ingredient3.getPrice());
    }
}
