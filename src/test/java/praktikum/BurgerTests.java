package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class BurgerTests {

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void shouldSetBunsWithMock() {

        Bun bun = Mockito.mock(Bun.class);

        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void shouldAddIngredientWithMock() {
        Ingredient ing = Mockito.mock(Ingredient.class);

        burger.addIngredient(ing);

        assertEquals(1, burger.ingredients.size());
        assertSame(ing, burger.ingredients.get(0));
    }

    @Test
    public void shouldRemoveIngredientByIndexWithMocks() {
        Ingredient ing1 = Mockito.mock(Ingredient.class);
        Ingredient ing2 = Mockito.mock(Ingredient.class);

        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(ing2, burger.ingredients.get(0));
    }

    @Test
    public void shouldMoveIngredientWithMocks() {
        Ingredient ing1 = Mockito.mock(Ingredient.class);
        Ingredient ing2 = Mockito.mock(Ingredient.class);
        Ingredient ing3 = Mockito.mock(Ingredient.class);

        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.addIngredient(ing3);

        burger.moveIngredient(0, 2);

        assertSame(ing2, burger.ingredients.get(0));
        assertSame(ing3, burger.ingredients.get(1));
        assertSame(ing1, burger.ingredients.get(2));
    }

    @Test
    public void shouldBuildReceiptWithMocks() {
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getName()).thenReturn("white bun");
        Mockito.when(bun.getPrice()).thenReturn(200f);

        Ingredient sauce = Mockito.mock(Ingredient.class);
        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getName()).thenReturn("hot sauce");
        Mockito.when(sauce.getPrice()).thenReturn(100f);

        Ingredient filling = Mockito.mock(Ingredient.class);
        Mockito.when(filling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(filling.getName()).thenReturn("cutlet");
        Mockito.when(filling.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        // bun header/footer appear
        assertTrue(receipt.contains("(==== white bun ====)"));

        // ingredient lines (type lowercased)
        assertTrue(receipt.contains("= sauce hot sauce ="));
        assertTrue(receipt.contains("= filling cutlet ="));

        // price line (%f => 6 decimals)
        float expected = 200f * 2 + 100f + 300f;
        assertTrue(receipt.contains(String.format("Price: %f", expected)));

        // verify interactions
        Mockito.verify(bun, Mockito.atLeastOnce()).getName();
        Mockito.verify(bun, Mockito.atLeastOnce()).getPrice();
        Mockito.verify(sauce, Mockito.atLeastOnce()).getType();
        Mockito.verify(sauce, Mockito.atLeastOnce()).getName();
        Mockito.verify(filling, Mockito.atLeastOnce()).getType();
        Mockito.verify(filling, Mockito.atLeastOnce()).getName();
    }
}
