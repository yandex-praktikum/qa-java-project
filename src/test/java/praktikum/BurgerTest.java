package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredient1Mock;

    @Mock
    private Ingredient ingredient2Mock;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSetsBunCorrectly() {
        burger.setBuns(bunMock);
        assertSame(bunMock, burger.bun);
    }

    @Test
    public void addIngredientAddsOneIngredient() {
        burger.addIngredient(ingredient1Mock);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsCorrectIngredient() {
        burger.addIngredient(ingredient1Mock);
        assertSame(ingredient1Mock, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesFromList() {
        burger.addIngredient(ingredient1Mock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientThrowsWhenIndexInvalid() {
        burger.removeIngredient(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientThrowsWhenListEmpty() {
        burger.moveIngredient(0, 1);
    }

    @Test
    public void moveIngredientMovesToNewPosition() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
        burger.moveIngredient(0, 1);
        assertSame(ingredient1Mock, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnsBunPriceTimesTwoWhenNoIngredients() {
        when(bunMock.getPrice()).thenReturn(100f);
        burger.setBuns(bunMock);
        assertEquals(200f, burger.getPrice(), 0.0f);
    }

    @Test
    public void getPriceIncludesSingleIngredientPrice() {
        when(bunMock.getPrice()).thenReturn(100f);
        when(ingredient1Mock.getPrice()).thenReturn(50f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1Mock);

        assertEquals(250f, burger.getPrice(), 0.0f);
    }

    @Test
    public void getPriceIncludesMultipleIngredientsPrices() {
        when(bunMock.getPrice()).thenReturn(100f);
        when(ingredient1Mock.getPrice()).thenReturn(50f);
        when(ingredient2Mock.getPrice()).thenReturn(30f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);

        assertEquals(280f, burger.getPrice(), 0.0f);
    }
}
