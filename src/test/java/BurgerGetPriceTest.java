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

    // Поля для хранения параметров теста
    private final Bun bun;
    private final Ingredient[] ingredients;
    private final float expectedPrice;
    private static final float DELTA = 0.01f;

    // Конструктор для инициализации параметров теста
    public BurgerGetPriceTest(Bun bun, Ingredient[] ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    // Метод, возвращающий коллекцию параметров теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        // Создаем заглушки для объектов Bun и Ingredient с заданными ценами
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(1.0f);

        // Создаем заглушки для ингредиентов с заданными ценами
        Ingredient sauceIngredient = mock(Ingredient.class);
        when(sauceIngredient.getPrice()).thenReturn(0.5f);

        Ingredient fillingIngredient = mock(Ingredient.class);
        when(fillingIngredient.getPrice()).thenReturn(0.8f);

        // Возвращаем коллекцию параметров для теста
        return Arrays.asList(new Object[][] {
                {bun, new Ingredient[]{}, 2.0f},  // Бургер без ингредиентов
                {bun, new Ingredient[]{sauceIngredient}, 2.5f},  // Бургер с одним ингредиентом (соус)
                {bun, new Ingredient[]{sauceIngredient, fillingIngredient}, 3.3f}  // Бургер с двумя ингредиентами (соус и начинка)
        });
    }

    // Метод, тестирующий getPrice()
    @Test
    public void testGetPrice() {
        // Создаем тестовый объект Burger с заданными булочкой и ингредиентами
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        // Проверяем, что метод getPrice возвращает ожидаемую цену с учетом погрешности DELTA
        Assert.assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }
}