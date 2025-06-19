import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    private String expectedName = "BigKing";
    private float expectedPrice = 411.65f;
    private Ingredient ingredient;
    private IngredientType ingredientType;

    @Before
    public void setUp() {
        ingredient = new Ingredient(ingredientType, "BigKing", 411.65f);
    }

    @Test
    public void getPriceTest() {
        assertEquals("Ожидаемая цена не соответствует актуальной цене", expectedPrice, ingredient.getPrice(), 0);
    }

    @Test
    public void getNameTest() {
        assertEquals("Ожидаемое имя не соответствует актуальному имени", expectedName, ingredient.getName());
    }

    @Test
    public void getIngredientTypeTest() {
        assertEquals(ingredientType, ingredient.getType());
    }

}
