package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType expectedType;
    private final String expectedName;
    private final float expectedPrice;

    public IngredientTest(IngredientType expectedType, String expectedName, float expectedPrice) {
        this.expectedType = expectedType;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Тестовые данные: тип={0}, название={1}, цена={2}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.FILLING, "cutlet", 200.0f}
        });
    }

    @Test
    public void ingredientObjectShouldNotBeNull() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        assertNotNull("Объект Ingredient не должен быть null", ingredient);
    }

    @Test
    public void ingredientTypeShouldMatchExpected() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        assertEquals("Тип ингредиента должен совпадать", expectedType, ingredient.type);
    }

    @Test
    public void getName_ShouldReturnCorrectValue() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        assertEquals(expectedName, ingredient.getName());
    }

    @Test
    public void getPrice_ShouldReturnCorrectValue() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        assertEquals(expectedPrice, ingredient.getPrice(), 0.001f);
    }
}