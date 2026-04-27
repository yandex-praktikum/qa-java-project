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

    @Parameterized.Parameters(name = "type = {0}, name = {1}, price = {2}")
    public static Collection<Object[]> getIngredientData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.FILLING, "cutlet", 250.5f}
        });
    }

    @Test
    public void getTypeReturnsIngredientType() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
    }

    @Test
    public void getNameReturnsIngredientName() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(name, ingredient.getName());
    }

    @Test
    public void getPriceReturnsIngredientPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(price, ingredient.getPrice(), 0.001f);
    }
}