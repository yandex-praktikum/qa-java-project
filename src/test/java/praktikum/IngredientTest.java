package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    @Test
    public void testIngredientConstructorAndGetters() {
        IngredientType expectedType = IngredientType.FILLING;
        String expectedName = "testFilling";
        float expectedPrice = 2.5f;

        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        assertEquals(expectedType, ingredient.getType(),
                "Тип ингредиента должен совпадать");
        assertEquals(expectedName, ingredient.getName(),
                "Имя ингредиента должно совпадать");
        assertEquals(expectedPrice, ingredient.getPrice(),
                "Цена ингредиента должна совпадать");
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    public void testIngredientWithAllTypes(IngredientType type) {
        String name = "ingredient_" + type.name();
        float price = 10.0f;

        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice());
    }

    @Test
    public void testIngredientWithZeroPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "freeSauce", 0.0f);
        assertEquals(0.0f, ingredient.getPrice());
    }

    @Test
    public void testIngredientWithNullType() {
        Ingredient ingredient = new Ingredient(null, "noType", 5.0f);
        assertNull(ingredient.getType(), "Тип может быть null");
    }

    @Test
    public void testIngredientWithNullName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, null, 5.0f);
        assertNull(ingredient.getName(), "Имя может быть null");
    }
}