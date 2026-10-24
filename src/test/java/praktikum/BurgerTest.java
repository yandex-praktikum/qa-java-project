package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient filling;

    @Mock
    Ingredient sauce;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals("Булка добавлена", bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(filling);
        assertTrue(burger.ingredients.contains(filling));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(filling));
    }

    @Test
    public void getPriceTest() {
        Mockito.when(bun.getPrice()).thenReturn(200.0f);
        Mockito.when(filling.getPrice()).thenReturn(300.0f);
        Mockito.when(sauce.getPrice()).thenReturn(100.0f);
        burger.setBuns(bun);
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        float expectedBurgerPrice = 800.0f;
        assertEquals("Стоимость неверная", expectedBurgerPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceiptTest() {
        Mockito.when(bun.getName()).thenReturn("Краторная булка");
        Mockito.when(bun.getPrice()).thenReturn(200.0f);
        Mockito.when(filling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(filling.getName()).thenReturn("Хрустящие минеральные кольца");
        Mockito.when(filling.getPrice()).thenReturn(300.0f);
        burger.setBuns(bun);
        burger.addIngredient(filling);
        String ecpectedReceipt = String.format(
                "(==== %s ====)%n" +
                "= %s %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: %f%n",
                "Краторная булка",
                "filling", "Хрустящие минеральные кольца",
                "Краторная булка",
                700.0f
        );
        assertEquals("Чек неверный", ecpectedReceipt, burger.getReceipt());
    }
}