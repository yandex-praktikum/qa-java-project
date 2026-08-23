import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetPriceTest {

    // Булочка, которая используется в текущем тестовом наборе
    private final Bun bun;

    // Массив ингредиентов, которые добавляются в бургер
    private final Ingredient[] ingredients;

    // Ожидаемая итоговая цена бургера
    private final float expectedPrice;

    // Допустимая погрешность при сравнении значений типа float
    private static final float DELTA = 0.01f;

    // Конструктор получает параметры для каждого запуска параметризованного теста
    public BurgerGetPriceTest(Bun bun, Ingredient[] ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    // Набор параметров для проверки разных вариантов состава бургера
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        // Создаём мок булочки и задаём цену 1.0
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(1.0f);

        // Создаём первый мок ингредиента и задаём ему цену 0.5
        Ingredient ingredient1 = mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(0.5f);

        // Создаём второй мок ингредиента и задаём ему цену 0.8
        Ingredient ingredient2 = mock(Ingredient.class);
        when(ingredient2.getPrice()).thenReturn(0.8f);

        // Возвращаем три набора данных:
        // 1. Бургер без ингредиентов
        // 2. Бургер с одним ингредиентом
        // 3. Бургер с двумя ингредиентами
        return Arrays.asList(new Object[][]{
                {bun, new Ingredient[]{}, 2.0f},
                {bun, new Ingredient[]{ingredient1}, 2.5f},
                {bun, new Ingredient[]{ingredient1, ingredient2}, 3.3f}
        });
    }

    @Test
    public void testGetPrice() {

        // Создаём новый объект Burger
        Burger burger = new Burger();

        // Устанавливаем булочку для бургера
        burger.setBuns(bun);

        // Добавляем в бургер все ингредиенты из текущего тестового набора
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        // Проверяем, что итоговая цена бургера соответствует ожидаемой
        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }
}