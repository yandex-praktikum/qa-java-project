package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient firstIngredient;

    @Mock
    Ingredient secondIngredient;

    Burger burger;

    @Before
    public void init() {
        burger = new Burger();
    }

    @Test
    public void getPriceBunOnlyTest() {
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        float expected = 200f;
        float actual = burger.getPrice();

        assertEquals(expected, actual, 0.001f);
    }

    @Test
    public void addIngredientAddsIngredientTest() {
        burger.addIngredient(firstIngredient);

        assertEquals(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesIngredientTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientKeepsRemainingIngredientTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(0);

        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientInvalidIndexTest() {
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredientForwardTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertTrue("Прямое перемещение: ингредиент не перемещён",
                burger.ingredients.indexOf(secondIngredient) < burger.ingredients.indexOf(firstIngredient));
    }

    @Test
    public void moveIngredientBackTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(1, 0);

        assertTrue("Обратное перемещение: ингредиент не перемещён",
                burger.ingredients.indexOf(secondIngredient) < burger.ingredients.indexOf(firstIngredient));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientInvalidIndexTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(10, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientInvalidNewIndexTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 10);
    }
}
