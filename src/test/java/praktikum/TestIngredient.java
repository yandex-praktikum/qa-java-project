package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestIngredient {

    private final Object type;
    private final String name;
    private final float price;
    private final boolean isValid;

    public TestIngredient(Object type, String name, float price, boolean isValid) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.isValid = isValid;
    }

    @Parameterized.Parameters(name = "{0} {1} {2}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "Вулканический кетчуп", 333, true},
                {IngredientType.FILLING, "Котлета из щупальца пришельца", 333, true},
                {"", "Неизвестный", 222, false},
                {IngredientType.SAUCE, "Кратерная жижа", 0, true},
                {IngredientType.SAUCE, "Кратерная жижа", -8888, true},
                {IngredientType.SAUCE, "Кратерная жижа", Float.MAX_VALUE, true},
                {IngredientType.SAUCE, "", 0, true},
                {IngredientType.SAUCE, "Кратерная склизкая зелено-фиолетовая жижа с кусочками астероидов туманности Андромеда", 0, true},
        });
    }

    @Test
    public void TestIngredientCreation() {
        try {
            // пробуем создать ингредиент с корректным типом
            if (type instanceof IngredientType) {
                IngredientType typeInstance = (IngredientType) type;
                Ingredient ingredient = new Ingredient(typeInstance, name, price);

                assertEquals(typeInstance, ingredient.getType());
                assertEquals(name, ingredient.getName());
                assertEquals(price, ingredient.getPrice(), 0.000001);
                assertTrue("Ожидалось, что тип ингредиента допустим", isValid);
            } else {
                // передаваемы тип не является значением IngredientType
                assertFalse("Недопустимый тип ингредиента", isValid);
            }
        } catch (Exception e) {
            if (isValid) {
                fail("Тип корректный, но получено исключение: " + e);
            } else {
                assertTrue(true);
            }
        }
    }
}
