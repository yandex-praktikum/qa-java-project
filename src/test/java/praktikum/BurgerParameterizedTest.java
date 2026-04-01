package praktikum;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final float bunPrice;
    private final float ingredientSaucePrice;
    private final float ingredientFillingPrice;

    public BurgerParameterizedTest (float bunPrice, float ingredientSaucePrice, float ingredientFillingPrice) {
        this.bunPrice = bunPrice;
        this.ingredientSaucePrice = ingredientSaucePrice;
        this.ingredientFillingPrice = ingredientFillingPrice;
    }

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredientSauce;

    @Mock
    Ingredient ingredientFilling;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Создать стабы для получения цены булки и ингредиентов
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredientSauce.getPrice()).thenReturn(ingredientSaucePrice);
        Mockito.when(ingredientFilling.getPrice()).thenReturn(ingredientFillingPrice);
    }

    @Parameterized.Parameters(name = "price = {0}")
    public static Collection<Object[]> getPriceBurger() {
        return Arrays.asList(new Object[][] {
                {10f, 200f, 300.5f},
                {200f, 300.5f, 10f},
                {300.5f, 10f, 200f}
        });
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger(); // Создать бургер
        burger.bun = bun; // Добавить булку
        burger.ingredients.add(ingredientSauce); // Добавить соус
        burger.ingredients.add(ingredientFilling); // Добавить начинку

        // Создать ожидаемый результат
        float expectedPrice = bunPrice * 2 + ingredientSaucePrice + ingredientFillingPrice;
        System.out.println(expectedPrice);

        // Фактический результат
        float actualPrice = burger.getPrice();
        System.out.println(burger.getPrice());

        // Проверить что результаты совпадают
        Assert.assertEquals("Pезультаты не совпадают",expectedPrice, actualPrice, 0.001f);
        }

}
