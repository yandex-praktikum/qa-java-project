package praktikum;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTests {

    Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        Bun mockBun = mock(Bun.class);
        burger.setBuns(mockBun);
        assertEquals(burger.bun, mockBun);
    }


    @Test
    public void addIngredientAddsCorrectIngredientTest() {
        Ingredient mockIngredient = mock(Ingredient.class);
        burger.addIngredient(mockIngredient);
        assertTrue(burger.ingredients.contains(mockIngredient));
    }

    @Test
    public void removeIngredientRemovesCorrectIngredientTest() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.removeIngredient(0);

        assertFalse(burger.ingredients.contains(firstIngredient));
    }
    
    @Test
    public void getPriceTest() {
        //создаю кучу моков и стабов, чтобы использовать
        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(70f);

        Ingredient firstIngredient = mock(Ingredient.class);
        when(firstIngredient.getPrice()).thenReturn(40f);

        Ingredient secondIngredient = mock(Ingredient.class);
        when(secondIngredient.getPrice()).thenReturn(30f);

        //вот тут уже собственно использую моки
        burger.setBuns(mockBun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        //проверяю, чтобы результат совпадал с тем, который должен быть
        assertEquals(210f, burger.getPrice(), 0.01);
    }

    @Test
    public void getReceiptTest() {
        //мок булки
        Bun mockBun = mock(Bun.class);
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);

        //мок для соуса (SAUCE)
        Ingredient mockSauce = mock(Ingredient.class);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getName()).thenReturn("hot sauce");
        when(mockSauce.getPrice()).thenReturn(100f);

        //мок для начинки (FILLING)
        Ingredient mockFilling = mock(Ingredient.class);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockFilling.getName()).thenReturn("cutlet");
        when(mockFilling.getPrice()).thenReturn(100f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        String lineSeparator = System.lineSeparator();
        String expected = "(==== black bun ====)" + lineSeparator +
                "= sauce hot sauce =" + lineSeparator +
                "= filling cutlet =" + lineSeparator +
                "(==== black bun ====)" + lineSeparator +
                lineSeparator +
                "Price: 400.000000" + lineSeparator;

        assertEquals(expected, burger.getReceipt());
    }
}
