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

    // Цена булочки
    private static final float BUN_PRICE = 1.0f;// Цена булочки
    private static final float SAUCE_PRICE = 0.5f;  // Цена соуса
    private static final float FILLING_PRICE = 0.8f;  // Цена начинки
    private static final float BURGER_WITHOUT_INGREDIENTS_PRICE = 2.0f;    // Ожидаемая цена бургера без ингредиентов
    private static final float BURGER_WITH_ONE_INGREDIENT_PRICE = 2.5f;// Ожидаемая цена бургера с одним ингредиентом
    private static final float BURGER_WITH_TWO_INGREDIENTS_PRICE = 3.3f;
    // Ожидаемая цена бургера с двумя ингредиентами
    private static final float DELTA = 0.01f;// Допустимая погрешность при сравнении значений типа float

    private final Bun bun; // Булочка, которая используется в текущем тестовом наборе
    private final Ingredient[] ingredients; // Массив ингредиентов, которые добавляются в бургер
    private final float expectedPrice; // Ожидаемая итоговая цена бургера

    // Конструктор 
    public BurgerGetPriceTest(
            Bun bun,
            Ingredient[] ingredients,
            float expectedPrice) {

        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    // Набор параметров для проверки разных вариантов состава бургера
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        // Создаём мок булочки
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(BUN_PRICE);

        // Создаём мок соуса
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getPrice()).thenReturn(SAUCE_PRICE);

        // Создаём мок начинки
        Ingredient filling = mock(Ingredient.class);
        when(filling.getPrice()).thenReturn(FILLING_PRICE);

        // Возвращаем три набора данных:
        // 1. Бургер без ингредиентов
        // 2. Бургер с одним ингредиентом
        // 3. Бургер с двумя ингредиентами
        return Arrays.asList(new Object[][]{
                {
                        bun,
                        new Ingredient[]{},
                        BURGER_WITHOUT_INGREDIENTS_PRICE
                },
                {
                        bun,
                        new Ingredient[]{sauce},
                        BURGER_WITH_ONE_INGREDIENT_PRICE
                },
                {
                        bun,
                        new Ingredient[]{sauce, filling},
                        BURGER_WITH_TWO_INGREDIENTS_PRICE
                }
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

        // Проверяем итоговую цену бургера
        Assert.assertEquals(
                expectedPrice,
                burger.getPrice(),
                DELTA
        );
    }
}