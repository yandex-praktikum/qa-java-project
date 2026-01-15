package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BurgerMockitoTests {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientOne;

    @Mock
    private Ingredient mockIngredientTwo;

    @Mock
    private Ingredient mockIngredientThree;

    @Before
    public void setUp(){
        burger = new Burger();
    }

    @Test
    public void testThatSetBunAddNotNull(){

        burger.setBuns(mockBun);

        assertNotNull(burger.bun);
        assertEquals(mockBun, burger.bun);

    }

    @Test
    public void testThatAddIngredientIncreaseIngredientsSize(){

        burger.addIngredient(mockIngredientOne);

        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredientOne, burger.ingredients.get(0));
    }

    @Test
    public void testThatRemoveIngredientDecreaseIngredientSize(){

        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredientTwo, burger.ingredients.get(0));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithEmptyList() {
        burger.removeIngredient(0);
    }

    @Test
    public void testThatMoveIngredientChangeIngredientIndex(){

        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);
        burger.addIngredient(mockIngredientThree);

        burger.moveIngredient(0, 2);

        assertEquals(mockIngredientOne, burger.ingredients.get(2));
        assertEquals(mockIngredientTwo, burger.ingredients.get(0));
        assertEquals(mockIngredientThree, burger.ingredients.get(1));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWithInvalidIndex() {
        burger.addIngredient(mockIngredientOne);
        burger.moveIngredient(5, 0);
    }

    @Test
    public void testGetPriceCorrectResult(){

        when(mockBun.getPrice()).thenReturn(150.0f);
        when(mockIngredientOne.getPrice()).thenReturn(75.0f);
        when(mockIngredientTwo.getPrice()).thenReturn(50.0f);
        when(mockIngredientThree.getPrice()).thenReturn(100.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);
        burger.addIngredient(mockIngredientThree);

        float price = burger.getPrice();

        assertEquals(150.0f * 2 + 75.0f + 50.0f + 100.0f, price, 0);
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredientOne, times(1)).getPrice();
        verify(mockIngredientTwo, times(1)).getPrice();
        verify(mockIngredientThree, times(1)).getPrice();

    }

}
