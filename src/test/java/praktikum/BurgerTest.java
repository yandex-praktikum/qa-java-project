package praktikum;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    @Before
    public void setup() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        ingredientMock1 = mock(Ingredient.class);
        ingredientMock2 = mock(Ingredient.class);
    }

    @Test
    public void testSetBunsAndGetPrice() {
        when(bunMock.getPrice()).thenReturn(50f);

        burger.setBuns(bunMock);

        assertEquals(bunMock, burger.bun);

        assertEquals(100f, burger.getPrice(), 0.0001);
    }

    @Test
    public void testAddIngredientAndPrice() {
        when(bunMock.getPrice()).thenReturn(40f);
        when(ingredientMock1.getPrice()).thenReturn(10f);
        when(ingredientMock2.getPrice()).thenReturn(20f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expectedPrice = 40f * 2 + 10f + 20f;
        assertEquals(expectedPrice, burger.getPrice(), 0.0001);
    }

    @Test
    public void testRemoveIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        assertEquals(2, burger.ingredients.size());

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(ingredientMock2));
    }

    @Test
    public void testMoveIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    public void testGetReceipt() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100f);

        Ingredient ingr1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);
        Ingredient ingr2 = new Ingredient(IngredientType.FILLING, "cutlet", 150f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingr1);
        burger.addIngredient(ingr2);

        String expectedReceipt = String.format("(==== %s ====)%n", "black bun") +
                String.format("= %s %s =%n", "sauce", "hot sauce") +
                String.format("= %s %s =%n", "filling", "cutlet") +
                String.format("(==== %s ====)%n", "black bun") +
                String.format("%nPrice: %f%n", burger.getPrice());

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}