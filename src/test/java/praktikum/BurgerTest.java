package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Test
    public void setBunsCheckReference() {
        Burger burger = new Burger();
        Bun bun = new Bun("White", 100f);
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientCheckAddingToList() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("White", 100f));
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "ketchup", 10f);
        burger.addIngredient(sauce);
        assertEquals(1, burger.ingredients.size());
        assertEquals(sauce, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientCheckRemoveByIndex() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("White", 100f));
        Ingredient a = new Ingredient(IngredientType.SAUCE, "ketchup", 10f);
        Ingredient b = new Ingredient(IngredientType.FILLING, "cutlet", 50f);
        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(b, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientCheckMoveElement() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("White", 100f));
        Ingredient a = new Ingredient(IngredientType.SAUCE, "ketchup", 10f);
        Ingredient b = new Ingredient(IngredientType.FILLING, "cutlet", 50f);
        Ingredient c = new Ingredient(IngredientType.SAUCE, "mustard", 5f);
        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        burger.moveIngredient(0, 2); // [a,b,c] -> [b,c,a]

        assertEquals(b, burger.ingredients.get(0));
        assertEquals(c, burger.ingredients.get(1));
        assertEquals(a, burger.ingredients.get(2));
    }

    @Test
    public void getPriceAndSumsBunTwiceAndIngredients() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        Ingredient ing1 = mock(Ingredient.class);
        when(ing1.getPrice()).thenReturn(30f);
        Ingredient ing2 = mock(Ingredient.class);
        when(ing2.getPrice()).thenReturn(70f);

        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        assertEquals(300f, burger.getPrice(), 0.0001f);
        verify(bun, atLeastOnce()).getPrice();
        verify(ing1, atLeastOnce()).getPrice();
        verify(ing2, atLeastOnce()).getPrice();
    }

    @Test
    public void getReceiptCheckBuildCorrectString() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Black Bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(50f);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(50f);

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expected = String.format("(==== %s ====)%n", "Black Bun") +
                String.format("= %s %s =%n", "sauce", "hot sauce") +
                String.format("= %s %s =%n", "filling", "cutlet") +
                String.format("(==== %s ====)%n", "Black Bun") +
                String.format("%nPrice: %f%n", 300f);

        assertEquals(expected, burger.getReceipt());
    }
}
