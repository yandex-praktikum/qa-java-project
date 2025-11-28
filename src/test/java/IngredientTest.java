import org.junit.Test;
import org.junit.Before;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    private Ingredient ingredient;

    @Before
    public void set() {
        ingredient = new Ingredient(IngredientType.SAUCE, "chili", 20.0f);
    }

    @Test
    public void getName() {

        String expectedNameI = "chili";
        String actualNemeI = ingredient.getName();
        assertEquals("Неверное название ингредиента", expectedNameI, actualNemeI);
    }

    @Test
    public void getPrice() {
        float expectedPriceI = 20.0f;
        float actualPriceI = ingredient.getPrice();
        assertEquals("Неверная цена ингредиента", expectedPriceI, actualPriceI, 0);
    }

    @Test
    public void getType() {
        IngredientType expectedPriceI = IngredientType.SAUCE;
        IngredientType actualPriceI = ingredient.getType();
        assertEquals("Неверный тип ингредиента", expectedPriceI, actualPriceI);
    }
}