package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    @DisplayName("Создание ингредиента типа SAUCE")
    public void testIngredientCreationSauce() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);

        Assertions.assertEquals(IngredientType.SAUCE, ingredient.getType());
        Assertions.assertEquals("hot sauce", ingredient.getName());
        Assertions.assertEquals(100, ingredient.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Создание ингредиента типа FILLING")
    public void testIngredientCreationFilling() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 150);

        Assertions.assertAll(
                () -> Assertions.assertEquals(IngredientType.FILLING, ingredient.getType()),
                () -> Assertions.assertEquals("cutlet", ingredient.getName()),
                () -> Assertions.assertEquals(150, ingredient.getPrice(), 0.001)
        );
    }

    @Test
    @DisplayName("Создание ингредиента с нулевой ценой")
    public void testIngredientWithZeroPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "free sauce", 0);

        Assertions.assertEquals(0, ingredient.getPrice(), 0.001);
    }
}