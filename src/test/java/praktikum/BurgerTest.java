package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSetsSelectedBun() {
        burger.setBuns(bun);

        assertSame("Burger should contain the selected bun", bun, burger.bun);
    }

    @Test
    public void addIngredientIncreasesIngredientsCount() {
        addTwoIngredients();

        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void addIngredientKeepsFirstIngredientAtFirstPosition() {
        addTwoIngredients();

        assertSame(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void addIngredientAddsSecondIngredientToEnd() {
        addTwoIngredients();

        assertSame(secondIngredient, burger.ingredients.get(1));
    }

    @Test
    public void removeIngredientDecreasesIngredientsCount() {
        addTwoIngredients();

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientRemovesIngredientByIndex() {
        addTwoIngredients();

        burger.removeIngredient(0);

        assertSame(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientMovesSecondIngredientToFirstPosition() {
        addTwoIngredients();

        burger.moveIngredient(0, 1);

        assertSame(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientMovesFirstIngredientToSecondPosition() {
        addTwoIngredients();

        burger.moveIngredient(0, 1);

        assertSame(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceForBurgerWithoutIngredientsReturnsDoubleBunPrice() {
        when(bun.getPrice()).thenReturn(75.5f);
        burger.setBuns(bun);

        assertEquals(151.0f, burger.getPrice(), 0.001f);
    }

    @Test
    public void getPriceReturnsBunsAndIngredientsTotal() {
        when(bun.getPrice()).thenReturn(100.0f);
        when(firstIngredient.getPrice()).thenReturn(25.5f);
        when(secondIngredient.getPrice()).thenReturn(74.5f);
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        assertEquals(300.0f, burger.getPrice(), 0.001f);
    }

    private void addTwoIngredients() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
    }
}
