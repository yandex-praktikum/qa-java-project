import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTest {
    private Ingredient ingredient;
    private final IngredientType expectedType = IngredientType.SAUCE;
    private final String expectedName = "hot sauce";
    private final float expectedPrice = 100.0f;

    @Before
    public void setUp() {
        ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
    }

    /**
     * Проверка метода getName: проверяет, что название ингредиента возвращается корректно.
     */
    @Test
    public void testGetName() {
        assertEquals("Название ингредиента некорректно", expectedName, ingredient.getName());
    }

    /**
     * Проверка метода getPrice: проверяет, что цена ингредиента возвращается корректно.
     */
    @Test
    public void testGetPrice() {
        assertEquals("Цена ингредиента некорректна", expectedPrice, ingredient.getPrice(), 0.0f);
    }

    /**
     * Проверка метода getType: проверяет, что тип ингредиента возвращается корректно.
     */
    @Test
    public void testGetType() {
        assertEquals("Тип ингредиента некорректен", expectedType, ingredient.getType());
    }
}