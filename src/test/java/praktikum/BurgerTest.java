package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    @Test
    public void setBunsShouldSetBunReference() {
        Burger burger = new Burger();
        Bun bunMock = mock(Bun.class);

        burger.setBuns(bunMock);

        assertSame(bunMock, burger.bun);
    }

    @Test
    public void addIngredientShouldIncreaseIngredientsSize() {
        Burger burger = new Burger();
        Ingredient ingredientMock = mock(Ingredient.class);

        burger.addIngredient(ingredientMock);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        Burger burger = new Burger();
        Ingredient ingredientMock = mock(Ingredient.class);

        burger.addIngredient(ingredientMock);

        assertSame(ingredientMock, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldDecreaseIngredientsSize() {
        Burger burger = new Burger();
        Ingredient firstIngredientMock = mock(Ingredient.class);
        Ingredient secondIngredientMock = mock(Ingredient.class);
        burger.ingredients.add(firstIngredientMock);
        burger.ingredients.add(secondIngredientMock);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveIngredientByIndex() {
        Burger burger = new Burger();
        Ingredient removedIngredientMock = mock(Ingredient.class);
        Ingredient remainingIngredientMock = mock(Ingredient.class);
        burger.ingredients.add(removedIngredientMock);
        burger.ingredients.add(remainingIngredientMock);

        burger.removeIngredient(0);

        assertSame(remainingIngredientMock, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewIndex() {
        Burger burger = new Burger();
        Ingredient movedIngredientMock = mock(Ingredient.class);
        Ingredient middleIngredientMock = mock(Ingredient.class);
        Ingredient lastIngredientMock = mock(Ingredient.class);
        burger.ingredients.add(movedIngredientMock);
        burger.ingredients.add(middleIngredientMock);
        burger.ingredients.add(lastIngredientMock);

        burger.moveIngredient(0, 2);

        assertSame(movedIngredientMock, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientShouldShiftOtherIngredients() {
        Burger burger = new Burger();
        Ingredient movedIngredientMock = mock(Ingredient.class);
        Ingredient middleIngredientMock = mock(Ingredient.class);
        Ingredient lastIngredientMock = mock(Ingredient.class);
        burger.ingredients.add(movedIngredientMock);
        burger.ingredients.add(middleIngredientMock);
        burger.ingredients.add(lastIngredientMock);

        burger.moveIngredient(0, 2);

        assertSame(middleIngredientMock, burger.ingredients.get(0));
    }

    @Test
    public void getReceiptShouldReturnFullFormattedReceipt() {
        Burger burger = new Burger();

        Bun bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100f);
        burger.setBuns(bunMock);

        Ingredient sauceMock = mock(Ingredient.class);
        when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceMock.getName()).thenReturn("hot sauce");
        when(sauceMock.getPrice()).thenReturn(100f);
        burger.addIngredient(sauceMock);

        Ingredient fillingMock = mock(Ingredient.class);
        when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingMock.getName()).thenReturn("cutlet");
        when(fillingMock.getPrice()).thenReturn(200f);
        burger.addIngredient(fillingMock);
        
        String expectedReceipt =
                "(==== black bun ====)\r\n" +
                        "= sauce hot sauce =\r\n" +
                        "= filling cutlet =\r\n" +
                        "(==== black bun ====)\r\n" +
                        "\r\n" +
                        "Price: 500,000000\r\n";

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
