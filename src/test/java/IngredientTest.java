import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private static final float DELTA = 0f;

    // Конструктор для инициализации параметров
    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    // Метод, который предоставляет набор параметров для теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.SAUCE, "sour cream", 200f},
                {IngredientType.SAUCE, "chili sauce", 300f},
                {IngredientType.FILLING, "cutlet", 100f},
                {IngredientType.FILLING, "dinosaur", 200f},
                {IngredientType.FILLING, "sausage", 300f},
        });
    }

    // Тест, который будет выполнен с использованием каждого набора параметров
    @Test
    public void testIngredientProperties() {
        // Создаем мок объекта Ingredient
        Ingredient ingredient = Mockito.mock(Ingredient.class);

        // Устанавливаем поведение мока
        Mockito.when(ingredient.getType()).thenReturn(type);
        Mockito.when(ingredient.getName()).thenReturn(name);
        Mockito.when(ingredient.getPrice()).thenReturn(price);

        // Проверка соответствия заданных параметров мок-ингредиенту
        Assert.assertEquals(type, ingredient.getType());
        Assert.assertEquals(name, ingredient.getName());
        Assert.assertEquals(price, ingredient.getPrice(), DELTA);
    }
}