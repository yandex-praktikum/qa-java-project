import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

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

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "Соус традиционный галактический", 15.0f},
                {IngredientType.FILLING, "Говяжий метеорит (отбивная)", 3000.0f},
                {IngredientType.SAUCE, "", 0.0f},               // Пустое название
                {IngredientType.FILLING, "Хрустящие минеральные кольца", -1.0f}, // Отрицательная цена
                {null, "Тестовый Ингредиент", 100.0f}          // Пустая строка
        });
    }

    @Test
    public void testIngredientCreation() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType()); // Проверка типа ингредиента
        assertEquals(name, ingredient.getName()); // Проверка названия
        assertEquals(price, ingredient.getPrice(), 0.001f); //Проверка цены
    }
}
