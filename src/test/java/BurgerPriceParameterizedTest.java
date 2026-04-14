import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final int sauceCount;
    private final int fillingCount;
    private final float expectedPrice;

    private Burger burger;
    private Bun mockBun;
    private Ingredient mockSauce;
    private Ingredient mockFilling;

    public BurgerPriceParameterizedTest(int sauceCount, int fillingCount, float expectedPrice) {
        this.sauceCount = sauceCount;
        this.fillingCount = fillingCount;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "sauces={0}, fillings={1}, expected={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, 0, 200f },
                { 1, 0, 230f },
                { 0, 1, 250f },
                { 1, 1, 280f },
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
        mockBun = mock(Bun.class);
        mockSauce = mock(Ingredient.class);
        mockFilling = mock(Ingredient.class);

        when(mockBun.getPrice()).thenReturn(100f);
        when(mockSauce.getPrice()).thenReturn(30f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockFilling.getPrice()).thenReturn(50f);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(mockBun);
        for (int i = 0; i < sauceCount; i++) burger.addIngredient(mockSauce);
        for (int i = 0; i < fillingCount; i++) burger.addIngredient(mockFilling);
    }

    @Test
    public void getPriceReturnsCorrectValue() {
        assertEquals(expectedPrice, burger.getPrice(), 0.0f);
    }
}