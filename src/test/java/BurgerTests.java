import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addOneIngredientTest() {
        burger.addIngredient(sauce);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addTwoIngredientTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void removeFirstIngredientTest() {
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void removeSecondIngredientTest() {
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        burger.removeIngredient(1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeTwoIngredientsTest() {
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        burger.removeIngredient(1);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveSecondIngredientToFirstTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(1, 0);
        assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    public void moveFirstIngredientToSecondTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);
        assertEquals(sauce, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientDoesNotRemoveIngredientTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);
        assertEquals(2, burger.ingredients.size());
    }
}