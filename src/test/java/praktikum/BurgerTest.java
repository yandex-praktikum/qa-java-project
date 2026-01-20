package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;

    private Bun bun;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = new Bun("Флюоресцентная булка R2-D3", 988);
        ingredient1 = new Ingredient(IngredientType.SAUCE, "Соус Spicy-X", 90);
        ingredient2 = new Ingredient(IngredientType.FILLING, "Мясо бессмертных моллюсков Protostomia", 1337);
    }

    @Test
    public void testSetBunsWithMock() {
        Bun mockBun = mock(Bun.class);
        when(mockBun.getName()).thenReturn("Краторная булка N-200i");
        when(mockBun.getPrice()).thenReturn(1255f);

        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
        assertEquals("Краторная булка N-200i", burger.bun.getName());
        assertEquals(1255f, burger.bun.getPrice(), 0);
    }

    @Test
    public void testAddIngredientWithMock() {
        Ingredient mockIngredient = mock(Ingredient.class);
        when(mockIngredient.getName()).thenReturn("Соус фирменный Space Sauce");
        when(mockIngredient.getPrice()).thenReturn(80f);
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);

        burger.addIngredient(mockIngredient);
        List<Ingredient> ingredients = burger.ingredients;
        assertTrue(ingredients.contains(mockIngredient));
        assertEquals(1, ingredients.size());
        assertEquals("Соус фирменный Space Sauce", ingredients.get(0).getName());
    }

    @Test
    public void testGetPriceWithMocks() {
        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(988f);

        Ingredient mockIngredient = mock(Ingredient.class);
        when(mockIngredient.getPrice()).thenReturn(90f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        float expected = 2 * 988 + 90;
        assertEquals(expected, burger.getPrice(), 0);
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredient1));
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String receipt = burger.getReceipt();

        String expected = String.format(
                "(==== %s ====)%n" +
                        "= sauce %s =%n" +
                        "= filling %s =%n" +
                        "(==== %s ====)%n%n" +
                        "Price: %f%n",
                bun.getName(),
                ingredient1.getName(),
                ingredient2.getName(),
                bun.getName(),
                burger.getPrice()
        );

        assertEquals(expected, receipt);
    }
}
