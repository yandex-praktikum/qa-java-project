package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType expcetdType;
    private final String expcetedName;
    private final float expectedPrice;
    private Ingredient ingredient;

    @Before
    public void setUp() {
        ingredient = new Ingredient(expcetdType, expcetedName, expectedPrice);
    }

    public IngredientTest(IngredientType expcetdType, String name, float expectedPrice) {
        this.expcetdType = expcetdType;
        this.expcetedName = name;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.SAUCE, "sour cream", 200.5f},
                {IngredientType.FILLING, "cutlet", 150f},
                {IngredientType.FILLING, "dinosaur", 250.75f},
                {IngredientType.SAUCE, "", 0f}
        };
    }

    @Test
    public void testGetType() {
        assertEquals(expcetdType, ingredient.getType());
    }

    @Test
    public void testGetName() {
        assertEquals(expcetedName, ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(expectedPrice, ingredient.getPrice(), 0.001);
    }
}