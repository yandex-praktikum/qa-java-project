package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestBurger {

    private Burger burger;
    private Bun bun;
    private Ingredient burgerPatty;
    private Ingredient burgerSauce;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Булочка");
        when(bun.getPrice()).thenReturn(Float.valueOf(100));

        burgerPatty = mock(Ingredient.class);
        when(burgerPatty.getType()).thenReturn(IngredientType.FILLING);
        when(burgerPatty.getName()).thenReturn("Котлета");
        when(burgerPatty.getPrice()).thenReturn(Float.valueOf(50));

        burgerSauce = mock(Ingredient.class);
        when(burgerSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(burgerSauce.getName()).thenReturn("Кетчуп");
        when(burgerSauce.getPrice()).thenReturn(Float.valueOf(10));
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals("Булочка", burger.bun.getName());
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(burgerPatty);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(burgerPatty);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(burgerPatty);
        burger.addIngredient(burgerSauce);
        burger.moveIngredient(1, 0 );
        assertEquals(2, burger.ingredients.size());
        assertEquals(burger.ingredients.get(0), burgerSauce);
        assertEquals(burger.ingredients.get(1), burgerPatty);
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(burgerPatty);
        burger.addIngredient(burgerSauce);
        float expectedPrice = bun.getPrice() * 2 + burgerPatty.getPrice() + burgerSauce.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.0000001);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(burgerPatty);
        burger.addIngredient(burgerSauce);
        burger.addIngredient(burgerPatty);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(bun.getName()));
        assertTrue(receipt.contains(burgerPatty.getName()));
        assertTrue(receipt.contains(burgerSauce.getName()));
        float expectedPrice = bun.getPrice() * 2 + burgerPatty.getPrice() * 2 + burgerSauce.getPrice();
        String expectedPriceString = String.format("%nPrice: %f%n", expectedPrice);
        assertTrue(receipt.contains(expectedPriceString));
    }
}
