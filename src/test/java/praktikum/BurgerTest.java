package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        ingredient1 = mock(Ingredient.class);
        ingredient2 = mock(Ingredient.class);
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredient() {
        burger.addIngredient(ingredient1);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getPrice()).thenReturn(50f);
        when(ingredient2.getPrice()).thenReturn(25f);

        float price = burger.getPrice();

        assertEquals(275f, price, 0.001f);
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("ketchup");
        when(ingredient1.getPrice()).thenReturn(50f);

        String expected = String.format("(==== %s ====)%n", "black bun") +
                String.format("= %s %s =%n", "sauce", "ketchup") +
                String.format("(==== %s ====)%n", "black bun") +
                String.format("%nPrice: %f%n", 250f);

        String actual = burger.getReceipt();

        assertEquals(expected, actual);
    }
}