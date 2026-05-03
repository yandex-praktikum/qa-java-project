package praktikum;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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
    public void setBunsSetsBunRight() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredientRight() {
        burger.addIngredient(firstIngredient);
        assertTrue(burger.ingredients.contains(firstIngredient));
    }

    @Test
    public void addIngredientIncreasesSize() {
        burger.addIngredient(firstIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientRemovesIngredientRight() {
        burger.addIngredient(firstIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientUpdatesFirstPosition() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);
        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientUpdatesSecondPosition() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);
        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

}