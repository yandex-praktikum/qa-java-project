package praktikum.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerGetPriceTest {
    private final Float bunPrice;
    private final Float ingredient1Price;
    private final Float ingredient2Price;
    private final int ingredientCount;
    private final float expectedPrice;

    private Bun mockBun;
    private Ingredient mockIngredient1;
    private Ingredient mockIngredient2;

    private Burger burger;

    public BurgerGetPriceTest(Float bunPrice, Float ingredient1Price, Float ingredient2Price, int ingredientCount, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Price = ingredient2Price;
        this.ingredientCount = ingredientCount;
        this.expectedPrice = expectedPrice;
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockBun = Mockito.mock(Bun.class);
        mockIngredient1 = Mockito.mock(Ingredient.class);
        mockIngredient2 = Mockito.mock(Ingredient.class);

        burger = new Burger();
        burger.setBuns(mockBun);
        when(mockBun.getPrice()).thenReturn(bunPrice);

        if (ingredientCount > 0) {
            when(mockIngredient1.getPrice()).thenReturn(ingredient1Price);
            burger.addIngredient(mockIngredient1);
        }
        if (ingredientCount > 1) {
            when(mockIngredient2.getPrice()).thenReturn(ingredient2Price);
            burger.addIngredient(mockIngredient2);
        }
    }

    // Тестовые данные
    @Parameterized.Parameters(name = "{index}: bunPrice={0}, ingredient1Price={1}, ingredient2Price={2}, ingredientCount={3} => expectedPrice={4}")
    public static Object[][] data() {
        return new Object[][]{
                {2.5f, 1.0f, 1.5f, 2, 2.5f * 2 + 1.0f + 1.5f},
                {3.0f, 0f, 0f, 0, 3.0f * 2},
                {1.5f, 0.5f, 0f, 1, 1.5f * 2 + 0.5f},
                {0f, 0f, 0f, 0, 0f},
                {2.0f, 0f, 2.0f, 1, 2.0f * 2 + 0f}
        };
    }

    // Тест для метода getPrice()
    @Test
    public void getPriceTest() {
        float actualPrice = burger.getPrice();
        verify(mockBun, atLeastOnce()).getPrice();
        if (ingredientCount > 0) {
            verify(mockIngredient1, atLeastOnce()).getPrice();
        }
        if (ingredientCount > 1) {
            verify(mockIngredient2, atLeastOnce()).getPrice();
        }
        assertEquals(expectedPrice, actualPrice, 0.001f);
    }
}
