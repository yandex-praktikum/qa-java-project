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
                {IngredientType.SAUCE, "", 0.0f}, // Проверка пустой строки и нуля
                {IngredientType.FILLING, "Cheese-X", 99.99f}
        });
    }

    @Test
    public void testIngredientGettersReturnCorrectValues() {
        Ingredient ingredient = new Ingredient(type, name, price);

        Assert.assertEquals("Тип ингредиента не совпадает с ожидаемым", type, ingredient.getType());
        Assert.assertEquals("Название ингредиента не совпадает с ожидаемым", name, ingredient.getName());
        Assert.assertEquals("Цена ингредиента не совпадает с ожидаемой", price, ingredient.getPrice(), 0.0f);
    }
}

