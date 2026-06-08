package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeValuesCountIsCorrect() {
        IngredientType[] types = IngredientType.values();
        Assert.assertEquals("Количество типов ингредиентов должно быть ровно 2", 2, types.length);
    }

    @Test
    public void testIngredientTypeValuesContainsSauce() {
        IngredientType[] types = IngredientType.values();
        Assert.assertSame("Элемент SAUCE должен находиться на ожидаемой позиции", IngredientType.SAUCE, types[0]);
    }

    @Test
    public void testIngredientTypeValuesContainsFilling() {
        IngredientType[] types = IngredientType.values();
        Assert.assertSame("Элемент FILLING должен находиться на ожидаемой позиции", IngredientType.FILLING, types[1]);
    }

    @Test
    public void testIngredientTypeValueOfReturnsSauce() {
        Assert.assertEquals("Метод valueOf должен возвращать SAUCE для строки 'SAUCE'", IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void testIngredientTypeValueOfReturnsFilling() {
        Assert.assertEquals("Метод valueOf должен возвращать FILLING для строки 'FILLING'", IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}



