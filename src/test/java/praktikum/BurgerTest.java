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
    public void setBunsSetsTheBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientIncreasesSize() {
        burger.addIngredient(firstIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsCorrectElement() {
        burger.addIngredient(firstIngredient);
        assertEquals(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientDecreasesSize() {
        burger.addIngredient(firstIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientChangesFirstElement() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(1, 0);

        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientChangesSecondElement() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(1, 0);

        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getReceiptContainsBunName() {
        when(bun.getName()).thenReturn("Black Bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Black Bun"));
    }

    @Test
    public void getReceiptContainsIngredientInfo() {
        when(bun.getName()).thenReturn("Black Bun");
        when(bun.getPrice()).thenReturn(100f);
        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredient.getName()).thenReturn("Hot Sauce");
        when(firstIngredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("sauce"));
        assertTrue(receipt.contains("Hot Sauce"));
    }
}