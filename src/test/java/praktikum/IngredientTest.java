package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
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

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.SAUCE, "sour cream", 200.0f},
                {IngredientType.FILLING, "cutlet", 100.0f},
                {IngredientType.FILLING, "dinosaur", 200.0f}
        });
    }

    @Test
    public void checkIngredientProperties() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals("Тип ингредиента не соответствует ожидаемому типу", type, ingredient.getType());
        assertEquals("Название ингредиента не соответствует ожидаемому названию", name, ingredient.getName());
        assertEquals("Цена ингредиента не соответствует ожидаемой цене", price, ingredient.getPrice(), 0.0f);
    }
}