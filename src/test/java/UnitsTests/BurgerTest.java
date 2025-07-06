package UnitsTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType; // добавьте этот импорт

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Sesame");
        when(bunMock.getPrice()).thenReturn(2.5f);

        ingredientMock1 = mock(Ingredient.class);
        when(ingredientMock1.getName()).thenReturn("Lettuce");
        when(ingredientMock1.getPrice()).thenReturn(0.5f);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE); // исправлено

        ingredientMock2 = mock(Ingredient.class);
        when(ingredientMock2.getName()).thenReturn("Tomato");
        when(ingredientMock2.getPrice()).thenReturn(0.7f);
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING); // исправлено
    }

    @Test
    public void testSetBun() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expectedPrice = bunMock.getPrice() * 2 + ingredientMock1.getPrice() + ingredientMock2.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.0001);
    }

    @Test
    public void testGetReceipt() {

        Bun bun = new Bun("Sesame Bun", 1.5f);
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "Lettuce", 0.5f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Cheese", 1.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(bun.getName()));
        assertTrue(receipt.contains(ingredient1.getName()));
        assertTrue(receipt.contains(ingredient2.getName()));
        float expectedPrice = bun.getPrice() * 2 + ingredient1.getPrice() + ingredient2.getPrice();
        assertTrue(receipt.contains(String.format("Price: %.2f", expectedPrice)));
    }
}

//