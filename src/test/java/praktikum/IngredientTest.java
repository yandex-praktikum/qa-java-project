package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void getPrice_ReturnsCorrectPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        assertEquals(100, ingredient.getPrice(), 0.001);
    }

    @Test
    public void getName_ReturnsCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 150);
        assertEquals("cutlet", ingredient.getName());
    }

    @Test
    public void getType_ReturnsCorrectType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "sour cream", 50);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @RunWith(Parameterized.class)
    public static class IngredientParameterizedTest {
        private final IngredientType type;
        private final String name;
        private final float price;

        public IngredientParameterizedTest(IngredientType type, String name, float price) {
            this.type = type;
            this.name = name;
            this.price = price;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {IngredientType.SAUCE, "hot sauce", 100f},
                    {IngredientType.SAUCE, "sour cream", 200f},
                    {IngredientType.SAUCE, "chili sauce", 300f},
                    {IngredientType.FILLING, "cutlet", 100f},
                    {IngredientType.FILLING, "dinosaur", 200f},
                    {IngredientType.FILLING, "sausage", 300f}
            });
        }

        @Test
        public void constructor_SetsFieldsCorrectly() {
            Ingredient ingredient = new Ingredient(type, name, price);
            assertEquals(type, ingredient.getType());
            assertEquals(name, ingredient.getName());
            assertEquals(price, ingredient.getPrice(), 0.001);
        }
    }
}