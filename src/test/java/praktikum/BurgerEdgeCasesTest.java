package praktikum;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerEdgeCasesTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testBurgerBunInitialState() {
        Burger burger = new Burger();
        assertNull(burger.bun);
    }

    @Test
    public void testBurgerIngredientsListInitialized() {
        Burger burger = new Burger();
        assertNotNull(burger.ingredients);
    }

    @Test
    public void testBurgerIngredientsInitialEmpty() {
        Burger burger = new Burger();
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testGetPriceWithZeroCost() {
        Bun mockBun = Mockito.mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(0f);

        Ingredient mockIngredient = Mockito.mock(Ingredient.class);
        when(mockIngredient.getPrice()).thenReturn(0f);

        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        assertEquals(0f, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetPriceWithZeroCostVerifiesBunPriceCall() {
        Bun mockBun = Mockito.mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(0f);

        Ingredient mockIngredient = Mockito.mock(Ingredient.class);
        when(mockIngredient.getPrice()).thenReturn(0f);

        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        burger.getPrice();

        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void testGetPriceWithZeroCostVerifiesIngredientPriceCall() {
        Bun mockBun = Mockito.mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(0f);

        Ingredient mockIngredient = Mockito.mock(Ingredient.class);
        when(mockIngredient.getPrice()).thenReturn(0f);

        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        burger.getPrice();

        verify(mockIngredient, times(1)).getPrice();
    }
}