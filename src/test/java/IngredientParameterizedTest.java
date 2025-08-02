import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientParameterizedTest (IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "Ketchup", 1.5f},
                {IngredientType.SAUCE, "Mustard", 0.9f},
                {IngredientType.FILLING, "Beef Patty", 3.2f},
                {IngredientType.FILLING, "Cheese", 1.0f}
        });
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Цена ингредиента не соответствует", price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void getNameShouldReturnCorrectNames() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Имя товара не соответстует", name, ingredient.getName());
    }

    @Test
    public void getTypeShouldReturnCorrectType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Имя товара не соответстует", type, ingredient.getType());
    }

}
