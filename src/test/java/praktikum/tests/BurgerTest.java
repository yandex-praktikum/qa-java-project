package praktikum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static praktikum.IngredientType.FILLING;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest{

    private Burger burger;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient filling;
    @Mock
    private Ingredient sauce;

    @Test
    public void testSetBunsPositiveResult() {
        burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("бриошь");
        burger.setBuns(bun);
        assertEquals(bun.getName(), burger.bun.getName());
    }

    @Test
    public void testAddIngredientPositiveResult() {
        burger = new Burger();
        burger.addIngredient(sauce);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientPositiveResult(){
        burger = new Burger();
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        burger.moveIngredient(1,0);
        assertEquals(sauce, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientIsSuccessPositiveResult() {
        burger = new Burger();
        burger.addIngredient(sauce);
        burger.removeIngredient(burger.ingredients.indexOf(sauce));
        assertFalse(burger.ingredients.contains(sauce));
    }

    @Test
    public void testGetPriceIsSuccessPositiveResult() {
        Mockito.when(bun.getPrice()).thenReturn(120f);
        Mockito.when(filling.getPrice()).thenReturn(180f);
        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(filling);
        assertEquals(420f, burger.getPrice(), 0);
    }

    @Test
    public void testGetReceiptIsSuccessPositiveResult() {
        Mockito.when(bun.getName()).thenReturn("бриошь");
        Mockito.when(bun.getPrice()).thenReturn(120f);
        Mockito.when(filling.getName()).thenReturn("cutlet");
        Mockito.when(filling.getPrice()).thenReturn(180f);
        Mockito.when(filling.getType()).thenReturn(FILLING);
        String expectedReceipt = String.format("(==== %s ====)%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n","бриошь","filling","cutlet","бриошь", 420f);burger = new Burger();
        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(filling);
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
