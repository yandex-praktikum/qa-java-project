package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Ингредиент -> Тип: {0}, Имя: {1}, Цена: {2}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "Chili Sauce", 100.50f},
                {IngredientType.FILLING, "Beef Patty", 250.0f},
                {IngredientType.SAUCE, "", 0.0f},
                {IngredientType.FILLING, "Cheese-X", 99.99f}
        });
    }

    @Test
    public void testIngredientGetTypeReturnsCorrectValue() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Тип ингредиента не совпадает с ожидаемым", type, ingredient.getType());
    }

    @Test
    public void testIngredientGetNameReturnsCorrectValue() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Название ингредиента не совпадает с ожидаемым", name, ingredient.getName());
    }

    @Test
    public void testIngredientGetPriceReturnsCorrectValue() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Цена ингредиента не совпадает с ожидаемой", price, ingredient.getPrice(), 0.0f);
    }
}
