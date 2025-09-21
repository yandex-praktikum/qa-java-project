package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private Burger burger;
    private Bun mockBun;
    private Ingredient mockSauce;
    private Ingredient mockFilling;

    @Parameterized.Parameter(0)
    public float bunPrice;
    @Parameterized.Parameter(1)
    public float saucePrice;
    @Parameterized.Parameter(2)
    public float fillingPrice;
    @Parameterized.Parameter(3)
    public float expectedPrice;

    @Parameterized.Parameters(name = "bun={0}, sauce={1}, filling={2} => total={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 150f, 400f},  // 2 * 100 + 50 + 150
                {200f, 0f, 100f, 500f},   // 2 * 200 + 0 + 100
                {50f, 25f, 25f, 150f},    // 2 * 50 + 25 + 25
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        // Создание моков для булки и ингредиентов
        mockBun = mock(Bun.class);
        mockSauce = mock(Ingredient.class);
        mockFilling = mock(Ingredient.class);

        // Устанавливаем поведение для булки и ингредиентов
        when(mockBun.getName()).thenReturn("test bun");
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getName()).thenReturn("ketchup");
        when(mockSauce.getPrice()).thenReturn(saucePrice);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockFilling.getName()).thenReturn("cutlet");
        when(mockFilling.getPrice()).thenReturn(fillingPrice);

        // Устанавливаем булку и ингредиенты в бургер
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
    }

    @Test
    public void testGetPrice() {
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        double price = burger.getPrice();
        String expectedReceipt = String.format(
                "(==== test bun ====)%n" +
                        "= sauce ketchup =%n" +
                        "= filling cutlet =%n" +
                        "Price: %.1f%n", price);

        String actualReceipt = burger.getReceipt().replaceAll("\r\n", "\n");
        assertEquals(expectedReceipt, actualReceipt);
    }
}
