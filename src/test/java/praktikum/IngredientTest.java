package praktikum;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.FILLING, "cutlet", 200.0f},
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void testGetType() {
        assertEquals(type, ingredient.getType());
    }

    @Test
    public void testGetName() {
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(price, ingredient.getPrice(), 0.001);
    }
}
