package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeValuesHasExactElements() {
        // Получаем все элементы перечисления
        IngredientType[] types = IngredientType.values();

        // Проверяем точное количество элементов в enum
        Assert.assertEquals("Количество типов ингредиентов должно быть ровно 2", 2, types.length);

        // Проверяем, что элементы расположены в правильном порядке и соответствуют константам
        Assert.assertSame("Первый элемент должен быть SAUCE", IngredientType.SAUCE, types[0]);
        Assert.assertSame("Второй элемент должен быть FILLING", IngredientType.FILLING, types[1]);
    }

    @Test
    public void testIngredientTypeValueOfReturnsCorrectEnum() {
        // Проверяем, что поиск по строковому имени возвращает правильный объект enum
        Assert.assertEquals("Метод valueOf должен возвращать SAUCE для строки 'SAUCE'", IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        Assert.assertEquals("Метод valueOf должен возвращать FILLING для строки 'FILLING'", IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}


