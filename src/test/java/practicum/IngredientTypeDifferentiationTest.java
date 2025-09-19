package practicum;

import org.junit.Test;
import ru.practicum.Ingredient;
import ru.practicum.IngredientType;

import static org.junit.Assert.assertNotEquals;

public class IngredientTypeDifferentiationTest {

    @Test
    public void testIngredientTypeDifferentiation() {
        Ingredient fillingIngredient = new Ingredient(IngredientType.FILLING, "cheese", 80);
        Ingredient sauceIngredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 50);

        // Проверяем, что типы ингредиентов различны
        assertNotEquals(fillingIngredient.getType(), sauceIngredient.getType());
    }
}