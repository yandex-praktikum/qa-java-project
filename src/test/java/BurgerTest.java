import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import static org.junit.Assert.assertEquals;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        Bun bun = new Bun("black bun", 100);

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientIncreasesIngredientsSizeTest() {
        burger.addIngredient(firstIngredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsCorrectIngredientTest() {
        burger.addIngredient(firstIngredient);

        assertEquals(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(firstIngredient);

        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientChangesFirstPositionTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientChangesSecondPositionTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(firstIngredient, burger.ingredients.get(1));
    }
}
