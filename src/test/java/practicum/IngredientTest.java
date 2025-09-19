package practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.Ingredient;
import ru.practicum.IngredientType;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientTest {

    private IngredientType ingredientType;
    private String name;
    private int price;

    // Параметризованный конструктор
    public IngredientTest(IngredientType ingredientType, String name, int price) {
        this.ingredientType = ingredientType;
        this.name = name;
        this.price = price;
    }

    // Данные для параметризации
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { IngredientType.FILLING, "cutlet", 100 },
                { IngredientType.SAUCE, "hot sauce", 50 },
                { IngredientType.FILLING, "cheese", 80 },
                { IngredientType.SAUCE, "spicy sauce", 60 },
                // Дополнительные тесты
                { IngredientType.FILLING, "", 0 },  // Пустое имя
                { IngredientType.SAUCE, null, 30 }, // Имя null
                { IngredientType.FILLING, "bacon", -10 }, // Отрицательная цена
                { IngredientType.SAUCE, "tomato", 0 } // Цена 0
        });
    }

    @Test
    public void testIngredientProperties() {
        Ingredient ingredient = new Ingredient(ingredientType, name, price);

        // Проверка имени
        if (name == null) {
            assertNull(ingredient.getName());
        } else {
            assertEquals(name, ingredient.getName());
        }

        // Проверка цены
        assertEquals(price, ingredient.getPrice(), 0);

        // Проверка типа
        assertEquals(ingredientType, ingredient.getType());
    }
}