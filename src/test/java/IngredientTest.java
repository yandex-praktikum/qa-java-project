import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;
import utils.Utils;

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
    public static Object[][] getIngredientType() {
        return new Object[][] {
                {IngredientType.SAUCE, Utils.randomName(), Utils.randomPrice()},
                {IngredientType.FILLING, Utils.randomName(), Utils.randomPrice()}
        };
    }

    @Before
    public void init() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void testGetPrice() {
        float actualValue = ingredient.getPrice();
        float expectedValue = price;
        Assert.assertEquals("Фактическое значение цены отличается от ожидаемого", expectedValue, actualValue, 0.0001);
    }

    @Test
    public void testGetName() {
        String actualValue = ingredient.getName();
        String expectedValue = name;
        Assert.assertEquals("Фактическое значение названия отличается от ожидаемого", expectedValue, actualValue);
    }

    @Test
    public void testGetType() {
        IngredientType actualValue = ingredient.getType();
        IngredientType expectedValue = type;
        Assert.assertEquals("Фактическое значение типа отличается от ожидаемой", expectedValue, actualValue);
    }

}
