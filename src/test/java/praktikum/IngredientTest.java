package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

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

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2}")
    public static Object[][] getData() {
        return new Object[][] {
                {IngredientType.SAUCE, "Соуc Спайсик", 100f},
                {IngredientType.FILLING, "Говяжий котлет", 500f}
        };
    }

    @Test
    public void getTypeReturnsCorrectType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Тип ингредиента должен совпадать", type, ingredient.getType());
    }

    @Test
    public void getNameReturnsCorrectName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Имя ингредиента должно совпадать", name, ingredient.getName());
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Цена ингредиента должна совпадать", price, ingredient.getPrice(), 0.0f);
    }
}