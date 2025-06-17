import org.junit.Assert;
import org.junit.Test;
import praktikum.IngredientType;

public class IngredientTypeTest {
    private static final String SAUCE = "SAUCE";
    private static final String FILLING = "FILLING";


    @Test
    public void testSauce() {
        String expectedValue = SAUCE;
        String actualValue = IngredientType.SAUCE.toString();
        Assert.assertEquals("Ожидаемое значение соуса отличается от фактического", expectedValue, actualValue);
    }

    @Test
    public void testFilling() {
        String expectedValue = FILLING;
        String actualValue = IngredientType.FILLING.toString();
        Assert.assertEquals("Ожидаемое значение начинки отличается от фактического", expectedValue, actualValue);
    }
}
