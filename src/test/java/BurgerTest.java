import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;


import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private final String bunName;
    private final float bunPrice;
    private final float firstIngredientPrice;
    private final float secondIngredientPrice;
    private final int moveFromIndex;
    private final int moveToIndex;

    private Burger burger;
    private Bun bun;
    private Ingredient firstIngredient;
    private Ingredient secondIngredient;


    public BurgerTest(String bunName, float bunPrice, float firstIngredientPrice, float secondIngredientPrice, int moveFromIndex, int moveToIndex){
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.firstIngredientPrice = firstIngredientPrice;
        this.secondIngredientPrice = secondIngredientPrice;
        this.moveFromIndex = moveFromIndex;
        this.moveToIndex = moveToIndex;
    }

    @Parameterized.Parameters(name = "{index}: bun={0}, bunPrice={1}, first={2}, second={3}")
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"black bun", 100f, 100f, 200f, 0, 1},
                {"white bun", 200f, 50f, 150f, 1, 0}
        });
    }

    @Before
    public void setUp(){
        burger = new Burger();

        bun = Mockito.mock(Bun.class);
        firstIngredient = Mockito.mock(Ingredient.class);
        secondIngredient = Mockito.mock(Ingredient.class);

        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);

        when(firstIngredient.getName()).thenReturn("first");
        when(firstIngredient.getPrice()).thenReturn(firstIngredientPrice);
        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);

        when(secondIngredient.getName()).thenReturn("second");
        when(secondIngredient.getPrice()).thenReturn(secondIngredientPrice);
        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void installingBunViaSetBuns(){
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void addingIngredientListUsingAddIngredient(){
        burger.addIngredient(firstIngredient);
        assertEquals(1, burger.ingredients.size());
        assertSame(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removingIngredientByIndexUsingRemoveIngredient(){
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void movingIngredientToNewPositionUsingMoveIngredient(){
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(moveFromIndex, moveToIndex);

        assertEquals(2, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(firstIngredient));
        assertTrue(burger.ingredients.contains(secondIngredient));
    }

    @Test
    public void gettingTheCorrectPriceBackViaGetPrice(){
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float expectedPrice = bunPrice * 2 + firstIngredientPrice + secondIngredientPrice;

        assertEquals(expectedPrice, burger.getPrice(), 0.0001);
        verify(bun).getPrice();
        verify(firstIngredient, times(1)).getPrice();
        verify(secondIngredient, times(1)).getPrice();
    }

    @Test
    public void returningCorrectReceiptUsingGetReceipt(){
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String expectedReceipt = String.format("(==== %s ====)%n", bunName)
                + String.format("= sauce %s =%n", "first")
                + String.format("= filling %s =%n", "second")
                + String.format("(==== %s ====)%n", bunName)
                + String.format("%nPrice: %f%n", bunPrice * 2 + firstIngredientPrice + secondIngredientPrice);

        assertEquals(expectedReceipt, burger.getReceipt());
        verify(bun, times(2)).getName();
        verify(bun).getPrice();
        verify(firstIngredient).getType();
        verify(firstIngredient).getName();
        verify(secondIngredient).getType();
        verify(secondIngredient).getName();
    }

    @Test(expected = NullPointerException.class)
    public void callingBunWithoutExceptionViaGetPrice(){
        burger.getPrice();
    }

    @Test
    public void getReceiptContainsAllTheParts(){
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains(bunName));
        assertTrue(receipt.contains("sauce"));
        assertTrue(receipt.contains("first"));
        assertTrue(receipt.contains("Price:"));
    }

}
