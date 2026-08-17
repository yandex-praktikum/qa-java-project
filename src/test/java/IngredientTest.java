import praktikum.Ingredient;
import praktikum.IngredientType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    private Ingredient ingredient;
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.SAUCE, "sour cream", 200.0f},
                {IngredientType.SAUCE, "chili sauce", 300.0f},
                {IngredientType.FILLING, "cutlet", 100.0f},
                {IngredientType.FILLING, "dinosaur", 200.0f},
                {IngredientType.FILLING, "sausage", 300.0f}
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void testGetPrice() {
        float expectedPrice = price;
        assertEquals(expectedPrice, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void testGetName() {
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testGetType() {
        assertEquals(type, ingredient.getType());
    }
    @Test
    public void testGetTypeReturnsNullWithNullType() {
        Ingredient ingredient = new Ingredient(null, "test", 100.0f);
        assertNull(ingredient.getType());
    }

    @Test
    public void testGetNameReturnsNullWithNullName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, null, 100.0f);
        assertNull(ingredient.getName());
    }


}