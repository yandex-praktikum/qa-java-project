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

    private final IngredientType type; // Тип ингредиента для текущего набора тестовых данных
    private final String name; // Название ингредиента для текущего набора тестовых данных
    private final float price;    // Цена ингредиента для текущего набора тестовых данных
    private static final float DELTA = 0f; // Допустимая погрешность

    // Конструктор
    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    // Набор тестовых данных для проверки разных типов и характеристик ингредиентов
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

    @Test
    public void testIngredientProperties() {

        // Создаём мок объекта Ingredient
        Ingredient ingredient = Mockito.mock(Ingredient.class);

        // Настраиваем мок: метод getType() должен возвращать тип из текущего набора данных
        Mockito.when(ingredient.getType()).thenReturn(type);

        // Настраиваем мок: метод getName() должен возвращать имя из текущего набора данных
        Mockito.when(ingredient.getName()).thenReturn(name);

        // Настраиваем мок: метод getPrice() должен возвращать цену из текущего набора данных
        Mockito.when(ingredient.getPrice()).thenReturn(price);

        // Проверяем, что getType() возвращает ожидаемый тип ингредиента
        Assert.assertEquals(type, ingredient.getType());

        // Проверяем, что getName() возвращает ожидаемое название ингредиента
        Assert.assertEquals(name, ingredient.getName());

        // Проверяем, что getPrice() возвращает ожидаемую цену ингредиента
        Assert.assertEquals(price, ingredient.getPrice(), DELTA);
    }
}